/*
 * Copyright 2015 Judge Muzinda.
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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.UserLevel;
import zw.org.zvandiri.business.domain.util.UserType;


/**
 *
 * @author Judge Muzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
/*@Table(name = "user", indexes = {
		@Index(name = "user_user_name", columnList = "userName"),
		@Index(name = "user_user_province", columnList = "province"),
		@Index(name = "user_user_district", columnList = "district")
})*/

public class User extends BaseEntity {

    @Enumerated
    private Gender gender;
    private static final long serialVersionUID = 1L;
    private String password;
    @Transient
    private String confirmPassword;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;
    @Enumerated
    private UserType userType = UserType.ZVANDIRI_STAFF;
    @Enumerated
    private UserLevel userLevel;
    @ManyToOne
    @JoinColumn(name = "province")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "district")
    private District district;
    @Transient
    private String displayName;
    @Transient
    private String roles;
    @Transient
    private String currentElement;
    @Transient
    private String token;

    @Transient
    private  String facilityId;



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", nullable = false)})
    private Set<UserRole> userRoles = new HashSet<>();

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getDisplayName() {
        return lastName + " " + firstName;
    }
    
    public Set<UserRole> getDisplayUserRole() {
        return userRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        if(userRoles.isEmpty()){
            return "";
        }
        StringBuilder r = new StringBuilder();
        int pos = 1;
        for(UserRole role : userRoles){
            if(pos < userRoles.size()) {
                r.append(role.getName());
                r.append(" ,");
            }else{
                r.append(role.getName());
            }
            pos++;
        }
        return r.toString();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
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

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String toString()
    {
       return "FirstName:"+firstName+"\tLastName:"+lastName+"\tUsername:"+userName+"\tDistrict :"+district+"\tProvince :"+province;
    }
}
