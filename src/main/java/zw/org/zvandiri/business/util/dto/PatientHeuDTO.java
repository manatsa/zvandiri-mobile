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
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author jmuzinda
 */
public class PatientHeuDTO implements Serializable {
    
    private Patient patient;
    private Patient motherOfHeu;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getMotherOfHeu() {
        return motherOfHeu;
    }

    public void setMotherOfHeu(Patient motherOfHeu) {
        this.motherOfHeu = motherOfHeu;
    }
    
    public Patient getInstance(PatientHeuDTO dto){
        Patient p = dto.getPatient();
        p.setMotherOfHei(dto.getMotherOfHeu());
        return p;
    }
}
