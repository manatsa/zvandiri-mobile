/*
 * Copyright 2017 jackie muzinda.
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.util.dto.SearchDTO;
import zw.org.zvandiri.business.service.DataEntryService;

/**
 *
 * @author jackie muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DataEntryServiceImpl implements DataEntryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getPatientCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from Patient p");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getCreatedBy() != null) {
                if (position == 0) {
                    builder.append("p.createdBy=:createdBy");
                    position++;
                } else {
                    builder.append(" and p.createdBy=:createdBy");
                }
            }
            if (dto.getUserType() != null) {
                if (position == 0) {
                    builder.append("p.createdBy.userType=:userType");
                    position++;
                } else {
                    builder.append(" and p.createdBy.userType=:userType");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("p.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and p.dateCreated between :startDate and :endDate");
                }
            }
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
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getCreatedBy() != null) {
            query.setParameter("createdBy", dto.getCreatedBy());
        }
        if (dto.getUserType() != null) {
            query.setParameter("userType", dto.getUserType());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getContactCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(c) from Contact c");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getCreatedBy() != null) {
                if (position == 0) {
                    builder.append("c.createdBy=:createdBy");
                    position++;
                } else {
                    builder.append(" and c.createdBy=:createdBy");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("c.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and c.dateCreated between :startDate and :endDate");
                }
            }
            if (dto.getUserType() != null) {
                if (position == 0) {
                    builder.append("c.createdBy.userType=:userType");
                    position++;
                } else {
                    builder.append(" and c.createdBy.userType=:userType");
                }
            }
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
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getCreatedBy() != null) {
            query.setParameter("createdBy", dto.getCreatedBy());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getUserType() != null) {
            query.setParameter("userType", dto.getUserType());
        }
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getReferralCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(r) from Referral r");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getCreatedBy() != null) {
                if (position == 0) {
                    builder.append("r.createdBy=:createdBy");
                    position++;
                } else {
                    builder.append(" and r.createdBy=:createdBy");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("r.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and r.dateCreated between :startDate and :endDate");
                }
            }
            if (dto.getUserType() != null) {
                if (position == 0) {
                    builder.append("r.createdBy.userType=:userType");
                    position++;
                } else {
                    builder.append(" and r.createdBy.userType=:userType");
                }
            }
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
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getCreatedBy() != null) {
            query.setParameter("createdBy", dto.getCreatedBy());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getUserType() != null) {
            query.setParameter("userType", dto.getUserType());
        }
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getViralLoadCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(v) from InvestigationTest v");
        int position = 0;
        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getCreatedBy() != null) {
                if (position == 0) {
                    builder.append("v.createdBy=:createdBy");
                    position++;
                } else {
                    builder.append(" and v.createdBy=:createdBy");
                }
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("v.dateCreated between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and v.dateCreated between :startDate and :endDate");
                }
            }
            if (dto.getUserType() != null) {
                if (position == 0) {
                    builder.append("v.createdBy.userType=:userType");
                    position++;
                } else {
                    builder.append(" and v.createdBy.userType=:userType");
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
            if (dto.getTestType() != null) {
                if (position == 0) {
                    builder.append("v.testType=:testType");
                    position++;
                } else {
                    builder.append(" and v.testType=:testType");
                }
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getCreatedBy() != null) {
            query.setParameter("createdBy", dto.getCreatedBy());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getUserType() != null) {
            query.setParameter("userType", dto.getUserType());
        }
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        if (dto.getTestType() != null) {
            query.setParameter("testType", dto.getTestType());
        }
        return (Long) query.getSingleResult();
    }

}
