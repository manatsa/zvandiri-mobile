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
package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author Judge Muzida
 */
public class PatientFiles implements Serializable {
    
    private String photo;
    private String consentForm;
    private String mhealthForm;
    private Patient patient;

    public PatientFiles() {
    }

    public PatientFiles(Patient patient) {
        this.patient = patient;
    }

    public PatientFiles(String photo, String consentForm, String mhealthForm, Patient patient) {
        this.photo = photo;
        this.consentForm = consentForm;
        this.mhealthForm = mhealthForm;
        this.patient = patient;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getConsentForm() {
        return consentForm;
    }

    public void setConsentForm(String consentForm) {
        this.consentForm = consentForm;
    }

    public String getMhealthForm() {
        return mhealthForm;
    }

    public void setMhealthForm(String mhealthForm) {
        this.mhealthForm = mhealthForm;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Patient getInstance(PatientFiles dto){
        Patient p = dto.getPatient();
        p.setPhoto(dto.getPhoto());
        p.setMhealthForm(dto.getMhealthForm());
        p.setConsentForm(dto.getMhealthForm());
        return p;
    }
}