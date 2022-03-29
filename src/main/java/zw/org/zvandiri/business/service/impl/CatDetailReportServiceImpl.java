/*
 * Copyright 2018 jmuzinda.
 /*
 * Copyright 2018 tasu.
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
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.ArvHist;
import zw.org.zvandiri.business.domain.CatActivity;
import zw.org.zvandiri.business.domain.CatDetail;
import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.SrhHist;
import zw.org.zvandiri.business.domain.TbIpt;
import zw.org.zvandiri.business.domain.util.TestType;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.service.ArvHistService;
import zw.org.zvandiri.business.service.CatActivityService;
import zw.org.zvandiri.business.service.CatDetailReportService;
import zw.org.zvandiri.business.service.ContactService;
import zw.org.zvandiri.business.service.InvestigationTestService;
import zw.org.zvandiri.business.service.SrhHistService;
import zw.org.zvandiri.business.service.TbIptService;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CatDetailReportServiceImpl implements CatDetailReportService {

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private InvestigationTestService investigationTestService;
    @Resource
    private ArvHistService arvHistService;
    @Resource
    private SrhHistService srhHistService;
    @Resource
    private TbIptService tbIptService;
    @Resource
    private CatActivityService catActivityService;
    @Resource
    private ContactService contactService;

    @Override
    public List<CatDetail> get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct p from CatDetail p");
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
                builder.append("p.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and p.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("p.patient.gender=:gender");
                position++;
            } else {
                builder.append(" and p.patient.gender=:gender");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.patient.status=:status");
                position++;
            } else {
                builder.append(" and p.patient.status=:status");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("p.dateAsCat between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.dateAsCat between :startDate and :endDate");
            }
        }

        TypedQuery query = entityManager.createQuery(builder.toString(), CatDetail.class);
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        List<CatDetail> list = query.getResultList();
        for (CatDetail catDetail : list) {
            InvestigationTest test = investigationTestService.getLatestTestByTestType(catDetail.getPatient(), TestType.VIRAL_LOAD);
            if (test != null) {
                if (test.getResultTaken() == null) {
                    test.setResultTaken(YesNo.YES);
                }
                catDetail.setVlDate(test.getDateTaken());
                catDetail.setVlResultTaken(test.getResultTaken());
            }
            ArvHist hist = arvHistService.getLatest(catDetail.getPatient());
            if (hist != null) {
                catDetail.setRegimenDate(hist.getStartDate());
            }
            SrhHist srhHist = srhHistService.getByPatient(catDetail.getPatient());
            if (srhHist != null) {
                catDetail.setSexuallyActive(srhHist.getSexuallyActive());
            }
            TbIpt tbIpt = tbIptService.getLatest(catDetail.getPatient());
            if (tbIpt != null) {
                catDetail.setTbScreening(tbIpt.getScreenedForTb());
                catDetail.setTbScreeningDate(tbIpt.getDateScreened());
                catDetail.setOutcome(tbIpt.getTbIdentificationOutcome()!= null ? tbIpt.getTbIdentificationOutcome().getName() : "");
                catDetail.setReceivedTreatment(tbIpt.getDateStartedTreatment() != null ? "Yes" : "No");
                //catDetail.setTreatmentOutcome(tbIpt.getTbTreatmentOutcome() != null ? tbIpt.getTbTreatmentOutcome().getName() : "");
            }
            catDetail.setHaveChildren(catDetail.getPatient().getMotherOfHei() != null ? "Yes" : "No");
            
            Contact contact = contactService.findLatestContact(catDetail.getPatient());
            if (contact != null) {
                catDetail.setCurrentStatus(contact.getCareLevelAfterAssessment() != null ? contact.getCareLevelAfterAssessment().getName() : "");
            }
            CatActivity catActivity = catActivityService.getLatest(catDetail.getId());
            if (catActivity != null) {
                catDetail.setBicycle(catActivity.getIssuedBicycle() != null ? catActivity.getIssuedBicycle().getName() : "");
                catDetail.setPhone(catActivity.getAssignedPhone() != null ? catActivity.getAssignedPhone().getName() : "");
                catDetail.setPhoneMode(catActivity.getPhoneModel());
            }
            
        }

        return list;
    }

    @Override
    public Long getCount(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from CatDetail p");
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
                builder.append("p.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and p.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("p.patient.gender=:gender");
                position++;
            } else {
                builder.append(" and p.patient.gender=:gender");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.patient.status=:status");
                position++;
            } else {
                builder.append(" and p.patient.status=:status");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("p.dateAsCat between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.dateAsCat between :startDate and :endDate");
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }
    
    

    @Override
    public Long getPatientAboutToGraduate(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(p) from CatDetail p");
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
                builder.append("p.patient.supportGroup=:supportGroup");
                position++;
            } else {
                builder.append(" and p.patient.supportGroup=:supportGroup");
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append("p.patient.gender=:gender");
                position++;
            } else {
                builder.append(" and p.patient.gender=:gender");
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                builder.append("p.patient.status=:status");
                position++;
            } else {
                builder.append(" and p.patient.status=:status");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append("p.patient.dateOfBirth between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and p.patient.dateOfBirth between :startDate and :endDate");
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
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }
}
