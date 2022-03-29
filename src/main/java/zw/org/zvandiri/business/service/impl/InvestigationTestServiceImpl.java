/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.TestType;
import zw.org.zvandiri.business.repo.InvestigationTestRepo;
import zw.org.zvandiri.business.service.InvestigationTestService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class InvestigationTestServiceImpl implements InvestigationTestService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private InvestigationTestRepo investigationTestRepo;
    @Resource
    private UserService userService;

    @Override
    public List<InvestigationTest> getAll() {
        return investigationTestRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public InvestigationTest get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return investigationTestRepo.findById(id).get();
    }

    @Override
    public void delete(InvestigationTest t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        investigationTestRepo.delete(t);
    }

    @Override
    public List<InvestigationTest> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InvestigationTest save(InvestigationTest t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return investigationTestRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return investigationTestRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(InvestigationTest current, InvestigationTest old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InvestigationTest> getByPatientAndTestType(Patient patient, TestType testType) {
        return investigationTestRepo.findByPatientAndTestType(patient, testType);
    }

    @Override
    public List<InvestigationTest> get(SearchDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InvestigationTest getLatestTestByTestType(Patient patient, TestType testType) {
        for(InvestigationTest item : getByPatientAndTestType(patient, testType)){
            return item;
        }
        return null;
    }

    @Override
    public List<InvestigationTest> getInvalidViralLoad(SearchDTO dto) {

        LocalDate now = LocalDate.now();
        LocalDate then=now.minusMonths(12);

        StringBuilder builder = new StringBuilder("Select Distinct i from InvestigationTest i left join fetch i.patient p  ");
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


            if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
                //System.err.println("################# DTO facilities is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic in :facilities");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic in :facilities");
                }
            }else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
                //System.err.println("$$$$$$$$$$$$$$$$$$$$$$$ DTO districts is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district in :districts");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district in :districts");
                }
            }else  if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
                //System.err.println("^^^^^^^^^^^^^^^^^^^^^^^ DTO provinces is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district.province in :provinces");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district.province in :provinces");
                }
            }

            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append(" p.primaryClinic.district=:district");
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

            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append(" p.status=:status");
                    position++;
                } else {
                    builder.append(" and p.status=:status");
                }
            }



            if (position == 0) {
                builder.append(" (i.result is null or i.dateTaken < :fromWhen ) ");
                position++;
            } else {
                builder.append(" and (i.result is null or i.dateTaken < :fromWhen) ");
            }


        }

        builder.append(" order by p.lastName ASC");

        TypedQuery<InvestigationTest> query = entityManager.createQuery(builder.toString(), InvestigationTest.class);
        //System.err.println(">>>>>>>>>>>>>>>> QUERY: "+builder.toString());
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }

        if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
            query.setParameter("facilities", dto.getFacilities());
        } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
            query.setParameter("districts", dto.getDistricts());
        }else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
            query.setParameter("provinces", dto.getProvinces());
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
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }

        query.setParameter("fromWhen", then.toDate());

        query.setFirstResult(dto.getFirstResult());
        //query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long getInvalidViralLoadCount(SearchDTO dto) {

        LocalDate now = LocalDate.now();
        LocalDate then=now.minusMonths(12);

        StringBuilder builder = new StringBuilder("Select count(Distinct i) from InvestigationTest i left join  i.patient p  ");
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

            if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
                //System.err.println("################# DTO facilities is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic in :facilities");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic in :facilities");
                }
            }else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
                //System.err.println("$$$$$$$$$$$$$$$$$$$$$$$ DTO districts is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district in :districts");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district in :districts");
                }
            }else  if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
                //System.err.println("^^^^^^^^^^^^^^^^^^^^^^^ DTO provinces is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district.province in :provinces");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district.province in :provinces");
                }
            }


            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append(" p.primaryClinic.district=:district");
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

            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append(" p.status=:status");
                    position++;
                } else {
                    builder.append(" and p.status=:status");
                }
            }



            if (position == 0) {
                builder.append(" (i.result is null or i.dateTaken < :fromWhen ) ");
                position++;
            } else {
                builder.append(" and (i.result is null or i.dateTaken < :fromWhen) ");
            }


            //builder.append(" )");
        }

        builder.append(" order by p.lastName ASC");

        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }

        if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
            query.setParameter("facilities", dto.getFacilities());
        } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
            query.setParameter("districts", dto.getDistricts());
        }else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
            query.setParameter("provinces", dto.getProvinces());
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
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }

        query.setParameter("fromWhen", then.toDate());


        //query.setFirstResult(dto.getFirstResult());
        //query.setMaxResults(dto.getPageSize());
        return (Long)query.getSingleResult();
    }
    
}
