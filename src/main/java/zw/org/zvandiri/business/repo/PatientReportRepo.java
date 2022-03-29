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

import org.springframework.data.repository.CrudRepository;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author Judge Muzinda
 */
public interface PatientReportRepo extends CrudRepository<Patient, String> {
    
    /*@Query("select count(p) from Patient p where p.district.province=:province and p.hivStatus=:hivStatus")
    public Integer findCountByProvinceAndHivStatus(@Param("province")  Province province,@Param("hivStatus") HIVStatus hivStatus);
    
    @Query("select count(p) from Patient p where p.district=:district and p.hivStatus=:hivStatus")
    public Integer findCountByDistrictAndHivStatus(@Param("district")  District district,@Param("hivStatus") HIVStatus hivStatus);
    
    @Query("select count(p) from Patient p where p.primaryClinic=:primaryClinic and p.hivStatus=:hivStatus")
    public Integer findCountByClinicAndHivStatus(@Param("primaryClinic")  Facility primaryClinic,@Param("hivStatus") HIVStatus hivStatus);*/



}