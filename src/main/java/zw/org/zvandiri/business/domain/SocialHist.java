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

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.AbuseOutcome;
import zw.org.zvandiri.business.domain.util.AbuseType;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialHist extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient", updatable = false, insertable = false)
    private Patient patient;
    private String liveWith;
    @ManyToOne
    @JoinColumn(name="relationship", updatable = false, insertable = false)
    private Relationship relationship;
    @Enumerated
    private YesNo abuse;
    @Enumerated
    private YesNo dosclosure;
    @Enumerated
    private YesNo feelSafe;
    @Enumerated
    private AbuseOutcome abuseOutcome;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated
    private Set<AbuseType> abuseTypes;
    private String socialSupport;
    private String lossOfSignificantRelationships;

    public SocialHist() {
    }

    public SocialHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AbuseOutcome getAbuseOutcome() {
        return abuseOutcome;
    }

    public void setAbuseOutcome(AbuseOutcome abuseOutcome) {
        this.abuseOutcome = abuseOutcome;
    }

    public String getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(String liveWith) {
        this.liveWith = liveWith;
    }

    public YesNo getAbuse() {
        return abuse;
    }

    public void setAbuse(YesNo abuse) {
        this.abuse = abuse;
    }

    public YesNo getDosclosure() {
        return dosclosure;
    }

    public void setDosclosure(YesNo dosclosure) {
        this.dosclosure = dosclosure;
    }

    public YesNo getFeelSafe() {
        return feelSafe;
    }

    public void setFeelSafe(YesNo feelSafe) {
        this.feelSafe = feelSafe;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Set<AbuseType> getAbuseTypes() {
        return abuseTypes;
    }

    public void setAbuseTypes(Set<AbuseType> abuseTypes) {
        this.abuseTypes = abuseTypes;
    }

    public String getSocialSupport() {
        return socialSupport;
    }

    public void setSocialSupport(String socialSupport) {
        this.socialSupport = socialSupport;
    }

    public String getLossOfSignificantRelationships() {
        return lossOfSignificantRelationships;
    }

    public void setLossOfSignificantRelationships(String lossOfSignificantRelationships) {
        this.lossOfSignificantRelationships = lossOfSignificantRelationships;
    }
    
}
