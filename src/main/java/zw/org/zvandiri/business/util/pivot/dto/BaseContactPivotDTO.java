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
public class BaseContactPivotDTO implements Serializable { 
    
    @JsonProperty(value = "Month")
    private final String month;
    @JsonProperty(value = "Care Level")
    private final String careLevel;
    @JsonProperty(value = "Follow Up")
    private final String followUp;
    @JsonProperty(value = "Age Group")
    private final String ageGroup;
    @JsonProperty(value = "Gender")
    private final String gender;
    private final String province;

    public BaseContactPivotDTO(String month, String careLevel, String followUp, String ageGroup, String gender, String province) {
        this.month = month;
        this.careLevel = careLevel;
        this.followUp = followUp;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.province = province;
    }

    public String getMonth() {
        return month;
    }

    public String getCareLevel() {
        return careLevel;
    }

    public String getFollowUp() {
        return followUp;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public String getProvince() {
        return province;
    }
    
}