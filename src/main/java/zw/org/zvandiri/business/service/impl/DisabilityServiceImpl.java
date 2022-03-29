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
import zw.org.zvandiri.business.domain.DisabilityCategory;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.PatientDisability;
import zw.org.zvandiri.business.repo.DisabilityRepo;
import zw.org.zvandiri.business.service.DisabilityService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author jmuzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DisabilityServiceImpl  implements DisabilityService {
    
    @Resource
    private DisabilityRepo disabilityRepo;
    @Resource
    private UserService userService;

    @Override
    public List<PatientDisability> getAll() {
        return disabilityRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public PatientDisability get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return disabilityRepo.findById(id).get();
    }

    @Override
    public void delete(PatientDisability t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        disabilityRepo.delete(t);
    }

    @Override
    public List<PatientDisability> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientDisability save(PatientDisability t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return disabilityRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return disabilityRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(PatientDisability current, PatientDisability old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!(current.getPatient().equals(old.getPatient()) && current.getDisabilityCategory().equals(old.getDisabilityCategory()))) {
                if (getByPatientAndDisabilityCategory(current.getPatient(), current.getDisabilityCategory()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByPatientAndDisabilityCategory(current.getPatient(), current.getDisabilityCategory()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<PatientDisability> getByPatient(Patient patient) {
        return disabilityRepo.findByPatient(patient);
    }

    @Override
    public PatientDisability getByPatientAndDisabilityCategory(Patient patient, DisabilityCategory disabilityCategory) {
        return disabilityRepo.findByPatientAndDisabilityCategory(patient, disabilityCategory);
    }
    
}
