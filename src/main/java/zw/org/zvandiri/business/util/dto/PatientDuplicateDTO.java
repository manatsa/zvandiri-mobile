/*
 * Copyright 2018 jmuzinda.
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
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.Gender;

/**
 *
 * @author jmuzinda
 */
public class PatientDuplicateDTO implements Serializable {
    
    private final String id;
    private final String fullName;
    private final Integer age;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final Date dateOfBirth;
    private final Gender gender;
    private final String region;
    private final String district;
    private final String primaryFacility;
    public Set<PatientDuplicateDTO> matches = new HashSet<>();
    

    private PatientDuplicateDTO(String id, String fullName, Integer age, Date dateOfBirth, Gender gender, String region, String district, String primaryFacility) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.region = region;
        this.district = district;
        this.primaryFacility = primaryFacility;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public String getPrimaryFacility() {
        return primaryFacility;
    }
    
    public Set<PatientDuplicateDTO> getMatches() {
        return matches;
    }

    public void setMatches(Set<PatientDuplicateDTO> matches) {
        this.matches = matches;
    }
    
    public int getCount () {
        return matches.size();
    }
    
    public static PatientDuplicateDTO getInstance (Patient patient) {
        
        return new PatientDuplicateDTO(patient.getId(), patient.getName(), patient.getAge(), 
                patient.getDateOfBirth(), patient.getGender(), patient.getPrimaryClinic().getDistrict().getProvince().getName(), patient.getPrimaryClinic().getDistrict().getName(), patient.getPrimaryClinic().getName());
    }
    
    public static Set<PatientDuplicateDTO> getRelatedPatients(Set<Patient> patients) {
        Set<PatientDuplicateDTO> possibleDuplicates = new HashSet<>();
        for (Patient p : patients) {
            possibleDuplicates.add(getInstance(p));
        }
        return possibleDuplicates;
    }
    
    @Override
    public int hashCode () {
        return 7 * fullName.hashCode() * age.hashCode() * dateOfBirth.hashCode() *
                district.hashCode() * primaryFacility.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatientDuplicateDTO other = (PatientDuplicateDTO) obj;
        if (!this.fullName.equals(other.fullName)) {
            return false;
        }
        if (!this.age.equals(other.age)) {
            return false;
        }
        if (!this.dateOfBirth.equals(other.dateOfBirth)) {
            return false;
        }
        if (!this.district.equals(other.district)) {
            return false;
        }
        return this.primaryFacility.equals(other.primaryFacility);
    }
}
