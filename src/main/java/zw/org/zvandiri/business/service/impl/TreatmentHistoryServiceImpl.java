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
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Period;
import zw.org.zvandiri.business.domain.TreatmentHistory;
import zw.org.zvandiri.business.repo.TreatmentHistoryRepo;
import zw.org.zvandiri.business.service.TreatmentHistoryService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TreatmentHistoryServiceImpl implements TreatmentHistoryService {

    @Resource
    private TreatmentHistoryRepo treatmentHistoryRepo;
    @Resource
    private UserService userService;

    @Override
    public List<TreatmentHistory> getAll() {
        return treatmentHistoryRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public TreatmentHistory get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return treatmentHistoryRepo.findById(id).get();
    }

    @Override
    public void delete(TreatmentHistory t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        treatmentHistoryRepo.save(t);
    }

    @Override
    public List<TreatmentHistory> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TreatmentHistory save(TreatmentHistory t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return treatmentHistoryRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return treatmentHistoryRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(TreatmentHistory current, TreatmentHistory old) {
        throw new UnsupportedOperationException("Implement method");
    }

    @Override
    public TreatmentHistory getByPatientAndPeriod(Patient patient, Period period) {
        return treatmentHistoryRepo.findByPatientAndPeriod(patient, period);
    }

    @Override
    public List<TreatmentHistory> getByPatient(Patient patient) {
        return treatmentHistoryRepo.findByPatient(patient, Boolean.TRUE);
    }

}