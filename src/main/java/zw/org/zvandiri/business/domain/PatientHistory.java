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

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "patient_history_patient", columnList = "patient"),
		@Index(name = "patient_history_status", columnList = "status"),
		@Index(name = "patient_history_primary_clinic", columnList = "primary_clinic"),
		@Index(name = "patient_history_support_group", columnList = "support_group"),
		@Index(name = "patient_history_created_by", columnList = "created_by")
})*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientHistory extends GenericPatient {
    
    @ManyToOne
    @JoinColumn(name="patient", updatable = false, insertable = false)
    private Patient patient;

    public PatientHistory() {
    }

    public PatientHistory(Patient patient) {
        this.patient = patient;
        this.setActive(patient.getActive());
        this.setAddress(patient.getAddress());
        this.setAddress1(patient.getAddress1());
        this.setCat(patient.getCat());
        this.setConsentForm(patient.getConsentForm());
        this.setConsentToMHealth(patient.getConsentToMHealth());
        this.setCreatedBy(patient.getCreatedBy());
        this.setDateCreated(patient.getDateCreated());
        this.setDateJoined(patient.getDateJoined());
        this.setDateModified(patient.getDateModified());
        this.setDateOfBirth(patient.getDateOfBirth());
        this.setDateTested(patient.getDateTested());
        this.setDeleted(patient.getDeleted());
        this.setDisability(patient.getDisability());
        this.setEducation(patient.getEducation());
        this.setEducationLevel(patient.getEducationLevel());
        this.setFirstName(patient.getFirstName());
        this.setGender(patient.getGender());
        this.setHivStatusKnown(patient.getHivStatusKnown());
        this.setLastName(patient.getLastName());
        this.setMhealthForm(patient.getMhealthForm());
//        this.setMiddleName(patient.getMiddleName());
        this.setMobileNumber(patient.getMobileNumber());
        this.setMobileOwner(patient.getMobileOwner());
        this.setMobileOwnerRelation(patient.getMobileOwnerRelation());
        this.setModifiedBy(patient.getModifiedBy());
        this.setOwnSecondaryMobile(patient.getOwnSecondaryMobile());
        this.setOwnerName(patient.getOwnerName());
        this.setPfirstName(patient.getPfirstName());
        this.setPgender(patient.getGender());
        this.setPhoto(patient.getPhoto());
        this.setPlastName(patient.getPlastName());
        this.setPmobileNumber(patient.getPmobileNumber());
        this.setPrimaryClinic(patient.getPrimaryClinic());
        this.setReferer(patient.getReferer());
        this.setRelationship(patient.getRelationship());
        this.setSecondaryMobileNumber(patient.getSecondaryMobileNumber());
        this.setSecondaryMobileOwnerName(patient.getSecondaryMobileOwnerName());
        this.setSecondaryMobileownerRelation(patient.getSecondaryMobileownerRelation());
        this.setSupportGroup(patient.getSupportGroup());
        this.setTransmissionMode(patient.getTransmissionMode());
        this.setYoungMumGroup(patient.getYoungMumGroup());
        this.sethIVDisclosureLocation(patient.gethIVDisclosureLocation());
        
    }
    

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
