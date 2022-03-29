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
package zw.org.zvandiri.business.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.CurrentStatus;

/**
 *
 * @author Judge Muzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
/*@Table(indexes = {
		@Index(name = "chronic_infection_item_patient", columnList = "patient"),
		@Index(name = "chronic_infection_item_chronic_infection", columnList = "chronic_infection"),
		@Index(name = "chronic_infection_item_infection_date", columnList = "infectionDate")
})*/
public class ChronicInfectionItem extends BaseEntity {
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient", updatable = false, insertable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name="chronic_infection", updatable = false, insertable = false)
    private ChronicInfection chronicInfection;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date infectionDate;
    private String medication;
    @Enumerated
    private CurrentStatus currentStatus;

    public ChronicInfectionItem() {
    }

    public ChronicInfectionItem(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ChronicInfection getChronicInfection() {
        return chronicInfection;
    }

    public void setChronicInfection(ChronicInfection chronicInfection) {
        this.chronicInfection = chronicInfection;
    }

    public Date getInfectionDate() {
        return infectionDate;
    }

    public void setInfectionDate(Date infectionDate) {
        this.infectionDate = infectionDate;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
    
}
