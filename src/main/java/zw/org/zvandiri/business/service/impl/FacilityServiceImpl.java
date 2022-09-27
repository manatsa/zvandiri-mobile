/*
 * Copyright 2015 Judge Muzinda.
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
import java.util.Optional;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.repo.FacilityRepo;
import zw.org.zvandiri.business.service.FacilityService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchNationalDTO;

/**
 * @author Judge Muzinda
 */
@Repository("facilityService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class FacilityServiceImpl implements FacilityService {

    @Resource
    private FacilityRepo facilityRepo;
    @Resource
    private UserService userService;

    @Override
    public List<Facility> getAll() {
        return facilityRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public Facility get(String id) {

        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        Optional<Facility> facilityOptional=facilityRepo.findById(id);
        return facilityOptional.isPresent()?facilityOptional.get():null;
    }

    @Override
    public void delete(Facility t) {

        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        facilityRepo.delete(t);
    }

    @Override
    public List<Facility> getPageable() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Facility save(Facility t) {

        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return facilityRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return facilityRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(Facility current, Facility old) {

        if(current.getId() != null){
            /**
             * @param current is in existence
             */
            if(!current.getName().equalsIgnoreCase(old.getName())){
                if(getByNameAndDistrict(current.getName(), current.getDistrict()) != null){
                    return true;
                }
            }
            
        }else if(current.getId() == null){
            /**
             * @param current is new
             */
            if(getByNameAndDistrict(current.getName(), current.getDistrict()) != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Facility> getOptByDistrict(District district) {

        return facilityRepo.getOptByDistrict(district);
    }

    @Override
    public List<Facility> getFacilitiesInDistricts(List<District> districts) {
        return facilityRepo.getFacilitiesInDistricts(districts);
    }

    @Override
    public Facility getByNameAndDistrict(String name, District district) {

        return facilityRepo.getByNameAndDistrict(name, district);
    }

    @Override
    public Integer getCount(SearchNationalDTO dto) {

        if (dto.getDistrict() != null) {
            return facilityRepo.getCountByDistrict(dto.getDistrict());
        } else if (dto.getProvince() != null) {
            return facilityRepo.getCountByProvince(dto.getProvince());
        }
        return facilityRepo.getCount();
    }

    @Override
    public Facility findByName(String name) {

        //Handling duplicate facilities...
        //TODO Remove duplicate facilities from the database
        List<Facility> facilities = facilityRepo.findAllByName(name);

        if (facilities != null && !facilities.isEmpty()) {

            return facilities.get(0);

        } else {

            return null;
        }
    }
}
