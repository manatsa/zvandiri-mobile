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

import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.util.DateUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */
public class CadreSearchDTO implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String type;
    private final String district;
    private final String primaryClinic;
    private final String id;
    private final PatientChangeEvent status;
    private final String dob;
    private final Province province;


    public CadreSearchDTO(String firstName, String lastName, String gender, String dob, String type, String district,
                          String primaryClinic, String id, PatientChangeEvent status, Province province) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender = gender;
        this.type=type;
        this.district = district;
        this.primaryClinic = primaryClinic;
        this.id = id;
        this.status = status;
        this.dob=dob;
        this.province=province;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
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

    public String getType() {
        return type;
    }

    public PatientChangeEvent getStatus() {
        return status;
    }

    public Province getProvince() {
        return province;
    }

    public static List<CadreSearchDTO> getInstance(List<Cadre> patients) {
        List<CadreSearchDTO> items = new ArrayList<>();
        for(Cadre cadre : patients){
            items.add(new CadreSearchDTO(
                    cadre.getFirstName(),
                    cadre.getLastName(),
                    cadre.getGender().getName(),
                    cadre.getDateOfBirth() == null ? "" : DateUtil.getStringFromDate(cadre.getDateOfBirth()),
                    cadre.getCaderType().getName(),
                    cadre.getPrimaryClinic().getDistrict().getName(),
                    cadre.getPrimaryClinic().getName(),
                    cadre.getId(),
                    cadre.getStatus(),
                    cadre.getProvince()

            ));
        }
        return items;
    }
}
