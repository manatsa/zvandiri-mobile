/*
 * Copyright 2017 jmuzinda.
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
 * @author jmuzinda
 */
public class BasePatientExitingProgramPivotDTO implements Serializable {
    
    private String province;
    @JsonProperty(value = "Age Group")
    private final String ageGroup;
    @JsonProperty(value = "Gender")
    private final String gender;
    @JsonProperty(value = "Age")
    private final Integer age;
    @JsonProperty(value = "Status")
    private final String status;
    @JsonProperty(value = "Education")
    private final String education;
    @JsonProperty(value = "Highest Education Level")
    private final String educationLevel;
    @JsonProperty(value = "Month")
    private final String month;

    public BasePatientExitingProgramPivotDTO(String province, String ageGroup, String gender, Integer age, String status, String education, String educationLevel, String month) {
        this.province = province;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.education = education;
        this.educationLevel = educationLevel;
        this.month = month;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getEducation() {
        return education;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getMonth() {
        return month;
    }
}
