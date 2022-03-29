/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Contact;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author tasu
 */
public interface MorbidityRepo extends JpaRepository<Contact, String> {
    
    @Query("from Contact c left join fetch c.clinicalAssessments left join fetch c.nonClinicalAssessments left join fetch c.patient where c.patient=:patient")
    public Contact findByPatient(@Param("patient") Patient patient);
}
