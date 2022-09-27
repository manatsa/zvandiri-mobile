package zw.org.zvandiri.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zw.org.zvandiri.business.domain.Referral;
import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.domain.dto.*;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.repo.*;
import zw.org.zvandiri.business.service.*;
import zw.org.zvandiri.business.util.dto.MobileStaticsDTO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/patient/")
public class MobilePatientResource implements Serializable {

    @Resource
    private CatDetailService catDetailService;
    @Resource
    private ContactService contactService;
    @Resource
    private PatientService patientService;
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
    private MentalHealthScreeningService mentalHealthScreeningService;
    @Resource
    private AssessmentService assessmentService;
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
    @Autowired
    ContactRepo contactRepo;
    @Autowired
    InvestigationTestRepo investigationTestRepo;
    @Autowired
    TbIptRepo tbIptRepo;
    @Autowired
    MentalHealthScreeningRepo mentalHealthScreeningRepo;
    @Autowired
    ReferralRepo referralRepo;
    @Autowired
    PatientRepo patientRepo;

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public MobilePatientResource() {
    }

    @GetMapping("/initial-statics")
    public MobileStaticsDTO getCatPatients(@RequestParam("email") String email) {
        User currentUser=userService.getCurrentUser();
        long start=System.currentTimeMillis();
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
        assessments.stream().forEach(assessment -> {
            assessmentDTOS.add(new AssessmentDTO(assessment));
        });


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
        System.err.println("\n<= INITIAL SYNC => User::" + email + " ******** District::"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" <> "+
                patients.size()+" patients retrieved, v2 ******** Time Taken:: "+timeTaken+"\n");


        return mobileStaticsDTO;
    }

    @GetMapping("/refresh-patients")
    public List<PatientListDTO> refreshCatPatients() {
        User currentUser=userService.getCurrentUser();
        long start=System.currentTimeMillis();
        List<Patient> patients=new ArrayList<>();
        User logUser=userService.findByUserName(currentUser.getUserName());
        if(logUser.getUserLevel()==null ||logUser.getUserLevel().equals(UserLevel.FACILITY)){
            CatDetail catDetail = catDetailService.getByEmail(currentUser.getUserName());
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

        long end = System.currentTimeMillis();
        long time=(end-start)/(60000);
        String timeTaken=time<120 ? time+" mins": ((double)Math.round(time/60))+" hrs";
        System.err.println("\n<= User::" + currentUser.getUserName() + " => Refresh Patients,********, User : "+userService.getCurrentUsername()+" <> "+
                patients.size()+" patients retrieved, v2 ******** Time Taken:: "+timeTaken+"\n");


        return patientListDTOS;
    }

    @GetMapping("/get-patient-stats")
    public ResponseEntity<?> getPatientStats(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, @RequestParam(value = "patient", required = false) String patient, @RequestParam(value = "facility", required = false) String primaryClinic) {
        User currentUser=userService.getCurrentUser();
        long starttime=System.currentTimeMillis();
        LineItemsDTO lineItemsDTO=new LineItemsDTO();

                if(primaryClinic!=null && !primaryClinic.isEmpty() && primaryClinic.equals("ALL")){
                    District district=currentUser.getDistrict();
                    List<Patient> patients=patientRepo.findByDistrictInGivenTime(start, end, district.getId());
                    List<Contact> contacts=contactRepo.findByDistrictAndContactDates(district, start, end);
                    List<MentalHealthScreening> mentalHealthScreenings=mentalHealthScreeningRepo.findByDistrictInGivenTime(start, end, district.getId());
                    List<InvestigationTest> investigationTests=investigationTestRepo.findByDistrictInGivenTime(start, end, district.getId());
                    List<TbIpt> tbIpts = tbIptRepo.findByDistrictInGivenTime(start, end, district.getId());
                    List<Referral> referrals=referralRepo.findByDistrictInGivenTime(start,end, district.getId());

                    List<PatientDTO> patientDTOList=patients.stream().map(patientz -> new PatientDTO(patientz)).collect(Collectors.toList());
                    List<ContactDTO> contactDTOList=contacts.stream().map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
                    List<MentalHealthScreeningDTO> mentalHealthScreeningDTOList=mentalHealthScreenings.stream().map(mentalHealthScreening -> new MentalHealthScreeningDTO(mentalHealthScreening)).collect(Collectors.toList());
                    List<TbTPTDTO> tbTPTDTOList=tbIpts.stream().map(tbIpt -> new TbTPTDTO(tbIpt)).collect(Collectors.toList());
                    List<VLCD4DTO> vlcd4DTOList=investigationTests.stream().map(investigationTest -> new VLCD4DTO(investigationTest)).collect(Collectors.toList());
                    List<ReferralDTO> referralDTOList=referrals.stream().map(referral -> new ReferralDTO(referral)).collect(Collectors.toList());

                    lineItemsDTO.setPatients(patientDTOList);
                    lineItemsDTO.setContacts(contactDTOList);
                    lineItemsDTO.setInvestigationTests(vlcd4DTOList);
                    lineItemsDTO.setReferrals(referralDTOList);
                    lineItemsDTO.setTbIpts(tbTPTDTOList);
                    lineItemsDTO.setMentalHealthScreenings(mentalHealthScreeningDTOList);

                    long endtime = System.currentTimeMillis();
                    long time=(endtime-starttime)/(60000);
                    String timeTaken=time<120 ? time+" mins": ((double)Math.round(time/60))+" hrs";
                    System.err.println("\n<<<<<<<<<<<< = User::" + currentUser.getUserName() + " **** "+"DISTRICT::"+district.getName()+"  <>  patients Line Items retrieved ******** Time Taken:: "+timeTaken+"\n");

                }
                else if(primaryClinic!=null && !primaryClinic.isEmpty()){
                    Facility facility=facilityService.get(primaryClinic);

                        List<Patient> patients = patientRepo.findByFacilityInGivenTime(start, end, facility.getId());
                        List<Contact> contacts = contactRepo.findByFacilityAndContactDates(facility, start, end);
                        List<MentalHealthScreening> mentalHealthScreenings = mentalHealthScreeningRepo.findByFacilityAndDateScreenedBetween(facility, start, end);
                        List<InvestigationTest> investigationTests = investigationTestRepo.findByFacilityAndDateTakenBetween(facility, start, end);
                        List<TbIpt> tbIpts = tbIptRepo.findByFacilityAndDateScreenedBetween(facility, start, end);
                        List<Referral> referrals = referralRepo.findByFacilityAndReferralDateBetween(facility, start, end);

                        List<PatientDTO> patientDTOList = patients.stream().map(patientz -> new PatientDTO(patientz)).collect(Collectors.toList());
                        List<ContactDTO> contactDTOList = contacts.stream().map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
                        List<MentalHealthScreeningDTO> mentalHealthScreeningDTOList = mentalHealthScreenings.stream().map(mentalHealthScreening -> new MentalHealthScreeningDTO(mentalHealthScreening)).collect(Collectors.toList());
                        List<TbTPTDTO> tbTPTDTOList = tbIpts.stream().map(tbIpt -> new TbTPTDTO(tbIpt)).collect(Collectors.toList());
                        List<VLCD4DTO> vlcd4DTOList = investigationTests.stream().map(investigationTest -> new VLCD4DTO(investigationTest)).collect(Collectors.toList());
                        List<ReferralDTO> referralDTOList = referrals.stream().map(referral -> new ReferralDTO(referral)).collect(Collectors.toList());

                        lineItemsDTO.setPatients(patientDTOList);
                        lineItemsDTO.setContacts(contactDTOList);
                        lineItemsDTO.setInvestigationTests(vlcd4DTOList);
                        lineItemsDTO.setReferrals(referralDTOList);
                        lineItemsDTO.setTbIpts(tbTPTDTOList);
                        lineItemsDTO.setMentalHealthScreenings(mentalHealthScreeningDTOList);

                    long endtime = System.currentTimeMillis();
                    long time=(endtime-starttime)/(60000);
                    String timeTaken=time<120 ? time+" mins": ((double)Math.round(time/60))+" hrs";
                    System.err.println("\n<<<<<<<<<<<< = User::" + currentUser.getUserName() + " **** "+"FACILITY::"+facilityService.get(primaryClinic)+"  <>  patients Line Items retrieved, v2 ******** Time Taken:: "+timeTaken+"\n");



                }
                else if(patient!=null && !patient.isEmpty()){
                    Patient patientx=patientService.get(patient);
                    List<Contact> contacts=contactRepo.findByPatientAndContactDates(patientx, start, end);
                    List<MentalHealthScreening> mentalHealthScreenings=mentalHealthScreeningRepo.findByPatientAndDateScreenedBetween(patientx, start, end);
                    List<InvestigationTest> investigationTests=investigationTestRepo.findByPatientAndDateTakenBetween(patientx, start, end);
                    List<TbIpt> tbIpts = tbIptRepo.findByPatientAndDateScreenedBetween(patientx, start, end);
                    List<Referral> referrals=referralRepo.findByPatientAndReferralDateBetween(patientx, start,end);

                    List<ContactDTO> contactDTOList=contacts.stream().map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
                    List<MentalHealthScreeningDTO> mentalHealthScreeningDTOList=mentalHealthScreenings.stream().map(mentalHealthScreening -> new MentalHealthScreeningDTO(mentalHealthScreening)).collect(Collectors.toList());
                    List<TbTPTDTO> tbTPTDTOList=tbIpts.stream().map(tbIpt -> new TbTPTDTO(tbIpt)).collect(Collectors.toList());
                    List<VLCD4DTO> vlcd4DTOList=investigationTests.stream().map(investigationTest -> new VLCD4DTO(investigationTest)).collect(Collectors.toList());
                    List<ReferralDTO> referralDTOList=referrals.stream().map(referral -> new ReferralDTO(referral)).collect(Collectors.toList());

                    lineItemsDTO.setContacts(contactDTOList);
                    lineItemsDTO.setInvestigationTests(vlcd4DTOList);
                    lineItemsDTO.setReferrals(referralDTOList);
                    lineItemsDTO.setTbIpts(tbTPTDTOList);
                    lineItemsDTO.setMentalHealthScreenings(mentalHealthScreeningDTOList);

                    long endtime = System.currentTimeMillis();
                    long time=(endtime-starttime)/(60000);
                    String timeTaken=time<120 ? time+" mins": ((double)Math.round(time/60))+" hrs";
                    System.err.println("\n<<<<<<<<<<<< = User::" + currentUser.getUserName() + " **** "+" <=> PATIENT::"+patientService.get(patient).getName()+"  <>  patients Line Items retrieved, v2 ******** Time Taken:: "+timeTaken+"\n");


                }
                else{
                    return new ResponseEntity<>("Your request is malformed.", HttpStatus.BAD_REQUEST);
                }




        return new ResponseEntity<>(lineItemsDTO, HttpStatus.OK);
    }

    @GetMapping("/get-cats")
    public CatDetail getCats(@RequestParam("email") String email) {
        return catDetailService.getByEmail(email);
    }

    @PostMapping("/add-patients")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addPatient(@RequestBody List<PatientDTO> patientDTOS) {

        Response response = new Response(200, "Saved successfully", "");
        User currentUser=userService.getCurrentUser();
        try {
            patientDTOS.stream().forEach(patientDTO -> {
                Patient patient = new Patient(patientDTO);
                patient.setCreatedBy(currentUser);
                patient.setDateCreated(new Date());
                patient.setActive(Boolean.TRUE);
                patient.setStatus(PatientChangeEvent.ACTIVE);
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
                List<Patient> duplicates=patientService.checkPatientDuplicate(patient);
                if(patient.getPrimaryClinic()!=null
                        && (duplicates==null || duplicates.isEmpty())
                ) {
                    try{
                        Patient patient1 = patientService.save(patient);
                        patientDTO.setId(patient1.getId());
                        //patientDTOList.add(patientDTO);
                        System.err.println("\n********** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>> saved new Client \n\n");
                    }catch (DataIntegrityViolationException e){
                        System.err.println("Duplicate patient :"+patient.getName()+" +user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()));
                    }catch (DataAccessException e){
                        System.err.println("Duplicate patient :"+patient.getName()+" +user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()));
                    }

                }else{
                    System.err.println("Failed to save patient-no primary clinic/is-duplicate :"+patient.getName()+" +user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()));
                }
            });

            System.err.println("\n******** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>> saved, via mobile, new Clients items, v2, :" + patientDTOS.size() + "\n\n");
        } catch (Exception e) {
            System.err.println("\n\n******************** ERROR=> user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>>>>\n");
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        response.setBaseEntities(patientDTOList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-contacts")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addContact(@RequestBody List<ContactDTO> contactDTOS) {
        Response response = new Response(200, "Saved successfully", "");
        //List<DuplicateIndicator> dContacts=new ArrayList<>();
        User currentUser=userService.getCurrentUser();

        try {

            contactDTOS.stream().forEach(contactDTO -> {
                Patient patient=patientService.get(contactDTO.getPatient());
                //Set<Contact> contacts=contactService.getByPatient(patient).stream().collect(Collectors.toSet());
                Position position=positionService.get(contactDTO.getPosition());
                Location location=locationService.get(contactDTO.getLocation());
                Contact contact=new Contact(contactDTO);
                contact.setCreatedBy(currentUser);
                contact.setDateCreated(new Date());
                contact.setPatient(patient);
                contact.setLocation(location);
                contact.setPosition(position);
                contact.setRecordSource(RecordSource.MOBILE_APP);
                //convert ID to specific Objects
                contact.setNonClinicalAssessments(contactDTO.getNonClinicalAssessments().stream().map(s ->assessmentService.get(s)).distinct().collect(Collectors.toSet()));
                contact.setClinicalAssessments(contactDTO.getClinicalAssessments().stream().map(s -> assessmentService.get(s)).distinct().collect(Collectors.toSet()));
                contact.setServiceOffereds(contactDTO.getServiceOffereds().stream().map(s -> serviceOfferedService.get(s)).distinct().collect(Collectors.toSet()));
                contact.setServicereferreds(contactDTO.getServicereferreds().stream().map(s -> servicesReferredService.get(s)).distinct().collect(Collectors.toSet()));

                    //System.err.println("Contact to Save::"+contact);
                    Contact contact1=contactService.save(contact);

                System.err.println("\n****** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" saved contact, via mobile\n");

            });

            System.err.println("\n*********** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" saved contacts, via mobile, v2 :"+contactDTOS.size()+"\n");

        } catch (Exception e) {
            System.err.println("\n******************** ERROR: user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Error::"+e.getMessage()+"\n");
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-vlcd4s")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addVLCD4(@RequestBody List<VLCD4DTO> vlcd4DTOS) {
        Response response = new Response(200, "Saved successfully", "");
        //List<DuplicateIndicator> dVLs=new ArrayList<>();
        User currentUser=userService.getCurrentUser();
        try {

            vlcd4DTOS.stream().forEach(vlcd4DTO -> {
                Patient patient=patientService.get(vlcd4DTO.getPatient());
                Set<InvestigationTest> tests=investigationTestRepo.getItemsByPatient(patient).stream().collect(Collectors.toSet());
                InvestigationTest test=new InvestigationTest(vlcd4DTO);
                test.setCreatedBy(currentUser);
                test.setDateCreated(new Date());
                test.setPatient(patient);
                test.setActive(true);
                test.setRecordSource(RecordSource.MOBILE_APP);
                if(tests.stream().filter(test1 ->test1.getPatient().getId().equals(test.getPatient().getId()) &&
                        test1.getDateTaken().toString().equals(formatter.format(test.getDateTaken()))
                        && test1.getDateTaken().toString().equals(formatter.format(test.getDateTaken()))
                        && test1.getDateCreated().toString().equals(formatter.format(test.getDateCreated()))
                        && test1.getTestType().equals(test.getTestType())).count()<=0){
                    try {
                        InvestigationTest investigationTest=investigationTestService.save(test);
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>> saved VL/CD4 item, via mobile \n");
                    }catch (DataIntegrityViolationException e){
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate VL not saved >>>>>> Date Taken::"+test.getDateTaken()+" \n");
                    }catch (DataAccessException e){
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate VL not saved >>>>>> Date Taken::"+test.getDateTaken()+" \n");
                    }

                }else{
                    System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate VL not saved >>>>>> Date Taken::"+test.getDateTaken()+" \n");
                }
            });

            System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>> saved VL/CD4 items, via mobile, v2 :"+vlcd4DTOS.size()+"\n");
            } catch (Exception e) {
            System.err.println("\n\n********************ERROR: user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Error::"+e.getMessage()+"\n");
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-referrals")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addReferrals(@RequestBody List<ReferralDTO> referralDTOS) {
        //List<DuplicateIndicator> dReferrals=new ArrayList<>();
        User currentUser=userService.getCurrentUser();
        Response response = new Response(200, "Saved successfully", "");

        try {
            referralDTOS.stream().forEach(referralDTO -> {
                Patient patient=patientService.get(referralDTO.getPatient());
                Set<Referral> referrals=referralService.getByPatient(patient).stream().collect(Collectors.toSet());
                Referral referral=new Referral(referralDTO);
                referral.setPatient(patient);
                referral.setCreatedBy(currentUser);
                referral.setDateCreated(new Date());
                referral.setPatient(patient);
                referral.setRecordSource(RecordSource.MOBILE_APP);

                referral.setHivStiServicesAvailed(referralDTO.getHivStiServicesAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setHivStiServicesReq(referralDTO.getHivStiServicesReq().stream().map(serviceReferredDTO ->servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setSrhReq(referralDTO.getSrhReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setSrhAvailed(referralDTO.getSrhAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setPsychAvailed(referralDTO.getPsychAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setPsychReq(referralDTO.getPsychReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setLaboratoryReq(referralDTO.getLaboratoryReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setLaboratoryAvailed(referralDTO.getLaboratoryAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setTbAvailed(referralDTO.getTbAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setTbReq(referralDTO.getTbReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setOiArtReq(referralDTO.getOiArtReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setOiArtAvailed(referralDTO.getOiArtAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setLegalAvailed(referralDTO.getLegalAvailed().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));
                referral.setLegalReq(referralDTO.getLegalReq().stream().map(serviceReferredDTO -> servicesReferredService.get(serviceReferredDTO.getId())).collect(Collectors.toSet()));

                if(referrals.stream().filter(referral1 ->
                        referral1.getPatient().getId().equals(referral.getPatient().getId())
                                && referral1.getReferralDate().toString().equals(formatter.format(referral.getReferralDate()))
                                && referral1.getHivStiServicesReq().equals(referral.getHivStiServicesReq())
                                && referral1.getHivStiServicesAvailed().equals(referral.getHivStiServicesAvailed())
                                && referral1.getSrhAvailed().equals(referral.getSrhAvailed())
                                && referral1.getSrhReq().equals(referral.getSrhReq())
                                && referral1.getPsychAvailed().equals(referral.getPsychAvailed())
                                && referral1.getPsychReq().equals(referral.getPsychReq())
                                && referral1.getLaboratoryAvailed().equals(referral.getLaboratoryAvailed())
                                && referral1.getLaboratoryReq().equals(referral.getLaboratoryReq())
                                && referral1.getTbAvailed().equals(referral.getTbAvailed())
                                && referral1.getTbReq().equals(referral.getTbReq())
                                && referral1.getOiArtReq().equals(referral.getOiArtReq())
                                && referral1.getOiArtAvailed().equals(referral.getOiArtAvailed())
                                && referral1.getLegalAvailed().equals(referral.getLegalAvailed())
                                && referral1.getLegalReq().equals(referral.getLegalReq())
                ).count()<=0){
                    //all services
                    try {
                        Referral newRef=referralService.save(referral);
                        System.err.println("\n****** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>>> saved referral, via mobile \n");
                    }catch (DataIntegrityViolationException e){
                        //dReferrals.add(new DuplicateIndicator(referral.getPatient().getId(), formatter.format(referral.getReferralDate())));
                        System.err.println("\n***** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate Referral not saved >>>>>> REf Date;"+referral.getReferralDate()+" \n");
                    }catch (DataAccessException e){
                        //dReferrals.add(new DuplicateIndicator(referral.getPatient().getId(), formatter.format(referral.getReferralDate())));
                        System.err.println("\n***** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate Referral not saved >>>>>> REf Date;"+referral.getReferralDate()+" \n");
                    }

                }else{
                    //dReferrals.add(new DuplicateIndicator(referral.getPatient().getId(), formatter.format(referral.getReferralDate())));
                    System.err.println("\n***** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate Referral not saved >>>>>> REf Date;"+referral.getReferralDate()+" \n");
                }
            });

            System.err.println("\n****** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>>> saved referrals, via mobile, v2 : "+referralDTOS.size()+"\n");

        } catch (Exception e) {
            System.err.println("\n\n********************ERROR: user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>>>>>>>> Error::"+e.getMessage()+"\n");
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-mental-health-screening-items")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addMHScreenings(@RequestBody List<MentalHealthScreeningDTO> mentalHealthScreeningDTOS) {
        Response response= new Response(200,"Saved successfully!","");
        //List<DuplicateIndicator> dMHs=new ArrayList<>();
        User currentUser=userService.getCurrentUser();
        try {
            mentalHealthScreeningDTOS.stream().forEach(mentalHealthScreeningDTO -> {
                Patient patient=patientService.get(mentalHealthScreeningDTO.getPatient());
                Set<MentalHealthScreening> screenings=mentalHealthScreeningService.findByPatient(patient).stream().collect(Collectors.toSet());
                MentalHealthScreening mentalHealthScreening=new MentalHealthScreening(mentalHealthScreeningDTO);
                mentalHealthScreening.setCreatedBy(currentUser);
                mentalHealthScreening.setDateCreated(new Date());
                mentalHealthScreening.setPatient(patient);
                mentalHealthScreening.setActive(true);
                mentalHealthScreening.setRecordSource(RecordSource.MOBILE_APP);
                if(screenings.stream().filter(mentalHealthScreening1 ->
                        mentalHealthScreening1.getPatient().getId().equals(mentalHealthScreening.getPatient().getId())
                                &&   mentalHealthScreening1.getDateScreened().toString().equals(formatter.format(mentalHealthScreening.getDateScreened()))
                                && mentalHealthScreening1.getDateCreated().toString().equals(formatter.format(mentalHealthScreening.getDateCreated()))
                                && mentalHealthScreening1.getRisk().equals(mentalHealthScreening.getRisk())
                                && ((mentalHealthScreening1.getIdentifiedRisks()==null && mentalHealthScreening.getIdentifiedRisks()==null) || mentalHealthScreening1.getIdentifiedRisks().equals(mentalHealthScreening.getIdentifiedRisks()))
                ).count()<=0){


                    try{
                        mentalHealthScreeningService.save(mentalHealthScreening);
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>>>> saved MH Screening item, via mobile\n");
                    }catch(DataIntegrityViolationException e){
                        //dMHs.add(new DuplicateIndicator(mentalHealthScreening.getPatient().getId(), formatter.format(mentalHealthScreening.getDateScreened())));
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate MH Screening not saved >>>>>> Date Screened::"+mentalHealthScreening.getDateScreened()+" **** "+e.getMessage()+"\n\n");
                    } catch(DataAccessException e){
                        //dMHs.add(new DuplicateIndicator(mentalHealthScreening.getPatient().getId(), formatter.format(mentalHealthScreening.getDateScreened())));
                        System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate MH Screening not saved >>>>>> Date Screened::"+mentalHealthScreening.getDateScreened()+" **** "+e.getMessage()+"\n\n");
                    }

                }else{
                    //dMHs.add(new DuplicateIndicator(mentalHealthScreening.getPatient().getId(), formatter.format(mentalHealthScreening.getDateScreened())));
                    System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate MH Screening not saved >>>>>> Date Screened::"+mentalHealthScreening.getDateScreened()+"\n\n");
                }

            });


            System.err.println("\n******************** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>>>> saved MH Screening items, via mobile, v2, ::"+mentalHealthScreeningDTOS.size()+"\n\n");

            } catch (Exception e) {
            System.err.println("\n\n******************** ERROR=> user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>> Error::"+e.getMessage()+"\n");

            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add-tb-tpt-screening-items")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public ResponseEntity<?> addTBScreenings(@RequestBody List<TbTPTDTO> tbTPTDTOS) {

        //List<DuplicateIndicator> dTBs=new ArrayList<>();
        User currentUser=userService.getCurrentUser();
        Response response= new Response(200,"Saved successfully!", "");
        try {
            tbTPTDTOS.stream().forEach(tbTPTDTO -> {
                Patient patient=patientService.get(tbTPTDTO.getPatient());
                Set<TbIpt> tbIpts=tbIptService.getByPatient(patient).stream().collect(Collectors.toSet());



                TbIpt tbIpt=new TbIpt(tbTPTDTO);

                //System.err.println("TB/IPT ITEM::"+tbIpt.toString());

                tbIpt.setCreatedBy(currentUser);
                tbIpt.setDateCreated(new Date());
                tbIpt.setPatient(patient);
                tbIpt.setActive(Boolean.TRUE);
                tbIpt.setRecordSource(RecordSource.MOBILE_APP);
                if(tbIpts.stream().filter(tbIpt1 ->
                        tbIpt1.getPatient().getId().equals(tbIpt.getPatient().getId())
                                && tbIpt1.getDateScreened().toString().equals(formatter.format(tbIpt.getDateScreened()))
                                && tbIpt1.getDateCreated().toString().equals(formatter.format(tbIpt.getDateCreated()))
                                && tbIpt1.getIdentifiedWithTb().equals(tbIpt.getIdentifiedWithTb())
                                && ((tbIpt1.getTbSymptoms()==null && tbIpt.getTbSymptoms()==null) || tbIpt1.getTbSymptoms().equals(tbIpt.getTbSymptoms()))
                                && ((tbIpt1.getEligibleForIpt()==null && tbIpt.getEligibleForIpt()==null) ||tbIpt1.getEligibleForIpt().equals(tbIpt.getEligibleForIpt()))
                                && ((tbIpt1.getOnIpt()==null && tbIpt.getOnIpt()==null) ||tbIpt1.getOnIpt().equals(tbIpt.getOnIpt()))
                                && ((tbIpt1.getOnTBTreatment()==null && tbIpt.getOnTBTreatment()==null) || tbIpt1.getOnTBTreatment().equals(tbIpt.getOnTBTreatment()))
                ).count()<=0){
                    // signs available, which ones, tpt eligibility, onTPT, onTreatment

                    try{
                        TbIpt tbIpt1=tbIptService.save(tbIpt);
                        System.err.println("\n*********  user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> saved TB Screening item \n");
                    }catch(DataIntegrityViolationException e){
                        //dTBs.add(new DuplicateIndicator(tbIpt.getPatient().getId(), formatter.format(tbIpt.getDateScreened())));
                        System.err.println("\n******** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate TB Screening not saved >>>>>> Date Screened::"+tbIpt.getDateScreened()+"\n");
                    }catch (DataAccessException e){
                        //dTBs.add(new DuplicateIndicator(tbIpt.getPatient().getId(), formatter.format(tbIpt.getDateScreened())));
                        System.err.println("\n*********** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate TB Screening not saved >>>>>> Date Screened::"+tbIpt.getDateScreened()+"\n");
                    }
                }else{
                    //dTBs.add(new DuplicateIndicator(tbIpt.getPatient().getId(), formatter.format(tbIpt.getDateScreened())));
                    System.err.println("\n********** user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" duplicate TB Screening not saved >>>>>> Date Screened::"+tbIpt.getDateScreened()+"\n");
                }
            });

            System.err.println("\n>>>>>>>>>>>>>>>  user :"+currentUser.getUserName()+" <=> District:"+(currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName())+" TB Screening Items, v2 ::"+tbTPTDTOS.size()+"\n");

        } catch (Exception e) {
            System.err.println("\n\n******************** ERROR=> user :"+currentUser.getUserName()+" <=> District:"+currentUser.getUserLevel()!=null?currentUser.getDistrict(): catDetailService.getByEmail(currentUser.getUserName()).getPrimaryClinic().getDistrict().getName()+" >>>>>>>>>>>>>>>>> Error::"+e.getMessage()+"\n");
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-messages")
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
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


            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Message item \n\n");
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
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
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
            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Feature request item \n\n");
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
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
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

            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" saved Bug Report item \n");
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
            List<BugReport> bugReports1=bugReportService.getAllByCreatedBy(user);
            for(BugReport bugReport: bugReports1){
                bugReports.add(new BugReportDTO(bugReport));
            }
            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled Bug Report items :"+bugReports.size()+"\n\n");
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
            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled messages items :"+messages.size()+"\n\n");
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
            System.err.println("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+user.getUserName()+" pulled Feature request items :"+featureRequests.size()+"\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(featureRequests, HttpStatus.OK);
    }

    @GetMapping("/get-facility-statistics")
    public ResponseEntity<?> getFacilityStatistics(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, @RequestParam("facilityID") String facilityID){
        Response response=new Response(200,"Saved successfully","");
        List<Patient> patients=new ArrayList<>();
        List<Contact> contacts=new ArrayList<>();
        List<MentalHealthScreening> MHScreenings=new ArrayList<>();
        List<Referral> referrals=new ArrayList<>();
        List<TbIpt> TBscreenings=new ArrayList<>();
        List<InvestigationTest> vls=new ArrayList<>();
        FacilityStatsDTO facilityStatsDTO=new FacilityStatsDTO();
        try {
            User user=userService.getCurrentUser();

            patients=patientService.findByFacilityInGivenTime(start, end, facilityID);
            contacts=contactService.findByFacilityInGivenTime(start, end, facilityID);
            MHScreenings=mentalHealthScreeningService.findByFacilityInGivenTime(start, end, facilityID);
            TBscreenings= tbIptService.findByFacilityInGivenTime(start, end, facilityID);
            referrals=referralService.findByFacilityInGivenTime(start, end, facilityID);
            vls=investigationTestService.findByFacilityInGivenTime(start, end, facilityID);

            facilityStatsDTO=new FacilityStatsDTO(patients.size(),contacts.size(),MHScreenings.size(), TBscreenings.size(), referrals.size(), vls.size());

            System.err.println("\n >>>>>>>>>> "+user.getUserName()+" pulled Facility Managements Data  :"+facilityService.get(facilityID).getName()+", v2\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(facilityStatsDTO, HttpStatus.OK);
    }

    @GetMapping("/get-district-statistics")
    public ResponseEntity<?> getDistrictStatistics(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, @RequestParam("districtId") String districtId){
        Response response=new Response(200,"Saved successfully","");
        List<Patient> patients=new ArrayList<>();
        List<Contact> contacts=new ArrayList<>();
        List<MentalHealthScreening> MHScreenings=new ArrayList<>();
        List<Referral> referrals=new ArrayList<>();
        List<TbIpt> TBscreenings=new ArrayList<>();
        List<InvestigationTest> vls=new ArrayList<>();
        List<Facility> facilities=facilityService.getOptByDistrict(districtService.get(districtId));
        List<FacilityRecord> facilityRecords= new ArrayList<>();
        try {
            User user=userService.getCurrentUser();

            patients=patientService.findByDistrictInGivenTime(start, end, districtId);
            contacts=contactService.findByDistrictInGivenTime(start, end, districtId);
            MHScreenings=mentalHealthScreeningService.findByDistrictInGivenTime(start, end, districtId);
            TBscreenings= tbIptService.findByDistrictInGivenTime(start, end, districtId);
            referrals=referralService.findByDistrictInGivenTime(start, end, districtId);
            vls=investigationTestService.findByDistrictInGivenTime(start, end, districtId);

            facilityRecords=DistrictStatsDTO.createFacilityRecordsList(facilities, patients, contacts, MHScreenings, TBscreenings, referrals, vls);

            System.err.println("\n >>>>>>>>>>> "+user.getUserName()+" pulled District Managements Data  :"+districtService.get(districtId).getName()+", v2\n");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500);
            response.setMessage(e.getMessage());
            response.setDescription(e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(facilityRecords, HttpStatus.OK);
    }


}
