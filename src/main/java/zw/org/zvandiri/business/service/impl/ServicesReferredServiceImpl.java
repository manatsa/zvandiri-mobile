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
import zw.org.zvandiri.business.domain.ServicesReferred;
import zw.org.zvandiri.business.domain.util.ReferalType;
import zw.org.zvandiri.business.repo.ServicesReferredRepo;
import zw.org.zvandiri.business.service.ServicesReferredService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicesReferredServiceImpl implements ServicesReferredService {

    @Resource
    private ServicesReferredRepo servicesReferredRepo;
    @Resource
    private UserService userService;

    @Override
    public List<ServicesReferred> getAll() {
        return servicesReferredRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public ServicesReferred get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return servicesReferredRepo.findById(id).get();
    }

    @Override
    public void delete(ServicesReferred t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        servicesReferredRepo.save(t);
    }

    @Override
    public List<ServicesReferred> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public ServicesReferred save(ServicesReferred t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return servicesReferredRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return servicesReferredRepo.save(t);
    }

    @Override
    public ServicesReferred getByName(String name) {
        return servicesReferredRepo.findByName(name);
    }

    @Override
    public Boolean checkDuplicate(ServicesReferred current, ServicesReferred old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equalsIgnoreCase(old.getName())) {
                if (servicesReferredRepo.findByNameAndReferalType(current.getName(), current.getReferalType()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
        	if (servicesReferredRepo.findByNameAndReferalType(current.getName(), current.getReferalType()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ServicesReferred> getByType(ReferalType referalType) {
        return servicesReferredRepo.getByType(referalType, Boolean.TRUE);
    }
    
}
