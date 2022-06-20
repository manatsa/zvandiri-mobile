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
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import zw.org.zvandiri.business.domain.*;

/**
 *
 * @author Judge Muzinda
 */
public interface ContactRepo extends JpaRepository<Contact, String> {
 
    @Query("from Contact c left join fetch c.createdBy left join fetch c.modifiedBy left join fetch c.patient left join fetch c.location left join fetch c.position  left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments where c.id=:id")
    public Contact findByIds(@Param("id") String id);
    
    @Query("Select Distinct(c) from Contact c left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments left join fetch c.serviceOffereds left join fetch c.servicereferreds where c.patient=:patient")
    public List<Contact> findByPatient(@Param("patient") Patient patient);
    
//    @Query("Select Distinct(c) from Contact c left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments where c.patient=:patient and (c.contactDate) between :start and :end")
    public List<Contact> findByPatientAndContactDateBetween(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

        @Query("Select Distinct(c) from Contact c left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments where c.patient=:patient and (c.contactDate) between :start and :end")
    public List<Contact> findByPatientAndContactDates(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

    @Query("Select Distinct(c) from Contact c left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments left join  fetch  c.patient p where p.primaryClinic=:facility and (c.contactDate) between :start and :end order by  p.lastName ASC, p.firstName ASC, c.dateCreated DESC")
    public List<Contact> findByFacilityAndContactDates(
            @Param("facility") Facility facility,
            @Param("start") Date start, @Param("end") Date end);
    
    @Query("Select Distinct(c) from Contact c left join fetch c.patient left join fetch c.location left join fetch c.position left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments")
    public List<Contact> findByAllContacts();

    public List<Contact> findTop1ByPatientOrderByContactDateDesc(Patient patient);

    @Query("select c from Contact c join c.clinicalAssessments d  where d = ?1")
    Set<Contact> findDeviceByClinicalAssessments(Contact contact);

    @Query(value = "select c.* from zvandiri.contact c inner join zvandiri.contact_clinical_assessment cs" +
            "on cs.contact_id=c.id inner join zvandiri.assessment a on cs.assessment_id=a.id " +
            "where a.name='Unwell' " +
            "and c.contact_date between :startDate and :endDate", nativeQuery = true)
    List<Contact> findUnwellClients(@Param("startDate") Date startDate, @Param("endDate") Date endDate );

    @Query("select distinct  c from Contact c where c.patient.primaryClinic.id=:facility and c.dateCreated between  :start and :end ")
    List<Contact> findByFacilityInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("facility") String facility);

    @Query("select distinct  c from Contact c where c.patient.primaryClinic.district.id=:district and c.dateCreated between  :start and :end ")
    List<Contact> findByDistrictInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("district") String district);


}