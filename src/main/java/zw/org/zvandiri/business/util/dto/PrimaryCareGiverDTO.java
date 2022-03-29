/*
 * Copyright 2016 Judge Muzinda.
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
import javax.persistence.Embeddable;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Relationship;
import zw.org.zvandiri.business.domain.util.Gender;

/**
 *
 * @author Judge Muzinda
 */
@Embeddable
public class PrimaryCareGiverDTO implements Serializable {

    private Patient patient;
    private String pfirstName;
    private String plastName;
    private String pmobileNumber;
    private Gender pgender;
    private Relationship relationship;

    public PrimaryCareGiverDTO(Patient patient) {
        this.patient = patient;
        this.pfirstName = patient.getPfirstName();
        this.plastName = patient.getPlastName();
        this.pmobileNumber = patient.getPmobileNumber();
        this.pgender = patient.getPgender();
        this.relationship = patient.getRelationship();
    }

    public PrimaryCareGiverDTO() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPfirstName() {
        return pfirstName;
    }

    public void setPfirstName(String pfirstName) {
        this.pfirstName = pfirstName;
    }

    public String getPlastName() {
        return plastName;
    }

    public void setPlastName(String plastName) {
        this.plastName = plastName;
    }

    public String getPmobileNumber() {
        return pmobileNumber;
    }

    public void setPmobileNumber(String pmobileNumber) {
        this.pmobileNumber = pmobileNumber;
    }

    public Gender getPgender() {
        return pgender;
    }

    public void setPgender(Gender pgender) {
        this.pgender = pgender;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Patient getInstance(PrimaryCareGiverDTO dto) {
        Patient p = dto.getPatient();
        p.setPfirstName(dto.getPfirstName());
        p.setPlastName(dto.getPlastName());
        p.setPmobileNumber(dto.getPmobileNumber());
        p.setPgender(dto.getPgender());
        p.setRelationship(dto.getRelationship());
        return p;
    }
}
