/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.SubstanceItem;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Substance;
import zw.org.zvandiri.business.repo.SubstanceItemRepo;
import zw.org.zvandiri.business.service.SubstanceItemService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SubstanceItemServiceImpl implements SubstanceItemService {

    @Resource
    private SubstanceItemRepo substanceItemRepo;
    @Resource
    private UserService userService;

    @Override
    public List<SubstanceItem> getAll() {
        return substanceItemRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public SubstanceItem get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return substanceItemRepo.findById(id).get();
    }

    @Override
    public void delete(SubstanceItem t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        substanceItemRepo.delete(t);
    }

    @Override
    public List<SubstanceItem> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubstanceItem save(SubstanceItem t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return substanceItemRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return substanceItemRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(SubstanceItem current, SubstanceItem old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!(current.getPatient().equals(old.getPatient()) && current.getSubstance().equals(old.getSubstance()))) {
                if (getByPatientAndSubstance(current.getPatient(), current.getSubstance()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByPatientAndSubstance(current.getPatient(), current.getSubstance()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SubstanceItem> getByPatient(Patient patient) {
        return substanceItemRepo.findByPatient(patient);
    }

    @Override
    public SubstanceItem getByPatientAndSubstance(Patient patient, Substance substance) {
        return substanceItemRepo.findByPatientAndSubstance(patient, substance);
    }

}
