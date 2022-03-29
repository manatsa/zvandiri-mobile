/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.MentalHealthScreening;
import zw.org.zvandiri.business.domain.TbIpt;
import zw.org.zvandiri.business.domain.util.IdentifiedRisk;
import zw.org.zvandiri.business.repo.MentalHealthScreeningRepo;
import zw.org.zvandiri.business.service.MentalHealthScreeningService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.Reportutil;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MentalHealthScreeningServiceImpl implements MentalHealthScreeningService {

    @Resource
    private MentalHealthScreeningRepo repo;
    @Resource
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MentalHealthScreening> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public MentalHealthScreening get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(MentalHealthScreening t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        repo.delete(t);
    }

    @Override
    public List<MentalHealthScreening> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MentalHealthScreening save(MentalHealthScreening t) {

        try{
            if (t.getId() == null) {

                Set<IdentifiedRisk> risks=t.getIdentifiedRisks();
                Set<IdentifiedRisk> newRisks=new HashSet<>();

                if(risks!=null && !risks.isEmpty()){
                    System.err.println("RISKS BEFORE : "+ Reportutil.StringsFromList(t.getIdentifiedRisks()));
                    /*Set<IdentifiedRisk> riskStream=risks.stream()
                            .map(r->(r.getCode()==5)?IdentifiedRisk.SUICIDAL_IDEATION:((r.getCode()==6)?IdentifiedRisk.PSYCHOSIS:
                                    (r.getCode()==1 || r.getCode()==2 || r.getCode()==3)?IdentifiedRisk.COMMON_MENTAL_HEALTH_PROBLEMS:r))
                            .collect(Collectors.toSet());*/
                    for(IdentifiedRisk risk:risks)
                    {
                        if(risk.getName().equalsIgnoreCase("DEPRESSION") || risk.getName().equalsIgnoreCase("ANXIETY") || risk.getName().equalsIgnoreCase("POST_TRAUMATIC_STRESS")){
                            newRisks.add(IdentifiedRisk.COMMON_MENTAL_HEALTH_PROBLEMS);
                        }
                    }

                    System.err.println("RISKS AFTER : "+ Reportutil.StringsFromList(newRisks));
                    t.setIdentifiedRisks(newRisks);
                    t.setDateCreated(new Date());
                }

                t.setDateModified(new Date());
                t.setId(UUIDGen.generateUUID());
                t.setCreatedBy(userService.getCurrentUser());

                return repo.save(t);
            }
            t.setModifiedBy(userService.getCurrentUser());
            t.setDateModified(new Date());
            return repo.save(t);
        }catch(Exception e){

        }
       return null;
    }

    @Override
    public Boolean checkDuplicate(MentalHealthScreening current, MentalHealthScreening old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getPatient().equals(old.getPatient())) {
                if (getByPatient(current.getPatient()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByPatient(current.getPatient()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MentalHealthScreening getByPatient(Patient patient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MentalHealthScreening> findByPatient(Patient patient) {
        return repo.findByPatient(patient);
    }

    @Override
    public List<MentalHealthScreening> get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct m from MentalHealthScreening m left join fetch m.patient p ");
        int position = 0;

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append(" p.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic=:primaryClinic");
                }
            }

            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("p.gender=:gender");
                    position++;
                } else {
                    builder.append(" and p.gender=:gender");
                }
            }

            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append(" m.dateCreated ");
                    builder.append(" between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and m.dateCreated");
                    builder.append(" between :startDate and :endDate)");
                }
            }

        }
        builder.append(" order by m.dateScreened DESC, p.lastName ASC, p.firstName ASC, p.middleName ASC, p.dateModified DESC, p.dateCreated DESC");

        TypedQuery<MentalHealthScreening> query = entityManager.createQuery(builder.toString(), MentalHealthScreening.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long count(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct m) from MentalHealthScreening m");
        int position = 0;

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append(" m.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and m.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("m.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and m.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("m.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and m.patient.primaryClinic=:primaryClinic");
                }
            }

            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("m.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and m.patient.gender=:gender");
                }
            }

            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append(" m.dateCreated ");
                    builder.append(" between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and m.dateCreated");
                    builder.append(" between :startDate and :endDate)");
                }
            }

        }

        TypedQuery<Long> query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }

        return query.getSingleResult();
    }

}
