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
    private TbIptService tbIptService;
    @Resource
    private ServicesReferredService servicesReferredService;
    @Resource
    private HivCoInfectionService hivCoInfectionService;
    @Resource
    private MentalHealthScreeningService mentalHealthScreeningService;
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
    @Resource
    private FeatureRequestService featureRequestService;
    @Resource
    private MessageService messageService;
    @Resource
    private BugReportService bugReportService;


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
            for (SupportGroup supportGroup : supportGroups) {
                supportGroupDTOS.add(new SupportGroupDTO(supportGroup));
            }
        }else if(currentUser.getUserLevel()==UserLevel.FACILITY || currentUser.getUserLevel()==null ){
            CatDetail catDetail=catDetailService.getByEmail(currentUser.getUserName());
            supportGroups=supportGroupService.getByDistrict(catDetail.getPrimaryClinic().getDistrict());
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

    @PostMapping("/add-patients")
    public ResponseEntity<?> addPatient(@RequestBody List<PatientDTO> patientDTOS) {

        Response response = new Response(200, "Saved successfully", "");
        try {
            User user = userService.getCurrentUser();

            for (PatientDTO patientDTO : patientDTOS) {
                Patient patient = new Patient(patientDTO);
                patient.setCreatedBy(user);
                patient.setDateCreated(new Date());
                patient.setActive(true);
                patient.setRecordSource(RecordSource.MOBILE_APP);

                String facilityId = patientDTO.getPrimaryClinic();
                Facility facility=facilityService.get(facilityId);
                patient.setPrimaryClinic(facility);

                String educationId=patientDTO.getEducation();
                Education education=educationService.get(educationId);
                patient.setEducation(education);

                String educationLevelId=patientDTO.getEducationLevel();
                EducationLevel educationLevel=educationLevelService.get(educationLevelId);
                patient.setEducationLevel(educationLevel);

                String supportGroupId=patientDTO.getSupportGroup();
                SupportGroup supportGroup=supportGroupService.get(supportGroupId);
                patient.setSupportGroup(supportGroup);

                String mobileOwnerRelationId=patientDTO.getMobileOwnerRelation();
                if(mobileOwnerRelationId!=null && !mobileOwnerRelationId.isEmpty()){
                    System.err.println("Mobile Owner Relation :"+mobileOwnerRelationId);
                    Relationship mobileOwnerRelationship=relationshipService.get(mobileOwnerRelationId);
                    patient.setMobileOwnerRelation(mobileOwnerRelationship);
                }

                String secondaryMobileOwnerRelationId=patientDTO.getSecondaryMobileownerRelation();
                if(secondaryMobileOwnerRelationId!=null && !secondaryMobileOwnerRelationId.isEmpty()){
                    Relationship secondaryMobileOwnerRelationship=relationshipService.get(secondaryMobileOwnerRelationId);
                    patient.setSecondaryMobileownerRelation(secondaryMobileOwnerRelationship);
                }

                String referer=patientDTO.getReferer();
                Referer referer1=refererService.get(referer);
                patient.setReferer(referer1);


                Patient patient1 = patientService.save(patient);

            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + user.getUserName() + " saved new Clients items :" + patientDTOS.size() + "\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-mental-health-screening-items")
    @Transactional
    public ResponseEntity<?> addMHScreenings(@RequestBody List<MentalHealthScreeningDTO> mentalHealthScreeningDTOS) {

        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();

            for(MentalHealthScreeningDTO mentalHealthScreeningDTO: mentalHealthScreeningDTOS){
                Patient patient=patientService.get(mentalHealthScreeningDTO.getPatient());

                MentalHealthScreening mentalHealthScreening=new MentalHealthScreening(mentalHealthScreeningDTO);
                mentalHealthScreening.setCreatedBy(user);
                mentalHealthScreening.setDateCreated(new Date());
                mentalHealthScreening.setPatient(patient);
                mentalHealthScreening.setActive(true);
                mentalHealthScreening.setRecordSource(RecordSource.MOBILE_APP);
                MentalHealthScreening mentalHealthScreening1=mentalHealthScreeningService.save(mentalHealthScreening);

            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved MH Screening test items :"+mentalHealthScreeningDTOS.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-tb-tpt-screening-items")
    @Transactional
    public ResponseEntity<?> addTBScreenings(@RequestBody List<TbTPTDTO> tbTPTDTOS) {

        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();

            for(TbTPTDTO tbTPTDTO: tbTPTDTOS){
                Patient patient=patientService.get(tbTPTDTO.getPatient());

                TbIpt tbIpt=new TbIpt(tbTPTDTO);
                tbIpt.setCreatedBy(user);
                tbIpt.setDateCreated(new Date());
                tbIpt.setPatient(patient);
                tbIpt.setActive(true);
                tbIpt.setRecordSource(RecordSource.MOBILE_APP);
                TbIpt tbIpt1=tbIptService.save(tbIpt);

            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved TB Screening test items :"+tbTPTDTOS.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-messages")
    public ResponseEntity<?> addMesssages(@RequestBody MessageDTO messageDTO){
        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();


                Message message=new Message(messageDTO);
                message.setCreatedBy(user);
                message.setDateCreated(new Date());
                message.setActive(true);
                message.setRecordSource(RecordSource.MOBILE_APP);
                messageService.save(message);


            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Message items \n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-features-request")
    public ResponseEntity<?> addFeatureRequest(@RequestBody FeatureRequestDTO featureRequestDTO){
        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();


                FeatureRequest featureRequest=new FeatureRequest(featureRequestDTO);
                featureRequest.setCreatedBy(user);
                featureRequest.setDateCreated(new Date());
                featureRequest.setActive(true);
                featureRequest.setRecordSource(RecordSource.MOBILE_APP);

                featureRequestService.save(featureRequest);
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Feature request item \n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-bug-reports")
    public ResponseEntity<?> addBugReports(@RequestBody BugReportDTO bugReportDTO){
        Response response=new Response(200,"Saved successfully","");
        try {
            User user=userService.getCurrentUser();


                BugReport bugReport= new BugReport(bugReportDTO);
                bugReport.setCreatedBy(user);
                bugReport.setDateCreated(new Date());
                bugReport.setActive(true);
                bugReport.setRecordSource(RecordSource.MOBILE_APP);
                if(bugReport.getStatus()==null || bugReport.getStatus().toString().isEmpty()){
                    bugReport.setStatus(BugStatus.PENDING);
                }

                bugReportService.save(bugReport);

            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Bug Report item \n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-bug-reports")
    public ResponseEntity<?> getBugReports(@RequestParam("all") Boolean all){
        Response response=new Response(200,"Saved successfully","");
        List<BugReportDTO> bugReports=new ArrayList<>();
        try {
            User user=userService.getCurrentUser();
            System.err.println("ALL : "+all);
            List<BugReport> bugReports1=bugReportService.getAllByCreatedBy(user);
            for(BugReport bugReport: bugReports1){
                bugReports.add(new BugReportDTO(bugReport));
            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled Bug Report items :"+bugReports.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bugReports, HttpStatus.OK);
    }

    @GetMapping("/get-messages")
    public ResponseEntity<?> getMessages(){
        Response response=new Response(200,"Saved successfully","");
        List<MessageDTO> messages=new ArrayList<>();
        try {
            User user=userService.getCurrentUser();

            List<Message> messages1=messageService.getAllByCreatedBy(user);
            for(Message message: messages1){
                messages.add(new MessageDTO(message));
            }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled messages items :"+messages.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/get-features-request")
    public ResponseEntity<?> getFeatureRequest(){
        Response response=new Response(200,"Saved successfully","");
        List<FeatureRequestDTO> featureRequests=new ArrayList<>();
        try {
            User user=userService.getCurrentUser();

             List<FeatureRequest> featureRequests1=featureRequestService.getAllByCreatedBy(user);
             for(FeatureRequest featureRequest: featureRequests1){
                 featureRequests.add(new FeatureRequestDTO(featureRequest));
             }
            System.err.println("\n\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled Feature request items :"+featureRequests.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(featureRequests, HttpStatus.OK);
    }

}
