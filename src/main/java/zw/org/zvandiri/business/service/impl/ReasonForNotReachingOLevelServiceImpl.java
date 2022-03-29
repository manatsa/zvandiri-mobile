/*
 * Copyright 2017 Judge Muzinda.
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
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.ReasonForNotReachingOLevel;
import zw.org.zvandiri.business.repo.ReasonForNotReachingOLevelRepo;
import zw.org.zvandiri.business.service.ReasonForNotReachingOLevelService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository("reasonForNotReachingOLevelService")
public class ReasonForNotReachingOLevelServiceImpl implements ReasonForNotReachingOLevelService {
    
    @Resource
    private ReasonForNotReachingOLevelRepo reasonForNotReachingOLevelRepo;
    @Resource
    private UserService userService;

    @Override
    public List<ReasonForNotReachingOLevel> getAll() {
        return reasonForNotReachingOLevelRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public ReasonForNotReachingOLevel get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return reasonForNotReachingOLevelRepo.findById(id).get();
    }

    @Override
    public void delete(ReasonForNotReachingOLevel t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        reasonForNotReachingOLevelRepo.save(t);
    }

    @Override
    public List<ReasonForNotReachingOLevel> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public ReasonForNotReachingOLevel save(ReasonForNotReachingOLevel t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return reasonForNotReachingOLevelRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return reasonForNotReachingOLevelRepo.save(t);
    }

    @Override
    public ReasonForNotReachingOLevel getByName(String name) {
        return reasonForNotReachingOLevelRepo.findByName(name);
    }

    @Override
    public Boolean checkDuplicate(ReasonForNotReachingOLevel current, ReasonForNotReachingOLevel old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equalsIgnoreCase(old.getName())) {
                if (getByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }
}