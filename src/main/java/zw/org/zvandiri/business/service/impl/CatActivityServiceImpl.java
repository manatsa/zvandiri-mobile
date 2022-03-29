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
import zw.org.zvandiri.business.domain.CatActivity;
import zw.org.zvandiri.business.repo.CatActivityRepo;
import zw.org.zvandiri.business.service.CatActivityService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author jmuzinda
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class CatActivityServiceImpl implements CatActivityService {
    
    @Resource
    private CatActivityRepo catActivityRepo;
    @Resource
    private UserService userService;

    @Override
    public List<CatActivity> getAll() {
        return catActivityRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public CatActivity get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return catActivityRepo.findById(id).get();
    }

    @Override
    public void delete(CatActivity t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        catActivityRepo.delete(t);
    }

    @Override
    public List<CatActivity> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public CatActivity save(CatActivity t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return catActivityRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return catActivityRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(CatActivity current, CatActivity old) {
        throw new UnsupportedOperationException("Method not yet added");
    }

    @Override
    public List<CatActivity> getByCat(String catId) {
        return catActivityRepo.findByCatDetailIdOrderByDateIssuedDesc(catId);
    }

    @Override
    public CatActivity getLatest(String catId) {
        for (CatActivity item : getByCat(catId)) {
            return item;
        }
        return null;
    }

}
