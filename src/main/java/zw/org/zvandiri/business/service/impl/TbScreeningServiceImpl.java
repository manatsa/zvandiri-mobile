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
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.TbScreening;
import zw.org.zvandiri.business.repo.TbScreeningRepo;
import zw.org.zvandiri.business.service.TbScreeningService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author tasu
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TbScreeningServiceImpl implements TbScreeningService{
    
    @Resource
    private TbScreeningRepo repo;
    @Resource
    private UserService userService;
    
    @Override
    @Transactional
    public TbScreening save(TbScreening t) {
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
    public List<TbScreening> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public TbScreening get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(TbScreening t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        save(t);
    }

    @Override
    public List<TbScreening> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkDuplicate(TbScreening current, TbScreening old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TbScreening> getByPatient(Patient patient){
        return repo.findByPatientOrderByDateCreatedDesc(patient);
    }

    @Override
    public TbScreening getLatest(Patient patient) {
        
        for (TbScreening tbScreening : getByPatient(patient)) {
            return tbScreening;
        }
        return null;
    }
    
}
