/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Relationship;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author jmuzinda
 */
public class PatientContactDTO implements Serializable {
    
    private Patient patient;
    private String mobileNumber;
    private YesNo mobileOwner;
    private String ownerName;
    private Relationship mobileOwnerRelation;
    private String secondaryMobileNumber;
    private YesNo ownSecondaryMobile;
    private String secondaryMobileOwnerName;
    private Relationship secondaryMobileownerRelation;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public YesNo getMobileOwner() {
        return mobileOwner;
    }

    public void setMobileOwner(YesNo mobileOwner) {
        this.mobileOwner = mobileOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Relationship getMobileOwnerRelation() {
        return mobileOwnerRelation;
    }

    public void setMobileOwnerRelation(Relationship mobileOwnerRelation) {
        this.mobileOwnerRelation = mobileOwnerRelation;
    }

    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public YesNo getOwnSecondaryMobile() {
        return ownSecondaryMobile;
    }

    public void setOwnSecondaryMobile(YesNo ownSecondaryMobile) {
        this.ownSecondaryMobile = ownSecondaryMobile;
    }

    public String getSecondaryMobileOwnerName() {
        return secondaryMobileOwnerName;
    }

    public void setSecondaryMobileOwnerName(String secondaryMobileOwnerName) {
        this.secondaryMobileOwnerName = secondaryMobileOwnerName;
    }

    public Relationship getSecondaryMobileownerRelation() {
        return secondaryMobileownerRelation;
    }

    public void setSecondaryMobileownerRelation(Relationship secondaryMobileownerRelation) {
        this.secondaryMobileownerRelation = secondaryMobileownerRelation;
    }
    
    public Patient getInstance (PatientContactDTO c) {
        Patient p = c.getPatient();
        p.setMobileNumber(c.getMobileNumber());
        p.setMobileOwner(c.getMobileOwner());
        p.setOwnerName(c.getOwnerName());
        p.setMobileOwnerRelation(c.getMobileOwnerRelation());
        p.setSecondaryMobileNumber(c.getSecondaryMobileNumber());
        p.setOwnSecondaryMobile(c.getOwnSecondaryMobile());
        p.setSecondaryMobileOwnerName(c.getSecondaryMobileOwnerName());
        p.setSecondaryMobileownerRelation(c.getSecondaryMobileownerRelation());
        return p;
    }
}
