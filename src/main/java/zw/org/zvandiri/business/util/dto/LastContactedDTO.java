package zw.org.zvandiri.business.util.dto;


import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
public class LastContactedDTO {

    private String oiNumber;
    private String fullName;
    private String gender;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateJoined;
    private String address;
    private String mobileNumber;
    private String facility;
    private String district;
    private String province;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date contactDate;
    private String followup;


    public String getOiNumber() {
        return oiNumber;
    }

    public void setOiNumber(String oiNumber) {
        this.oiNumber = oiNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

}
