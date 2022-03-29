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

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.ArtStarted;
import zw.org.zvandiri.business.domain.util.GestationalAge;
import zw.org.zvandiri.business.domain.util.NumberOfANCVisit;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "obsterc_hist_patient", columnList = "patient")
})*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObstercHist extends BaseEntity {
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient", updatable = false, insertable = false)
    private Patient patient;
    @Enumerated
    private YesNo pregnant;
    @Enumerated
    private NumberOfANCVisit numberOfANCVisit;
    @Enumerated
    private YesNo breafFeedingCurrent;
    @Enumerated
    private YesNo pregCurrent;
    private GestationalAge gestationalAge;
    @Enumerated
    private ArtStarted artStarted;
    private Integer children;

    public ObstercHist() {
    }

    public ObstercHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public YesNo getPregnant() {
        return pregnant;
    }

    public void setPregnant(YesNo pregnant) {
        this.pregnant = pregnant;
    }

    public YesNo getPregCurrent() {
        return pregCurrent;
    }

    public void setPregCurrent(YesNo pregCurrent) {
        this.pregCurrent = pregCurrent;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public YesNo getBreafFeedingCurrent() {
        return breafFeedingCurrent;
    }

    public void setBreafFeedingCurrent(YesNo breafFeedingCurrent) {
        this.breafFeedingCurrent = breafFeedingCurrent;
    }

    public NumberOfANCVisit getNumberOfANCVisit() {
        return numberOfANCVisit;
    }

    public void setNumberOfANCVisit(NumberOfANCVisit numberOfANCVisit) {
        this.numberOfANCVisit = numberOfANCVisit;
    }

    public GestationalAge getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(GestationalAge gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public ArtStarted getArtStarted() {
        return artStarted;
    }

    public void setArtStarted(ArtStarted artStarted) {
        this.artStarted = artStarted;
    }

    @Override
    public String toString() {
        return "ObstercHist{" + "patient=" + patient.getLastName() + ", pregnant=" + pregnant + ", numberOfANCVisit=" + numberOfANCVisit + ", breafFeedingCurrent=" + breafFeedingCurrent + ", pregCurrent=" + pregCurrent + ", gestationalAge=" + gestationalAge + ", artStarted=" + artStarted + ", children=" + children + '}';
    }
    
}
