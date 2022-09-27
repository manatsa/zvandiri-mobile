package zw.org.zvandiri.business.domain.dto;


import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Referral;
import zw.org.zvandiri.business.domain.ServicesReferred;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ReferralDTO implements Serializable {
    private String id;
    private String patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date referralDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expectedVisitDate;
    private String organisation;
    private String designation;
    private String attendingOfficer;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAttended;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCreated;
    private String pname;

    private Set<ServiceReferredDTO> hivStiServicesReq = new HashSet<>();

    private Set<ServiceReferredDTO> hivStiServicesAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> oiArtReq = new HashSet<>();

    private Set<ServiceReferredDTO> oiArtAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> srhReq = new HashSet<>();

    private Set<ServiceReferredDTO> srhAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> laboratoryReq = new HashSet<>();

    private Set<ServiceReferredDTO> laboratoryAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> tbReq = new HashSet<>();

    private Set<ServiceReferredDTO> tbAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> psychReq = new HashSet<>();

    private Set<ServiceReferredDTO> psychAvailed = new HashSet<>();

    private Set<ServiceReferredDTO> legalReq = new HashSet<>();

    private Set<ServiceReferredDTO> legalAvailed = new HashSet<>();

    public ReferralDTO() {
    }

    public ReferralDTO(Referral referral) {
        this.id=referral.getId();
        this.patient = referral.getPatient().getId();
        this.referralDate = referral.getReferralDate();
        this.expectedVisitDate = referral.getExpectedVisitDate();
        this.organisation = referral.getOrganisation();
        this.designation = referral.getDesignation();
        this.attendingOfficer = referral.getAttendingOfficer();
        this.dateAttended = referral.getDateAttended();
        this.dateCreated=referral.getDateCreated();
        this.pname=referral.getPatient().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }

    public Date getExpectedVisitDate() {
        return expectedVisitDate;
    }

    public void setExpectedVisitDate(Date expectedVisitDate) {
        this.expectedVisitDate = expectedVisitDate;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAttendingOfficer() {
        return attendingOfficer;
    }

    public void setAttendingOfficer(String attendingOfficer) {
        this.attendingOfficer = attendingOfficer;
    }

    public Date getDateAttended() {
        return dateAttended;
    }

    public void setDateAttended(Date dateAttended) {
        this.dateAttended = dateAttended;
    }

    public Set<ServiceReferredDTO> getHivStiServicesReq() {
        return hivStiServicesReq;
    }

    public void setHivStiServicesReq(Set<ServiceReferredDTO> hivStiServicesReq) {
        this.hivStiServicesReq = hivStiServicesReq;
    }

    public Set<ServiceReferredDTO> getHivStiServicesAvailed() {
        return hivStiServicesAvailed;
    }

    public void setHivStiServicesAvailed(Set<ServiceReferredDTO> hivStiServicesAvailed) {
        this.hivStiServicesAvailed = hivStiServicesAvailed;
    }

    public Set<ServiceReferredDTO> getOiArtReq() {
        return oiArtReq;
    }

    public void setOiArtReq(Set<ServiceReferredDTO> oiArtReq) {
        this.oiArtReq = oiArtReq;
    }

    public Set<ServiceReferredDTO> getOiArtAvailed() {
        return oiArtAvailed;
    }

    public void setOiArtAvailed(Set<ServiceReferredDTO> oiArtAvailed) {
        this.oiArtAvailed = oiArtAvailed;
    }

    public Set<ServiceReferredDTO> getSrhReq() {
        return srhReq;
    }

    public void setSrhReq(Set<ServiceReferredDTO> srhReq) {
        this.srhReq = srhReq;
    }

    public Set<ServiceReferredDTO> getSrhAvailed() {
        return srhAvailed;
    }

    public void setSrhAvailed(Set<ServiceReferredDTO> srhAvailed) {
        this.srhAvailed = srhAvailed;
    }

    public Set<ServiceReferredDTO> getLaboratoryReq() {
        return laboratoryReq;
    }

    public void setLaboratoryReq(Set<ServiceReferredDTO> laboratoryReq) {
        this.laboratoryReq = laboratoryReq;
    }

    public Set<ServiceReferredDTO> getLaboratoryAvailed() {
        return laboratoryAvailed;
    }

    public void setLaboratoryAvailed(Set<ServiceReferredDTO> laboratoryAvailed) {
        this.laboratoryAvailed = laboratoryAvailed;
    }

    public Set<ServiceReferredDTO> getTbReq() {
        return tbReq;
    }

    public void setTbReq(Set<ServiceReferredDTO> tbReq) {
        this.tbReq = tbReq;
    }

    public Set<ServiceReferredDTO> getTbAvailed() {
        return tbAvailed;
    }

    public void setTbAvailed(Set<ServiceReferredDTO> tbAvailed) {
        this.tbAvailed = tbAvailed;
    }

    public Set<ServiceReferredDTO> getPsychReq() {
        return psychReq;
    }

    public void setPsychReq(Set<ServiceReferredDTO> psychReq) {
        this.psychReq = psychReq;
    }

    public Set<ServiceReferredDTO> getPsychAvailed() {
        return psychAvailed;
    }

    public void setPsychAvailed(Set<ServiceReferredDTO> psychAvailed) {
        this.psychAvailed = psychAvailed;
    }

    public Set<ServiceReferredDTO> getLegalReq() {
        return legalReq;
    }

    public void setLegalReq(Set<ServiceReferredDTO> legalReq) {
        this.legalReq = legalReq;
    }

    public Set<ServiceReferredDTO> getLegalAvailed() {
        return legalAvailed;
    }

    public void setLegalAvailed(Set<ServiceReferredDTO> legalAvailed) {
        this.legalAvailed = legalAvailed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "ReferralDTO{" +
                "id='" + id + '\'' +
                ", patient='" + patient + '\'' +
                ", referralDate=" + referralDate +
                ", expectedVisitDate=" + expectedVisitDate +
                ", organisation='" + organisation + '\'' +
                ", designation='" + designation + '\'' +
                ", attendingOfficer='" + attendingOfficer + '\'' +
                ", dateAttended=" + dateAttended +
                ", dateCreated=" + dateCreated +
                ", pname='" + pname + '\'' +
                ", hivStiServicesReq=" + hivStiServicesReq +
                ", hivStiServicesAvailed=" + hivStiServicesAvailed +
                ", oiArtReq=" + oiArtReq +
                ", oiArtAvailed=" + oiArtAvailed +
                ", srhReq=" + srhReq +
                ", srhAvailed=" + srhAvailed +
                ", laboratoryReq=" + laboratoryReq +
                ", laboratoryAvailed=" + laboratoryAvailed +
                ", tbReq=" + tbReq +
                ", tbAvailed=" + tbAvailed +
                ", psychReq=" + psychReq +
                ", psychAvailed=" + psychAvailed +
                ", legalReq=" + legalReq +
                ", legalAvailed=" + legalAvailed +
                '}';
    }
}

