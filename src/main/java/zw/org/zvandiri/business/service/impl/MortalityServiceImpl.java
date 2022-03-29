/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.repo.MortalityRepo;
import zw.org.zvandiri.business.service.MortalityService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MortalityServiceImpl implements MortalityService{
    
    @Resource
    private MortalityRepo repo;
    @Resource
    private UserService userService;
    @Resource
    private PatientService patientService;
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Mortality> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public Mortality get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(Mortality t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        repo.delete(t);
    }

    @Override
    public List<Mortality> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mortality save(Mortality t) {
        Patient patient = t.getPatient();
        patient.setStatus(PatientChangeEvent.DECEASED);
        patientService.save(patient);
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Mortality current, Mortality old) {
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
    public Mortality getByPatient(Patient patient) {
        return repo.findByPatient(patient);
    }
    
    @Override
    public List<Mortality> get(SearchDTO dto) {
        dto.setStatus(null);
        StringBuilder builder = new StringBuilder("Select Distinct m from Mortality m left join fetch m.patient p ");
        int position = 0;

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic.district.province=:province");
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
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("p.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and p.supportGroup=:supportGroup");
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
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("p.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and p.dateOfBirth between :start and :end");
                }
            }
            if (dto.getStatuses() == null || dto.getStatuses().isEmpty()) {
                if (dto.getStatus() != null) {
                    if (position == 0) {
                        builder.append("p.status=:status");
                        position++;
                    } else {
                        builder.append(" and p.status=:status");
                    }
                }
            }
            if (dto.getHei() != null) {
                if (position == 0) {
                    builder.append("p.hei=:hei");
                    position++;
                } else {
                    builder.append(" and p.hei=:hei");
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
            if (dto.getStatuses() != null && !dto.getStatuses().isEmpty()) {
                if (position == 0) {
                    builder.append("p.status in (:statuses)");
                    position++;
                } else {
                    builder.append(" and p.status in (:statuses)");
                }
            }
        }
        builder.append(" order by m.dateOfDeath DESC, p.lastName ASC, p.firstName ASC, p.middleName ASC, p.dateModified DESC, p.dateCreated DESC");
        TypedQuery<Mortality> query = entityManager.createQuery(builder.toString(), Mortality.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        if (dto.getSupportGroup() != null) {
            query.setParameter("supportGroup", dto.getSupportGroup());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getAgeGroup() != null) {
            query.setParameter("start", DateUtil.getDateFromAge(dto.getAgeGroup().getEnd()));
            query.setParameter("end", DateUtil.getEndDate(dto.getAgeGroup().getStart()));
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        if (dto.getHei() != null) {
            query.setParameter("hei", dto.getHei());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getStatuses() != null && !dto.getStatuses().isEmpty()) {
            query.setParameter("statuses", dto.getStatuses());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }
    
    @Override
    public Long count(SearchDTO dto) {
        dto.setStatus(null);
        StringBuilder builder = new StringBuilder("Select count(Distinct m) from Mortality m ");
        int position = 0;

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("m.patient.primaryClinic.district.province=:province");
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
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("m.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and m.patient.supportGroup=:supportGroup");
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
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("m.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and m.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getStatuses() == null || dto.getStatuses().isEmpty()) {
                if (dto.getStatus() != null) {
                    if (position == 0) {
                        builder.append("m.patient.status=:status");
                        position++;
                    } else {
                        builder.append(" and m.patient.status=:status");
                    }
                }
            }
            if (dto.getHei() != null) {
                if (position == 0) {
                    builder.append("m.patient.hei=:hei");
                    position++;
                } else {
                    builder.append(" and m.patient.hei=:hei");
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
            if (dto.getStatuses() != null && !dto.getStatuses().isEmpty()) {
                if (position == 0) {
                    builder.append("m.patient.status in (:statuses)");
                    position++;
                } else {
                    builder.append(" and m.patient.status in (:statuses)");
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
        if (dto.getSupportGroup() != null) {
            query.setParameter("supportGroup", dto.getSupportGroup());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getAgeGroup() != null) {
            query.setParameter("start", DateUtil.getDateFromAge(dto.getAgeGroup().getEnd()));
            query.setParameter("end", DateUtil.getEndDate(dto.getAgeGroup().getStart()));
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        if (dto.getHei() != null) {
            query.setParameter("hei", dto.getHei());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getStatuses() != null && !dto.getStatuses().isEmpty()) {
            query.setParameter("statuses", dto.getStatuses());
        }
        return query.getSingleResult();
    }
}
