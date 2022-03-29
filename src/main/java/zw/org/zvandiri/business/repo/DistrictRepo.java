/*
 * Copyright 2014 Judge Muzinda.
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
import zw.org.zvandiri.business.domain.Province;

/**
 *
 * @author Judge Muzinda
 */
public interface DistrictRepo extends JpaRepository<District, String> {
    
    public List<District> findByProvince(Province province);
    
    @Query("from District d left join fetch d.province left join fetch d.createdBy left join fetch d.modifiedBy where d.active=:active Order By d.name ASC")
    public List<District> getOptAll(@Param("active") Boolean active);
    
    @Query("from District d left join fetch d.province where d.province=:province Order By d.name ASC")
    public List<District> getOptByProvince(@Param("province") Province province);

    @Query("from District d left join fetch d.province where d.province in :provinces Order By d.name ASC")
    public List<District> getDistrictsInProvinces(@Param("provinces") List<Province> provinces);
    
    @Query("from District d left join fetch d.province where d.name=:name and d.province=:province")
    public District getByNameAndProvince(@Param("name") String name, @Param("province") Province province);
}