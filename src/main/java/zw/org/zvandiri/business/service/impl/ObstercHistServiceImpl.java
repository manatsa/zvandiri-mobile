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
import zw.org.zvandiri.business.domain.ObstercHist;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.repo.ObstercHistRepo;
import zw.org.zvandiri.business.service.ObstercHistService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ObstercHistServiceImpl implements ObstercHistService {

    @Resource
    private ObstercHistRepo obstercHistRepo;
    @Resource
    private UserService userService;

    @Override
    public List<ObstercHist> getAll() {
        return obstercHistRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public ObstercHist get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return obstercHistRepo.findById(id).get();
    }

    @Override
    public void delete(ObstercHist t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        obstercHistRepo.delete(t);
    }

    @Override
    public List<ObstercHist> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObstercHist save(ObstercHist t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return obstercHistRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return obstercHistRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(ObstercHist current, ObstercHist old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getPatient().equals(old.getPatient())) {
                if (getByPatient(current.getPatient()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByPatient(current.getPatient()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ObstercHist getByPatient(Patient patient) {
        return obstercHistRepo.findByPatient(patient);
    }
}
