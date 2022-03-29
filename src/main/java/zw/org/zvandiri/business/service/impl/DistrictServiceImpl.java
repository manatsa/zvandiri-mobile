package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.repo.DistrictRepo;
import zw.org.zvandiri.business.service.DistrictService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Edward Zengeni
 */
@Repository("districtService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictRepo districtRepo;
    @Resource
    private UserService userService;

    @Override
    @Transactional
    public District save(District t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return districtRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return districtRepo.save(t);
    }

    @Override
    public List<District> getAll() {
        return districtRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public District get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return districtRepo.findById(id).get();
    }

    @Override
    public void delete(District t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        districtRepo.delete(t);
    }

    @Override
    public List<District> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<District> getDistrictByProvince(Province province) {
        return districtRepo.getOptByProvince(province);
    }

    @Override
    public List<District> getDistrictsByProvinces(List<Province> provinces) {
        return districtRepo.getDistrictsInProvinces(provinces);
    }

    @Override
    public Boolean checkDuplicate(District current, District old) {
        if(current.getId() != null){
            /**
             * @param current is in existence
             */
            if(!current.getName().equalsIgnoreCase(old.getName())){
                if(districtRepo.getByNameAndProvince(current.getName(), current.getProvince()) != null){
                    return true;
                }
            }
            
        }else if(current.getId() == null){
            /**
             * @param current is new
             */
            if(districtRepo.getByNameAndProvince(current.getName(), current.getProvince()) != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public District getByNameAndProvince(String name, Province province) {
        return districtRepo.getByNameAndProvince(name, province);
    }
}