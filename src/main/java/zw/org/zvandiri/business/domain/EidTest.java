/*
 * Copyright 2017 jmuzinda.
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
package zw.org.zvandiri.business.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author jmuzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
/*@Table(indexes = {
		@Index(name = "eid_test_patient", columnList = "patient"),
		@Index(name = "eid_test_dateTaken", columnList = "dateTaken"),
})*/
public class EidTest extends TestResult {

    private Boolean status;
    @Transient
    private String testResult;
    
    public EidTest() {
    }

    public EidTest(Patient patient) {
        super(patient);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTestResult() {
        if (status == null){
            return "";
        }
        return status ? "Positive" : "Negative";
    }
    
}
