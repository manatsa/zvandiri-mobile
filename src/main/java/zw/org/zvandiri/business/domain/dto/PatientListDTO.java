package zw.org.zvandiri.business.domain.dto;


import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.Gender;

import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class PatientListDTO implements Serializable {
    private String id;
    private Integer enhancedStatus;
    private String firstName;
    private String lastName;
    @Enumerated
    private Gender gender;
    private String mobileNumber;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String facility;
    private Integer age;
    private String district;
    private String facilityID;

    public PatientListDTO() {
    }

    public PatientListDTO(Patient patient){
        this.id=patient.getId();
        this.enhancedStatus = patient.getEnhancedStatus();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.gender = patient.getGender();
        this.mobileNumber = patient.getMobileNumber();
        this.dateOfBirth = patient.getDateOfBirth();
        this.facility = patient.getPrimaryClinic().getName();
        this.district=patient.getPrimaryClinic().getDistrict().getName();
        this.age = patient.getAge();
        this.facilityID=patient.getPrimaryClinic().getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEnhancedStatus() {
        return enhancedStatus;
    }

    public void setEnhancedStatus(Integer enhancedStatus) {
        this.enhancedStatus = enhancedStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }
}
