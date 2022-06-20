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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
public class CatDetail extends BaseEntity {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name="primary_clinic")
    private Facility primaryClinic;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAsCat;
    private String email;
    @Transient
    private String userName;
    @Transient
    private String password;
    @Transient
    private String confirmPassword;
    @Transient
    private Province province;
    @Transient
    private District district;
    @Transient
    private String currentElement;
    @Transient
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date vlDate;
    @Transient
    private YesNo vlResultTaken;
    @Transient
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date regimenDate;
    @Transient
    private YesNo sexuallyActive;
    @Transient
    private YesNo tbScreening;
    @Transient
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date tbScreeningDate;
    @Transient
    private YesNo cervicalCancerScreening;
    @Transient
    private String outcome;
    @Transient
    private String receivedTreatment;
    @Transient 
    private String treatmentOutcome;
    @Transient
    private String haveChildren;
    @Transient
    private String currentStatus;
    @Transient
    private String bicycle;
    @Transient
    private String phone;
    @Transient
    private String phoneMode;
    
    public CatDetail() {
    }

    public CatDetail(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Facility getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(Facility primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public Date getDateAsCat() {
        return dateAsCat;
    }

    public void setDateAsCat(Date dateAsCat) {
        this.dateAsCat = dateAsCat;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(String currentElement) {
        this.currentElement = currentElement;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
    public String getGraduationDate() {
        return DateUtil.formatDate(DateUtil.getDateDiffYear(24, patient.getDateOfBirth()));
    }

    public Date getVlDate() {
        return vlDate;
    }

    public void setVlDate(Date vlDate) {
        this.vlDate = vlDate;
    }

    public YesNo getVlResultTaken() {
        return vlResultTaken;
    }

    public void setVlResultTaken(YesNo vlResultTaken) {
        this.vlResultTaken = vlResultTaken;
    }

    public Date getRegimenDate() {
        return regimenDate;
    }

    public void setRegimenDate(Date regimenDate) {
        this.regimenDate = regimenDate;
    }

    public YesNo getSexuallyActive() {
        return sexuallyActive;
    }

    public void setSexuallyActive(YesNo sexuallyActive) {
        this.sexuallyActive = sexuallyActive;
    }

    public YesNo getTbScreening() {
        return tbScreening;
    }

    public void setTbScreening(YesNo tbScreening) {
        this.tbScreening = tbScreening;
    }

    public Date getTbScreeningDate() {
        return tbScreeningDate;
    }

    public void setTbScreeningDate(Date tbScreeningDate) {
        this.tbScreeningDate = tbScreeningDate;
    }

    public YesNo getCervicalCancerScreening() {
        return cervicalCancerScreening;
    }

    public void setCervicalCancerScreening(YesNo cervicalCancerScreening) {
        this.cervicalCancerScreening = cervicalCancerScreening;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getReceivedTreatment() {
        return receivedTreatment;
    }

    public void setReceivedTreatment(String receivedTreatment) {
        this.receivedTreatment = receivedTreatment;
    }

    public String getTreatmentOutcome() {
        return treatmentOutcome;
    }

    public void setTreatmentOutcome(String treatmentOutcome) {
        this.treatmentOutcome = treatmentOutcome;
    }

    public String getHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(String haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getBicycle() {
        return bicycle;
    }

    public void setBicycle(String bicycle) {
        this.bicycle = bicycle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneMode() {
        return phoneMode;
    }

    public void setPhoneMode(String phoneMode) {
        this.phoneMode = phoneMode;
    }


}
