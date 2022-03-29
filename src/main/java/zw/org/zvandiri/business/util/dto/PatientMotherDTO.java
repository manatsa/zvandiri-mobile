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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author jmuzinda
 */
public class PatientMotherDTO implements Serializable {
    private String id;
    private String mother;

    public PatientMotherDTO(String id, String mother) {
        this.id = id;
        this.mother = mother;
    }

    public PatientMotherDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }
    
    public static Set<PatientMotherDTO> getItems(List<Patient> patients){
        Set<PatientMotherDTO> items = new HashSet<>();
        for (Patient patient : patients){
            items.add(new PatientMotherDTO(patient.getId(), patient.getMother()));
        }
        return items;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.mother);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatientMotherDTO other = (PatientMotherDTO) obj;
        if (!Objects.equals(this.mother, other.mother)) {
            return false;
        }
        return true;
    }
    
    
}