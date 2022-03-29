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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jmuzinda
 */
public class PatientExitingProgramDistrictPivotDTO extends BasePatientExitingProgramPivotDTO {

    public PatientExitingProgramDistrictPivotDTO(String province, String ageGroup, String gender, Integer age, String status, String education, String educationLevel, String month) {
        super(province, ageGroup, gender, age, status, education, educationLevel, month);
    }

    @Override
    @JsonProperty(value = "Facility")
    public String getProvince() {
        return super.getProvince();
    }
    
}
