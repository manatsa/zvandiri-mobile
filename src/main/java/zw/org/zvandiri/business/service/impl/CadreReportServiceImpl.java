package zw.org.zvandiri.business.service.impl;

import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.service.CadreReportService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.Reportutil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CadreReportServiceImpl implements CadreReportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cadre> get(SearchDTO dto) {
        int position=0;
        StringBuilder builder=new StringBuilder("select distinct p from Cadre p ");
        position=Reportutil.commonCadreQuery(builder,dto, position);
        builder.append(" order by p.lastName ASC");
        TypedQuery<Cadre> query = entityManager.createQuery(builder.toString(), Cadre.class);
        Reportutil.commonQueryParams(query, dto);
        query.setFirstResult(dto.getFirstResult());
        query.setMaxResults(dto.getPageSize());
        return query.getResultList();
    }

    @Override
    public Long getCount(SearchDTO dto) {
        StringBuilder builder=new StringBuilder("select count(p) from Cadre p");
        int position = 0;
        position=Reportutil.commonCadreQuery(builder,dto, position);
        TypedQuery<Long> query = entityManager.createQuery(builder.toString(), Long.class);
        Reportutil.commonQueryParams(query, dto);

        /*if (dto.getStartDate() != null && dto.getEndDate() != null) {
            query.setParameter("startDate", dto.getStartDate());
            query.setParameter("endDate", dto.getEndDate());
        }*/
        return  query.getSingleResult();
    }

}
