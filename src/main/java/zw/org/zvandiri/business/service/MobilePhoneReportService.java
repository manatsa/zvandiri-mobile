package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.MobilePhone;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public interface MobilePhoneReportService {
    public List<MobilePhone> get(SearchDTO dto);
}
