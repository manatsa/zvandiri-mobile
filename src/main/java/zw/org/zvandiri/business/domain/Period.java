/*
 * Copyright 2015 Judge Muzinda.
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

import static zw.org.zvandiri.business.util.DateUtil.getYearMonthName;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.PeriodType;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Period extends GenericPeriod {
    
    @Transient
    private String name;

    public Period() {
    }

    public Period(Date startDate, Date endDate, PeriodType periodType) {
        super(startDate, endDate, periodType);
    }
    
    @Override
    public String getName() {
        return getYearMonthName(getStartDate());
    }   
}
