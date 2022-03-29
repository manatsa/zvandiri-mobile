package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public interface CadreService {

    public List<Cadre> search(SearchDTO dto, String... exp);
    public List getAll();

    public Cadre get(String id);

    public void delete(Cadre cadre) ;

    public List<Cadre> getPageable();

    public Cadre save(Cadre cadre);

    public Boolean checkDuplicate(Cadre current, Cadre old);
}
