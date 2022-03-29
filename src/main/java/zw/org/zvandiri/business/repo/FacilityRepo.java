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
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Province;

/**
 *
 * @author Judge Muzinda
 */
public interface FacilityRepo extends JpaRepository<Facility, String> {

    @Query("from Facility f left join fetch f.district left join fetch f.createdBy left join fetch f.modifiedBy where f.active=:active Order By f.name ASC")
    public List<Facility> getOptAll(@Param("active") Boolean active);

    @Query("from Facility f left join fetch f.district where f.district=:district Order By f.name ASC")
    public List<Facility> getOptByDistrict(@Param("district") District district);

    @Query("from Facility f left join fetch f.district where f.district in :districts Order By f.name ASC")
    public List<Facility> getFacilitiesInDistricts(@Param("districts") List<District> districts);

    @Query("from Facility f left join fetch f.district where f.name=:name and f.district=:district")
    public Facility getByNameAndDistrict(@Param("name") String name, @Param("district") District district);
    
    @Query("select count(f) from Facility f")
    public Integer getCount();
    
    @Query("select count(f) from Facility f where f.district.province=:province")
    public Integer getCountByProvince(@Param("province") Province province);
    
    @Query("select count(f) from Facility f where f.district=:district")
    public Integer getCountByDistrict(@Param("district") District district);

    @Query("select f from Facility f where f.name = :facilityName")
    public List<Facility> findAllByName(@Param("facilityName") String facilityName);
}
