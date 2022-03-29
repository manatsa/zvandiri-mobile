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
import zw.org.zvandiri.business.domain.HivCoInfection;
import zw.org.zvandiri.business.domain.HivConInfectionItem;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.repo.HivConInfectionItemRepo;
import zw.org.zvandiri.business.service.HivConInfectionItemService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class HivConInfectionItemServiceImpl implements HivConInfectionItemService {

    @Resource
    private HivConInfectionItemRepo hivConInfectionItemRepo;
    @Resource
    private UserService userService;

    @Override
    public List<HivConInfectionItem> getAll() {
        return hivConInfectionItemRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public HivConInfectionItem get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return hivConInfectionItemRepo.findById(id).get();
    }

    @Override
    public void delete(HivConInfectionItem t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        hivConInfectionItemRepo.delete(t);
    }

    @Override
    public List<HivConInfectionItem> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HivConInfectionItem save(HivConInfectionItem t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return hivConInfectionItemRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return hivConInfectionItemRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(HivConInfectionItem current, HivConInfectionItem old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!(current.getPatient().equals(old.getPatient()) && current.getHivCoInfection().equals(old.getHivCoInfection()))) {
                if (getByPatientAndHivConInfection(current.getPatient(), current.getHivCoInfection()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByPatientAndHivConInfection(current.getPatient(), current.getHivCoInfection()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HivConInfectionItem> getByPatient(Patient patient) {
        return hivConInfectionItemRepo.findByPatient(patient);
    }

    @Override
    public HivConInfectionItem getByPatientAndHivConInfection(Patient patient, HivCoInfection hivCoInfection) {
        return hivConInfectionItemRepo.findByPatientAndHivCoInfection(patient, hivCoInfection);
    }

}
