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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.service.ContactByLevelOfCareService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContactByLevelOfCareServiceImpl implements ContactByLevelOfCareService {

    @PersistenceContext
    private EntityManager entityManager;
    
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
            if(dto.getCareLevel() != null){
                if(position == 0){
                    builder.append("c.careLevel=:careLevel");
                    position++;
                }else{
                    builder.append(" and c.careLevel=:careLevel");
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
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append("(c.contactDate between :startDate and :endDate)");
                    position++;
                } else {
                    builder.append(" and (c.contactDate between :startDate and :endDate)");
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if(dto.getCareLevel() != null){
            query.setParameter("careLevel", dto.getCareLevel());
        }
        return (Long) query.getSingleResult();
    }   
    
}