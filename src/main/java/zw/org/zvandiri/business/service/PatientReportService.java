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
package zw.org.zvandiri.business.service;

import java.util.List;

import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.HIVStatus;
import zw.org.zvandiri.business.util.dto.SearchDTO;
import zw.org.zvandiri.business.util.dto.SearchNationalDTO;

/**
 *
 * @author Judge Muzinda
 */
public interface PatientReportService {

    public Integer get(SearchNationalDTO dto, HIVStatus hivStatus);

    public Long getCount(SearchDTO dto);

    public Long getNewlyRegistered(SearchDTO dto);
    
    public Long getPatientWithNoContact(SearchDTO dto);
    
    public List<Patient> getPatientWithNoContactList(SearchDTO dto);
    
    public List<Patient> getPatientWithContactList(SearchDTO dto);
    
    public Long getPatientAboutToGraduate(SearchDTO dto);
    
    public List<Patient> getPatientAboutToGraduateList(SearchDTO dto);
    
    public Long getPatientLabResults(SearchDTO dto);
    
    public List<Patient> getPatientLabResultsList(SearchDTO dto);

    List<InvestigationTest> getPatientLabResultList(SearchDTO dto);

    public Long getPatientWithContact(SearchDTO dto);
    
    public Long getPatientWithViralLoad(SearchDTO dto);

    public List<Patient> getPatientDeceased(SearchDTO dto);

    public List<Patient> getUncontactedClients(SearchDTO dto);

    public List<Patient> getEnhancedClients(SearchDTO dto);

    public List<Patient> getMHScreeningCandidates(SearchDTO dto);

    public List<Patient> getTBScreeningCandidates(SearchDTO dto);

    public List<Patient> getPatientsWithInvalidVL(SearchDTO dto);
    
    Long countUncontacted(SearchDTO dto);
    
    public Long getCountDueForViralLoad(SearchDTO dto);
    
    public List<Patient> getDueForViralLoadList(SearchDTO dto);
}
