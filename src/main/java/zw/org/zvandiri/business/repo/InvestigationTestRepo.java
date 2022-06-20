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

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.web.bind.annotation.RequestParam;
import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.TestType;

/**
 *
 * @author Judge Muzinda
 */
public interface InvestigationTestRepo extends JpaRepository<InvestigationTest, String> {
    
    @Query("from InvestigationTest c left join fetch c.patient where c.patient=:patient and c.testType=:testType order By c.dateTaken DESC")
    public List<InvestigationTest> findByPatientAndTestType(@Param("patient") Patient patient, @Param("testType") TestType testType);
    
    @Query("from InvestigationTest c left join fetch c.patient where c.id=:id")
    public InvestigationTest findByIds(@Param("id") String id);

    List<InvestigationTest> findByActive(@Param("active") Boolean aTrue);

    @Query("select distinct  i from InvestigationTest i where i.patient.primaryClinic.id=:facility and i.dateCreated between  :start and :end ")
    List<InvestigationTest> findByFacilityInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("facility") String facility);

    @Query("select distinct  i from InvestigationTest i where i.patient.primaryClinic.district.id=:district and i.dateCreated between  :start and :end ")
    List<InvestigationTest> findByDistrictInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("district") String district);

//    @Query("Select Distinct(t) from InvestigationTest t where t.patient=:patient and (t.dateTaken) between :start and :end")
    public List<InvestigationTest> findByPatientAndDateTakenBetween(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

        @Query("Select Distinct(t) from InvestigationTest t left join  fetch  t.patient p where p.primaryClinic=:facility and (t.dateTaken) between :start and :end order by  p.lastName ASC, p.firstName ASC, t.dateCreated DESC")
    public List<InvestigationTest> findByFacilityAndDateTakenBetween(
            @Param("facility") Facility facility,
            @Param("start") Date start, @Param("end") Date end);
}