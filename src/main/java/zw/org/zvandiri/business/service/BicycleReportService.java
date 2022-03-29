package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public interface BicycleReportService {
    public List<Bicycle> get(SearchDTO dto);
}
