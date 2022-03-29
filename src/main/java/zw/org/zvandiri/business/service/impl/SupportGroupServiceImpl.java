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
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.SupportGroup;
import zw.org.zvandiri.business.repo.SupportGroupRepo;
import zw.org.zvandiri.business.service.SupportGroupService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository("supportGroupService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SupportGroupServiceImpl implements SupportGroupService {
 
    @Resource
    private SupportGroupRepo supportGroupRepo;
    @Resource
    private UserService userService;

    @Override
    public List<SupportGroup> getAll() {
        return supportGroupRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public SupportGroup get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return supportGroupRepo.findById(id).get();
    }

    @Override
    public void delete(SupportGroup t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        supportGroupRepo.delete(t);
    }

    @Override
    public List<SupportGroup> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SupportGroup save(SupportGroup t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return supportGroupRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return supportGroupRepo.save(t);
    }

    @Override
    public SupportGroup getByNameAndDistrict(String name, District district) {
        return supportGroupRepo.findByNameAndDistrict(name, district);
    }

    @Override
    public List<SupportGroup> getByDistrict(District district) {
        return supportGroupRepo.findByDistrictAndActive(district, Boolean.TRUE);
    }

    @Override
    public List<SupportGroup> getByProvince(Province province) {
        return supportGroupRepo.findByProvinceAndActive(province, Boolean.TRUE);
    }

    @Override
    public Boolean checkDuplicate(SupportGroup current, SupportGroup old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (getByNameAndDistrict(current.getName(), current.getDistrict()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (getByNameAndDistrict(current.getName(), current.getDistrict()) != null) {
                return true;
            }
        }
        return false;
    }
}