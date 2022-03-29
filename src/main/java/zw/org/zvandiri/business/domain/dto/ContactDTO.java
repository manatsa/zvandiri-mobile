package zw.org.zvandiri.business.domain.dto;

import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Location;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Position;
import zw.org.zvandiri.business.domain.util.CareLevel;
import zw.org.zvandiri.business.domain.util.ContactPhoneOption;
import zw.org.zvandiri.business.domain.util.FollowUp;
import zw.org.zvandiri.business.domain.util.YesNo;

import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */

public class ContactDTO {

    private String patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date contactDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date nextClinicAppointmentDate;
    @Enumerated
    private CareLevel careLevel;
    @Enumerated
    private FollowUp careLevelAfterAssessment;
    @Enumerated
    private FollowUp systemDeterminedCareLevel;
    private String location;
    @Enumerated
    private ContactPhoneOption contactPhoneOption;
    private Integer numberOfSms;
    private String position;
    //@ElementCollection
    private Set<String> clinicalAssessments = new HashSet<>();
    //@ElementCollection
    private Set<String> nonClinicalAssessments = new HashSet<>();
    //@ElementCollection
    private Set<String> serviceOffereds = new HashSet<>();
    private Set<String> servicereferreds = new HashSet<>();
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastClinicAppointmentDate;
    private YesNo attendedClinicAppointment;
    @Enumerated
    private YesNo eac;
    private Integer eac1;
    private Integer eac2;
    private Integer eac3;
    private String contactMadeBy;
    private String supportGroupTheme;
    private String userID;



    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public Date getNextClinicAppointmentDate() {
        return nextClinicAppointmentDate;
    }

    public void setNextClinicAppointmentDate(Date nextClinicAppointmentDate) {
        this.nextClinicAppointmentDate = nextClinicAppointmentDate;
    }

    public CareLevel getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(CareLevel careLevel) {
        this.careLevel = careLevel;
    }

    public FollowUp getCareLevelAfterAssessment() {
        return careLevelAfterAssessment;
    }

    public void setCareLevelAfterAssessment(FollowUp careLevelAfterAssessment) {
        this.careLevelAfterAssessment = careLevelAfterAssessment;
    }

    public FollowUp getSystemDeterminedCareLevel() {
        return systemDeterminedCareLevel;
    }

    public void setSystemDeterminedCareLevel(FollowUp systemDeterminedCareLevel) {
        this.systemDeterminedCareLevel = systemDeterminedCareLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<String> getClinicalAssessments() {
        return clinicalAssessments;
    }

    public void setClinicalAssessments(Set<String> clinicalAssessments) {
        this.clinicalAssessments = clinicalAssessments;
    }

    public Set<String> getNonClinicalAssessments() {
        return nonClinicalAssessments;
    }

    public void setNonClinicalAssessments(Set<String> nonClinicalAssessments) {
        this.nonClinicalAssessments = nonClinicalAssessments;
    }

    public Set<String> getServiceOffereds() {
        return serviceOffereds;
    }

    public void setServiceOffereds(Set<String> serviceOffereds) {
        this.serviceOffereds = serviceOffereds;
    }

    public Set<String> getServicereferreds() {
        return servicereferreds;
    }

    public void setServicereferreds(Set<String> servicereferreds) {
        this.servicereferreds = servicereferreds;
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

    public String getSupportGroupTheme() {
        return supportGroupTheme;
    }

    public void setSupportGroupTheme(String supportGroupTheme) {
        this.supportGroupTheme = supportGroupTheme;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

}
