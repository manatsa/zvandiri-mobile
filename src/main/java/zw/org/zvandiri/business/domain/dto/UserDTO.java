package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.User;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class UserDTO implements Serializable {
    private String id;
    private String userName;
    private String userLevel;
    private String facilityID;
    private String districtID;
    private String provinceID;
    private String provinceName;
    private String token;

    public UserDTO() {
    }

    public UserDTO(String id, String userName, String userLevel, String facilityID, String districtID, String provinceID, String provinceName, String token) {
        this.id = id;
        this.userName = userName;
        this.userLevel = userLevel;
        this.facilityID = facilityID;
        this.districtID = districtID;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.token = token;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userLevel = user.getUserLevel().getName();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", facilityID='" + facilityID + '\'' +
                ", districtID='" + districtID + '\'' +
                ", provinceID='" + provinceID + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
