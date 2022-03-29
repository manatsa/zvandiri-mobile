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
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.util.PatientInnerJoin;

/**
 *
 * @author Judge Muzinda
 */
@Repository
public interface PatientRepo extends JpaRepository<Patient, String> {
    
    @Query("Select Distinct p from Patient p left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup   left join fetch p.relationship left join fetch p.mobileOwnerRelation left join fetch p.secondaryMobileownerRelation left join fetch p.motherOfHei where p.active=:active order by p.lastName, p.firstName, p.middleName ASC")

    public List<Patient> findByActive(@Param("active") Boolean active);
    
    @Query("Select Distinct p from Patient p left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where p.cat=:cat and p.active=:active order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByCatAndActive(@Param("cat") Boolean cat, @Param("active") Boolean active);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like :name% or p.lastName Like :name% or p.patientNumber Like :name%) order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameOrLastName(@Param("name") String name, @Param("active") Boolean active);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy  left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like :name% or p.lastName Like :name% or p.patientNumber Like :name%) and p.primaryClinic.district.province=:province order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameOrLastNameAndProvince(@Param("name") String name, @Param("active") Boolean active, @Param("province") Province province);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where p.active=:active and (p.firstName Like :name% or p.lastName Like :name% or p.patientNumber Like :name%) and p.primaryClinic.district=:district order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameOrLastNameAndDistrict(@Param("name") String name, @Param("active") Boolean active, @Param("district") District district);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like :first% or p.firstName Like :last%  or p.patientNumber Like :last% or p.patientNumber Like :first%) and (p.lastName Like :last% or p.lastName Like :first% or p.patientNumber Like :first% or p.patientNumber Like :last%) ) order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameAndLastName(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like :first% or p.firstName Like :last%) and (p.lastName Like :last% or p.lastName Like :first%) ) and p.primaryClinic.district.province=:province order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameAndLastNameAndProvince(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active, @Param("province") Province province);
    
    @Query("Select Distinct p from Patient p left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup  where p.active=:active and ((p.firstName Like :first% or p.firstName Like :last%) and (p.lastName Like :last% or p.lastName Like :first%) ) and p.primaryClinic.district=:district order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findByFirstNameAndLastNameAndDistrict(@Param("first") String first, @Param("last") String last, @Param("active") Boolean active, @Param("district") District district);
    
    @Query("Select Distinct p from Patient p left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where p.firstName=:firstName and p.lastName=:lastName and p.dateOfBirth=:dateOfBirth and p.gender=:gender")
    public Patient findByFirstNameANdLastNameAndDateOfBirthAndGender(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("dateOfBirth") Date dateOfBirth, @Param("gender") Gender gender);
    
    @Query("Select Distinct p from Patient p where (p.firstName like :firstName% and p.lastName like :lastName%) and (p.dateOfBirth between :start and :end) and p.primaryClinic=:primaryClinic and (p.firstName like %:firstNameLastPart and p.lastName like %:lastNameLastPart) and p.active=:active")
    public List<Patient> checkPatientDuplicate(
            @Param("firstName") String firstName, 
            @Param("lastName") String lastName, 
            @Param("start") Date start, @Param("end") Date end, 
            @Param("primaryClinic") Facility primaryClinic,
            @Param("firstNameLastPart") String firstNameLastPart, 
            @Param("lastNameLastPart") String lastNameLastPart, @Param("active") Boolean active);
    
    @Query("Select Distinct p from Patient p left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where (p.dateOfBirth between :start and :end) and p.primaryClinic.district=:district and p.gender=:gender and (p.firstName Like :name% or p.lastName Like :name%) order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findYoungMothersByName(
            @Param("start") Date start, @Param("end") Date end, 
            @Param("district") District district, 
            @Param("gender") Gender gender,
            @Param("name") String name);
    
    @Query("Select Distinct p from Patient p left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup where (p.dateOfBirth between :start and :end) and p.primaryClinic.district=:district and p.gender=:gender and ((p.firstName Like :first% or p.firstName Like :last%) and (p.lastName Like :last% or p.lastName Like :first%) ) order by p.lastName, p.firstName, p.middleName ASC")
    public List<Patient> findYoungMothersByBoth(
            @Param("start") Date start, @Param("end") Date end, 
            @Param("district") District district, 
            @Param("gender") Gender gender,
            @Param("first") String first, @Param("last") String last);
    
    @Query("Select Distinct p from Patient p "+PatientInnerJoin.PATIENT_FULL_ASSOC_FETCH+" where p.id=:id")
    public Patient getPatient(@Param("id") String id);
    
    public int countByPatientNumberNotNull();


    @Query(value = "" +
            "select u1.pid, u1.first_name, u1.last_name, u1.mobile_number, u1.facility, u1.district , \n" +
            "    case c2.follow_up\n" +
            "       when 0 then 'Stable'\n" +
            "       when 1 then 'Enhanced'\n" +
            "       when 2 then 'VST'\n" +
            "       when 3 then 'Young_Mothers_Group'\n" +
            "       when 4 then 'Youth_Group'\n" +
            "       else 'null'\n" +
            "    end last_status \n" +
            "from (" +
            "       select p.id pid, p.first_name first_name, p.last_name last_name,p.mobile_number, f.name facility, d.name district from zvandiri.patient p\n" +
            "       left outer join (\n" +
            "           select c.patient, max(c.contact_date) the_date, c.follow_up from zvandiri.contact c  \n" +
            "               where c.contact_date between :startDate and :endDate -- str_to_date('30,5,2020','%d,%m,%Y')  \n" +
            "                                    group by c.id \n" +
            "           )c1 on p.id=c1.patient\n" +
            "       inner  join zvandiri.facility f on p.primary_clinic=f.id\n" +
            "       inner join zvandiri.district d on f.district=d.id\n" +
            "       where  p.deleted=0 and p.status not in (0,1,2,3,4)  and d.name= :district\n" +
            "       group by p.id\n" +
            "       having count(c1.the_date)<1\n" +
            "left outer join (\n" +
            "   select c.patient, max(c.contact_date) the_date, c.follow_up \n" +
            "   from zvandiri.contact c  \n" +
            "   group by c.id \n" +
            "              )c2 on u1.pid=c2.patient;" +
            "", nativeQuery = true)
    List<Patient> findDistinctBy(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("district") String district);

    


    @Query("select p from Patient p where " +
            "p.id not in (select c.patient from Contact c  where c.dateCreated between :start and :end)")
    public List<Patient> getUncontactedNational(@Param("start") Date start, @Param("end") Date date);


    @Query("select p from Patient p where " +
            "p.id not in (select c.patient from Contact c  where (c.dateCreated between :start and :end)  and p.primaryClinic.district=:district )")
    public List<Patient> getUncontactedDistrict(@Param("start") Date start, @Param("end") Date date, @Param("district") District district);

    public  List<Patient> getAllByPrimaryClinicAndActive(@Param("facility") Facility primaryClinic, @Param("active") boolean active);

    
}