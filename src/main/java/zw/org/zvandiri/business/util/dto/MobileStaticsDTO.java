package zw.org.zvandiri.business.util.dto;


import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.domain.dto.*;
import zw.org.zvandiri.business.service.LabTaskService;

import java.io.Serializable;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class MobileStaticsDTO implements Serializable {
    List<PatientListDTO> patients;
    List<DistrictDTO> districts;
    List<ProvinceDTO> provinces;
    List<FacilityDTO> facilities;
    List<SupportGroupDTO> supportGroups;
    List<RelationshipDTO> relationships;
    List<RefererDTO> referers;
    List<OrphanStatus> orphanStatuses;
    List<EducationDTO> educations;
    List<EducationLevelDTO> educationLevels;
    List<LocationDTO> locations;
    List<PositionDTO> positions;
    List<ChronicInfection> chronicInfections;
    List<ServiceOfferredDTO> serviceOffereds;
    List<ServiceReferredDTO> hivStiServicesReq;
    List<ServiceReferredDTO> hivStiServicesAvailed;
    List<ServiceReferredDTO> oiArtReq;
    List<ServiceReferredDTO> oiArtAvailed;
    List<ServiceReferredDTO> srhReq;
    List<ServiceReferredDTO> srhAvailed;
    List<ServiceReferredDTO> laboratoryReq;
    List<ServiceReferredDTO> laboratoryAvailed;
    List<ServiceReferredDTO>tbReq;
    List<ServiceReferredDTO> tbAvailed;
    List<ServiceReferredDTO> psychReq;
    List<ServiceReferredDTO> psychAvailed;
    List<ServiceReferredDTO> legalReq;
    List<ServiceReferredDTO> legalAvailed;
    List<HivCoInfection> hivCoInfections;
    List<MentalHealth> mentalHealths;
    List<DisabilityCategory> disabilityCategories;
    List<AssessmentDTO> assessments;
    List<ArvMedicine> arvMedicines;
    List<HospCause> hospCauses;
    List<Substance> substances;
    List<ActionTaken> actionTakens;
    List<ReasonForNotReachingOLvelDTO> reasonForNotReachingOLevels;
    User currentUser;
    List<LabTaskService> labTaskServices;

    public MobileStaticsDTO() {
    }



    public List<PatientListDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientListDTO> patients) {
        this.patients = patients;
    }

    public List<DistrictDTO> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictDTO> districts) {
        this.districts = districts;
    }

    public List<ProvinceDTO> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceDTO> provinces) {
        this.provinces = provinces;
    }

    public List<FacilityDTO> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilityDTO> facilities) {
        this.facilities = facilities;
    }

    public List<OrphanStatus> getOrphanStatuses() {
        return orphanStatuses;
    }

    public void setOrphanStatuses(List<OrphanStatus> orphanStatuses) {
        this.orphanStatuses = orphanStatuses;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

    public List<ChronicInfection> getChronicInfections() {
        return chronicInfections;
    }

    public void setChronicInfections(List<ChronicInfection> chronicInfections) {
        this.chronicInfections = chronicInfections;
    }

    public List<ServiceOfferredDTO> getServiceOffereds() {
        return serviceOffereds;
    }

    public void setServiceOffereds(List<ServiceOfferredDTO> serviceOffereds) {
        this.serviceOffereds = serviceOffereds;
    }

    public List<ServiceReferredDTO> getHivStiServicesReq() {
        return hivStiServicesReq;
    }

    public void setHivStiServicesReq(List<ServiceReferredDTO> hivStiServicesReq) {
        this.hivStiServicesReq = hivStiServicesReq;
    }

    public List<ServiceReferredDTO> getHivStiServicesAvailed() {
        return hivStiServicesAvailed;
    }

    public void setHivStiServicesAvailed(List<ServiceReferredDTO> hivStiServicesAvailed) {
        this.hivStiServicesAvailed = hivStiServicesAvailed;
    }

    public List<ServiceReferredDTO> getOiArtReq() {
        return oiArtReq;
    }

    public void setOiArtReq(List<ServiceReferredDTO> oiArtReq) {
        this.oiArtReq = oiArtReq;
    }

    public List<ServiceReferredDTO> getOiArtAvailed() {
        return oiArtAvailed;
    }

    public void setOiArtAvailed(List<ServiceReferredDTO> oiArtAvailed) {
        this.oiArtAvailed = oiArtAvailed;
    }

    public List<ServiceReferredDTO> getSrhReq() {
        return srhReq;
    }

    public void setSrhReq(List<ServiceReferredDTO> srhReq) {
        this.srhReq = srhReq;
    }

    public List<ServiceReferredDTO> getSrhAvailed() {
        return srhAvailed;
    }

    public void setSrhAvailed(List<ServiceReferredDTO> srhAvailed) {
        this.srhAvailed = srhAvailed;
    }

    public List<ServiceReferredDTO> getLaboratoryReq() {
        return laboratoryReq;
    }

    public void setLaboratoryReq(List<ServiceReferredDTO> laboratoryReq) {
        this.laboratoryReq = laboratoryReq;
    }

    public List<ServiceReferredDTO> getLaboratoryAvailed() {
        return laboratoryAvailed;
    }

    public void setLaboratoryAvailed(List<ServiceReferredDTO> laboratoryAvailed) {
        this.laboratoryAvailed = laboratoryAvailed;
    }

    public List<ServiceReferredDTO> getTbReq() {
        return tbReq;
    }

    public void setTbReq(List<ServiceReferredDTO> tbReq) {
        this.tbReq = tbReq;
    }

    public List<ServiceReferredDTO> getTbAvailed() {
        return tbAvailed;
    }

    public void setTbAvailed(List<ServiceReferredDTO> tbAvailed) {
        this.tbAvailed = tbAvailed;
    }

    public List<ServiceReferredDTO> getPsychReq() {
        return psychReq;
    }

    public void setPsychReq(List<ServiceReferredDTO> psychReq) {
        this.psychReq = psychReq;
    }

    public List<ServiceReferredDTO> getPsychAvailed() {
        return psychAvailed;
    }

    public void setPsychAvailed(List<ServiceReferredDTO> psychAvailed) {
        this.psychAvailed = psychAvailed;
    }

    public List<ServiceReferredDTO> getLegalReq() {
        return legalReq;
    }

    public void setLegalReq(List<ServiceReferredDTO> legalReq) {
        this.legalReq = legalReq;
    }

    public List<ServiceReferredDTO> getLegalAvailed() {
        return legalAvailed;
    }

    public void setLegalAvailed(List<ServiceReferredDTO> legalAvailed) {
        this.legalAvailed = legalAvailed;
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

    public List<AssessmentDTO> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<AssessmentDTO> assessments) {
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

    public List<ReasonForNotReachingOLvelDTO> getReasonForNotReachingOLevels() {
        return reasonForNotReachingOLevels;
    }

    public List<SupportGroupDTO> getSupportGroups() {
        return supportGroups;
    }

    public void setSupportGroups(List<SupportGroupDTO> supportGroups) {
        this.supportGroups = supportGroups;
    }

    public List<RelationshipDTO> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipDTO> relationships) {
        this.relationships = relationships;
    }

    public List<RefererDTO> getReferers() {
        return referers;
    }

    public void setReferers(List<RefererDTO> referers) {
        this.referers = referers;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<EducationLevelDTO> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<EducationLevelDTO> educationLevels) {
        this.educationLevels = educationLevels;
    }

    public void setReasonForNotReachingOLevels(List<ReasonForNotReachingOLvelDTO> reasonForNotReachingOLevels) {
        this.reasonForNotReachingOLevels = reasonForNotReachingOLevels;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<LabTaskService> getLabTaskServices() {
        return labTaskServices;
    }

    public void setLabTaskServices(List<LabTaskService> labTaskServices) {
        this.labTaskServices = labTaskServices;
    }
}
