/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author jmuzinda
 */
public class PatientZvandiridetailsDTO implements Serializable {
    
    private Patient patient;
    private YesNo consentToMHealth;
    private YesNo cat;
    private YesNo youngMumGroup;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public YesNo getConsentToMHealth() {
        return consentToMHealth;
    }

    public void setConsentToMHealth(YesNo consentToMHealth) {
        this.consentToMHealth = consentToMHealth;
    }

    public YesNo getCat() {
        return cat;
    }

    public void setCat(YesNo cat) {
        this.cat = cat;
    }

    public YesNo getYoungMumGroup() {
        return youngMumGroup;
    }

    public void setYoungMumGroup(YesNo youngMumGroup) {
        this.youngMumGroup = youngMumGroup;
    }
    
    public Patient getInstance (PatientZvandiridetailsDTO z) {
        Patient p = z.getPatient();
        p.setConsentToMHealth(z.getConsentToMHealth());
        p.setCat(z.getCat());
        p.setYoungMumGroup(z.getYoungMumGroup());
        return p;
    }
}
