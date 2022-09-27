/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.dto.ReferralDTO;
import zw.org.zvandiri.business.domain.util.ReferralActionTaken;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table(indexes = {
		@Index(name = "referral_patient", columnList = "patient"),
		@Index(name = "referral_referral_date", columnList = "referralDate")
})
public class Referral extends BaseEntity {

    @ManyToOne
    @Fetch(value= FetchMode.SELECT)
    @JoinColumn(name="patient")
    private Patient patient;
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
    @Transient
    private String servicesRequestedError;
    @Transient
    private String servicesAvailedError;
    @Transient
    private Set<String> servicesReceived;
    @Transient
    private Set<String> servicesRequested;
    @Transient
    @Enumerated
    private YesNo hasReferred;
    @Enumerated
    private ReferralActionTaken actionTaken;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_hiv_sti_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "hiv_sti_id", nullable = false)})
    private Set<ServicesReferred> hivStiServicesReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_hiv_sti_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "hiv_sti_id", nullable = false)})
    private Set<ServicesReferred> hivStiServicesAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_oi_art_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "oi_arti_id", nullable = false)})
    private Set<ServicesReferred> oiArtReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_oi_art_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "oi_art_id", nullable = false)})
    private Set<ServicesReferred> oiArtAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_srh_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "srh_id", nullable = false)})
    private Set<ServicesReferred> srhReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_srh_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "srh_id", nullable = false)})
    private Set<ServicesReferred> srhAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_laboratory_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "laboratory_id", nullable = false)})
    private Set<ServicesReferred> laboratoryReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_laboratory_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "laboratory_id", nullable = false)})
    private Set<ServicesReferred> laboratoryAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_tb_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tb_id", nullable = false)})
    private Set<ServicesReferred> tbReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_tb_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tb_id", nullable = false)})
    private Set<ServicesReferred> tbAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_psych_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "psych_id", nullable = false)})
    private Set<ServicesReferred> psychReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_psychb_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "psych_id", nullable = false)})
    private Set<ServicesReferred> psychAvailed = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_legal_req", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "legal_id", nullable = false)})
    private Set<ServicesReferred> legalReq = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "referral_legalb_availed", joinColumns = {
        @JoinColumn(name = "referral_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "legal_id", nullable = false)})
    private Set<ServicesReferred> legalAvailed = new HashSet<>();

    public YesNo getHasReferred() {
        return hasReferred;
    }

    public void setHasReferred(YesNo hasReferred) {
        this.hasReferred = hasReferred;
    }

    public Referral() {
    }

    public Referral(Patient patient) {
        this.patient = patient;
    }

    public Referral(ReferralDTO referralDTO) {
        this.referralDate = referralDTO.getReferralDate();
        this.expectedVisitDate = referralDTO.getExpectedVisitDate();
        this.organisation = referralDTO.getOrganisation();
        this.designation = referralDTO.getDesignation();
        this.attendingOfficer = referralDTO.getAttendingOfficer();
        this.dateAttended = referralDTO.getDateAttended();

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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

    public ReferralActionTaken getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(ReferralActionTaken actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Set<ServicesReferred> getHivStiServicesReq() {
        return hivStiServicesReq;
    }

    public void setHivStiServicesReq(Set<ServicesReferred> hivStiServicesReq) {
        this.hivStiServicesReq = hivStiServicesReq;
    }

    public Set<ServicesReferred> getHivStiServicesAvailed() {
        return hivStiServicesAvailed;
    }

    public void setHivStiServicesAvailed(Set<ServicesReferred> hivStiServicesAvailed) {
        this.hivStiServicesAvailed = hivStiServicesAvailed;
    }

    public Set<ServicesReferred> getOiArtReq() {
        return oiArtReq;
    }

    public void setOiArtReq(Set<ServicesReferred> oiArtReq) {
        this.oiArtReq = oiArtReq;
    }

    public Set<ServicesReferred> getOiArtAvailed() {
        return oiArtAvailed;
    }

    public void setOiArtAvailed(Set<ServicesReferred> oiArtAvailed) {
        this.oiArtAvailed = oiArtAvailed;
    }

    public Set<ServicesReferred> getSrhReq() {
        return srhReq;
    }

    public void setSrhReq(Set<ServicesReferred> srhReq) {
        this.srhReq = srhReq;
    }

    public Set<ServicesReferred> getSrhAvailed() {
        return srhAvailed;
    }

    public void setSrhAvailed(Set<ServicesReferred> srhAvailed) {
        this.srhAvailed = srhAvailed;
    }

    public Set<ServicesReferred> getLaboratoryReq() {
        return laboratoryReq;
    }

    public void setLaboratoryReq(Set<ServicesReferred> laboratoryReq) {
        this.laboratoryReq = laboratoryReq;
    }

    public Set<ServicesReferred> getLaboratoryAvailed() {
        return laboratoryAvailed;
    }

    public void setLaboratoryAvailed(Set<ServicesReferred> laboratoryAvailed) {
        this.laboratoryAvailed = laboratoryAvailed;
    }

    public Set<ServicesReferred> getTbReq() {
        return tbReq;
    }

    public void setTbReq(Set<ServicesReferred> tbReq) {
        this.tbReq = tbReq;
    }

    public Set<ServicesReferred> getTbAvailed() {
        return tbAvailed;
    }

    public void setTbAvailed(Set<ServicesReferred> tbAvailed) {
        this.tbAvailed = tbAvailed;
    }

    public Set<ServicesReferred> getPsychReq() {
        return psychReq;
    }

    public void setPsychReq(Set<ServicesReferred> psychReq) {
        this.psychReq = psychReq;
    }

    public Set<ServicesReferred> getPsychAvailed() {
        return psychAvailed;
    }

    public void setPsychAvailed(Set<ServicesReferred> psychAvailed) {
        this.psychAvailed = psychAvailed;
    }

    public Set<ServicesReferred> getLegalReq() {
        return legalReq;
    }

    public void setLegalReq(Set<ServicesReferred> legalReq) {
        this.legalReq = legalReq;
    }

    public Set<ServicesReferred> getLegalAvailed() {
        return legalAvailed;
    }

    public void setLegalAvailed(Set<ServicesReferred> legalAvailed) {
        this.legalAvailed = legalAvailed;
    }

    public String getServicesRequestedError() {
        return servicesRequestedError;
    }

    public void setServicesRequestedError(String servicesRequestedError) {
        this.servicesRequestedError = servicesRequestedError;
    }

    public String getServicesAvailedError() {
        return servicesAvailedError;
    }

    public void setServicesAvailedError(String servicesAvailedError) {
        this.servicesAvailedError = servicesAvailedError;
    }

    public Set<String> getServicesReceived() {

        Set<String> services = new HashSet<>();
        services.addAll(filterServices(hivStiServicesAvailed));
        services.addAll(filterServices(oiArtAvailed));
        services.addAll(filterServices(srhAvailed));
        services.addAll(filterServices(laboratoryAvailed));
        services.addAll(filterServices(tbAvailed));
        services.addAll(filterServices(psychAvailed));
        services.addAll(filterServices(legalAvailed));
        return services;
    }

    public Set<String> getServicesRequested() {
        Set<String> services = new HashSet<>();
        services.addAll(filterServices(hivStiServicesReq));
        services.addAll(filterServices(oiArtReq));
        services.addAll(filterServices(srhReq));
        services.addAll(filterServices(laboratoryReq));
        services.addAll(filterServices(tbReq));
        services.addAll(filterServices(psychReq));
        services.addAll(filterServices(legalReq));
        return services;
    }

    private Set<String> filterServices(Set<ServicesReferred> services) {
        Set<String> service = new HashSet<>();
        if(services!=null && !services.isEmpty())
        {
            for (ServicesReferred item : services) {
                service.add(item.getName());
            }
        }

        return service;
    }

}
