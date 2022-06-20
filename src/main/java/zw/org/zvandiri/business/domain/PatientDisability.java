/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.util.DisabilitySeverity;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author jmuzinda
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "patient_disability_patient", columnList = "patient"),
		@Index(name = "patient_disability_disability_category", columnList = "disability_category")
})*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDisability extends BaseEntity {
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name="disability_category")
    private DisabilityCategory disabilityCategory;
    @Enumerated
    private DisabilitySeverity severity;
    @Enumerated
    private YesNo screened;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateScreened;

    public PatientDisability() {
    }

    public PatientDisability(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public DisabilityCategory getDisabilityCategory() {
        return disabilityCategory;
    }

    public void setDisabilityCategory(DisabilityCategory disabilityCategory) {
        this.disabilityCategory = disabilityCategory;
    }

    public DisabilitySeverity getSeverity() {
        return severity;
    }

    public void setSeverity(DisabilitySeverity severity) {
        this.severity = severity;
    }

    public YesNo getScreened() {
        return screened;
    }

    public void setScreened(YesNo screened) {
        this.screened = screened;
    }

    public Date getDateScreened() {
        return dateScreened;
    }

    public void setDateScreened(Date dateScreened) {
        this.dateScreened = dateScreened;
    }
    
    
}
