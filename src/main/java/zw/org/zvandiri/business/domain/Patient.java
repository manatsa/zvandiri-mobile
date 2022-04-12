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

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.*;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Configurable;
import zw.org.zvandiri.business.domain.dto.PatientDTO;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.domain.util.OrphanStatus;
import zw.org.zvandiri.business.service.ContactService;
import zw.org.zvandiri.business.service.InvestigationTestService;
import zw.org.zvandiri.business.service.MentalHealthScreeningService;
import zw.org.zvandiri.business.service.impl.ContactServiceImpl;
import zw.org.zvandiri.business.service.impl.InvestigationTestServiceImpl;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@ToString
@Configurable(preConstruction = true)
@Table(indexes = {
		@Index(name = "patient_first_name_last_name", columnList = "firstName, lastName"),
		@Index(name = "patient_status", columnList = "status"),
		@Index(name = "patient_primary_clinic", columnList = "primary_clinic"),
		@Index(name = "patient_support_group", columnList = "support_group"),
		@Index(name = "patient_created_by", columnList = "created_by")
		})
@JsonIgnoreProperties
public class Patient extends GenericPatient {

    @Transient
    private District district;
    @Transient
    private Province province;
    @Transient
    @JsonIgnore
    @JsonProperty(value = "disabilities")
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Set<PatientDisability> disabilityCategorys = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PatientHistory> patientHistories = new HashSet<>();
    @Transient
    @JsonIgnore
    @OneToOne(mappedBy = "patient", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private MobilePhone mobilePhone;
    @Transient
    private District supportGroupDistrict;
    @Transient
    private int age = 0;
    @Transient
    private String currentElement;
    @Transient
    private Boolean patientStatus;
    @Transient
    private String patientExist;
    @Transient
    private String name;
    @Formula("(Select c.id From cat_detail c where c.patient = id)")
    private String catId;

    @Formula("(Select i.result From investigation_test i where i.patient = id and i.test_type = 0 order by i.date_created desc limit 0,1)")
    private Integer viralLoad;

    @Formula("(Select i.id From investigation_test i where i.patient = id and i.test_type = 0 order by i.date_created desc limit 0,1)")
    private String lastViralLoad;

    @Formula("(Select i.result From investigation_test i where i.patient = id and i.test_type = 1 order by i.date_created desc limit 0,1)")
    private Integer cd4Count;

    @Formula("(Select concat(a1.name, ', ', a2.name, ', ', a3.name) From arv_hist a inner join arv_medicine a1 on a1.id=a.arv_medicine inner join arv_medicine a2 on a2.id=a.arv_medicine2 inner join arv_medicine a3 on a3.id=a.arv_medicine3 where a.patient = id order by a.start_date desc limit 0,1)")
    private String currentArvRegimen;

    @Formula("(Select p.severity From patient_disability p where p.patient = id order by p.date_screened desc limit 0,1)")
    private Integer disabilitySeverity;

    @Formula("(Select c.follow_up From contact c where c.patient = id order by c.date_created desc limit 0,1)")
    private Integer enhancedStatus;

    @Transient
    private DisabilitySeverity disabilityStatus;

    @Enumerated
    private YesNo haveBirthCertificate;

    @JsonProperty(value = "idNumber")
    private String IDNumber;

    @Enumerated
    @JsonProperty(value = "maritalStatus")
    private MaritalStatus maritalStatus;

    @Enumerated
    @JsonProperty(value = "orphanStatus")
    private OrphanageStatus orphanStatus;

    @Enumerated
    @JsonProperty(value = "onArvs")
    private YesNo onArvs;

    @Enumerated
    @JsonProperty(value = "OnCotrimoxazole")
    private YesNo onCotrimoxazole;

    @JsonProperty(value = "dateStartedTreatment")
    private Date dateStartedTreatment;

    @Enumerated
    @JsonProperty(value = "disclosureType")
    private DisclosureType disclosureType;

    @JsonProperty(value = "artRegimen")
    private String artRegimen;

    @Enumerated
    @JsonProperty(value = "isKeypopulation")
    private YesNo isKeypopulation;

    @Enumerated
    @JsonProperty(value = "keyPopulation")
    private KeyPopulation keyPopulation;

    @JsonProperty(value = "disablityType")
    private String disablityType;

    @JsonProperty(value = "clientType")
    private ClientType clientType;

    public Patient() {
    }

    public Patient(PatientDTO patientDTO) {
        this.haveBirthCertificate = patientDTO.getHaveBirthCertificate();
        this.IDNumber = patientDTO.getIDNumber();
        this.maritalStatus = patientDTO.getMaritalStatus();
        this.orphanStatus = patientDTO.getOrphanStatus();
        this.onArvs = patientDTO.getOnArvs();
        this.onCotrimoxazole = patientDTO.getOnCotrimoxazole();
        this.dateStartedTreatment = patientDTO.getDateStartedTreatment();
        this.disclosureType = patientDTO.getDisclosureType();
        this.artRegimen = patientDTO.getArtRegimen();
        this.isKeypopulation = patientDTO.getIsKeypopulation();
        this.keyPopulation = patientDTO.getKeyPopulation();
        this.disablityType = patientDTO.getDisablityType();
        this.clientType = patientDTO.getClientType();
        this.setLastName(patientDTO.getLastName());
        this.setFirstName(patientDTO.getFirstName());
        this.setMiddleName(patientDTO.getMiddleName());
        this.setDateStartedTreatment(patientDTO.getDateStartedTreatment());
        this.setAddress(patientDTO.getAddress());
        this.setMobileNumber(patientDTO.getMobileNumber());
        this.setMobileOwner(patientDTO.getMobileOwner());
        this.setOwnerName(patientDTO.getOwnerName());
        this.setSecondaryMobileNumber(patientDTO.getSecondaryMobileNumber());
        this.setSecondaryMobileOwnerName(patientDTO.getSecondaryMobileOwnerName());
        this.setOwnSecondaryMobile(patientDTO.getOwnSecondaryMobile());
        this.setDateOfBirth(patientDTO.getDateOfBirth());
        this.setGender(patientDTO.getGender());
        this.setConsentToMHealth(patientDTO.getConsentToMHealth());
        this.setHivStatusKnown(patientDTO.getHivStatusKnown());
        this.sethIVDisclosureLocation(patientDTO.gethIVDisclosureLocation());
        this.setDateJoined(patientDTO.getDateJoined());
        this.setDateTested(patientDTO.getDateTested());
        this.setTransmissionMode(patientDTO.getTransmissionMode());
        this.setoINumber(patientDTO.getoINumber());
        this.setStatus(patientDTO.getStatus());
        this.setPfirstName(patientDTO.getPfirstName());
        this.setPlastName(patientDTO.getPlastName());
        this.setPgender(patientDTO.getPgender());
        this.setRefererName(patientDTO.getRefererName());

    }

    public InvestigationTest getLastPatientVL(InvestigationTestService investigationTestService) {

        if(lastViralLoad!=null) {
            if (investigationTestService == null) {
                System.err.println("last viral load service is null");
            } else {
                InvestigationTest test=investigationTestService.get(lastViralLoad);
                return test;
            }
        }

        return null;

    }

    public CareLevel getCurrentCareLevelObject(){
        if(this.enhancedStatus==null)
            return CareLevel.ENHANCED;
        return CareLevel.get(this.enhancedStatus+1);
    }

    public Integer getEnhancedStatus() {
        return enhancedStatus;
    }

    public void setEnhancedStatus(Integer enhancedStatus) {
        this.enhancedStatus = enhancedStatus;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(String currentElement) {
        this.currentElement = currentElement;
    }

    public District getSupportGroupDistrict() {
        return supportGroupDistrict;
    }

    public void setSupportGroupDistrict(District supportGroupDistrict) {
        this.supportGroupDistrict = supportGroupDistrict;
    }

    public String getLastViralLoad() {
        return lastViralLoad;
    }

    public void setLastViralLoad(String lastViralLoad) {
        this.lastViralLoad = lastViralLoad;
    }

    public String getName() {
        return getFirstName() +  " " + getLastName();
    }

    public Set<PatientDisability> getDisabilityCategorys() {
        return disabilityCategorys;
    }

    public void setDisabilityCategorys(Set<PatientDisability> disabilityCategorys) {
        this.disabilityCategorys = disabilityCategorys;
    }

    public int getAge() {
        if (getDateOfBirth() == null) {
            return 0;
        }
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(getDateOfBirth());
        Calendar todayCalendar = Calendar.getInstance();
        age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (todayCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (todayCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && todayCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public String getPic() {
        if (getGender() == null) {
            return "/resources/images/noimage.gif";
        } else if (getGender().equals(Gender.MALE)) {
            return "/resources/images/male.gif";
        } else if (getGender().equals(Gender.FEMALE)) {
            return "/resources/images/female.gif";
        }
        return "/resources/images/noimage.gif";
    }

    public String getDateJoin() {
        if (getDateJoined() == null) {
            return "";
        }
        return DateUtil.getStringFromDate(getDateJoined());
    }

    public Boolean getPatientStatus() {
        if (getStatus() == null || getStatus().equals(PatientChangeEvent.ACTIVE)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientExist() {
        return patientExist;
    }

    public void setPatientExist(String patientExist) {
        this.patientExist = patientExist;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getMother() {
        return getName() + " dob " + DateUtil.zimDate(getDateOfBirth());
    }

    public Set<PatientHistory> getPatientHistories() {
        return patientHistories;
    }

    public void setPatientHistories(Set<PatientHistory> patientHistories) {
        this.patientHistories = patientHistories;
    }

    public Integer getViralLoad() {
        return viralLoad != null ? viralLoad : 0;
    }

    public Integer getCd4Count() {
        return cd4Count != null ? cd4Count : 0;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void add(PatientDisability item, Patient patient) {
        disabilityCategorys.add(item);
        item.setPatient(patient);
    }

    public String getCurrentArvRegimen() {
        return currentArvRegimen;
    }

    public Integer getDisabilitySeverity() {
        return disabilitySeverity;
    }

    public DisabilitySeverity getDisabilityStatus() {
        if (disabilitySeverity != null) {
            return DisabilitySeverity.get(disabilitySeverity + 1);
        }
        return null;
    }


    public YesNo getHaveBirthCertificate() {
        return haveBirthCertificate;
    }

    public void setHaveBirthCertificate(YesNo haveBirthCertificate) {
        this.haveBirthCertificate = haveBirthCertificate;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public OrphanageStatus getOrphanStatus() {
        return orphanStatus;
    }

    public void setOrphanStatus(OrphanageStatus orphanStatus) {
        this.orphanStatus = orphanStatus;
    }

    public YesNo getOnArvs() {
        return onArvs;
    }

    public void setOnArvs(YesNo onArvs) {
        this.onArvs = onArvs;
    }

    public YesNo getOnCotrimoxazole() {
        return onCotrimoxazole;
    }

    public void setOnCotrimoxazole(YesNo onCotrimoxazole) {
        onCotrimoxazole = onCotrimoxazole;
    }

    public Date getDateStartedTreatment() {
        return dateStartedTreatment;
    }

    public void setDateStartedTreatment(Date dateStartedTreatment) {
        this.dateStartedTreatment = dateStartedTreatment;
    }

    public DisclosureType getDisclosureType() {
        return disclosureType;
    }

    public void setDisclosureType(DisclosureType disclosureType) {
        this.disclosureType = disclosureType;
    }

    public String getArtRegimen() {
        return artRegimen;
    }

    public void setArtRegimen(String artRegimen) {
        this.artRegimen = artRegimen;
    }

    public YesNo getIsKeypopulation() {
        return isKeypopulation;
    }

    public void setIsKeypopulation(YesNo isKeypopulation) {
        this.isKeypopulation = isKeypopulation;
    }

    public KeyPopulation getKeyPopulation() {
        return keyPopulation;
    }

    public void setKeyPopulation(KeyPopulation keyPopulation) {
        this.keyPopulation = keyPopulation;
    }

    public String getDisablityType() {
        return disablityType;
    }

    public void setDisablityType(String disablityType) {
        this.disablityType = disablityType;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }



}
