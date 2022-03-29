package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.persistence.TypedQuery;
import java.util.List;

public interface CadreReportService {

    public List<Cadre> get(SearchDTO dto);

    public Long getCount(SearchDTO dto);

}
