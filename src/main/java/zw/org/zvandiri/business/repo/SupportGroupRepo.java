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
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.SupportGroup;

/**
 *
 * @author Judge Muzinda
 */
public interface SupportGroupRepo extends JpaRepository<SupportGroup, String> {
    
    @Query("from SupportGroup s left join fetch s.district left join fetch s.createdBy left join fetch s.modifiedBy where s.active=:active Order By s.name ASC")
    public List<SupportGroup> getOptAll(@Param("active") Boolean active);
    
    @Query("from SupportGroup s left join fetch s.district where s.name=:name and s.district=:district")
    public SupportGroup findByNameAndDistrict(@Param("name") String name, @Param("district") District district);
    
    @Query("from SupportGroup s left join fetch s.district where (s.district=:district or s.district is null) and s.active=:active order by s.name ASC")
    public List<SupportGroup> findByDistrictAndActive(@Param("district") District district, @Param("active") Boolean active);
    
    @Query("from SupportGroup s left join fetch s.district where s.district.province=:province and s.active=:active order by s.name ASC")
    public List<SupportGroup> findByProvinceAndActive(@Param("province") Province province, @Param("active") Boolean active);
}