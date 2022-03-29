package zw.org.zvandiri.business.util.dto;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.ClientType;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.service.PatientService;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author manatsachinyeruse@gmail.com
 */

//@JsonIgnoreProperties(value = { "uuid", "createdBy", "modifiedBy", "dateCreated","dateModified","version","deleted" })
public class MobilePatientDTO implements Serializable {

    private String name;
    private String id;
    private Date dateOfBirth;
    private Gender gender;
    private PatientChangeEvent status;
    private Boolean active;
    private String primaryClinicId;
    private String facility;
    private String district;
    private String province;
    private Integer vlResult;
    private Integer enhancedStatus;
    private ClientType clientType;

    @Autowired
    PatientService patientService;

    public MobilePatientDTO() {
    }

    public MobilePatientDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public MobilePatientDTO(String patientId) {
        Patient patient=patientService.get(patientId);
        this.name = patient.getName();
        this.id = patient.getId();
        this.dateOfBirth = patient.getDateOfBirth();
        this.gender = patient.getGender();
        this.status = patient.getStatus();
        this.active = patient.getActive();
        this.primaryClinicId=patient.getPrimaryClinic().getId();
        this.clientType=patient.getClientType();
        this.vlResult=patient.getViralLoad();
        this.enhancedStatus=patient.getEnhancedStatus();
        this.facility=patient.getPrimaryClinic().getName();
        this.district=patient.getPrimaryClinic().getDistrict().getName();
        this.province=patient.getPrimaryClinic().getDistrict().getProvince().getName();
    }

    public MobilePatientDTO(Patient patient) {
        this.name = patient.getName();
        this.id = patient.getId();
        this.dateOfBirth = patient.getDateOfBirth();
        this.gender = patient.getGender();
        this.status = patient.getStatus();
        this.active = patient.getActive();
        this.primaryClinicId=patient.getPrimaryClinic().getId();
        this.clientType=patient.getClientType();
        this.vlResult=patient.getViralLoad();
        this.enhancedStatus=patient.getEnhancedStatus();
        this.facility=patient.getPrimaryClinic().getName();
        this.district=patient.getPrimaryClinic().getDistrict().getName();
        this.province=patient.getPrimaryClinic().getDistrict().getProvince().getName();
    }

    public MobilePatientDTO(String name, String id, Date dateOfBirth, Gender gender, PatientChangeEvent status, Boolean active, String facilityId) {
        this.name = name;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.status = status;
        this.active = active;
        this.primaryClinicId=facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PatientChangeEvent getStatus() {
        return status;
    }

    public void setStatus(PatientChangeEvent status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPrimaryClinicId() {
        return primaryClinicId;
    }

    public void setPrimaryClinicId(String primaryClinicId) {
        this.primaryClinicId = primaryClinicId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getVlResult() {
        return vlResult;
    }

    public void setVlResult(Integer vlResult) {
        this.vlResult = vlResult;
    }

    public Integer getEnhancedStatus() {
        return enhancedStatus;
    }

    public void setEnhancedStatus(Integer enhancedStatus) {
        this.enhancedStatus = enhancedStatus;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }



    @Override
    public String toString() {
        return "MobilePatientDTO{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", status=" + status +
                ", active=" + active +
                ", primaryClinicId='" + primaryClinicId + '\'' +
                '}';
    }
}