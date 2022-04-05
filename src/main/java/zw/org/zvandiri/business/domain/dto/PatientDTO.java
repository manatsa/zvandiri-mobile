package zw.org.zvandiri.business.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.domain.util.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class PatientDTO {
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

    private String firstName;
    private String middleName;
    private String lastName;
    @Enumerated
    private Gender gender;
    @Enumerated
    private YesNo consentToMHealth;
    private String address;
    private String mobileNumber;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name="education")
    private String education;
    @ManyToOne
    @JoinColumn(name="education_level")
    private String educationLevel;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateJoined;
    @ManyToOne
    @JoinColumn(name="referer")
    private String referer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="primary_clinic")
    private String primaryClinic;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="support_group")
    private String supportGroup;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTested;
    @Enumerated
    private HIVDisclosureLocation hIVDisclosureLocation;
    @Enumerated
    private YesNo disability;
    @Enumerated
    private YesNo selfPrimaryCareGiver;
    private String pfirstName;
    private String plastName;
    private String pmobileNumber;
    private Gender pgender;
    @ManyToOne
    @JoinColumn(name="relationship")
    private String relationship;
    private String secondaryMobileNumber;
    @Enumerated
    private YesNo mobileOwner;
    private String ownerName;
    @ManyToOne
    @JoinColumn(name="mobile_Owner_relation")
    private String mobileOwnerRelation;
    @Enumerated
    private YesNo ownSecondaryMobile;
    private String secondaryMobileOwnerName;
    @ManyToOne
    @JoinColumn(name="secondary_mobileowner_relation")
    private String secondaryMobileownerRelation;
    @Enumerated
    private TransmissionMode transmissionMode;
    @Enumerated
    private YesNo hivStatusKnown;
    @Enumerated
    private PatientChangeEvent status = PatientChangeEvent.ACTIVE;
    @ManyToOne
    @JoinColumn(name="reason_for_not_reachingolevel")
    private String reasonForNotReachingOLevel;
    private String refererName;
    private String oINumber;

    public PatientDTO() {
    }

    public PatientDTO(Patient patient) {
        this.haveBirthCertificate = patient.getHaveBirthCertificate();
        this.IDNumber = patient.getIDNumber();
        this.maritalStatus = patient.getMaritalStatus();
        this.orphanStatus = patient.getOrphanStatus();
        this.onArvs = patient.getOnArvs();
        this.onCotrimoxazole = patient.getOnCotrimoxazole();
        this.dateStartedTreatment = patient.getDateStartedTreatment();
        this.disclosureType = patient.getDisclosureType();
        this.artRegimen = patient.getArtRegimen();
        this.isKeypopulation = patient.getIsKeypopulation();
        this.keyPopulation = patient.getKeyPopulation();
        this.disablityType = patient.getDisablityType();
        this.clientType = patient.getClientType();
        this.firstName = patient.getFirstName();
        this.middleName = patient.getMiddleName();
        this.lastName = patient.getLastName();
        this.gender = patient.getGender();
        this.consentToMHealth = patient.getConsentToMHealth();
        this.address = patient.getAddress();
        this.mobileNumber = patient.getMobileNumber();
        this.dateOfBirth = patient.getDateOfBirth();
        this.education = patient.getEducation().getId();
        this.educationLevel = patient.getEducationLevel().getId();
        this.dateJoined = patient.getDateJoined();
        this.referer = patient.getReferer().getId();
        this.primaryClinic = patient.getPrimaryClinic().getId();
        this.supportGroup = patient.getSupportGroup().getId();
        this.dateTested = patient.getDateTested();
        this.hIVDisclosureLocation = patient.gethIVDisclosureLocation();
        this.disability = patient.getDisability();
        this.selfPrimaryCareGiver = patient.getSelfPrimaryCareGiver();
        this.pfirstName = patient.getPfirstName();
        this.plastName = patient.getPlastName();
        this.pmobileNumber = patient.getPmobileNumber();
        this.pgender = patient.getPgender();
        this.relationship = patient.getRelationship().getId();
        this.secondaryMobileNumber = patient.getSecondaryMobileNumber();
        this.mobileOwner = patient.getMobileOwner();
        this.ownerName = patient.getOwnerName();
        this.mobileOwnerRelation = (patient.getMobileOwnerRelation()!=null)?patient.getMobileOwnerRelation().getId():null;
        this.ownSecondaryMobile = patient.getOwnSecondaryMobile();
        this.secondaryMobileOwnerName = patient.getSecondaryMobileOwnerName();
        this.secondaryMobileownerRelation = (patient.getSecondaryMobileownerRelation()!=null)?patient.getSecondaryMobileownerRelation().getId():null;
        this.transmissionMode = patient.getTransmissionMode();
        this.hivStatusKnown = patient.getHivStatusKnown();
        this.status = patient.getStatus();
        this.reasonForNotReachingOLevel = (patient.getReasonForNotReachingOLevel()!=null)?patient.getReasonForNotReachingOLevel().getId():null;
        this.refererName = patient.getRefererName();
        this.oINumber = patient.getoINumber();
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
        this.onCotrimoxazole = onCotrimoxazole;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public YesNo getConsentToMHealth() {
        return consentToMHealth;
    }

    public void setConsentToMHealth(YesNo consentToMHealth) {
        this.consentToMHealth = consentToMHealth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public Date getDateTested() {
        return dateTested;
    }

    public void setDateTested(Date dateTested) {
        this.dateTested = dateTested;
    }

    public HIVDisclosureLocation gethIVDisclosureLocation() {
        return hIVDisclosureLocation;
    }

    public void sethIVDisclosureLocation(HIVDisclosureLocation hIVDisclosureLocation) {
        this.hIVDisclosureLocation = hIVDisclosureLocation;
    }

    public YesNo getDisability() {
        return disability;
    }

    public void setDisability(YesNo disability) {
        this.disability = disability;
    }

    public YesNo getSelfPrimaryCareGiver() {
        return selfPrimaryCareGiver;
    }

    public void setSelfPrimaryCareGiver(YesNo selfPrimaryCareGiver) {
        this.selfPrimaryCareGiver = selfPrimaryCareGiver;
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

    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public YesNo getMobileOwner() {
        return mobileOwner;
    }

    public void setMobileOwner(YesNo mobileOwner) {
        this.mobileOwner = mobileOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public YesNo getOwnSecondaryMobile() {
        return ownSecondaryMobile;
    }

    public void setOwnSecondaryMobile(YesNo ownSecondaryMobile) {
        this.ownSecondaryMobile = ownSecondaryMobile;
    }

    public String getSecondaryMobileOwnerName() {
        return secondaryMobileOwnerName;
    }

    public void setSecondaryMobileOwnerName(String secondaryMobileOwnerName) {
        this.secondaryMobileOwnerName = secondaryMobileOwnerName;
    }

    public String getSecondaryMobileownerRelation() {
        return secondaryMobileownerRelation;
    }

    public void setSecondaryMobileownerRelation(String secondaryMobileownerRelation) {
        this.secondaryMobileownerRelation = secondaryMobileownerRelation;
    }

    public TransmissionMode getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(TransmissionMode transmissionMode) {
        this.transmissionMode = transmissionMode;
    }

    public YesNo getHivStatusKnown() {
        return hivStatusKnown;
    }

    public void setHivStatusKnown(YesNo hivStatusKnown) {
        this.hivStatusKnown = hivStatusKnown;
    }

    public PatientChangeEvent getStatus() {
        return status;
    }

    public void setStatus(PatientChangeEvent status) {
        this.status = status;
    }

    public String getRefererName() {
        return refererName;
    }

    public void setRefererName(String refererName) {
        this.refererName = refererName;
    }

    public String getoINumber() {
        return oINumber;
    }

    public void setoINumber(String oINumber) {
        this.oINumber = oINumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(String primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public String getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(String supportGroup) {
        this.supportGroup = supportGroup;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMobileOwnerRelation() {
        return mobileOwnerRelation;
    }

    public void setMobileOwnerRelation(String mobileOwnerRelation) {
        this.mobileOwnerRelation = mobileOwnerRelation;
    }

    public String getReasonForNotReachingOLevel() {
        return reasonForNotReachingOLevel;
    }

    public void setReasonForNotReachingOLevel(String reasonForNotReachingOLevel) {
        this.reasonForNotReachingOLevel = reasonForNotReachingOLevel;
    }
}
