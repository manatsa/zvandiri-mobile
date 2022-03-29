package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import java.util.List;

public interface MorbidityService {

    public List<Contact> getUnwell(SearchDTO dto);

}
