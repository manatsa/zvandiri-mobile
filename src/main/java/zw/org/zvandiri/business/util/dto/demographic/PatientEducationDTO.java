/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import java.util.Date;
import zw.org.zvandiri.business.domain.Education;
import zw.org.zvandiri.business.domain.EducationLevel;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.ReasonForNotReachingOLevel;
import zw.org.zvandiri.business.domain.Referer;

/**
 *
 * @author jmuzinda
 */
public class PatientEducationDTO implements Serializable {
    
    private Patient patient;
    private Education education;
    private EducationLevel educationLevel;
    private ReasonForNotReachingOLevel reasonForNotReachingOLevel;
    private Date dateJoined;
    private Referer referer;
    private String refererName;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public ReasonForNotReachingOLevel getReasonForNotReachingOLevel() {
        return reasonForNotReachingOLevel;
    }

    public void setReasonForNotReachingOLevel(ReasonForNotReachingOLevel reasonForNotReachingOLevel) {
        this.reasonForNotReachingOLevel = reasonForNotReachingOLevel;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Referer getReferer() {
        return referer;
    }

    public void setReferer(Referer referer) {
        this.referer = referer;
    }

    public String getRefererName() {
        return refererName;
    }

    public void setRefererName(String refererName) {
        this.refererName = refererName;
    }
    
    public Patient getInstance (PatientEducationDTO e) {
        Patient p = e.getPatient();
        p.setEducation(e.getEducation());
        p.setEducationLevel(e.getEducationLevel());
        p.setReasonForNotReachingOLevel(e.getReasonForNotReachingOLevel());
        p.setDateJoined(e.getDateJoined());
        p.setReferer(e.getReferer());
        p.setRefererName(e.getRefererName());
        return p;
    }
}
