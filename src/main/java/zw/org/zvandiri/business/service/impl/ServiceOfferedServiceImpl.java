/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.ServiceOffered;
import zw.org.zvandiri.business.repo.ServiceOfferedRepo;
import zw.org.zvandiri.business.service.ServiceOfferedService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author jmuzinda
 */
@Repository("serviceOfferedService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServiceOfferedServiceImpl implements ServiceOfferedService {

    @Resource
    private ServiceOfferedRepo serviceOfferedRepo;
    @Resource
    private UserService userService;

    @Override
    public List<ServiceOffered> getAll() {
        return serviceOfferedRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public ServiceOffered get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return serviceOfferedRepo.findById(id).get();
    }

    @Override
    public void delete(ServiceOffered t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        serviceOfferedRepo.save(t);
    }

    @Override
    public List<ServiceOffered> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceOffered save(ServiceOffered t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return serviceOfferedRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return serviceOfferedRepo.save(t);
    }

    @Override
    public ServiceOffered getByName(String name) {
        return serviceOfferedRepo.findByName(name);
    }

    @Override
    public Boolean checkDuplicate(ServiceOffered current, ServiceOffered old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equalsIgnoreCase(old.getName())) {
                if (getByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }
    
}
