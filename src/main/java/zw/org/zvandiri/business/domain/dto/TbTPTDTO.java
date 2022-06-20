/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain.dto;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.TbIpt;
import zw.org.zvandiri.business.domain.util.TbSymptom;
import zw.org.zvandiri.business.domain.util.YesNo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author manatsachinyeruse@gmail.com
 */

@ToString()
public class TbTPTDTO {

    private String id;
    @ManyToOne
    @JoinColumn(name = "patient")
    private String patient;
    @Enumerated
    private YesNo screenedForTb;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateScreened;
    @Enumerated
    private YesNo identifiedWithTb;
    @ElementCollection
    private Set<TbSymptom> tbSymptoms;
    @Enumerated
    private YesNo referredForInvestigation;
    @Enumerated
    private YesNo eligibleForIpt;
    @Enumerated
    private YesNo referredForIpt;
    @Enumerated
    private YesNo referralComplete;
    @Enumerated
    private YesNo screenedByHcw;
    @Enumerated
    private YesNo identifiedWithTbByHcw;
    @Enumerated
    private YesNo onTBTreatment;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateStartedTreatment;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCompletedTreatment;
    @Enumerated
    private YesNo onIpt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateStartedIpt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCompletedIpt;
    @Enumerated
    private YesNo startedOnIpt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateStartedOnIpt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCompletedOnIpt;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCreated;
    private String pname;


    public TbTPTDTO() {
    }

    public TbTPTDTO(TbIpt tbIpt) {
        this.id=tbIpt.getId();
        this.screenedForTb = tbIpt.getScreenedForTb();
        this.dateScreened = tbIpt.getDateScreened();
        this.identifiedWithTb = tbIpt.getIdentifiedWithTb();
        this.tbSymptoms = tbIpt.getTbSymptoms();
        this.referredForInvestigation = tbIpt.getReferredForInvestigation();
        this.eligibleForIpt = tbIpt.getEligibleForIpt();
        this.referredForIpt = tbIpt.getReferredForIpt();
        this.referralComplete = tbIpt.getReferralComplete();
        this.screenedByHcw = tbIpt.getScreenedByHcw();
        this.identifiedWithTbByHcw = tbIpt.getIdentifiedWithTbByHcw();
        this.onTBTreatment = tbIpt.getOnTBTreatment();
        this.dateStartedTreatment = tbIpt.getDateStartedTreatment();
        this.dateCompletedTreatment = tbIpt.getDateCompletedTreatment();
        this.onIpt = tbIpt.getOnIpt();
        this.dateStartedIpt = tbIpt.getDateStartedIpt();
        this.dateCompletedIpt = tbIpt.getDateCompletedIpt();
        this.startedOnIpt = tbIpt.getStartedOnIpt();
        this.dateStartedOnIpt = tbIpt.getDateStartedOnIpt();
        this.dateCompletedOnIpt = tbIpt.getDateCompletedOnIpt();
        this.dateCreated=tbIpt.getDateCreated();
        this.pname=tbIpt.getPatient().getName();
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

    public YesNo getScreenedForTb() {
        return screenedForTb;
    }

    public void setScreenedForTb(YesNo screenedForTb) {
        this.screenedForTb = screenedForTb;
    }

    public Date getDateScreened() {
        return dateScreened;
    }

    public void setDateScreened(Date dateScreened) {
        this.dateScreened = dateScreened;
    }

    public Set<TbSymptom> getTbSymptoms() {
        return tbSymptoms;
    }

    public void setTbSymptoms(Set<TbSymptom> tbSymptoms) {
        this.tbSymptoms = tbSymptoms;
    }

    public YesNo getIdentifiedWithTb() {
        return identifiedWithTb;
    }

    public void setIdentifiedWithTb(YesNo identifiedWithTb) {
        this.identifiedWithTb = identifiedWithTb;
    }

    public Date getDateStartedTreatment() {
        return dateStartedTreatment;
    }

    public void setDateStartedTreatment(Date dateStartedTreatment) {
        this.dateStartedTreatment = dateStartedTreatment;
    }

    public YesNo getReferredForIpt() {
        return referredForIpt;
    }

    public void setReferredForIpt(YesNo referredForIpt) {
        this.referredForIpt = referredForIpt;
    }

    public YesNo getOnIpt() {
        return onIpt;
    }

    public void setOnIpt(YesNo onIpt) {
        this.onIpt = onIpt;
    }

    public Date getDateStartedIpt() {
        return dateStartedIpt;
    }

    public void setDateStartedIpt(Date dateStartedIpt) {
        this.dateStartedIpt = dateStartedIpt;
    }

    public YesNo getReferredForInvestigation() {
        return referredForInvestigation;
    }

    public void setReferredForInvestigation(YesNo referredForInvestigation) {
        this.referredForInvestigation = referredForInvestigation;
    }

    public YesNo getEligibleForIpt() {
        return eligibleForIpt;
    }

    public void setEligibleForIpt(YesNo eligibleForIpt) {
        this.eligibleForIpt = eligibleForIpt;
    }

    public YesNo getReferralComplete() {
        return referralComplete;
    }

    public void setReferralComplete(YesNo referralComplete) {
        this.referralComplete = referralComplete;
    }

    public YesNo getOnTBTreatment() {
        return onTBTreatment;
    }

    public void setOnTBTreatment(YesNo onTBTreatment) {
        this.onTBTreatment = onTBTreatment;
    }

    public Date getDateCompletedTreatment() {
        return dateCompletedTreatment;
    }

    public void setDateCompletedTreatment(Date dateCompletedTreatment) {
        this.dateCompletedTreatment = dateCompletedTreatment;
    }

    public Date getDateCompletedIpt() {
        return dateCompletedIpt;
    }

    public void setDateCompletedIpt(Date dateCompletedIpt) {
        this.dateCompletedIpt = dateCompletedIpt;
    }

    public YesNo getScreenedByHcw() {
        return screenedByHcw;
    }

    public void setScreenedByHcw(YesNo screenedByHcw) {
        this.screenedByHcw = screenedByHcw;
    }

    public YesNo getIdentifiedWithTbByHcw() {
        return identifiedWithTbByHcw;
    }

    public void setIdentifiedWithTbByHcw(YesNo identifiedWithTbByHcw) {
        this.identifiedWithTbByHcw = identifiedWithTbByHcw;
    }

    public YesNo getStartedOnIpt() {
        return startedOnIpt;
    }

    public void setStartedOnIpt(YesNo startedOnIpt) {
        this.startedOnIpt = startedOnIpt;
    }

    public Date getDateStartedOnIpt() {
        return dateStartedOnIpt;
    }

    public void setDateStartedOnIpt(Date dateStartedOnIpt) {
        this.dateStartedOnIpt = dateStartedOnIpt;
    }

    public Date getDateCompletedOnIpt() {
        return dateCompletedOnIpt;
    }

    public void setDateCompletedOnIpt(Date dateCompletedOnIpt) {
        this.dateCompletedOnIpt = dateCompletedOnIpt;
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

    public String getSymptoms() {
        if (tbSymptoms.isEmpty()) {
            return "";
        }
        StringBuilder r = new StringBuilder();
        int pos = 1;
        for (TbSymptom role : tbSymptoms) {
            if (pos < tbSymptoms.size()) {
                r.append(role.getName());
                r.append(" ,");
            } else {
                r.append(role.getName());
            }
            pos++;
        }
        return r.toString();
    }

    @Override
    public String toString() {
        return "TbTPTDTO{" +
                "id='" + id + '\'' +
                ", patient='" + patient + '\'' +
                ", screenedForTb=" + screenedForTb +
                ", dateScreened=" + dateScreened +
                ", identifiedWithTb=" + identifiedWithTb +
                ", tbSymptoms=" + tbSymptoms +
                ", referredForInvestigation=" + referredForInvestigation +
                ", eligibleForIpt=" + eligibleForIpt +
                ", referredForIpt=" + referredForIpt +
                ", referralComplete=" + referralComplete +
                ", screenedByHcw=" + screenedByHcw +
                ", identifiedWithTbByHcw=" + identifiedWithTbByHcw +
                ", onTBTreatment=" + onTBTreatment +
                ", dateStartedTreatment=" + dateStartedTreatment +
                ", dateCompletedTreatment=" + dateCompletedTreatment +
                ", onIpt=" + onIpt +
                ", dateStartedIpt=" + dateStartedIpt +
                ", dateCompletedIpt=" + dateCompletedIpt +
                ", startedOnIpt=" + startedOnIpt +
                ", dateStartedOnIpt=" + dateStartedOnIpt +
                ", dateCompletedOnIpt=" + dateCompletedOnIpt +
                ", dateCreated=" + dateCreated +
                ", pname='" + pname + '\'' +
                '}';
    }

}
