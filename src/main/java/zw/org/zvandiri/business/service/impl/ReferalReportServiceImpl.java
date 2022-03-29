/*
 * Copyright 2017 Judge Muzinda.
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Referral;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.service.ReferalReportService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ReferalReportServiceImpl implements ReferalReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Referral> get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct r from Referral r");
        int position = 0;        
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("r.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and r.patient.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("r.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and r.patient.gender=:gender");
                }
            }
            if (dto.getYesNo() != null) {
                if (position == 0) {
                    builder.append(getPatientAttended(dto));
                    position++;
                } else {
                    builder.append(" and ");
                    builder.append(getPatientAttended(dto));
                }
            }
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("r.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and r.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getReferralStatus() != null) {
                if (position == 0) {
                    builder.append("r.referralStatus=:referralStatus");
                    position++;
                } else {
                    builder.append(" and r.referralStatus=:referralStatus");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("r.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and (r.dateCreated between :startDate and :endDate)");
                }
            }
            if (dto.getStatus()!= null) {
                if (position == 0) {
                    builder.append("r.patient.status=:status");
                    position++;
                } else {
                    builder.append(" and r.patient.status=:status");
                }
            }
        }
        builder.append(" order by r.referralDate DESC");
        Query query = entityManager.createQuery(builder.toString(), Referral.class);
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
        if (dto.getReferralStatus() != null) {
            query.setParameter("referralStatus", dto.getReferralStatus());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getStatus()!= null) {
            query.setParameter("status", dto.getStatus());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long getCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(r) from Referral r");
        int position = 0;        
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic.district.province=:province");
                }
            }
            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("r.patient.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and r.patient.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("r.patient.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and r.patient.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("r.patient.gender=:gender");
                    position++;
                } else {
                    builder.append(" and r.patient.gender=:gender");
                }
            }
            if (dto.getYesNo() != null) {
                if (position == 0) {
                    builder.append(getPatientAttended(dto));
                    position++;
                } else {
                    builder.append(" and ");
                    builder.append(getPatientAttended(dto));
                }
            }
            if (dto.getAgeGroup() != null) {
                if (position == 0) {
                    builder.append("r.patient.dateOfBirth between :start and :end");
                    position++;
                } else {
                    builder.append(" and r.patient.dateOfBirth between :start and :end");
                }
            }
            if (dto.getReferralStatus() != null) {
                if (position == 0) {
                    builder.append("r.referralStatus=:referralStatus");
                    position++;
                } else {
                    builder.append(" and r.referralStatus=:referralStatus");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("r.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and (r.dateCreated between :startDate and :endDate)");
                }
            }
            if (dto.getStatus()!= null) {
                if (position == 0) {
                    builder.append("r.patient.status=:status");
                    position++;
                } else {
                    builder.append(" and r.patient.status=:status");
                }
            }
        }
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
        if (dto.getReferralStatus() != null) {
            query.setParameter("referralStatus", dto.getReferralStatus());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getStatus()!= null) {
            query.setParameter("status", dto.getStatus());
        }
        return (Long) query.getSingleResult();
    }

    private String getPatientAttended(SearchDTO dto) {
        if (dto.getYesNo().equals(YesNo.YES)) {
            return "r.dateAttended is not null";
        } 
        return "r.dateAttended is null";
    }

}
