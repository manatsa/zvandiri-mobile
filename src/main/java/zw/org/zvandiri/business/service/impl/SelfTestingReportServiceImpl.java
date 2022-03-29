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
import zw.org.zvandiri.business.service.SelfTestingReportService;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SelfTestingReportServiceImpl implements SelfTestingReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countByArtInitiation(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.person.dateCreated between :startDate and :endDate)");
            }
        }
        if (dto.getYesNo() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.artInitiation=:artInitiation");
                position++;
            } else {
                builder.append(" and v.artInitiation=:artInitiation");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getYesNo() != null) {
            query.setParameter("artInitiation", dto.getYesNo());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countByHIvSelfTesting(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (position == 0) {
            builder.append(" where ");
            builder.append("v.hisSelfTestingResult is not null");
            position++;
        } else {
            builder.append(" and v.hisSelfTestingResult is not null");
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countHomeBased(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (position == 0) {
            builder.append(" where ");
            builder.append("v.homeBasedTestingResult is not null");
            position++;
        } else {
            builder.append(" and v.homeBasedTestingResult is not null");
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countFacilityBased(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (position == 0) {
            builder.append(" where ");
            builder.append("v.testedAtHealthFacilityResult is not null");
            position++;
        } else {
            builder.append(" and v.testedAtHealthFacilityResult is not null");
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countIndividualsMobilizedForTesting(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(v) from Person v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.district.province=:province");
                position++;
            } else {
                builder.append(" and v.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.district=:district");
                position++;
            } else {
                builder.append(" and v.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.gender=:gender");
                position++;
            } else {
                builder.append(" and v.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countIndividualsTestingPostive(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (dto.getResult() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("(v.testedAtHealthFacilityResult=:result");
                builder.append(" OR v.hisSelfTestingResult=:result");
                builder.append(" OR v.homeBasedTestingResult=:result");
                builder.append(" OR v.confirmatoryTestingResult=:result)");
                position++;
            } else {
                builder.append(" and (v.testedAtHealthFacilityResult=:result");
                builder.append(" OR v.hisSelfTestingResult=:result");
                builder.append(" OR v.homeBasedTestingResult=:result");
                builder.append(" OR v.confirmatoryTestingResult=:result)");
            }
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        if (dto.getResult() != null) {
            query.setParameter("result", dto.getResult());
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countIndividualsTestingForHIV(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select count(Distinct v.person) from HIVSelfTesting v");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district.province=:province");
                position++;
            } else {
                builder.append(" and v.person.district.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.district=:district");
                position++;
            } else {
                builder.append(" and v.person.district=:district");
            }

        }
        if (dto.getGender() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.person.gender=:gender");
                position++;
            } else {
                builder.append(" and v.person.gender=:gender");
            }
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            if (position == 0) {
                builder.append(" where ");
                builder.append("v.dateCreated between :startDate and :endDate");
                position++;
            } else {
                builder.append(" and (v.dateCreated between :startDate and :endDate)");
            }
        }
        if (position == 0) {
            builder.append(" where ");
            builder.append("(v.testedAtHealthFacilityResult is not null");
            builder.append(" OR v.hisSelfTestingResult is not null");
            builder.append(" OR v.homeBasedTestingResult is not null");
            builder.append(" OR v.confirmatoryTestingResult is not null)");
            position++;
        } else {
            builder.append(" and (v.testedAtHealthFacilityResult is not null");
            builder.append(" OR v.hisSelfTestingResult is not null");
            builder.append(" OR v.homeBasedTestingResult is not null");
            builder.append(" OR v.confirmatoryTestingResult is not null)");
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }
        return (Long) query.getSingleResult();
    }

}
