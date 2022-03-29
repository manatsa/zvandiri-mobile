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

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.FollowUp;
import zw.org.zvandiri.business.domain.util.HIVStatus;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.service.PatientReportService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.PatientInnerJoin;
import zw.org.zvandiri.business.util.Reportutil;
import zw.org.zvandiri.business.util.dto.SearchDTO;
import zw.org.zvandiri.business.util.dto.SearchNationalDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PatientReportServiceImpl implements PatientReportService {

    @PersistenceContext
    private EntityManager entityManager;

    LocalDate now = LocalDate.now();
    LocalDate vlDate=now.minusMonths(12);
    LocalDate mhDate=now.minusMonths(6);
    LocalDate tbDate=now.minusMonths(1);

    @Override
    public Integer get(SearchNationalDTO dto, HIVStatus hivStatus) {
        if (dto.getFacility() != null) {
            return 0;
        } else if (dto.getDistrict() != null) {
            return 0;
        } else if (dto.getProvince() != null) {
            return 0;
        }
        return 0;
    }

    @Override
    public Long getCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p.dateJoined between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (p.dateJoined between :startDate and :endDate)");
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
        if (dto.getPeriod() != null) {
            query.setParameter("period", dto.getPeriod());
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
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getNewlyRegistered(SearchDTO dto) {
        dto.setEndDate(new Date());
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p.dateJoined between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (p.dateJoined between :startDate and :endDate)");
            }
        }

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
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getPatientWithNoContact(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p not in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
                position++;
            } else {
                builder.append(" and p not in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
            }
        }

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
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getPatientAboutToGraduate(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
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
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p.dateOfBirth between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.dateOfBirth between :startDate and :endDate");
            }
        }

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
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Patient> getPatientWithNoContactList(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p ");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p not in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
                position++;
            } else {
                builder.append(" and p not in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
            }
        }

        TypedQuery query = entityManager.createQuery(builder.toString(), Patient.class);
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
        return query.getResultList();
    }

    @Override
    public List<Patient> getPatientWithContactList(SearchDTO dto) {
        dto.setEndDate(new Date());
        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p left join fetch p.referrals ");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("p in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
                position++;
            } else {
                builder.append(" and p in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
            }
        }

        TypedQuery query = entityManager.createQuery(builder.toString(), Patient.class);
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
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return query.getResultList();
    }

    @Override
    public List<Patient> getPatientAboutToGraduateList(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p " + PatientInnerJoin.PATIENT_INNER_JOIN);
        int position = 0;
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
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p.dateOfBirth between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.dateOfBirth between :startDate and :endDate");
            }
        }

        TypedQuery query = entityManager.createQuery(builder.toString(), Patient.class);
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
        return query.getResultList();
    }

   @Override
    public Long getPatientLabResults(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.patient) from InvestigationTest v");
        int position = 0;
        builder.append(" where ");
        if (dto.getMaxViralLoad() != null) {
            if (position == 0) {
                builder.append("v.result >:result and (v.dateTaken between :start and :end)");
                position++;
            } else {
                builder.append(" and v.result >:result and (v.dateTaken between :start and :end)");
            }
        }
        if (dto.getMinCd4Count() != null) {
            if (position == 0) {
                builder.append("v.result <:result");
                position++;
            } else {
                builder.append(" and v.result <:result");
            }
        }
        if (dto.getMinViralLoad()!= null) {
            if (position == 0) {
                builder.append("v.result <:result and (v.dateTaken between :start and :end)");
                position++;
            } else {
                builder.append(" and v.result <:result and (v.dateTaken between :start and :end)");
            }
        }
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district=:district");
            }

        }
        if (dto.getPrimaryClinic() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic=:primaryClinic");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic=:primaryClinic");
            }
        }
        if (dto.getSupportGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and v.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("v.patient..gender=:gender");
                position++;
            } else {
                builder.append(" and v.patient.gender=:gender");
            }
        }
        if (dto.getAgeGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.dateOfBirth between :start and :end");
                position++;
            } else {
                builder.append(" and v.patient.dateOfBirth between :start and :end");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("v.patient.period=:period");
                position++;
            } else {
                builder.append(" and v.patient.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("v.patient.status=:status");
                position++;
            } else {
                builder.append(" and v.patient.status=:status");
            }
        }
        if (dto.getHei() != null) {
            if (position == 0) {
                builder.append("v.patient.hei=:hei");
                position++;
            } else {
                builder.append(" and v.patient.hei=:hei");
            }
        }
        if (dto.getTestType() != null) {
            if (position == 0) {
                builder.append("v.testType=:testType");
                position++;
            } else {
                builder.append(" and v.testType=:testType");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("v.dateTaken between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateTaken between :startDate and :endDate)");
            }
        }
        if (dto.getIsDueForVL() != null) {
            if (position == 0) {
                builder.append("v.nextTestDate between :vlStartDate and :vlEndDate");
                position++;
            } else {
                builder.append(" and (v.nextTestDate between :vlStartDate and :vlEndDate)");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("v.patient.status=:status");
                position++;
            } else {
                builder.append(" and v.patient.status=:status");
            }
        }
        builder.append(" order by v.dateTaken DESC");
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
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        if (dto.getHei() != null) {
            query.setParameter("hei", dto.getHei());
        }
        if (dto.getTestType() != null) {
            query.setParameter("testType", dto.getTestType());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if(dto.getIsDueForVL() != null) {
            query.setParameter("vlStartDate", new Date());
            query.setParameter("vlEndDate", DateUtil.getDateDiffDate(7));
        }
        if (dto.getMaxViralLoad() != null) {
            query.setParameter("result", dto.getMaxViralLoad());
            query.setParameter("start", DateUtil.getDateDiffMonth(new Date(), -12));
        	query.setParameter("end", new Date());
        }
        if (dto.getMinCd4Count() != null) {
            query.setParameter("result", dto.getMinCd4Count());
        }
        if (dto.getMinViralLoad()!= null) {
            query.setParameter("result", dto.getMinViralLoad());
            query.setParameter("start", DateUtil.getDateDiffMonth(new Date(), -12));
        	query.setParameter("end", new Date());
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Patient> getPatientLabResultsList(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct v.patient from InvestigationTest v");
        int position = 0;
        builder.append(" where ");
        if (dto.getMaxViralLoad() != null) {
            if (position == 0) {
                builder.append("v.result >:result");
                position++;
            } else {
                builder.append(" and v.result >:result");
            }
        }
        if (dto.getMinViralLoad()!= null) {
            if (position == 0) {
                builder.append("v.result <:result");
                position++;
            } else {
                builder.append(" and v.result <:result");
            }
        }
        if (dto.getMinCd4Count() != null) {
            if (position == 0) {
                builder.append("v.result <:result");
                position++;
            } else {
                builder.append(" and v.result <:result");
            }
        }
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district=:district");
            }

        }
        if (dto.getPrimaryClinic() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic=:primaryClinic");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic=:primaryClinic");
            }
        }
        if (dto.getSupportGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and v.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("v.patient..gender=:gender");
                position++;
            } else {
                builder.append(" and v.patient.gender=:gender");
            }
        }
        if (dto.getAgeGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.dateOfBirth between :start and :end");
                position++;
            } else {
                builder.append(" and v.patient.dateOfBirth between :start and :end");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("v.patient.period=:period");
                position++;
            } else {
                builder.append(" and v.patient.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("v.patient.status=:status");
                position++;
            } else {
                builder.append(" and v.patient.status=:status");
            }
        }
        if (dto.getHei() != null) {
            if (position == 0) {
                builder.append("v.patient.hei=:hei");
                position++;
            } else {
                builder.append(" and v.patient.hei=:hei");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("v.dateTaken between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateTaken between :startDate and :endDate)");
            }
        }
        if (dto.getIsDueForVL() != null) {
            if (position == 0) {
                builder.append("v.nextTestDate between :vlStartDate and :vlEndDate");
                position++;
            } else {
                builder.append(" and (v.nextTestDate between :vlStartDate and :vlEndDate)");
            }
        }
        if (dto.getTestType() != null) {
            if (position == 0) {
                builder.append("v.testType=:testType");
                position++;
            } else {
                builder.append(" and v.testType=:testType");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Patient.class);
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
        if(dto.getIsDueForVL() != null) {
            query.setParameter("vlStartDate", new Date());
            query.setParameter("vlEndDate", DateUtil.getDateDiffDate(7));
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
        if (dto.getMaxViralLoad() != null) {
            query.setParameter("result", dto.getMaxViralLoad());
        }
        if (dto.getMinCd4Count() != null) {
            query.setParameter("result", dto.getMinCd4Count());
        }
        if (dto.getMinViralLoad()!= null) {
            query.setParameter("result", dto.getMinViralLoad());
        }
        if (dto.getTestType() != null) {
            query.setParameter("testType", dto.getTestType());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<InvestigationTest> getPatientLabResultList(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select v from InvestigationTest v left join fetch v.patient ");
        int position = 0;
        builder.append(" where ");
        if (dto.getMaxViralLoad() != null) {
            if (position == 0) {
                builder.append("v.result >:result");
                position++;
            } else {
                builder.append(" and v.result >:result");
            }
        }

        if (dto.getMinCd4Count() != null) {
            if (position == 0) {
                builder.append("v.result <:result");
                position++;
            } else {
                builder.append(" and v.result <:result");
            }
        }
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district=:district");
            }

        }
        if (dto.getPrimaryClinic() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic=:primaryClinic");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic=:primaryClinic");
            }
        }
        if (dto.getSupportGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and v.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("v.patient..gender=:gender");
                position++;
            } else {
                builder.append(" and v.patient.gender=:gender");
            }
        }
        if (dto.getAgeGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.dateOfBirth between :start and :end");
                position++;
            } else {
                builder.append(" and v.patient.dateOfBirth between :start and :end");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("v.patient.period=:period");
                position++;
            } else {
                builder.append(" and v.patient.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("v.patient.status=:status");
                position++;
            } else {
                builder.append(" and v.patient.status=:status");
            }
        }
        if (dto.getHei() != null) {
            if (position == 0) {
                builder.append("v.patient.hei=:hei");
                position++;
            } else {
                builder.append(" and v.patient.hei=:hei");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (dto.getTestType() != null) {
            if (position == 0) {
                builder.append("v.testType=:testType");
                position++;
            } else {
                builder.append(" and v.testType=:testType");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), InvestigationTest.class);
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
        if (dto.getMaxViralLoad() != null) {
            query.setParameter("result", dto.getMaxViralLoad());
        }
        if (dto.getMinCd4Count() != null) {
            query.setParameter("result", dto.getMinCd4Count());
        }
        if (dto.getTestType() != null) {
            query.setParameter("testType", dto.getTestType());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long getPatientWithContact(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.status=:status");
                position++;
            } else {
                builder.append(" and p.status=:status");
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
                builder.append("p in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
                position++;
            } else {
                builder.append(" and p in (Select c.patient From Contact c where c.contactDate between :startDate and :endDate)");
            }
        }

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
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getPatientWithViralLoad(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.patient) from InvestigationTest v");
        int position = 0;
        builder.append(" where ");
        if (dto.getMaxViralLoad() != null) {
            if (position == 0) {
                builder.append("v.result >:result");
                position++;
            } else {
                builder.append(" and v.result >:result");
            }
        }

        if (dto.getMinCd4Count() != null) {
            if (position == 0) {
                builder.append("v.result <:result");
                position++;
            } else {
                builder.append(" and v.result <:result");
            }
        }
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic.district=:district");
            }

        }
        if (dto.getPrimaryClinic() != null) {
            if (position == 0) {
                builder.append("v.patient.primaryClinic=:primaryClinic");
                position++;
            } else {
                builder.append(" and v.patient.primaryClinic=:primaryClinic");
            }
        }
        if (dto.getSupportGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and v.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("v.patient..gender=:gender");
                position++;
            } else {
                builder.append(" and v.patient.gender=:gender");
            }
        }
        if (dto.getAgeGroup() != null) {
            if (position == 0) {
                builder.append("v.patient.dateOfBirth between :start and :end");
                position++;
            } else {
                builder.append(" and v.patient.dateOfBirth between :start and :end");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("v.patient.period=:period");
                position++;
            } else {
                builder.append(" and v.patient.period=:period");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("v.patient.status=:status");
                position++;
            } else {
                builder.append(" and v.patient.status=:status");
            }
        }
        if (dto.getHei() != null) {
            if (position == 0) {
                builder.append("v.patient.hei=:hei");
                position++;
            } else {
                builder.append(" and v.patient.hei=:hei");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("v.patient.dateJoined between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.patient.dateJoined between :startDate and :endDate)");
            }
        }
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
        if (dto.getMaxViralLoad() != null) {
            query.setParameter("result", dto.getMaxViralLoad());
        }
        if (dto.getMinCd4Count() != null) {
            query.setParameter("result", dto.getMinCd4Count());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Patient> getPatientDeceased(SearchDTO dto) {
        dto.setEndDate(new Date());
        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p ");
        int position = 0;
        builder.append(" where p.status=0 ");
        position++;
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
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append("p.period=:period");
                position++;
            } else {
                builder.append(" and p.period=:period");
            }
        }

        //builder.append(" and p.status= 0 ");
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" p.dateModified between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.dateModified between :startDate and :endDate ");
            }
        }

        TypedQuery query = entityManager.createQuery(builder.toString(), Patient.class);
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
        List<Patient> patients = query.getResultList();

        return patients;
    }

    @Override
    public List<Patient> getUncontactedClients(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p  ");
        int position = 0;
        position=Reportutil.commonPatientQuery(builder, dto, position);

        if (position == 0) {
            builder.append(" p not in (select distinct c.patient from Contact c ");
            position++;
        } else {
            builder.append(" and p not in (select distinct c.patient from Contact c ");
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            builder.append(" where c.dateCreated between :startDate and :endDate");
        }
        builder.append(" )");
        builder.append(" order by p.lastName ASC");
        TypedQuery<Patient> query = entityManager.createQuery(builder.toString(), Patient.class);
        Reportutil.commonQueryParams(query, dto);
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        query.setFirstResult(dto.getFirstResult());

        return query.getResultList();
    }

    @Override
    public List<Patient> getEnhancedClients(SearchDTO dto) {

        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p  ");
        int position = 0;
        position=Reportutil.commonPatientQuery(builder, dto, position);

            if (position == 0) {
                builder.append(" (p.enhancedStatus is null or p.enhancedStatus=1) ");
                position++;
            } else {
                builder.append(" and (p.enhancedStatus is null or p.enhancedStatus= 1) ");
            }

        builder.append(" order by p.lastName ASC");
        TypedQuery<Patient> query = entityManager.createQuery(builder.toString(), Patient.class);
        Reportutil.commonQueryParams(query, dto);
        query.setFirstResult(dto.getFirstResult());


        return query.getResultList();
    }

    @Override
    public List<Patient> getMHScreeningCandidates(SearchDTO dto) {

        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p  ");
        int position = 0;
        position=Reportutil.commonPatientQuery(builder, dto, position);

        if (position == 0) {
            builder.append(" p.id not in ( select m.patient from MentalHealthScreening m where m.dateScreened < :when ) order by p.lastName ASC");
            position++;
        } else {
            builder.append(" and p.id not in ( select m.patient from MentalHealthScreening m where m.dateScreened < :when ) order by p.lastName ASC");
        }
        TypedQuery<Patient> query = entityManager.createQuery(builder.toString(), Patient.class);
        Reportutil.commonQueryParams(query, dto);
        query.setParameter("when", mhDate.toDate());
        query.setFirstResult(dto.getFirstResult());
        return query.getResultList();
    }

    @Override
    public List<Patient> getTBScreeningCandidates(SearchDTO dto) {

        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p  ");
        int position = 0;
        position=Reportutil.commonPatientQuery(builder, dto, position);

        if (position == 0) {
            builder.append(" p not in (select distinct c.patient from TbIpt c ");
            position++;
        } else {
            builder.append(" and p not in (select distinct c.patient from TbIpt c where c.dateScreened < :when)");
        }

        builder.append(" order by p.lastName ASC");
        TypedQuery<Patient> query = entityManager.createQuery(builder.toString(), Patient.class);
        Reportutil.commonQueryParams(query, dto);
        query.setParameter("when", tbDate.toDate());
        query.setFirstResult(dto.getFirstResult());

        return query.getResultList();
    }

    @Override
    public List<Patient> getPatientsWithInvalidVL(SearchDTO dto) {

        StringBuilder builder = new StringBuilder("Select Distinct p from Patient p  ");
        int position = 0;
        position=Reportutil.commonPatientQuery(builder, dto, position);

        if (position == 0) {
            builder.append(" p.id not in (select i.patient from InvestigationTest i where i.dateTaken > :when) ");
            position++;
        } else {
            builder.append(" and p.id not in (select i.patient from InvestigationTest i where i.dateTaken > :when) ");
        }
        builder.append(" order by p.lastName ASC");
        TypedQuery<Patient> query = entityManager.createQuery(builder.toString(), Patient.class);
        Reportutil.commonQueryParams(query, dto);
        query.setParameter("when", vlDate.toDate());
        query.setFirstResult(dto.getFirstResult());

        return query.getResultList();
    }

    @Override
    public Long countUncontacted(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct p) from Patient p  ");
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

            if (position == 0) {
                builder.append("p.status="+ PatientChangeEvent.ACTIVE);
                position++;
            } else {
                builder.append(" and p.status="+ PatientChangeEvent.ACTIVE);
            }

            if (position == 0) {
                builder.append(" p.id not in (select c.patient from Contact c ");
                position++;
            } else {
                builder.append(" and p.id not in (select c.patient from Contact c ");
            }

            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                builder.append(" where c.dateCreated between :startDate and :endDate");

            }
            builder.append(" )");
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

        if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
            query.setParameter("facilities", dto.getFacilities());
        } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
            query.setParameter("districts", dto.getDistricts());
        }else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
            query.setParameter("provinces", dto.getProvinces());
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }

        return query.getSingleResult();
    }

	@Override
	public Long getCountDueForViralLoad(SearchDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getDueForViralLoadList(SearchDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
