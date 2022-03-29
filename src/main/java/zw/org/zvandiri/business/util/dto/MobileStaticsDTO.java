package zw.org.zvandiri.business.util.dto;


import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.service.LabTaskService;

import java.io.Serializable;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class MobileStaticsDTO implements Serializable {
    List<Patient> patients;
    List<District> districts;
    List<Province> provinces;
    List<SupportGroup> supportGroups;
    List<Relationship> relationships;
    List<Referer> referers;
    List<OrphanStatus> orphanStatuses;
    List<Education> educations;
    List<EducationLevel> educationLevels;
    List<Location> locations;
    List<Position> positions;
    List<InternalReferral> internalReferrals;
    List<ExternalReferral> externalReferrals;
    List<ChronicInfection> chronicInfections;
    List<ServiceOffered> serviceOffereds;
    List<ServicesReferred> servicesReferred;
    List<HivCoInfection> hivCoInfections;
    List<MentalHealth> mentalHealths;
    List<DisabilityCategory> disabilityCategories;
    List<Assessment> assessments;
    List<ArvMedicine> arvMedicines;
    List<HospCause> hospCauses;
    List<Substance> substances;
    List<ActionTaken> actionTakens;
    List<ReasonForNotReachingOLevel> reasonForNotReachingOLevels;
    User user;
    List<LabTaskService> labTaskServices;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<SupportGroup> getSupportGroups() {
        return supportGroups;
    }

    public void setSupportGroups(List<SupportGroup> supportGroups) {
        this.supportGroups = supportGroups;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public List<Referer> getReferers() {
        return referers;
    }

    public void setReferers(List<Referer> referers) {
        this.referers = referers;
    }

    public List<OrphanStatus> getOrphanStatuses() {
        return orphanStatuses;
    }

    public void setOrphanStatuses(List<OrphanStatus> orphanStatuses) {
        this.orphanStatuses = orphanStatuses;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<EducationLevel> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<EducationLevel> educationLevels) {
        this.educationLevels = educationLevels;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<InternalReferral> getInternalReferrals() {
        return internalReferrals;
    }

    public void setInternalReferrals(List<InternalReferral> internalReferrals) {
        this.internalReferrals = internalReferrals;
    }

    public List<ExternalReferral> getExternalReferrals() {
        return externalReferrals;
    }

    public void setExternalReferrals(List<ExternalReferral> externalReferrals) {
        this.externalReferrals = externalReferrals;
    }

    public List<ChronicInfection> getChronicInfections() {
        return chronicInfections;
    }

    public void setChronicInfections(List<ChronicInfection> chronicInfections) {
        this.chronicInfections = chronicInfections;
    }

    public List<ServiceOffered> getServiceOffereds() {
        return serviceOffereds;
    }

    public void setServiceOffereds(List<ServiceOffered> serviceOffereds) {
        this.serviceOffereds = serviceOffereds;
    }

    public List<ServicesReferred> getServicesReferred() {
        return servicesReferred;
    }

    public void setServicesReferred(List<ServicesReferred> servicesReferred) {
        this.servicesReferred = servicesReferred;
    }

    public List<HivCoInfection> getHivCoInfections() {
        return hivCoInfections;
    }

    public void setHivCoInfections(List<HivCoInfection> hivCoInfections) {
        this.hivCoInfections = hivCoInfections;
    }

    public List<MentalHealth> getMentalHealths() {
        return mentalHealths;
    }

    public void setMentalHealths(List<MentalHealth> mentalHealths) {
        this.mentalHealths = mentalHealths;
    }

    public List<DisabilityCategory> getDisabilityCategories() {
        return disabilityCategories;
    }

    public void setDisabilityCategories(List<DisabilityCategory> disabilityCategories) {
        this.disabilityCategories = disabilityCategories;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<ArvMedicine> getArvMedicines() {
        return arvMedicines;
    }

    public void setArvMedicines(List<ArvMedicine> arvMedicines) {
        this.arvMedicines = arvMedicines;
    }

    public List<HospCause> getHospCauses() {
        return hospCauses;
    }

    public void setHospCauses(List<HospCause> hospCauses) {
        this.hospCauses = hospCauses;
    }

    public List<Substance> getSubstances() {
        return substances;
    }

    public void setSubstances(List<Substance> substances) {
        this.substances = substances;
    }

    public List<ActionTaken> getActionTakens() {
        return actionTakens;
    }

    public void setActionTakens(List<ActionTaken> actionTakens) {
        this.actionTakens = actionTakens;
    }

    public List<ReasonForNotReachingOLevel> getReasonForNotReachingOLevels() {
        return reasonForNotReachingOLevels;
    }

    public void setReasonForNotReachingOLevels(List<ReasonForNotReachingOLevel> reasonForNotReachingOLevels) {
        this.reasonForNotReachingOLevels = reasonForNotReachingOLevels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public List<LabTaskService> getLabTaskServices() {
        return labTaskServices;
    }

    public void setLabTaskServices(List<LabTaskService> labTaskServices) {
        this.labTaskServices = labTaskServices;
    }

    @Override
    public String toString() {
        return "MobileStaticsDTO{" +
                "patients=" + patients.toString() +
                ", districts=" + districts.toString() +
                ", provinces=" + provinces.toString() +
                ", supportGroups=" + supportGroups +
                ", relationships=" + relationships +
                ", referers=" + referers +
                ", orphanStatuses=" + orphanStatuses +
                ", educations=" + educations +
                ", educationLevels=" + educationLevels +
                ", locations=" + locations +
                ", positions=" + positions +
                ", internalReferrals=" + internalReferrals +
                ", externalReferrals=" + externalReferrals +
                ", chronicInfections=" + chronicInfections +
                ", serviceOffereds=" + serviceOffereds +
                ", hivCoInfections=" + hivCoInfections +
                ", mentalHealths=" + mentalHealths +
                ", disabilityCategories=" + disabilityCategories +
                ", assessments=" + assessments +
                ", arvMedicines=" + arvMedicines +
                ", hospCauses=" + hospCauses +
                ", substances=" + substances +
                ", actionTakens=" + actionTakens +
                ", reasonForNotReachingOLevels=" + reasonForNotReachingOLevels +
                ", user=" + user +
                ", labTaskServices=" + labTaskServices +
                '}';
    }
}
