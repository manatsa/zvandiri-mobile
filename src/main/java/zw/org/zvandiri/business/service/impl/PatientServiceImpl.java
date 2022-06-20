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
package zw.org.zvandiri.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import zw.org.zvandiri.business.domain.*;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.repo.PatientRepo;
import zw.org.zvandiri.business.service.CatDetailService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.MobilePatientDTO;
import zw.org.zvandiri.business.util.dto.NameIdDTO;
import zw.org.zvandiri.business.util.dto.PatientDuplicateDTO;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientRepo patientRepo;
    @Resource
    private UserService userService;
    @Resource
    private CatDetailService catDetailService;

    @Override
    public List<Patient> getAll() {
        return patientRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public Patient get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return patientRepo.findById(id).get();
    }

    @Override
    public void delete(Patient t) {
    	if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setDeleted(Boolean.TRUE);
        t.setStatus(PatientChangeEvent.OTHER);
        t.setActive(Boolean.FALSE);
        patientRepo.save(t);
        patientRepo.delete(t);
    }

    @Override
    public List<Patient> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Patient save(Patient t)  {
        if (t.getId() == null || StringUtils.isBlank(t.getId())) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            t.setPatientNumber(getPatientUAC(t));
            return patientRepo.save(t);
        }
        if(t.getPrimaryClinic()==null){
            System.err.println("\n\nFacility is null : BY:"+t.getCreatedBy().getFirstName()+" "+t.getCreatedBy().getLastName()+" District: "+t.getCreatedBy().getDistrict());
            return null;
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return patientRepo.save(t);

    }

    @Override
    public Boolean checkDuplicate(Patient current, Patient old) {
        List<Patient> patients = checkPatientDuplicate(current);
        return checkItemDuplicate(current, patients);
    }

    private Boolean checkItemDuplicate(Patient current, List<Patient> patients) {
        if (current.getId() == null) {
            if (patients != null && !patients.isEmpty()) {
                return true;
            }
        } else if (current.getId() != null) {
            if ((patients != null && !patients.isEmpty()) && patients.size() > 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Patient> checkPatientDuplicate(Patient patient) {
        String firstName = getSubString(patient.getFirstName());
        String lastName = getSubString(patient.getLastName());
        String firstNameLastPart = getThreeLastChars(patient.getFirstName());
        String lastNameLastPart = getThreeLastChars(patient.getLastName());

        Date start = DateUtil.getDateDiffMonth(patient.getDateOfBirth(), -6);
        Date end = DateUtil.getDateDiffMonth(patient.getDateOfBirth(), 6);
        return patientRepo.checkPatientDuplicate(firstName, lastName, start, end, patient.getPrimaryClinic(), firstNameLastPart, lastNameLastPart, true);
    }

    private String getSubString(String name) {
        return name.length() > 4 ? name.substring(0, 4) : name;
    }

    private String getThreeLastChars(String name) {
        int length = name.length();
        return length > 4 ? name.substring(length - 3, length) : name;
    }

    @Override
    public List<Patient> getByCat(Boolean cat) {
        return patientRepo.findByCatAndActive(cat, Boolean.TRUE);
    }

    @Override
    public List<Patient> search(SearchDTO dto, String... exp) {
        if (exp == null) {
            throw new IllegalArgumentException("Provide parameter for search");
        } else if (exp.length == 1 && dto.getProvince() == null && dto.getDistrict() == null) {
            return patientRepo.findByFirstNameOrLastName(exp[0], Boolean.TRUE);
        } else if (exp.length == 1 && dto.getProvince() != null && dto.getDistrict() == null) {
            return patientRepo.findByFirstNameOrLastNameAndProvince(exp[0], Boolean.TRUE, dto.getProvince());
        } else if (exp.length == 1 && dto.getProvince() == null && dto.getDistrict() != null) {
            return patientRepo.findByFirstNameOrLastNameAndDistrict(exp[0], Boolean.TRUE, dto.getDistrict());
        } else if (exp.length > 1 && dto.getProvince() != null && dto.getDistrict() == null) {
            return patientRepo.findByFirstNameAndLastNameAndProvince(exp[0], exp[1], Boolean.TRUE, dto.getProvince());
        } else if (exp.length > 1 && dto.getProvince() == null && dto.getDistrict() != null) {
            return patientRepo.findByFirstNameAndLastNameAndDistrict(exp[0], exp[1], Boolean.TRUE, dto.getDistrict());
        }
        return patientRepo.findByFirstNameAndLastName(exp[0], exp[1], Boolean.TRUE);
    }

    @Override
    public Boolean hasCatDetailRecord(Patient patient) {
        if (patient.getCat() != null && patient.getCat().equals(YesNo.YES) && catDetailService.getByPatient(patient) != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Patient> getYoungMothers(SearchDTO dto, String... exp) {
        if (exp == null) {
            throw new IllegalArgumentException("Provide parameter for search");
        } else if (exp.length == 1) {
            return patientRepo.findYoungMothersByName(DateUtil.getDateFromAge(40), DateUtil.getDateFromAge(8), dto.getDistrict(), dto.getGender(), exp[0]);
        }
        return patientRepo.findYoungMothersByBoth(DateUtil.getDateFromAge(40), DateUtil.getDateFromAge(8), dto.getDistrict(), dto.getGender(), exp[0], exp[1]);
    }

    @Override
    public Set<PatientDuplicateDTO> getAllPossibleDuplicates(List<Patient> patients) {

        Set<PatientDuplicateDTO> patientsWithPossibleDuplicates = new HashSet<>();
        for (Iterator<Patient> pi = patients.iterator(); pi.hasNext();) {
            Patient currentPatient = pi.next();
            Set<Patient> estDuplicates = new HashSet<>(checkPatientDuplicate(currentPatient));
            estDuplicates.remove(currentPatient);
            if (!estDuplicates.isEmpty()) {
                PatientDuplicateDTO patientWithDuplicates = PatientDuplicateDTO.getInstance(currentPatient);
                patientWithDuplicates.setMatches(PatientDuplicateDTO.getRelatedPatients(estDuplicates));
                patientsWithPossibleDuplicates.add(patientWithDuplicates);

            }
            pi.remove();
        }
        return patientsWithPossibleDuplicates;
    }


    @Override
    @Transactional
    public void mergePatients(String patientId, String patientToBeMergedId) {
    	
    	Patient patient = patientRepo.getPatient(patientId);
        Patient patientToBeMerged = patientRepo.getPatient(patientToBeMergedId);
        for (InvestigationTest item : patientToBeMerged.getInvestigationTests()) {
            patient.add(item, patient);
        }
        for (CatDetail item : patientToBeMerged.getCatDetails()) {
            patient.add(item, patient);
        }
        for (Referral item : patientToBeMerged.getReferrals()) {
            patient.add(item, patient);
        }
        for (EidTest item : patientToBeMerged.getEidTests()) {
            patient.add(item, patient);
        }
        for (Contact item : patientToBeMerged.getContacts()) {
            patient.add(item, patient);
        }
        for (Family item : patientToBeMerged.getFamilys()) {
            patient.add(item, patient);
        }
        for (SubstanceItem item : patientToBeMerged.getSubstanceItems()) {
            patient.add(item, patient);
        }
        for (SrhHist item : patientToBeMerged.getSrhHists()) {
            patient.add(item, patient);
        }
        for (SocialHist item : patientToBeMerged.getSocialHists()) {
            patient.add(item, patient);
        }
        for (ObstercHist item : patientToBeMerged.getObstercHists()) {
            patient.add(item, patient);
        }
        for (MentalHealthItem item : patientToBeMerged.getMentalHealthItems()) {
            patient.add(item, patient);
        }
        for (ArvHist item : patientToBeMerged.getArvHists()) {
            patient.add(item, patient);
        }
        for (HivConInfectionItem item : patientToBeMerged.getHivConInfectionItems()) {
            patient.add(item, patient);
        }
        for (ChronicInfectionItem item : patientToBeMerged.getChronicInfectionItems()) {
            patient.add(item, patient);
        }

        for (MedicalHist item : patientToBeMerged.getMedicalHists()) {
            patient.add(item, patient);
        }

        for (Dependent item : patientToBeMerged.getDependents()) {
            patient.add(item, patient);
        }
        for (PatientDisability item : patientToBeMerged.getDisabilityCategorys()) {
            patient.add(item, patient);
        }
        
        save(patient);
        delete(patientToBeMerged);
        
    }

    @Override
    @Transactional
    public void updatePatientStatus(List<Patient> pateints) {

        for (Patient p : pateints) {
            p.setStatus(PatientChangeEvent.GRADUATED);
            save(p);
        }
    }

    @Override
    public String getPatientUAC(Patient patient) {
        if (patient.getPatientNumber() != null) {
            return patient.getPatientNumber();
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMYY");
        String surname = getLastTwoLetters(patient.getLastName());
        String forename = getLastTwoLetters(patient.getFirstName());
        String district = patient.getPrimaryClinic().getDistrict().getName().substring(0, 2);
        String sex = patient.getGender().getAltName();
        String dob = format.format(patient.getDateOfBirth());
        String uac = surname + forename + sex + dob + district;

        int current = patientRepo.countByPatientNumberNotNull();
        String combinedUAC = "";
        if (current == 0) {
            combinedUAC = uac + "000000";
        } else {
            combinedUAC = transformCode(uac + String.valueOf(current));
        }

        return combinedUAC.toUpperCase();
    }

    private String getLastTwoLetters(String name) {
        return name.substring(name.length() - 2, name.length());
    }

    private String transformCode(String code) {
        String lastDigits = code.substring(13);
        final String firstDigits = code.substring(0, 13);
        lastDigits = padWithZeroes(lastDigits);
        String[] digits = explodeString(lastDigits);
        String calculatedCode = "";
        boolean found = false;
        int position = 0;
        for (String digit : digits) {
            Integer currentVal = Integer.valueOf(digit);
            if (currentVal >= 1) {
                found = true;
                // potential bug here on 90 100 000
                break;
            }
            position++;
        }
        if (!found) {
            calculatedCode = "000001";
        } else {
            Integer lastPart = Integer.valueOf(lastDigits.substring(position - 1));
            String firstPart = lastDigits.substring(0, position);
            if (lastPart == 9 || lastPart == 99 || lastPart == 999 || lastPart == 9999 || lastPart == 99999) {
                firstPart = lastDigits.substring(0, position - 1);
            }
            calculatedCode = firstPart + (lastPart + 1);
        }
        return firstDigits + calculatedCode;
    }

    private String padWithZeroes(String lastBits) {

        // we need 000000
        int count = lastBits.length();
        switch (count) {
            case 1:
                return "00000" + lastBits;
            case 2:
                return "0000" + lastBits;
            case 3:
                return "000" + lastBits;
            case 4:
                return "00" + lastBits;
            case 5:
                return "0" + lastBits;
            case 6:
                return lastBits;
            default:
                throw new IllegalStateException("Illegal application state only expected counts on 1 - 4 : " + count);
        }
    }

    private String[] explodeString(String s) {
        if (s == null) {
            return null;
        }
        char[] c = s.toCharArray();
        String[] sArray = new String[c.length];
        for (int i = 0; i < c.length; i++) {
            sArray[i] = String.valueOf(c[i]);
        }
        return sArray;
    }

    @Override
    @Transactional
    public void updatePatientUAC() {

        for (Patient patient : getAll()) {
            if (patient.getFirstName().length() <= 1 || patient.getLastName().length() <= 1) {
                continue;
            }
            Patient newPatient = get(patient.getId());
            newPatient.setPatientNumber(getPatientUAC(patient));
            save(newPatient);
        }
    }

    /**
     *
     * @param catDetail
     * @return
     */
  
    @Override
    public List<NameIdDTO> getCatPatients(CatDetail catDetail) {
        
    	return catDetailService.getCatPatients(catDetail);
    }

    @Override
    public List<Patient> getFacilityPatients(CatDetail catdDetail) {
        System.err.println(">>>>>> Primary Clinic : "+catdDetail.getPrimaryClinic());
        List<Patient> patients=patientRepo.getAllByPrimaryClinicAndActive(catdDetail.getPrimaryClinic(), true);
        /*List<MobilePatientDTO> mobilePatientDTOS=new ArrayList<>();
        for(Patient p: patients){
            mobilePatientDTOS.add(new MobilePatientDTO(p));
        }*/
        return patients;
    }

    @Override
    public List<Patient> getActiveByDistrict(District district) {
        return patientRepo.getActiveByDistrict(district);
    }

    @Override
    public List<Patient> getActiveByProvince(Province province) {
        return  patientRepo.getActiveByProvince(province);
    }

    @Override
    public List<Patient> findByDistrictInGivenTime(Date start, Date end, String district) {
        return patientRepo.findByDistrictInGivenTime(start, end, district);
    }

    @Override
    public List<Patient> findByFacilityInGivenTime(Date start, Date end, String facility) {
        return patientRepo.findByFacilityInGivenTime(start, end, facility);
    }


}
