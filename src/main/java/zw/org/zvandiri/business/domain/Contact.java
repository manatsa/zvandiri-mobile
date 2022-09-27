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

import java.util.*;
import javax.annotation.Resource;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.dto.ContactDTO;
import zw.org.zvandiri.business.domain.util.CareLevel;
import zw.org.zvandiri.business.domain.util.ContactPhoneOption;
import zw.org.zvandiri.business.domain.util.FollowUp;
import zw.org.zvandiri.business.domain.util.UserLevel;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.repo.PatientRepo;
import zw.org.zvandiri.business.service.*;

/**
 *
 * @author Judge Muzinda
 */
@Entity
//@EqualsAndHashCode(exclude = "nameAttributeInThisClassWithOneToMany")
@Table(indexes = {
		@Index(name = "contact_patient", columnList = "patient"),
		@Index(name = "contact_contact_date", columnList = "contactDate"),
		@Index(name = "contact_location", columnList = "location"),
		@Index(name = "contact_position", columnList = "position")
}
/*,
        uniqueConstraints={
        @UniqueConstraint(columnNames = {"patient", "contact_date"})
}*/
)
public class Contact extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @Fetch(value= FetchMode.SELECT)
    @JoinColumn(name="patient")
    private Patient patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date contactDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date nextClinicAppointmentDate;
    @Enumerated
    private CareLevel careLevel;
    @Enumerated
    @Column(name = "follow_up")
    private FollowUp careLevelAfterAssessment;
    @Enumerated
    private FollowUp systemDeterminedCareLevel;
    @ManyToOne
    @JoinColumn(name="location")
    private Location location;
    @Enumerated
    private ContactPhoneOption contactPhoneOption;
    private Integer numberOfSms;
    @ManyToOne
    @JoinColumn(name="position")
    private Position position;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "contact_lab_service", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "lab_service_id", nullable = false)})
    private Set<LabTask> labTasks = new HashSet<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "contact_clinical_assessment", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "assessment_id", nullable = false)})
    private Set<Assessment> clinicalAssessments = new HashSet<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "contact_non_clinical_assessment", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "assessment_id", nullable = false)})
    private Set<Assessment> nonClinicalAssessments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "contact_service_offered", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "service_offered_id", nullable = false)})
    private Set<ServiceOffered> serviceOffereds = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "contact_service_offered", joinColumns = {
            @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "service_offered_id", nullable = false)})
    private Set<ServicesReferred> servicereferreds = new HashSet<>();

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastClinicAppointmentDate;
    private YesNo attendedClinicAppointment;
    @Transient
    private InvestigationTest viralLoad;
    @Transient
    private InvestigationTest cd4Count;
    @Transient
    private UserLevel userLevel;
    @Enumerated
    private YesNo eac;
    private Integer eac1;
    private Integer eac2;
    private Integer eac3;
    private String contactMadeBy;
    private String supportGroupTheme;



    public Contact(Patient patient) {
        this.patient = patient;
    }

    public Contact() {
    }

    public Contact(ContactDTO dto){



        this.contactDate=dto.getContactDate();
        this.careLevel=dto.getCareLevel();
        this.careLevelAfterAssessment=dto.getCareLevelAfterAssessment();
        this.contactPhoneOption=dto.getContactPhoneOption();
        this.contactMadeBy=dto.getContactMadeBy();
        this.eac=dto.getEac();
        this.eac1=dto.getEac1();
        this.eac2=dto.getEac2();
        this.eac3=dto.getEac3();
        this.lastClinicAppointmentDate=dto.getLastClinicAppointmentDate();
        this.nextClinicAppointmentDate=dto.getNextClinicAppointmentDate();
        this.supportGroupTheme=dto.getSupportGroupTheme();
        this.numberOfSms=dto.getNumberOfSms();
        this.attendedClinicAppointment=dto.getAttendedClinicAppointment();



    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public CareLevel getCareLevel() {
        return careLevel;
    }

    public FollowUp getCareLevelAfterAssessment() {
        return careLevelAfterAssessment;
    }

    public void setCareLevelAfterAssessment(FollowUp careLevelAfterAssessment) {
        this.careLevelAfterAssessment = careLevelAfterAssessment;
    }

    public void setCareLevel(CareLevel careLevel) {
        this.careLevel = careLevel;
    }

    public Date getNextClinicAppointmentDate() {
        return nextClinicAppointmentDate;
    }

    public void setNextClinicAppointmentDate(Date nextClinicAppointmentDate) {
        this.nextClinicAppointmentDate = nextClinicAppointmentDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public Set<Assessment> getClinicalAssessments() {
        return clinicalAssessments;
    }

    public void setClinicalAssessments(Set<Assessment> clinicalAssessments) {
        this.clinicalAssessments = clinicalAssessments;
    }

    public Set<Assessment> getNonClinicalAssessments() {
        return nonClinicalAssessments;
    }

    public void setNonClinicalAssessments(Set<Assessment> nonClinicalAssessments) {
        this.nonClinicalAssessments = nonClinicalAssessments;
    }


    public Date getLastClinicAppointmentDate() {
        return lastClinicAppointmentDate;
    }

    public void setLastClinicAppointmentDate(Date lastClinicAppointmentDate) {
        this.lastClinicAppointmentDate = lastClinicAppointmentDate;
    }

    public YesNo getAttendedClinicAppointment() {
        return attendedClinicAppointment;
    }

    public void setAttendedClinicAppointment(YesNo attendedClinicAppointment) {
        this.attendedClinicAppointment = attendedClinicAppointment;
    }

    public ContactPhoneOption getContactPhoneOption() {
        return contactPhoneOption;
    }

    public void setContactPhoneOption(ContactPhoneOption contactPhoneOption) {
        this.contactPhoneOption = contactPhoneOption;
    }

    public Integer getNumberOfSms() {
        return numberOfSms;
    }

    public void setNumberOfSms(Integer numberOfSms) {
        this.numberOfSms = numberOfSms;
    }

    public Set<ServiceOffered> getServiceOffereds() {
        return serviceOffereds;
    }

    public void setServiceOffereds(Set<ServiceOffered> serviceOffereds) {
        this.serviceOffereds = serviceOffereds;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public InvestigationTest getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(InvestigationTest viralLoad) {
        this.viralLoad = viralLoad;
    }

    public InvestigationTest getCd4Count() {
        return cd4Count;
    }

    public void setCd4Count(InvestigationTest cd4Count) {
        this.cd4Count = cd4Count;
    }

    public Set<LabTask> getLabTasks() {
        return labTasks;
    }

    public void setLabTasks(Set<LabTask> labTasks) {
        this.labTasks = labTasks;
    }

    public YesNo getEac() {
        return eac;
    }

    public void setEac(YesNo eac) {
        this.eac = eac;
    }

    public Integer getEac1() {
        return eac1;
    }

    public void setEac1(Integer eac1) {
        this.eac1 = eac1;
    }

    public Integer getEac2() {
        return eac2;
    }

    public void setEac2(Integer eac2) {
        this.eac2 = eac2;
    }

    public Integer getEac3() {
        return eac3;
    }

    public void setEac3(Integer eac3) {
        this.eac3 = eac3;
    }

    public String getContactMadeBy() {
        return contactMadeBy;
    }

    public void setContactMadeBy(String contactMadeBy) {
        this.contactMadeBy = contactMadeBy;
    }

    public FollowUp getSystemDeterminedCareLevel() {
        return systemDeterminedCareLevel;
    }

    public void setSystemDeterminedCareLevel(FollowUp systemDeterminedCareLevel) {
        this.systemDeterminedCareLevel = systemDeterminedCareLevel;
    }

    public Set<ServicesReferred> getServicereferreds() {
        return servicereferreds;
    }

    public void setServicereferreds(Set<ServicesReferred> servicereferreds) {
        this.servicereferreds = servicereferreds;
    }

    public String getSupportGroupTheme() {
        return supportGroupTheme;
    }

    public void setSupportGroupTheme(String supoortGroupTheme) {
        this.supportGroupTheme = supoortGroupTheme;
    }

    @Override
    public String toString() {
        return super.toString().concat("Contact{" + "patient=" + patient + ", contactDate=" + contactDate + ", nextClinicAppointmentDate=" +
                nextClinicAppointmentDate + ", careLevel=" + careLevel + ", location=" + location + ", contactPhoneOption=" +
                contactPhoneOption + ", numberOfSms=" + numberOfSms + ", position=" + position   +
                ", clinicalAssessments=" + clinicalAssessments + ", nonClinicalAssessments=" + nonClinicalAssessments + ", serviceOffereds=" + serviceOffereds
                + ", lastClinicAppointmentDate=" + lastClinicAppointmentDate + ", attendedClinicAppointment=" + attendedClinicAppointment +
                ", careLevelAfterAssessment ="+careLevelAfterAssessment+", EAC="+eac+", eac1="+eac1+",eac2="+eac2+",eac3="+eac3+",contactMadeBy="+contactMadeBy+
                ", SystemDeterminedCareLevel = "+systemDeterminedCareLevel+"}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return patient.equals(contact.patient) && contactDate.equals(contact.contactDate) && location.equals(contact.getLocation()) && contactMadeBy.equals(contact.contactMadeBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patient, contactDate,location, contactMadeBy);
    }
}
