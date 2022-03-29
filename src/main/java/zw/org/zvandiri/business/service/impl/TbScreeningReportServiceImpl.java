/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.service.TbScreeningReportService;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TbScreeningReportServiceImpl implements TbScreeningReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(t) from TbScreening t");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where t.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and t.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where t.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and t.patient.primaryClinic.district=:district");
            }
        }
        if (dto.getYesNo() != null) {
            if (position == 0) {
                builder.append(" where t.currentlyOnTreatment=:currentlyOnTreatment");
                position++;
            } else {
                builder.append(" and t.currentlyOnTreatment=:currentlyOnTreatment");
            }
        }
        if (dto.getTbTreatmentStatus() != null) {
            if (position == 0) {
                builder.append(" where t.tbTreatmentStatus=:tbTreatmentStatus");
                position++;
            } else {
                builder.append(" and t.tbTreatmentStatus=:tbTreatmentStatus");
            }
        }
        if (dto.getTbTreatmentOutcome() != null) {
            if (position == 0) {
                builder.append(" where t.tbTreatmentOutcome=:tbTreatmentOutcome");
                position++;
            } else {
                builder.append(" and t.tbTreatmentOutcome=:tbTreatmentOutcome");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append(" where t.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and t.dateCreated between :startDate and :endDate");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where t.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and t.dateCreated between :startDate and :endDate");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getYesNo() != null) {
            query.setParameter("currentlyOnTreatment", dto.getYesNo());
        }
        if (dto.getTbTreatmentStatus() != null) {
            query.setParameter("tbTreatmentStatus", dto.getTbTreatmentStatus());
        }
        if (dto.getTbTreatmentOutcome() != null) {
            query.setParameter("tbTreatmentOutcome", dto.getTbTreatmentOutcome());
        }
        if (dto.getPeriod() != null) {
            query.setParameter("startDate", dto.getPeriod().getStartDate());
            query.setParameter("endDate", dto.getPeriod().getEndDate());
        }
        if(dto.getStartDate() != null && dto.getEndDate() != null){
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getIndividualsOnTbTreatment(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(t) from TbScreening t");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where t.patient.primaryClinic.district.province=:province");
                position++;
            } else {
                builder.append(" and t.patient.primaryClinic.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where t.patient.primaryClinic.district=:district");
                position++;
            } else {
                builder.append(" and t.patient.primaryClinic.district=:district");
            }
        }
        if (dto.getPeriod() != null) {
            if (position == 0) {
                builder.append(" where t.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and t.dateCreated between :startDate and :endDate");
            }
        }
        if (dto.getYesNo() != null) {
            if (position == 0) {
                builder.append(" where t.currentlyOnTreatment=:currentlyOnTreatment");
                position++;
            } else {
                builder.append(" and t.currentlyOnTreatment=:currentlyOnTreatment");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPeriod() != null) {
            query.setParameter("startDate", dto.getPeriod().getStartDate());
            query.setParameter("endDate", dto.getPeriod().getEndDate());
        }
        query.setParameter("currentlyOnTreatment", dto.getYesNo());
        return (Long) query.getSingleResult();
    }
}
