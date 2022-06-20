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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalHist extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient")
    private Patient patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date hospWhen;
    @ManyToOne
    @JoinColumn(name="primary_clinic")
    private Facility primaryClinic;
    @Transient
    private Province province;
    @Transient
    private District district;
    @ManyToOne
    @JoinColumn(name="hosp_cause")
    private HospCause hospCause;
    @Enumerated
    private YesNo outcome;

    public MedicalHist() {
    }

    public MedicalHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getHospWhen() {
        return hospWhen;
    }

    public void setHospWhen(Date hospWhen) {
        this.hospWhen = hospWhen;
    }

    public Facility getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(Facility primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public HospCause getHospCause() {
        return hospCause;
    }

    public void setHospCause(HospCause hospCause) {
        this.hospCause = hospCause;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

	public YesNo getOutcome() {
		return outcome;
	}

	public void setOutcome(YesNo outcome) {
		this.outcome = outcome;
	}
    
    
}
