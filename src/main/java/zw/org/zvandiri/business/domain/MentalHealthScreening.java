/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.dto.MentalHealthScreeningDTO;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.domain.util.Referral;
import zw.org.zvandiri.business.service.PatientService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author tasu
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "mental_health_screening_patient", columnList = "patient")
})*/

@ToString
public class MentalHealthScreening extends BaseEntity {

    @ManyToOne(optional = false)
    @JsonIgnore
    @NotNull
    @JoinColumn(name="patient")
    private Patient patient;
    @Enumerated
    private YesNo screenedForMentalHealth;
    @Enumerated
    private MentalHealthScreeningType screening;
    @Enumerated
    private YesNo risk;
    @JsonIgnore
    @ElementCollection(targetClass = IdentifiedRisk.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mental_health_screening_risk",
            joinColumns = @JoinColumn(name = "screening_id"))
    @Column(name = "risk_id")
    private Set<IdentifiedRisk> identifiedRisks;
    @Enumerated
    private YesNo support;
    @JsonIgnore
    @ElementCollection(targetClass = Support.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mental_health_screening_support",
            joinColumns = @JoinColumn(name = "screening_id"))
    @Column(name = "support_id")
    private Set<Support> supports;
    @Enumerated
    private YesNo referral;
    @JsonIgnore
    @ElementCollection(targetClass = zw.org.zvandiri.business.domain.util.Referral.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mental_health_screening_referral",
            joinColumns = @JoinColumn(name = "screening_id"))
    @Column(name = "referral_id")
    private Set<zw.org.zvandiri.business.domain.util.Referral> referrals;
    @Enumerated
    private YesNo diagnosis;
    @JsonIgnore
    @ElementCollection(targetClass = Diagnosis.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mental_health_screening_diagnosis",
            joinColumns = @JoinColumn(name = "screening_id"))
    @Column(name = "diagnosis_id")
    private Set<Diagnosis> diagnoses;
    private String otherDiagnosis;
    @Enumerated
    private YesNo intervention;
    @JsonIgnore
    @ElementCollection(targetClass = Intervention.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mental_health_screening_intervention",
            joinColumns = @JoinColumn(name = "screening_id"))
    @Column(name = "intervention_id")
    private Set<Intervention> interventions;
    private String otherIntervention;
    @Transient
    private String patientId;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateScreened;
    @Enumerated
    private YesNo referralComplete;
    @Transient
    private String currentElement;

    public MentalHealthScreening(Patient patient) {
        this.patient = patient;
    }

    public MentalHealthScreening() {
    }

    public MentalHealthScreening(MentalHealthScreeningDTO mentalHealthScreeningDTO){
        this.screenedForMentalHealth=mentalHealthScreeningDTO.getScreenedForMentalHealth();
        this.dateScreened=mentalHealthScreeningDTO.getDateScreened();
        this.risk=mentalHealthScreeningDTO.getRisk();
        this.identifiedRisks= mentalHealthScreeningDTO.getIdentifiedRisks();
        this.support=mentalHealthScreeningDTO.getSupport();
        this.supports= mentalHealthScreeningDTO.getSupports();
        this.referral=mentalHealthScreeningDTO.getReferral();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public YesNo getRisk() {
        return risk;
    }

    public void setRisk(YesNo risk) {
        this.risk = risk;
    }

    public YesNo getSupport() {
        return support;
    }

    public void setSupport(YesNo support) {
        this.support = support;
    }

    public YesNo getReferral() {
        return referral;
    }

    public void setReferral(YesNo referral) {
        this.referral = referral;
    }

    public YesNo getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(YesNo diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getOtherDiagnosis() {
        return otherDiagnosis;
    }

    public void setOtherDiagnosis(String otherDiagnosis) {
        this.otherDiagnosis = otherDiagnosis;
    }

    public YesNo getIntervention() {
        return intervention;
    }

    public void setIntervention(YesNo intervention) {
        this.intervention = intervention;
    }

    public String getOtherIntervention() {
        return otherIntervention;
    }

    public void setOtherIntervention(String otherIntervention) {
        this.otherIntervention = otherIntervention;
    }

    public Set<IdentifiedRisk> getIdentifiedRisks() {
        return identifiedRisks;
    }

    public void setIdentifiedRisks(Set<IdentifiedRisk> identifiedRisks) {
        this.identifiedRisks = identifiedRisks;
    }

    public Set<Support> getSupports() {
        return supports;
    }

    public void setSupports(Set<Support> supports) {
        this.supports = supports;
    }

    public Set<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(Set<Referral> referrals) {
        this.referrals = referrals;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(Set<Intervention> interventions) {
        this.interventions = interventions;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public YesNo getScreenedForMentalHealth() {
        return screenedForMentalHealth;
    }

    public void setScreenedForMentalHealth(YesNo screenedForMentalHealth) {
        this.screenedForMentalHealth = screenedForMentalHealth;
    }

    public MentalHealthScreeningType getScreening() {
        return screening;
    }

    public void setScreening(MentalHealthScreeningType screening) {
        this.screening = screening;
    }

    public Date getDateScreened() {
        return dateScreened;
    }

    public void setDateScreened(Date dateScreened) {
        this.dateScreened = dateScreened;
    }

    public YesNo getReferralComplete() {
        return referralComplete;
    }

    public void setReferralComplete(YesNo referralComplete) {
        this.referralComplete = referralComplete;
    }
    
    public String getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(String currentElement) {
        this.currentElement = currentElement;
    }
    
}
