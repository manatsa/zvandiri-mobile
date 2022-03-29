/*
 * Copyright 2017 Judge Muzinda.
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
package zw.org.zvandiri.business.util.pivot.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Judge Muzinda
 */
public class BasePatientPivotDTO implements Serializable {
    
    private String province;
    @JsonProperty(value = "Age Group")
    private final String ageGroup;
    @JsonProperty(value = "Gender")
    private final String gender;
    @JsonProperty(value = "Age")
    private final Integer age;
    @JsonProperty(value = "Status")
    private final String status;
    @JsonProperty(value = "Hiv Status Known")
    private final String hivStatusKnown;
    @JsonProperty(value = "Has Disability")
    private final String hasDisability;
    @JsonProperty(value = "Transmission Mode")
    private final String transmissionMode;
    @JsonProperty(value = "Education")
    private final String education;
    @JsonProperty(value = "Highest Education Level")
    private final String educationLevel;
    @JsonProperty(value = "Referer")
    private final String referer;
    @JsonProperty(value = "CATS Member")
    private final String cats;
    @JsonProperty(value = "Have Children")
    private final String haveChildren;
    @JsonProperty(value = "On TB Treatment")
    private final String onTbTreatment;

    public BasePatientPivotDTO(String province, String ageGroup, String gender, Integer age, String status, String hivStatusKnown, String hasDisability, String transmissionMode, String education, String educationLevel, String referer, String cats, String haveChildren, String onTbTreatment) {
        this.province = province;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.hivStatusKnown = hivStatusKnown;
        this.hasDisability = hasDisability;
        this.transmissionMode = transmissionMode;
        this.education = education;
        this.educationLevel = educationLevel;
        this.referer = referer;
        this.cats = cats;
        this.haveChildren = haveChildren;
        this.onTbTreatment = onTbTreatment;
    }

    public String getCats() {
        return cats;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public String getHivStatusKnown() {
        return hivStatusKnown;
    }

    public String getHasDisability() {
        return hasDisability;
    }

    public String getTransmissionMode() {
        return transmissionMode;
    }

    public String getEducation() {
        return education;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getReferer() {
        return referer;
    }

    public String getHaveChildren() {
        return haveChildren;
    }

    public String getOnTbTreatment() {
        return onTbTreatment;
    }

    
}