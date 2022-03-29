/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import java.util.Date;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.HIVDisclosureLocation;
import zw.org.zvandiri.business.domain.util.TransmissionMode;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author jmuzinda
 */
public class PatientHivDetailsDTO implements Serializable {
    
    private Patient patient;
    private YesNo hivStatusKnown;
    private TransmissionMode transmissionMode;
    private Date dateTested;
    private HIVDisclosureLocation hIVDisclosureLocation;
    private YesNo disability;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public YesNo getHivStatusKnown() {
        return hivStatusKnown;
    }

    public void setHivStatusKnown(YesNo hivStatusKnown) {
        this.hivStatusKnown = hivStatusKnown;
    }

    public TransmissionMode getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(TransmissionMode transmissionMode) {
        this.transmissionMode = transmissionMode;
    }

    public Date getDateTested() {
        return dateTested;
    }

    public void setDateTested(Date dateTested) {
        this.dateTested = dateTested;
    }

    public HIVDisclosureLocation gethIVDisclosureLocation() {
        return hIVDisclosureLocation;
    }

    public void sethIVDisclosureLocation(HIVDisclosureLocation hIVDisclosureLocation) {
        this.hIVDisclosureLocation = hIVDisclosureLocation;
    }

    public YesNo getDisability() {
        return disability;
    }

    public void setDisability(YesNo disability) {
        this.disability = disability;
    }
    
    public Patient getInstance (PatientHivDetailsDTO h) {
        Patient p = h.getPatient();
        p.setHivStatusKnown(h.getHivStatusKnown());
        p.setTransmissionMode(h.getTransmissionMode());
        p.setDateTested(h.getDateTested());
        p.sethIVDisclosureLocation(h.gethIVDisclosureLocation());
        p.setDisability(h.getDisability());        
        return p;
    }
}
