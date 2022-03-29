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

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.service.ContactReportService;
import zw.org.zvandiri.business.util.ContactInnerJoin;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContactReportServiceImpl implements ContactReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Patient> getUnique(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("select distinct p from Contact c left join  c.patient as p "
                + "");
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

            if (dto.getStartDate() != null && dto.getEndDate() != null) {

                if (position == 0) {
                    builder.append(" (c.dateCreated between :startDate and :endDate)");
                    position++;
                } else {
                    builder.append(" and (c.dateCreated between :startDate and :endDate)");
                }

            }

        }

        builder.append(" )");
        builder.append(" Order By p.first_name, p.last_name DESC");
        Query query = entityManager.createQuery(builder.toString(), Patient.class);

        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        List<Patient> patients = query.getResultList();
        return patients;
    }
    
    @Override
    public Long countUnique(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("select count(distinct c) from Contact c"
                + "");
        int position = 0;

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic=:primaryClinic");
                }
            }

            if (dto.getStartDate() != null && dto.getEndDate() != null) {

                if (position == 0) {
                    builder.append(" (c.contactDate between :startDate and :endDate)");
                    position++;
                } else {
                    builder.append(" and (c.contactDate between :startDate and :endDate)");
                }

            }

        }

        builder.append(" )");
        Query query = entityManager.createQuery(builder.toString(), Long.class);

        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Contact> get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct c from Contact c ");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and c.patient.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("c.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and c.patient.gender=:gender");
                }
            }
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and c.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append("c.patient.status=:status");
                    position++;
                } else {
                    builder.append(" and c.patient.status=:status");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("c.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and (c.dateCreated between :startDate and :endDate)");
                }
            }
            if (dto.getCareLevel() != null) {
                if (position == 0) {
                    builder.append("c.careLevel=:careLevel");
                    position++;
                } else {
                    builder.append(" and c.careLevel=:careLevel");
                }
            }
            if (dto.getFollowUp() != null) {
                if (position == 0) {
                    builder.append("c.followUp=:followUp");
                    position++;
                } else {
                    builder.append(" and c.followUp=:followUp");
                }
            }
        }
        builder.append(" Order By c.contactDate DESC");
        Query query = entityManager.createQuery(builder.toString(), Contact.class);
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getCareLevel() != null) {
            query.setParameter("careLevel", dto.getCareLevel());
        }
        if (dto.getFollowUp() != null) {
            query.setParameter("followUp", dto.getFollowUp());
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<Contact> getContactListByPatient(Patient patient, SearchDTO dto) {

        StringBuilder builder = new StringBuilder("Select Distinct c from Contact c " + ContactInnerJoin.CONTACT_INNER_JOIN);
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and c.patient.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("c.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and c.patient.gender=:gender");
                }
            }
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and c.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append("c.patient.status=:status");
                    position++;
                } else {
                    builder.append(" and c.patient.status=:status");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("c.contactDate between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and (c.contactDate between :startDate and :endDate)");
                }
            }
            if (dto.getCareLevel() != null) {
                if (position == 0) {
                    builder.append("c.careLevel=:careLevel");
                    position++;
                } else {
                    builder.append(" and c.careLevel=:careLevel");
                }
            }
            if (dto.getFollowUp() != null) {
                if (position == 0) {
                    builder.append("c.followUp=:followUp");
                    position++;
                } else {
                    builder.append(" and c.followUp=:followUp");
                }
            }
        }
        builder.append(" Order By c.contactDate DESC");
        Query query = entityManager.createQuery(builder.toString(), Contact.class);
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getCareLevel() != null) {
            query.setParameter("careLevel", dto.getCareLevel());
        }
        if (dto.getFollowUp() != null) {
            query.setParameter("followUp", dto.getFollowUp());
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        return query.getResultList();

    }

    @Override
    public Long getCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(c) from Contact c");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("c.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and c.patient.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and c.patient.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("c.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and c.patient.gender=:gender");
                }
            }
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("c.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and c.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getPeriod() != null) {
                if (position == 0) {
                    builder.append("c.period=:period");
                    position++;
                } else {
                    builder.append(" and c.period=:period");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("c.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and (c.dateCreated between :startDate and :endDate)");
                }
            }
            if (dto.getCareLevel() != null) {
                if (position == 0) {
                    builder.append("c.careLevel=:careLevel");
                    position++;
                } else {
                    builder.append(" and c.careLevel=:careLevel");
                }
            }
            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append("c.patient.status=:status");
                    position++;
                } else {
                    builder.append(" and c.patient.status=:status");
                }
            }

            if (dto.getYesNo() != null) {
                if (position == 0) {
                    builder.append("c.attendedClinicAppointment=:attendedClinicAppointment");
                    position++;
                } else {
                    builder.append(" and c.attendedClinicAppointment=:attendedClinicAppointment");
                }
            }

        }
        //TypedQuery
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
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
        if (dto.getPeriod() != null) {
            query.setParameter("period", dto.getPeriod());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getCareLevel() != null) {
            query.setParameter("careLevel", dto.getCareLevel());
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        if (dto.getYesNo() != null) {
            query.setParameter("attendedClinicAppointment", dto.getYesNo());
        }
        return (Long) query.getSingleResult();
    }

}
