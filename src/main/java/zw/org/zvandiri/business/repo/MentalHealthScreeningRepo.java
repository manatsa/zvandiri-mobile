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
import zw.org.zvandiri.business.domain.MentalHealthScreening;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author tasu
 */
public interface MentalHealthScreeningRepo extends JpaRepository<MentalHealthScreening, String> {
    @Query("from MentalHealthScreening s left join fetch s.patient left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient")
    //public MentalHealthScreening findByPatient(@Param("patient") Patient patient);
    List<MentalHealthScreening> findByPatient(@Param("patient") Patient patient);

    List<MentalHealthScreening> findByActive(@Param("active")Boolean aTrue);
}
