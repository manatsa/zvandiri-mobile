package zw.org.zvandiri.controllers;


import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.domain.Referral;
import zw.org.zvandiri.business.domain.dto.*;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.repo.CatDetailRepo;
import zw.org.zvandiri.business.repo.ContactRepo;
import zw.org.zvandiri.business.repo.PatientRepo;
import zw.org.zvandiri.business.service.*;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.MobilePatientDTO;
import zw.org.zvandiri.business.util.dto.MobileStaticsDTO;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/patient/")
public class MobilePatientResource {

    @Resource
    private CatDetailService catDetailService;
    @Resource
    private ContactService contactService;
    @Resource
    private PatientService patientService;
    @Resource
    PatientRepo patientRepo;
    @Resource
    private ReferralService referralService;
    @Resource
    private UserService userService;

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
    private InvestigationTestService investigationTestService;


    @GetMapping("/initial-statics")
    public MobileStaticsDTO getCatPatients(@RequestParam("email") String email) {
        long start=System.currentTimeMillis();
        User currentUser=userService.getCurrentUser();
        List<Patient> patients=new ArrayList<>();
        MobileStaticsDTO mobileStaticsDTO = new MobileStaticsDTO();
        User logUser=userService.findByUserName(email);
        if(logUser.getUserLevel()==null ||logUser.getUserLevel().equals(UserLevel.FACILITY)){
            CatDetail catDetail = catDetailService.getByEmail(email);
            currentUser.setFacilityId(catDetail.getPrimaryClinic().getId());
            patients = patientService.getFacilityPatients(catDetail);
        }else if(logUser.getUserLevel().equals(UserLevel.DISTRICT)){
            patients=patientService.getActiveByDistrict(logUser.getDistrict());
        }else if(logUser.getUserLevel().equals(UserLevel.PROVINCE)){
            patients=patientService.getActiveByProvince(logUser.getProvince());
        }else{
            patients=patientService.getAll();
        }

        List<PatientListDTO> patientListDTOS=new ArrayList<>();
        for(Patient patient: patients){
            patientListDTOS.add(new PatientListDTO(patient));
        }
        List<District> districts = districtService.getAll();
        List<DistrictDTO> districtDTOS=new ArrayList<>();
        for (District district: districts) {
            districtDTOS.add(new DistrictDTO(district));
        }
        List<Province> provinces = provinceService.getAll();
        List<ProvinceDTO> provinceDTOS=new ArrayList<>();
        for(Province province: provinces){
            provinceDTOS.add(new ProvinceDTO(province));
        }
        List<Facility> facilities = facilityService.getAll();
        List<FacilityDTO> facilityDTOS = new ArrayList<>();
        if(currentUser.getUserLevel()==UserLevel.DISTRICT) {
            facilities = facilityService.getOptByDistrict(currentUser.getDistrict());
            for (Facility facility : facilities) {
                facilityDTOS.add(new FacilityDTO(facility));
            }
        }

        List<SupportGroup> supportGroups=supportGroupService.getAll();
        List<SupportGroupDTO> supportGroupDTOS=new ArrayList<>();
        if(currentUser.getUserLevel()==UserLevel.DISTRICT) {
            supportGroups=supportGroupService.getByDistrict(currentUser.getDistrict());
            System.err.println("1. "+supportGroups);
            for (SupportGroup supportGroup : supportGroups) {
                supportGroupDTOS.add(new SupportGroupDTO(supportGroup));
            }
        }else if(currentUser.getUserLevel()==UserLevel.FACILITY || currentUser.getUserLevel()==null ){
            CatDetail catDetail=catDetailService.getByEmail(currentUser.getUserName());
            supportGroups=supportGroupService.getByDistrict(catDetail.getPrimaryClinic().getDistrict());
            System.err.println("2. "+supportGroups);
            for (SupportGroup supportGroup : supportGroups) {
                supportGroupDTOS.add(new SupportGroupDTO(supportGroup));
            }
        }

        List<ServiceOffered> servicesOffered = serviceOfferedService.getAll();
        List<ServiceOfferredDTO> serviceOfferredDTOS=new ArrayList<>();
        for(ServiceOffered serviceOffered: servicesOffered){
            serviceOfferredDTOS.add(new ServiceOfferredDTO(serviceOffered));
        }
        List<ServicesReferred> services = servicesReferredService.getAll();
        List<ServiceReferredDTO> hivServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.HIV_STI_PREVENTION)).collect(Collectors.toList())){
            hivServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }
        List<ServiceReferredDTO> srhServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.SRH_SERVICES)).collect(Collectors.toList())){
            srhServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }
        List<ServiceReferredDTO> tbServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.TB_SERVICES)).collect(Collectors.toList())){
            tbServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }

        List<ServiceReferredDTO> oiServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.OI_ART_SERVICES)).collect(Collectors.toList())){
            oiServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }

        List<ServiceReferredDTO> legalServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.LEGAL_SUPPORT)).collect(Collectors.toList())){
            legalServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }

        List<ServiceReferredDTO> labServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.LABORATORY_DIAGNOSES)).collect(Collectors.toList())){
            labServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }

        List<ServiceReferredDTO> psychoServiceReferredDTOS=new ArrayList<>();
        for(ServicesReferred servicesReferred1: services.stream().filter(s1 -> s1.getReferalType().equals(ReferalType.PSYCHO_SOCIAL_SUPPORT)).collect(Collectors.toList())){
            psychoServiceReferredDTOS.add(new ServiceReferredDTO(servicesReferred1));
        }

        List<Assessment> assessments = assessmentService.getAll();
        List<AssessmentDTO> assessmentDTOS= new ArrayList<>();
        for(Assessment assessment: assessments){
            assessmentDTOS.add(new AssessmentDTO(assessment));
        }
        List<Location> locations=locationService.getAll();
        List<LocationDTO> locationDTOS=new ArrayList<>();
        for(Location location: locations){
            locationDTOS.add(new LocationDTO(location));
        }
        List<Position> positions=positionService.getAll();
        List<PositionDTO> positionDTOS= new ArrayList<>();
        for(Position position: positions){
            positionDTOS.add(new PositionDTO(position));
        }

        List<Relationship> relationships=relationshipService.getAll();
        List<RelationshipDTO> relationshipDTOS=new ArrayList<>();
        for(Relationship relationship: relationships){
            relationshipDTOS.add(new RelationshipDTO(relationship));
        }

        List<Referer> referers =refererService.getAll();
        List<RefererDTO> refererDTOS= new ArrayList<>();
        for(Referer referer: referers){
            refererDTOS.add(new RefererDTO(referer));
        }

        List<EducationLevel> educationLevels=educationLevelService.getAll();
        List<EducationLevelDTO> educationLevelDTOS=new ArrayList<>();
        for(EducationLevel educationLevel: educationLevels){
            educationLevelDTOS.add(new EducationLevelDTO(educationLevel));
        }

        List<Education> educations=educationService.getAll();
        List<EducationDTO> educationDTOS=new ArrayList<>();
        for(Education education: educations){
            educationDTOS.add(new EducationDTO(education));
        }

        List<ReasonForNotReachingOLevel> reasonForNotReachingOLevels=reasonForNotReachingOLevelService.getAll();
        List<ReasonForNotReachingOLvelDTO> reasonForNotReachingOLvelDTOS= new ArrayList<>();
        for(ReasonForNotReachingOLevel reasonForNotReachingOLevel: reasonForNotReachingOLevels){
            reasonForNotReachingOLvelDTOS.add(new ReasonForNotReachingOLvelDTO(reasonForNotReachingOLevel));
        }






        mobileStaticsDTO.setPatients(patientListDTOS);
        mobileStaticsDTO.setDistricts(districtDTOS);
        mobileStaticsDTO.setProvinces(provinceDTOS);
        mobileStaticsDTO.setFacilities(facilityDTOS);
        mobileStaticsDTO.setServiceOffereds(serviceOfferredDTOS);
        mobileStaticsDTO.setHivStiServicesReq(hivServiceReferredDTOS);
        mobileStaticsDTO.setLaboratoryReq(labServiceReferredDTOS);
        mobileStaticsDTO.setLegalReq(legalServiceReferredDTOS);
        mobileStaticsDTO.setPsychReq(psychoServiceReferredDTOS);
        mobileStaticsDTO.setOiArtReq(oiServiceReferredDTOS);
        mobileStaticsDTO.setTbReq(tbServiceReferredDTOS);
        mobileStaticsDTO.setSrhReq(srhServiceReferredDTOS);
        mobileStaticsDTO.setAssessments(assessmentDTOS); //2.19
        mobileStaticsDTO.setCurrentUser(currentUser);
        mobileStaticsDTO.setLocations(locationDTOS);
        mobileStaticsDTO.setPositions(positionDTOS);
        mobileStaticsDTO.setRelationships(relationshipDTOS);
        mobileStaticsDTO.setReferers(refererDTOS);
        mobileStaticsDTO.setEducations(educationDTOS);
        mobileStaticsDTO.setEducationLevels(educationLevelDTOS);
        mobileStaticsDTO.setSupportGroups(supportGroupDTOS);
        mobileStaticsDTO.setReasonForNotReachingOLevels(reasonForNotReachingOLvelDTOS);

        long end = System.currentTimeMillis();
        long time=(end-start)/(60000);
        String timeTaken=time<120 ? time+" mins": ((double)Math.round(time/60))+" hrs";
        System.err.println("<= User::" + email + " => Statics,********, User : "+userService.getCurrentUsername()+" <> "+
                patients.size()+" patients retrieved ******** Time Taken:: "+timeTaken);


        return mobileStaticsDTO;
    }

    @GetMapping("/get-cats")
    public CatDetail getCats(@RequestParam("email") String email) {
        return catDetailService.getByEmail(email);
    }

    @PostMapping(value = "/add-contacts")
    @Transactional
    public ResponseEntity<?> addContact(@RequestBody List<ContactDTO> contactDTOS) {
        Response response=new Response(200,"Saved successfully","");


        try {
            List<BaseEntity> savedContas=new ArrayList<>();
            User user=userService.getCurrentUser();

            for(ContactDTO contactDTO: contactDTOS){
                Patient patient=patientService.get(contactDTO.getPatient());
                Position position=positionService.get(contactDTO.getPosition());
                Location location=locationService.get(contactDTO.getLocation());
                Contact contact=new Contact(contactDTO);
                contact.setCreatedBy(user);
                contact.setDateCreated(new Date());
                contact.setPatient(patient);
                contact.setLocation(location);
                contact.setPosition(position);
                contact.setRecordSource(RecordSource.MOBILE_APP);
                Set<Assessment> nonClinicals=new HashSet<>();
                for(String assessment:contactDTO.getNonClinicalAssessments()){
                    Assessment assessment1=assessmentService.get(assessment);
                    nonClinicals.add(assessment1);
                }
                contact.setNonClinicalAssessments(nonClinicals);
                Set<Assessment> clinicals=new HashSet<>();
                for(String assessment:contactDTO.getClinicalAssessments()){
                    Assessment assessment1=assessmentService.get(assessment);
                    clinicals.add(assessment1);
                }
                contact.setClinicalAssessments(clinicals);
                Set<ServiceOffered> serviceOffereds=new HashSet<>();
                for(String service:contactDTO.getServiceOffereds()){
                    ServiceOffered services=serviceOfferedService.get(service);
                    serviceOffereds.add(services);
                }
                contact.setServiceOffereds(serviceOffereds);
                Set<ServicesReferred> servicesReferreds=new HashSet<>();
                for(String service:contactDTO.getServicereferreds()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service);
                    servicesReferreds.add(servicesReferred);
                }
                contact.setServicereferreds(servicesReferreds);
                Contact contact1= contactService.save(contact);
            }
            System.err.println("\n\n******************** user :"+user.getUserName()+" saved contacts :"+contactDTOS.size()+"\n\n");
           response.setBaseEntities(savedContas);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-vlcd4s")
    @Transactional
    public ResponseEntity<?> addVLCD4(@RequestBody List<VLCD4DTO> vlcd4DTOS) {
        System.err.println("Sent Contact : " + vlcd4DTOS);
        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();

            for(VLCD4DTO vlcd4DTO: vlcd4DTOS){
                Patient patient=patientService.get(vlcd4DTO.getPatient());

                InvestigationTest test=new InvestigationTest(vlcd4DTO);
                test.setCreatedBy(user);
                test.setDateCreated(new Date());
                test.setPatient(patient);
                test.setActive(true);
                test.setRecordSource(RecordSource.MOBILE_APP);
               InvestigationTest investigationTest=investigationTestService.save(test);

            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved investigation test items :"+vlcd4DTOS.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-referrals")
    @Transactional
    public ResponseEntity<?> addReferrals(@RequestBody List<ReferralDTO> referralDTOS) {
        Response response=new Response(200,"Saved successfully","");


        try {
            List<BaseEntity> savedReferrals=new ArrayList<>();
            User user=userService.getCurrentUser();

            for(ReferralDTO referralDTO: referralDTOS){
                Patient patient=patientService.get(referralDTO.getPatient());

                Referral referral=new Referral(referralDTO);
                referral.setPatient(patient);
                referral.setCreatedBy(user);
                referral.setDateCreated(new Date());
                referral.setPatient(patient);
                referral.setRecordSource(RecordSource.MOBILE_APP);
                Set<ServicesReferred> hivServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getHivStiServicesAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    hivServicesAvailed.add(servicesReferred);
                }
                referral.setHivStiServicesAvailed(hivServicesAvailed);

                Set<ServicesReferred> hivServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getHivStiServicesReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    hivServicesReq.add(servicesReferred);
                }
                referral.setHivStiServicesReq(hivServicesReq);

                Set<ServicesReferred> srhServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getSrhReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    srhServicesReq.add(servicesReferred);
                }
                referral.setSrhReq(srhServicesReq);

                Set<ServicesReferred> srhServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getSrhAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    srhServicesAvailed.add(servicesReferred);
                }
                referral.setSrhAvailed(srhServicesAvailed);

                Set<ServicesReferred> psychoServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getPsychAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    psychoServicesAvailed.add(servicesReferred);
                }
                referral.setPsychAvailed(psychoServicesAvailed);

                Set<ServicesReferred> psychoServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getPsychReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    psychoServicesReq.add(servicesReferred);
                }
                referral.setPsychReq(psychoServicesReq);

                Set<ServicesReferred> labServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getLaboratoryReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    labServicesReq.add(servicesReferred);
                }
                referral.setLaboratoryReq(labServicesReq);

                Set<ServicesReferred> labServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getLaboratoryAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    labServicesAvailed.add(servicesReferred);
                }
                referral.setLaboratoryAvailed(labServicesAvailed);

                Set<ServicesReferred> tbServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getTbAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    tbServicesAvailed.add(servicesReferred);
                }
                referral.setTbAvailed(tbServicesAvailed);

                Set<ServicesReferred> tbServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getTbReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    tbServicesReq.add(servicesReferred);
                }
                referral.setTbReq(tbServicesReq);

                Set<ServicesReferred> oiServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getOiArtReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    oiServicesReq.add(servicesReferred);
                }
                referral.setOiArtReq(oiServicesReq);

                Set<ServicesReferred> oiServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getOiArtAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    oiServicesAvailed.add(servicesReferred);
                }
                referral.setOiArtAvailed(oiServicesAvailed);

                Set<ServicesReferred> legalServicesAvailed=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getLegalAvailed()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    legalServicesAvailed.add(servicesReferred);
                }
                referral.setLegalAvailed(legalServicesAvailed);

                Set<ServicesReferred> legalServicesReq=new HashSet<>();
                for(ServiceReferredDTO service:referralDTO.getLegalReq()){
                    ServicesReferred servicesReferred=servicesReferredService.get(service.getId());
                    legalServicesReq.add(servicesReferred);
                }
                referral.setLegalReq(legalServicesReq);

                Referral newRef=referralService.save(referral);


            }
            System.err.println("\n\n******************** user :"+user.getUserName()+" saved referrals :"+referralDTOS.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-patient")
    public ResponseEntity<Map<String, Object>> addPatient(Patient patient) {
        Map<String, Object> response = validatePatient(patient);
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            patientService.save(patient);
        } catch (Exception e) {
            response.put("message", "System error occurred saving patient");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Patient created sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-disability")
    public ResponseEntity<Map<String, Object>> addPatientDisabilities(Patient patient) {
        Map<String, Object> response = new HashMap<>();
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Set<PatientDisability> disabilitys = patient.getDisabilityCategorys();
            Patient item = patientService.get(patient.getId());
            for (PatientDisability disability : disabilitys) {
                disability.setPatient(item);
            }
            item.setDisabilityCategorys(disabilitys);
            patientService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "System error occurred saving patient");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Patient created sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/change-facility")
    public ResponseEntity<Map<String, Object>> changeFacility(Patient patient) {
        Map<String, Object> response = new HashMap<>();
        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Patient item = patientService.get(patient.getId());
            item.setPrimaryClinic(patient.getPrimaryClinic());
            patientService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "System error occurred saving patient");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Patient created sucessfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Object> validateReferral(Referral item) {
        Map<String, Object> response = new HashMap<>();
        if (item.getReferralDate() == null) {
            response.put("referralDate", "Field is required");
        }
        if (item.getOrganisation() == null) {
            response.put("organisation", "Field is required");
        }
        if (item.getReferralDate() != null && item.getReferralDate().after(new Date())) {
            response.put("referralDate", "Date cannot be in the future");
        }
        if (item.getReferralDate() != null && item.getPatient().getDateOfBirth() != null && item.getReferralDate().before(item.getPatient().getDateOfBirth())) {
            response.put("referralDate", "Date cannot be before individual was born");
        }
        if (item.getDateAttended() != null && item.getDateAttended().after(new Date())) {
            response.put("dateAttended", "Date cannot be in the future");
        }
        if (item.getDateAttended() != null && item.getPatient().getDateOfBirth() != null && item.getDateAttended().before(item.getPatient().getDateOfBirth())) {
            response.put("dateAttended", "Date cannot be before individual was born");
        }
        if ((item.getReferralDate() != null && item.getDateAttended() != null) && item.getDateAttended().before(item.getReferralDate())) {
            response.put("dateAttended", "Date attended cannot be before referral date");
        }
        // check that @least one section is checked
        boolean serviceReq = false;
        if ((item.getHivStiServicesReq() != null && !item.getHivStiServicesReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getLaboratoryReq() != null && !item.getLaboratoryReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getOiArtReq() != null && !item.getOiArtReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getLegalReq() != null && !item.getLegalReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getPsychReq() != null && !item.getPsychReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getSrhReq() != null && !item.getSrhReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq && (item.getTbReq() != null && !item.getTbReq().isEmpty())) {
            serviceReq = true;
        }
        if (!serviceReq) {
            response.put("servicesRequestedError", "item.select.one");
        }
        if (item.getDateAttended() != null) {
            if (item.getAttendingOfficer() == null) {
                response.put("attendingOfficer", "Field is required");
            }
            if (item.getDesignation() == null) {
                response.put("designation", "Field is required");
            }
            if (item.getActionTaken() == null) {
                response.put("actionTaken", "Field is required");
            }
        }
        return response;
    }

    private Map<String, Object> validatePatient(Patient item) {
        Map<String, Object> response = new HashMap<>();
        String ZIMBABWE = "\\d{10}";
        if (StringUtils.isEmpty(item.getFirstName())) {
            response.put("firstName", "Field is required");
        }
        if (StringUtils.isEmpty(item.getLastName())) {
            response.put("lastName", "Field is required");
        }
        if (item.getDateOfBirth() == null) {
            response.put("dateOfBirth", "Field is required");
        }
        if (item.getGender() == null) {
            response.put("gender", "Field is required");
        }
        if (item.getDateOfBirth() != null) {
            if (item.getDateOfBirth().after(new Date())) {
                response.put("dateOfBirth", "Date cannot be in the future");
            }
            if (item.getDateOfBirth().before(DateUtil.getDateFromAge(30))) {
                response.put("dateOfBirth", "Date too early");
            }
        }
        if (StringUtils.isNotEmpty(item.getMobileNumber()) && !item.getMobileNumber().matches(ZIMBABWE)) {
            response.put("mobileNumber", "Mobile number not valid");
        }
        if (StringUtils.isNotEmpty(item.getMobileNumber()) && item.getMobileOwner() == null) {
            response.put("mobileOwner", "Field is required");
        }
        if (item.getMobileOwner() != null && item.getMobileOwner().equals(YesNo.NO) && item.getOwnerName() == null) {
            response.put("ownerName", "Field is required");
        }
        if (item.getMobileOwner() != null && item.getMobileOwner().equals(YesNo.NO) && item.getMobileOwnerRelation() == null) {
            response.put("mobileOwnerRelation", "Field is required");
        }
        if ((item.getOwnSecondaryMobile() != null && item.getOwnSecondaryMobile().equals(YesNo.YES)) && StringUtils.isEmpty(item.getSecondaryMobileNumber())) {
            response.put("secondaryMobileNumber", "Field is required");
        }
        if (StringUtils.isNotEmpty(item.getSecondaryMobileNumber()) && item.getOwnSecondaryMobile() == null) {
            response.put("ownSecondaryMobile", "Field is required");
        }
        if (item.getOwnSecondaryMobile() != null && item.getOwnSecondaryMobile().equals(YesNo.NO) && StringUtils.isEmpty(item.getSecondaryMobileOwnerName())) {
            response.put("secondaryMobileOwnerName", "Field is required");
        }
        if ((item.getOwnSecondaryMobile() != null && item.getOwnSecondaryMobile().equals(YesNo.NO)) && item.getSecondaryMobileownerRelation() == null) {
            response.put("secondaryMobileownerRelation", "Field is required");
        }
        if ((item.getOwnSecondaryMobile() != null && item.getOwnSecondaryMobile().equals(YesNo.NO)) && StringUtils.isEmpty(item.getSecondaryMobileOwnerName())) {
            response.put("secondaryMobileOwnerName", "Field is required");
        }
        if (StringUtils.isEmpty(item.getAddress())) {
            response.put("address", "Field is required");
        }
        if (item.getPrimaryClinic() == null) {
            response.put("primaryClinic", "Field is required");
        }
        if (item.getSupportGroup() == null) {
            response.put("supportGroup", "Field is required");
        }
        if (StringUtils.isNotEmpty(item.getFirstName()) && StringUtils.isNotEmpty(item.getLastName()) && item.getDateOfBirth() != null && item.getPrimaryClinic() != null) {
            if (patientService.checkDuplicate(item, null)) {
                response.put("patientExist", "Patient already exists");
            }
        }
        if (item.getDateJoined() == null) {
            response.put("dateJoined", "Field is required");
        }
        if (StringUtils.isEmpty(item.getRefererName())) {
            response.put("refererName", "Field is required");
        }
        if (item.getEducation() == null) {
            response.put("education", "Field is required");
        }
        if (item.getEducationLevel() == null) {
            response.put("educationLevel", "Field is required");
        }
if (item.getEducation() != null && item.getEducation().getName().equalsIgnoreCase("Out of School")) {
            if ((item.getEducationLevel() != null
                    && (item.getEducationLevel().getName().equalsIgnoreCase("N/A")
                    || item.getEducationLevel().getName().equalsIgnoreCase("Primary School")))
                    && item.getReasonForNotReachingOLevel() == null) {
                response.put("reasonForNotReachingOLevel", "Field is required");
            }
        }

        if (item.getReferer() == null) {
            response.put("referer", "Field is required");
        }
        if (item.getDateJoined() != null && item.getDateJoined().after(new Date())) {
            response.put("dateJoined", "Date cannot be in the future");
        }
        if (item.getDateJoined() != null && item.getDateOfBirth() != null && item.getDateJoined().before(item.getDateOfBirth())) {
            response.put("dateJoined", "Date cannot be before individual was born");
        }
        if (item.getHivStatusKnown() != null && item.getHivStatusKnown().equals(YesNo.YES) && item.getTransmissionMode() == null) {
            response.put("transmissionMode", "Field is required");
        }
        if (item.getDateTested() != null && item.getDateTested().after(new Date())) {
            response.put("dateTested", "Date cannot be in the future");
        }
        if (item.getDateTested() != null && item.getDateOfBirth() != null && item.getDateTested().before(item.getDateOfBirth())) {
            response.put("dateTested", "Date cannot be before individual was born");
        }
        if (item.getHivStatusKnown() != null && item.getHivStatusKnown().equals(YesNo.YES)) {
            if (item.gethIVDisclosureLocation() == null) {
                response.put("hIVDisclosureLocation", "Field is required");
            }
        }
        if (item.getDisability() != null && item.getDisability().equals(YesNo.YES) && item.getDisabilityCategorys() == null) {
            response.put("disabilityCategorys", "Select at least one item in this list");
        }
        if (item.getYoungMumGroup() != null && item.getYoungMumGroup().equals(YesNo.YES) && (item.getGender() != null && item.getGender().equals(Gender.MALE))) {
            response.put("youngMumGroup", "Only female beneficiaries can be in this group");
        }
        if ((item.getGender() != null && item.getGender().equals(Gender.FEMALE)) && item.getYoungMumGroup() != null && item.getYoungMumGroup().equals(YesNo.YES) && (item.getDateOfBirth() != null && item.getAge() <= 10)) {
            response.put("youngMumGroup", "Too young to be a mom");
        }
        if (item.getCat() != null && item.getCat().equals(YesNo.YES) && (item.getDateOfBirth() != null && item.getAge() <= 10)) {
            response.put("youngMumGroup", "Too young to be a CATS");
        }
        return response;
    }

    private Map<String, Object> validateContact(Contact item) {
        Map<String, Object> response = new HashMap<>();
        if (item.getContactDate() == null) {
            response.put("contactDate", "Field is required");
        }
        if (item.getCareLevel() == null) {
            response.put("careLevel", "Field is required");
        }
        if (item.getLocation() == null) {
            response.put("location", "Field is required");
        }
        if (item.getPosition() == null) {
            response.put("position", "Field is required");
        }
        if (item.getContactDate() != null && item.getContactDate().after(new Date())) {
            response.put("contactDate", "Date cannot be in the future");
        }
        if (item.getContactDate() != null && item.getPatient().getDateOfBirth() != null && item.getContactDate().before(item.getPatient().getDateOfBirth())) {
            response.put("contactDate", "Date cannot be before individual was born");
        }
        if (item.getLastClinicAppointmentDate() != null && item.getLastClinicAppointmentDate().after(new Date())) {
            response.put("lastClinicAppointmentDate", "Date cannot be in the future");
        }
        if (item.getLastClinicAppointmentDate() != null && item.getPatient().getDateOfBirth() != null && item.getLastClinicAppointmentDate().before(item.getPatient().getDateOfBirth())) {
            response.put("lastClinicAppointmentDate", "Date cannot be before individual was born");
        }
        if (item.getLastClinicAppointmentDate() != null && item.getAttendedClinicAppointment() == null) {
            response.put("attendedClinicAppointment", "Field is required");
        }
        return response;
    }


}
