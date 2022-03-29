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
package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author jmuzinda
 */
public class PatientSearchDTO implements Serializable {

    private final String name;
    private final String gender;
    private final Integer age;
    private final String dateJoined;
    private final String district;
    private final String primaryClinic;
    private final String id;
    private final PatientChangeEvent status;
    private final String patientNumber;

    public PatientSearchDTO(String name, String gender, Integer age, String dateJoined, String district, String primaryClinic, String id, PatientChangeEvent status, String patientNumber) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateJoined = dateJoined;
        this.district = district;
        this.primaryClinic = primaryClinic;
        this.id = id;
        this.status = status;
        this.patientNumber = patientNumber;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public String getDistrict() {
        return district;
    }

    public String getPrimaryClinic() {
        return primaryClinic;
    }

    public String getId() {
        return id;
    }
    
    public Boolean getActive(){
        if(status == null || status.equals(PatientChangeEvent.ACTIVE)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public static List<PatientSearchDTO> getInstance(List<Patient> patients) {
        List<PatientSearchDTO> items = new ArrayList<>();
        for(Patient patient : patients){
            items.add(new PatientSearchDTO(
                patient.getName(),
                patient.getGender().getName(),
                patient.getAge(),
                patient.getDateJoined() == null ? "" : DateUtil.getStringFromDate(patient.getDateJoined()),
                patient.getPrimaryClinic().getDistrict().getName(),
                patient.getPrimaryClinic().getName(),
                patient.getId(),
                patient.getStatus(),
                patient.getPatientNumber()
            ));
        }
        return items;
    }
}
