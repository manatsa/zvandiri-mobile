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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Referral;
import zw.org.zvandiri.business.repo.ReferralRepo;
import zw.org.zvandiri.business.service.ReferralService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ReferralServiceImpl implements ReferralService {
    
    @Resource
    private ReferralRepo referralRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Referral> getAll() {
        return referralRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public Referral get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return referralRepo.findById(id).get();
    }

    @Override
    public void delete(Referral t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        referralRepo.delete(t);
    }

    @Override
    public List<Referral> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Referral save(Referral t) {
        if (t.getId() == null || StringUtils.isBlank(t.getId())) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return referralRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return referralRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Referral current, Referral old) {
        throw new UnsupportedOperationException("Implement method");
    }

    @Override
    public List<Referral> getByPatient(Patient patient) {
        return referralRepo.findByPatient(patient);
    }
}