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
 *//*

package zw.org.zvandiri.mobile.api.resource;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import zw.org.zvandiri.business.domain.ActionTaken;
import zw.org.zvandiri.business.domain.ArvMedicine;
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.ChronicInfection;
import zw.org.zvandiri.business.domain.DisabilityCategory;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Education;
import zw.org.zvandiri.business.domain.EducationLevel;
import zw.org.zvandiri.business.domain.Enhanced;
import zw.org.zvandiri.business.domain.ExternalReferral;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.HivCoInfection;
import zw.org.zvandiri.business.domain.HospCause;
import zw.org.zvandiri.business.domain.InternalReferral;
import zw.org.zvandiri.business.domain.LabTask;
import zw.org.zvandiri.business.domain.Location;
import zw.org.zvandiri.business.domain.MentalHealth;
import zw.org.zvandiri.business.domain.OrphanStatus;
import zw.org.zvandiri.business.domain.Position;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.ReasonForNotReachingOLevel;
import zw.org.zvandiri.business.domain.Referer;
import zw.org.zvandiri.business.domain.Relationship;
import zw.org.zvandiri.business.domain.ServiceOffered;
import zw.org.zvandiri.business.domain.ServicesReferred;
import zw.org.zvandiri.business.domain.Stable;
import zw.org.zvandiri.business.domain.Substance;
import zw.org.zvandiri.business.domain.SupportGroup;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.service.ActionTakenService;
import zw.org.zvandiri.business.service.ArvMedicineService;
import zw.org.zvandiri.business.service.AssessmentService;
import zw.org.zvandiri.business.service.ChronicInfectionService;
import zw.org.zvandiri.business.service.DisabilityCategoryService;
import zw.org.zvandiri.business.service.DistrictService;
import zw.org.zvandiri.business.service.EducationLevelService;
import zw.org.zvandiri.business.service.EducationService;
import zw.org.zvandiri.business.service.EnhancedService;
import zw.org.zvandiri.business.service.ExternalReferralService;
import zw.org.zvandiri.business.service.FacilityService;
import zw.org.zvandiri.business.service.HivCoInfectionService;
import zw.org.zvandiri.business.service.HospCauseService;
import zw.org.zvandiri.business.service.InternalReferralService;
import zw.org.zvandiri.business.service.LabTaskService;
import zw.org.zvandiri.business.service.LocationService;
import zw.org.zvandiri.business.service.MentalHealthService;
import zw.org.zvandiri.business.service.OrphanStatusService;
import zw.org.zvandiri.business.service.PositionService;
import zw.org.zvandiri.business.service.ProvinceService;
import zw.org.zvandiri.business.service.ReasonForNotReachingOLevelService;
import zw.org.zvandiri.business.service.RefererService;
import zw.org.zvandiri.business.service.RelationshipService;
import zw.org.zvandiri.business.service.ServiceOfferedService;
import zw.org.zvandiri.business.service.ServicesReferredService;
import zw.org.zvandiri.business.service.StableService;
import zw.org.zvandiri.business.service.SubstanceService;
import zw.org.zvandiri.business.service.SupportGroupService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.dto.UserDTO;

*/
/**
 * @author Judge Muzinda
 *//*

@Component
@Path("/mobile/static")
@Produces(MediaType.APPLICATION_JSON)
public class StaticDataResource {

    @Resource
    private ProvinceService provinceService;
    @Resource
    private DistrictService districtService;
    @Resource
    private SupportGroupService supportGroupService;
    @Resource
    private FacilityService facilityService;
    @Resource
    private RelationshipService relationshipService;
    @Resource
    private RefererService refererService;
    @Resource
    private OrphanStatusService orphanStatusService;
    @Resource
    private EducationService educationService;
    @Resource
    private EducationLevelService educationLevelService;
    @Resource
    private LocationService locationService;
    @Resource
    private PositionService positionService;
    @Resource
    private InternalReferralService internalReferralService;
    @Resource
    private ExternalReferralService externalReferralService;
    @Resource
    private ChronicInfectionService chronicInfectionService;
    @Resource
    private StableService stableService;
    @Resource
    private EnhancedService enhancedService;
    @Resource
    private ServicesReferredService servicesReferredService;
    @Resource
    private HivCoInfectionService hivCoInfectionService;
    @Resource
    private MentalHealthService mentalHealthService;
    @Resource
    private DisabilityCategoryService disabilityCategoryService;
    @Resource
    private AssessmentService assessmentService;
    @Resource
    private ArvMedicineService arvMedicineService;
    @Resource
    private HospCauseService hospService;
    @Resource
    private SubstanceService substanceService;
    @Resource
    private ActionTakenService actionTakenService;
    @Resource
    private ReasonForNotReachingOLevelService reasonForNotReachingOLevelService;
    @Resource
    private ServiceOfferedService serviceOfferedService;
    @Resource
    private LabTaskService labTaskService;
    @Resource
    private UserService userService;

    @GET
    @Path("/province")
    public List<Province> getProvinces() {
        return provinceService.getAll();
    }

    @GET
    @Path("/district")
    public List<District> getDistricts() {
        return districtService.getAll();
    }

    @GET
    @Path("/support-group")
    public List<SupportGroup> getSupportGroups() {
        return supportGroupService.getAll();
    }

    @GET
    @Path("/facility")
    public List<Facility> getFacilities() {
        return facilityService.getAll();
    }

    @GET
    @Path("/relationship")
    public List<Relationship> getRelationships() {
        return relationshipService.getAll();
    }

    @GET
    @Path("/refer")
    public List<Referer> getReferer() {
        return refererService.getAll();
    }

    @GET
    @Path("/orphan-status")
    public List<OrphanStatus> getOrphanStatus() {
        return orphanStatusService.getAll();
    }

    @GET
    @Path("/education")
    public List<Education> getEducation() {
        return educationService.getAll();
    }

    @GET
    @Path("/education-level")
    public List<EducationLevel> getEducationLevel() {
        return educationLevelService.getAll();
    }

    @GET
    @Path("/location")
    public List<Location> getLocation() {
        return locationService.getAll();
    }

    @GET
    @Path("/position")
    public List<Position> getPosition() {
        return positionService.getAll();
    }

    @GET
    @Path("/internal-referral")
    public List<InternalReferral> getInternalReferral() {
        return internalReferralService.getAll();
    }

    @GET
    @Path("/external-referral")
    public List<ExternalReferral> getExternalReferral() {
        return externalReferralService.getAll();
    }

    @GET
    @Path("/chronic-infection")
    public List<ChronicInfection> getChronicInfection() {
        return chronicInfectionService.getAll();
    }

    @GET
    @Path("/standard-follow-up")
    public List<Stable> getStable() {
        return stableService.getAll();
    }

    @GET
    @Path("/enhanced-follow-up")
    public List<Enhanced> getEnhanced() {
        return enhancedService.getAll();
    }

    @GET
    @Path("/service-referred")
    public List<ServicesReferred> getServicesReferred() {
        return servicesReferredService.getAll();
    }

    @GET
    @Path("/hiv-co-infection")
    public List<HivCoInfection> getHivConInfection() {
        return hivCoInfectionService.getAll();
    }

    @GET
    @Path("/mental-health")
    public List<MentalHealth> getMentalHealth() {
        return mentalHealthService.getAll();
    }

    @GET
    @Path("/disability-category")
    public List<DisabilityCategory> getDisabilityCategory() {
        return disabilityCategoryService.getAll();
    }

    @GET
    @Path("/assessment")
    public List<Assessment> getAssessment() {
        return assessmentService.getAll();
    }

    @GET
    @Path("/arv-medicine")
    public List<ArvMedicine> getArvMedicine() {
        return arvMedicineService.getAll();
    }

    @GET
    @Path("/hospitalisation-cause")
    public List<HospCause> getHospCause() {
        return hospService.getAll();
    }

    @GET
    @Path("/substance")
    public List<Substance> getSubstance() {
        return substanceService.getAll();
    }

    @GET
    @Path("/action-taken")
    public List<ActionTaken> getActionTaken() {
        return actionTakenService.getAll();
    }

    @GET
    @Path("/reason-for-not-reaching-o-level")
    public List<ReasonForNotReachingOLevel> getReasonForNotReachingOLevel() {
        return reasonForNotReachingOLevelService.getAll();
    }

    @GET
    @Path("/service-offered")
    public List<ServiceOffered> getServicesOffered() {
        return serviceOfferedService.getAll();
    }

    @GET
    @Path("/user")
    public List<UserDTO> getUsers() {
        List<UserDTO> list = new ArrayList<>();
        for (User user : userService.getAll()) {
            list.add(UserDTO.getInstance(user));
        }
        return list;
    }

    @GET
    @Path("/lab-task-service")
    public List<LabTask> getLabTaskService() {
        return labTaskService.getAll();
    }
}*/
