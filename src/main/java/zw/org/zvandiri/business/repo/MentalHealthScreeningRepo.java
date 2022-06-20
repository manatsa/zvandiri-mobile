/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import zw.org.zvandiri.business.domain.*;

/**
 *
 * @author tasu
 */
public interface MentalHealthScreeningRepo extends JpaRepository<MentalHealthScreening, String> {
    @Query("from MentalHealthScreening s left join fetch s.patient left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient")
    //public MentalHealthScreening findByPatient(@Param("patient") Patient patient);
    List<MentalHealthScreening> findByPatient(@Param("patient") Patient patient);

    List<MentalHealthScreening> findByActive(@Param("active")Boolean aTrue);

    @Query("select distinct  m from MentalHealthScreening m where m.patient.primaryClinic.id=:facility and m.dateCreated between  :start and :end ")
    List<MentalHealthScreening> findByFacilityInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("facility") String facility);

    @Query("select distinct  m from MentalHealthScreening m where m.patient.primaryClinic.district.id=:district and m.dateCreated between  :start and :end ")
    List<MentalHealthScreening> findByDistrictInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("district") String district);

//    @Query("Select Distinct(c) from MentalHealthScreening c where c.patient=:patient and (c.dateScreened) between :start and :end")
    public List<MentalHealthScreening> findByPatientAndDateScreenedBetween(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

        @Query("Select Distinct(m) from MentalHealthScreening m left join  fetch  m.patient p where p.primaryClinic=:facility and (m.dateScreened) between :start and :end order  by p.lastName ASC, p.firstName ASC, m.dateCreated DESC")
    public List<MentalHealthScreening> findByFacilityAndDateScreenedBetween(
            @Param("facility") Facility facility,
            @Param("start") Date start, @Param("end") Date end);
}
