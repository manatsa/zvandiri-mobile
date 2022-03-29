package zw.org.zvandiri.business.service.impl;

import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.service.MorbidityService;
import zw.org.zvandiri.business.util.ContactInnerJoin;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class MorbidityServiceImpl implements MorbidityService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Contact> getUnwell(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct c from Contact c "+ ContactInnerJoin.CONTACT_INNER_JOIN);
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
            if (dto.getStatus()!= null) {
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
            if(dto.getCareLevel() != null){
                if(position == 0){
                    builder.append("c.careLevel=:careLevel");
                    position++;
                }else{
                    builder.append(" and c.careLevel=:careLevel");
                }
            }
            if(dto.getFollowUp() != null){
                if(position == 0){
                    builder.append("c.followUp=:followUp");
                    position++;
                }else{
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
        if(dto.getCareLevel() != null){
            query.setParameter("careLevel", dto.getCareLevel());
        }
        if(dto.getFollowUp() != null){
            query.setParameter("followUp", dto.getFollowUp());
        }
        if (dto.getStatus()!= null) {
            query.setParameter("status", dto.getStatus());
        }
        return query.getResultList();
    }
}
