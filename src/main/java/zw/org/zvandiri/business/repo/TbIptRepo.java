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
import zw.org.zvandiri.business.domain.util.TbIdentificationOutcome;

/**
 *
 * @author tasu
 */
public interface TbIptRepo extends JpaRepository<TbIpt, String> {

    @Query("from TbIpt s left join fetch s.patient left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient")
    public TbIpt findByPatient(@Param("patient") Patient patient);
    
    @Query("Select count(s) from TbIpt s where s.patient=:patient and s.tbIdentificationOutcome=:tbIdentificationOutcome")
    public int existsByPatientAndTbIdentificationOutcome(@Param("patient") Patient patient, @Param("tbIdentificationOutcome") TbIdentificationOutcome tbIdentificationOutcome);
    
    @Query("from TbIpt s left join fetch s.patient left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient")
    public List<TbIpt> findTopByPatientOrderByDateStartedIptDesc(@Param("patient") Patient patient);
    List<TbIpt> findByPatientOrderByDateCreatedDesc(Patient patient);

    List<TbIpt> findByActive(@Param("active")Boolean aTrue);

    @Query("select distinct  i from TbIpt i where i.patient.primaryClinic.id=:facility and i.dateCreated between  :start and :end ")
    List<TbIpt> findByFacilityInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("facility") String facility);

    @Query("select distinct  i from TbIpt i where i.patient.primaryClinic.district.id=:district and i.dateCreated between  :start and :end ")
    List<TbIpt> findByDistrictInGivenTime(@RequestParam("start") Date start, @RequestParam("end") Date end, @RequestParam("district") String district);

//    @Query("Select Distinct(t) from TbIpt t  where t.patient=:patient and (t.dateScreened) between :start and :end")
    public List<TbIpt> findByPatientAndDateScreenedBetween(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

        @Query("Select Distinct(t) from TbIpt t left join  fetch  t.patient p where p.primaryClinic=:facility  and (t.dateScreened) between :start and :end  order by  p.lastName ASC, p.firstName ASC, t.dateCreated DESC ")
    public List<TbIpt> findByFacilityAndDateScreenedBetween(
            @Param("facility") Facility facility,
            @Param("start") Date start, @Param("end") Date end);
}
