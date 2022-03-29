/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.domain.DisabilityCategory;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.PatientDisability;

/**
 *
 * @author jmuzinda
 */
public interface DisabilityService  extends GenericPatientService<PatientDisability> {
    
    public PatientDisability getByPatientAndDisabilityCategory(Patient patient, DisabilityCategory disabilityCategory);
}
