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
package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.HalfYearPeriod;
import zw.org.zvandiri.business.domain.Period;
import zw.org.zvandiri.business.domain.QuarterPeriod;
import zw.org.zvandiri.business.domain.YearPeriod;

/**
 *
 * @author Judge Muzinda
 */
public class SearchDistrictDTO implements Serializable {

    private Period period;
    private QuarterPeriod quarterPeriod;
    private HalfYearPeriod halfYearPeriod;
    private YearPeriod yearPeriod;
    private Facility facility;

    public SearchDistrictDTO() {
    }

    public SearchDistrictDTO(Period period) {
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public QuarterPeriod getQuarterPeriod() {
        return quarterPeriod;
    }

    public void setQuarterPeriod(QuarterPeriod quarterPeriod) {
        this.quarterPeriod = quarterPeriod;
    }

    public HalfYearPeriod getHalfYearPeriod() {
        return halfYearPeriod;
    }

    public void setHalfYearPeriod(HalfYearPeriod halfYearPeriod) {
        this.halfYearPeriod = halfYearPeriod;
    }

    public YearPeriod getYearPeriod() {
        return yearPeriod;
    }

    public void setYearPeriod(YearPeriod yearPeriod) {
        this.yearPeriod = yearPeriod;
    }

    @Override
    public String toString() {
        return "SearchDistrictDTO{" + "period=" + period + ", facility=" + facility + '}';
    }

}
