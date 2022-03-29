/*
 * Copyright 2016 Tasunungurwa Muzinda.
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

/**
 *
 * @author Tasunungurwa Muzinda
 */
public interface ProblemReportHeaderNames {
    
    public String [] headerNames = {
        "Province",
        "District",
        "Facility",
        "# of contacts",
        "# of clients",
        "# of newly registered clients",
        "# exiting the program",
        "# newly initiated on ART",
        "# currently on ART",
        "# on ART for 3 months",
        "# on ART for 6 months",
        "# on ART for 12 months",
        "# on ART for 24 months",
        "# on ART for 36 months",
        "# on ART for > 36 months"
    };
    
    public String [] coreheaderNames = {
        "# of contacts",
        "# of clients",
        "# of newly registered clients",
        "# exiting the program",
        "# newly initiated on ART",
        "# currently on ART",
        "# on ART for 3 months",
        "# on ART for 6 months",
        "# on ART for 12 months",
        "# on ART for 24 months",
        "# on ART for 36 months",
        "# on ART for > 36 months"
    };
}
