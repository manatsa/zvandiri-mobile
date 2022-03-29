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

package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import java.util.Date;

import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.ClientType;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;

/**
 *
 * @author Judge Muzinda
 */
public class NameIdDTO implements Serializable {

    private String name;
    private String id;
    private Date dateOfBirth;
    private Gender gender;
    private PatientChangeEvent status;
    private Boolean active;
    private String primaryClinicId;



    public NameIdDTO() {
    }

    public NameIdDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public NameIdDTO(String name, String id, Date dateOfBirth, Gender gender, PatientChangeEvent status, Boolean active, String facilityId) {
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


    @Override
    public String toString() {
        return "NameIdDTO{" +
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