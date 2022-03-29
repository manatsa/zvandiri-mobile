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
import zw.org.zvandiri.business.domain.util.TestType;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
public interface InvestigationTestService extends GenericService<InvestigationTest> {
    
	    public List<InvestigationTest> getByPatientAndTestType(Patient patient, TestType testType);
        
        public List<InvestigationTest> get(SearchDTO dto);
        
        public InvestigationTest getLatestTestByTestType(Patient patient, TestType testType);

        public List<InvestigationTest> getInvalidViralLoad(SearchDTO dto);

    Long getInvalidViralLoadCount(SearchDTO dto);
}