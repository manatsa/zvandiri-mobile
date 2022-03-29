package zw.org.zvandiri.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.repo.CadreRepo;
import zw.org.zvandiri.business.service.CadreService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Service
public class CadreServiceImpl implements CadreService {

    @Resource
    CadreRepo cadreRepo;

    @Resource
    UserService userService;

    @Override
    public List<Cadre> search(SearchDTO dto, String... exp) {
        if (exp == null) {
            throw new IllegalArgumentException("Provide parameter for search");
        } else if (exp.length == 1 && dto.getProvince() == null && dto.getDistrict() == null) {
            return cadreRepo.findByFirstNameOrLastName(exp[0], Boolean.TRUE);
        } else if (exp.length == 1 && dto.getProvince() != null && dto.getDistrict() == null) {
            return cadreRepo.findByFirstNameOrLastNameAndProvince(exp[0], Boolean.TRUE, dto.getProvince());
        } else if (exp.length == 1 && dto.getProvince() == null && dto.getDistrict() != null) {
            return cadreRepo.findByFirstNameOrLastNameAndDistrict(exp[0], Boolean.TRUE, dto.getDistrict());
        } else if (exp.length > 1 && dto.getProvince() != null && dto.getDistrict() == null) {
            return cadreRepo.findByFirstNameAndLastNameAndProvince(exp[0], exp[1], Boolean.TRUE, dto.getProvince());
        } else if (exp.length > 1 && dto.getProvince() == null && dto.getDistrict() != null) {
            return cadreRepo.findByFirstNameAndLastNameAndDistrict(exp[0], exp[1], Boolean.TRUE, dto.getDistrict());
        }
        return cadreRepo.findByFirstNameAndLastName(exp[0], exp[1], Boolean.TRUE);
    }

    @Override
    public List getAll() {
        return cadreRepo.findAll();
    }

    @Override
    public Cadre get(String id) {
        if(id!=null)
            return cadreRepo.findById(id).get();
        else throw new IllegalArgumentException("Id Cannot be null");
    }

    @Override
    public void delete(Cadre cadre) {

    }

    @Override
    public List<Cadre> getPageable() {
        return null;
    }

    @Override
    public Cadre save(Cadre cadre) {
                if (cadre.getId() == null || cadre.getId().isEmpty()) {
                    String id=UUIDGen.generateUUID();
                    System.err.println("***************************** Cadre ID is :"+id);
                    cadre.setId(id);
                    cadre.setCreatedBy(userService.getCurrentUser());
                    cadre.setDateCreated(new Date());
                    return cadreRepo.save(cadre);
                }
                cadre.setModifiedBy(userService.getCurrentUser());
                cadre.setDateModified(new Date());

                return cadreRepo.save(cadre);
    }

    @Override
    public Boolean checkDuplicate(Cadre current, Cadre old) {
        return null;
    }


}
