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

import javax.persistence.Entity; import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

/**
 *
 * @author Judge Muzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
public class Settings extends BaseEntity {
    
    private Integer patientMinAge;
    private Integer patientMaxAge;
    private Integer patientAutoExpireAfterMaxAge;
    private Integer catMinAge;
    private Integer catMaxAge;
    private Integer cd4MinCount;
    private Integer viralLoadMaxCount;
    private Integer maxDaysAfterContact;
    private Integer minAgeToGiveBirth;
    private Integer heuMotherMaxAge;
    private Integer maxNumContactIndex = 1500;

    public Integer getPatientMinAge() {
        return patientMinAge;
    }

    public void setPatientMinAge(Integer patientMinAge) {
        this.patientMinAge = patientMinAge;
    }

    public Integer getPatientMaxAge() {
        return patientMaxAge;
    }

    public void setPatientMaxAge(Integer patientMaxAge) {
        this.patientMaxAge = patientMaxAge;
    }

    public Integer getCatMinAge() {
        return catMinAge;
    }

    public void setCatMinAge(Integer catMinAge) {
        this.catMinAge = catMinAge;
    }

    public Integer getCatMaxAge() {
        return catMaxAge;
    }

    public void setCatMaxAge(Integer catMaxAge) {
        this.catMaxAge = catMaxAge;
    }

    public Integer getCd4MinCount() {
        return cd4MinCount;
    }

    public void setCd4MinCount(Integer cd4MinCount) {
        this.cd4MinCount = cd4MinCount;
    }

    public Integer getViralLoadMaxCount() {
        return viralLoadMaxCount;
    }

    public void setViralLoadMaxCount(Integer viralLoadMaxCount) {
        this.viralLoadMaxCount = viralLoadMaxCount;
    }

    public Integer getMaxDaysAfterContact() {
        return maxDaysAfterContact;
    }

    public void setMaxDaysAfterContact(Integer maxDaysAfterContact) {
        this.maxDaysAfterContact = maxDaysAfterContact;
    }

    public Integer getPatientAutoExpireAfterMaxAge() {
        return patientAutoExpireAfterMaxAge;
    }

    public void setPatientAutoExpireAfterMaxAge(Integer patientAutoExpireAfterMaxAge) {
        this.patientAutoExpireAfterMaxAge = patientAutoExpireAfterMaxAge;
    }

    public Integer getMinAgeToGiveBirth() {
        return minAgeToGiveBirth;
    }

    public void setMinAgeToGiveBirth(Integer minAgeToGiveBirth) {
        this.minAgeToGiveBirth = minAgeToGiveBirth;
    }

    public Integer getHeuMotherMaxAge() {
        return heuMotherMaxAge;
    }

    public void setHeuMotherMaxAge(Integer heuMotherMaxAge) {
        this.heuMotherMaxAge = heuMotherMaxAge;
    }   

    public Integer getMaxNumContactIndex() {
        return maxNumContactIndex;
    }

    public void setMaxNumContactIndex(Integer maxNumContactIndex) {
        this.maxNumContactIndex = maxNumContactIndex;
    }
    
}
