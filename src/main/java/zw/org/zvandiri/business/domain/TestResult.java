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

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.Cd4CountResultSource;
import zw.org.zvandiri.business.domain.util.YesNo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author Judge Muzinda
 */
@MappedSuperclass
@ToString
public class TestResult extends BaseEntity {
    
    @ManyToOne
    @NotNull
    //@JsonIgnore
    @JoinColumn(name="patient")
    private Patient patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTaken;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date nextTestDate;
    private Integer result;
    private String tnd;
    @Enumerated
    private Cd4CountResultSource source;
    @Transient
    private YesNo resultTaken;

    public TestResult() {
    }


    public TestResult(Patient patient) {
        this.patient = patient;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Date getNextTestDate() {
        return nextTestDate;
    }

    public void setNextTestDate(Date nextTestDate) {
        this.nextTestDate = nextTestDate;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Cd4CountResultSource getSource() {
        return source;
    }

    public void setSource(Cd4CountResultSource source) {
        this.source = source;
    }

    public String getTnd() {
        return tnd;
    }

    public void setTnd(String tnd) {
        this.tnd = tnd;
    }

    public YesNo getResultTaken() {
        return resultTaken;
    }

    public void setResultTaken(YesNo resultTaken) {
        this.resultTaken = resultTaken;
    }


}