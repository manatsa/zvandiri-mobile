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
import zw.org.zvandiri.business.domain.HIVSelfTesting;
import zw.org.zvandiri.business.domain.Person;
import zw.org.zvandiri.business.repo.HIVSelfTestingRepo;
import zw.org.zvandiri.business.service.HIVSelfTestingService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class HIVSelfTestingServiceImpl implements HIVSelfTestingService{
    
    @Resource
    private HIVSelfTestingRepo repo;
    @Resource
    private UserService userService;
    
    @Override
    @Transactional
    public HIVSelfTesting save(HIVSelfTesting t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public List<HIVSelfTesting> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public HIVSelfTesting get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(HIVSelfTesting t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        save(t);
    }

    @Override
    public List<HIVSelfTesting> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkDuplicate(HIVSelfTesting current, HIVSelfTesting old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HIVSelfTesting> getByPerson(Person person) {
        return repo.findByPerson(person);
    }
    
}
