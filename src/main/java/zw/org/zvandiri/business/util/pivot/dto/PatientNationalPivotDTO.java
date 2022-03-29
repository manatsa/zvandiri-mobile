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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Judge Muzinda
 */
public class PatientNationalPivotDTO extends BasePatientPivotDTO {

    public PatientNationalPivotDTO(String province, String ageGroup, String gender, Integer age, String status, String hivStatusKnown, String hasDisability, String transmissionMode, String education, String educationLevel, String referer, String cats, String haveChildren, String onTbTreatment) {
        super(province, ageGroup, gender, age, status, hivStatusKnown, hasDisability, transmissionMode, education, educationLevel, referer, cats, haveChildren, onTbTreatment);
    }
    
    @Override
    @JsonProperty(value = "Province")
    public String getProvince() {
        return super.getProvince();
    }
}