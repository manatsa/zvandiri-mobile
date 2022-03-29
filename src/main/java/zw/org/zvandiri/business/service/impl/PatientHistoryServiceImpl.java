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
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.PatientHistory;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.repo.PatientHistoryRepo;
import zw.org.zvandiri.business.service.MortalityService;
import zw.org.zvandiri.business.service.PatientHistoryService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PatientHistoryServiceImpl implements PatientHistoryService {
    
    @Resource
    private PatientHistoryRepo patientHistoryRepo;
    @Resource
    private UserService userService;
    @Resource
    private PatientService patientService;
    @Resource
    private MortalityService mortalityService;

    @Override
    public List<PatientHistory> getAll() {
        return patientHistoryRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public PatientHistory get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return patientHistoryRepo.findById(id).get();
    }

    @Override
    public void delete(PatientHistory t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        patientHistoryRepo.delete(t);
    }

    @Override
    public List<PatientHistory> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PatientHistory save(PatientHistory t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return patientHistoryRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return patientHistoryRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(PatientHistory current, PatientHistory old) {
        throw new UnsupportedOperationException("Implement method");
    }

    @Override
    public List<PatientHistory> getByPatient(Patient patient) {
        return patientHistoryRepo.findByPatient(patient);
    }

    @Override
    @Transactional
    public Patient saveItem(PatientHistory history, Patient patient) {
        save(history);
        if((patient.getStatus().equals(PatientChangeEvent.OTHER))
        || (patient.getStatus().equals(PatientChangeEvent.OPT_OUT))
        || (patient.getStatus().equals(PatientChangeEvent.DECEASED))
        || (patient.getStatus().equals(PatientChangeEvent.GRADUATED))
        || (patient.getStatus().equals(PatientChangeEvent.LOST_TO_FOLOWUP))
        )
        {
            patient.setActive(Boolean.FALSE);
            patient.setDeleted(Boolean.TRUE);
        }
        return patientService.save(patient);
    }
    
    @Override
    @Transactional
    public void saveMortality(PatientHistory history, Patient patient, Mortality mortality) {
        saveItem(history, patient);
        mortalityService.save(mortality);
    }
    
}