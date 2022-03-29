package zw.org.zvandiri.business.service.impl;

import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.domain.MobilePhone;
import zw.org.zvandiri.business.service.BicycleReportService;
import zw.org.zvandiri.business.service.MobilePhoneReportService;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Service
public class BicycleReportServiceImpl implements BicycleReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bicycle> get(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct m from Bicycle m left join fetch m.cadre p ");
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

            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                if (position == 0) {
                    builder.append(" m.dateCreated ");
                    builder.append(" between :startDate and :endDate");
                    position++;
                } else {
                    builder.append(" and m.dateCreated");
                    builder.append(" between :startDate and :endDate)");
                }
            }

        }
        builder.append(" order by m.dateCreated DESC, p.lastName ASC, p.firstName ASC, p.middleName ASC");

        TypedQuery<Bicycle> query = entityManager.createQuery(builder.toString(), Bicycle.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
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

        return query.getResultList();
    }
}
