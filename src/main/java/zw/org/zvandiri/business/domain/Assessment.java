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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;
import zw.org.zvandiri.business.domain.util.ContactAssessment;

/**
 *
 * @author Judge Muzinda
 */
@ToString
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
public class Assessment extends BaseName {

    @ToString.Exclude
    @ManyToMany(mappedBy = "clinicalAssessments", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Contact> clinicalAssessments = new java.util.LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "nonClinicalAssessments", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Contact> nonClinicalAssessments = new java.util.LinkedHashSet<>();
    @ToString.Include
    @Enumerated
    private ContactAssessment contactAssessment;

    public Set<Contact> getClinicalAssessments() {
        return clinicalAssessments;
    }

    public void setClinicalAssessments(Set<Contact> clinicalAssessments) {
        this.clinicalAssessments = clinicalAssessments;
    }

    public Set<Contact> getNonClinicalAssessments() {
        return nonClinicalAssessments;
    }

    public void setNonClinicalAssessments(Set<Contact> nonClinicalAssessments) {
        this.nonClinicalAssessments = nonClinicalAssessments;
    }

    public ContactAssessment getContactAssessment() {
        return contactAssessment;
    }

    public void setContactAssessment(ContactAssessment contactAssessment) {
        this.contactAssessment = contactAssessment;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "ID"+getId()+
                "name=" + getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assessment that = (Assessment) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getId(), that.getId()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getId());
    }
}
