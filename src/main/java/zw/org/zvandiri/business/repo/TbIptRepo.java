/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.TbIpt;
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
}
