package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.repo.ProvinceRepo;
import zw.org.zvandiri.business.service.ProvinceService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository("locService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceRepo provinceRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Province> getAll() {
        return provinceRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public Province get(String id) {
        return provinceRepo.findById(id).get();
    }

    @Override
    public void delete(Province t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        provinceRepo.delete(t);
    }

    @Override
    public List<Province> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Province save(Province t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return provinceRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return provinceRepo.save(t);
    }

    @Override
    public Province getByName(String name) {
        return provinceRepo.getProvinceByName(name);
    }

    @Override
    public Boolean checkDuplicate(Province current, Province old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equalsIgnoreCase(old.getName())) {
                if (provinceRepo.getProvinceByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (provinceRepo.getProvinceByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }
}