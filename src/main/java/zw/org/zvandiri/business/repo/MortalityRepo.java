/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Patient;

import java.util.List;

/**
 *
 * @author tasu
 */
public interface MortalityRepo extends JpaRepository<Mortality, String> {
    
    @Query("from Mortality s left join fetch s.patient left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient")
    public Mortality findByPatient(@Param("patient") Patient patient);

    List<Mortality> findByActive(@Param("active") Boolean aTrue);
}
