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

import zw.org.zvandiri.business.domain.LevelOfCare;
import zw.org.zvandiri.business.domain.Period;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.util.PeriodType;

/**
 *
 * @author Judge Muzinda
 */
public class SearchNationalDTO extends SearchProvinceDTO {

    private Province province;
    private PeriodType periodType;
    private LevelOfCare levelOfCare;

    public SearchNationalDTO() {
    }

    public SearchNationalDTO(Period period) {
        super(period);
    }

    public LevelOfCare getLevelOfCare() {
        return levelOfCare;
    }

    public void setLevelOfCare(LevelOfCare levelOfCare) {
        this.levelOfCare = levelOfCare;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public static SearchNationalDTO getInstance(SearchNationalDTO dto) {
        SearchNationalDTO search = new SearchNationalDTO(dto.getPeriod());
        search.setProvince(dto.getProvince());
        search.setDistrict(dto.getDistrict());
        search.setFacility(dto.getFacility());
        search.setQuarterPeriod(dto.getQuarterPeriod());
        search.setHalfYearPeriod(dto.getHalfYearPeriod());
        search.setYearPeriod(dto.getYearPeriod());
        search.setPeriodType(dto.getPeriodType());
        search.setLevelOfCare(dto.getLevelOfCare());
        return search;
    }

    public String getQueryString(SearchNationalDTO dto) {
        StringBuilder query = new StringBuilder("");
        String buffer = null;
        query.append("?period=");
        if (dto.getPeriod() == null) {
            query.append(buffer);
        } else {
            query.append(dto.getPeriod().getId());
        }
        if (dto.getDistrict() != null) {
            query.append("&district=");
            query.append(dto.getDistrict().getId());
        }
        if (dto.getProvince() != null) {
            query.append("&province=");
            query.append(dto.getProvince().getId());
        }
        if (dto.getDistrict() != null) {
            query.append("&district=");
            query.append(dto.getDistrict().getId());
        }
        if (dto.getQuarterPeriod() != null) {
            query.append("&quarterPeriod=");
            query.append(dto.getQuarterPeriod().getId());
        }
        if (dto.getHalfYearPeriod() != null) {
            query.append("&halfYearPeriod=");
            query.append(dto.getHalfYearPeriod().getId());
        }
        if (dto.getPeriodType() != null) {
            query.append("&periodType=");
            query.append(dto.getPeriodType().getCode());
        }
        return query.toString();
    }

    @Override
    public String toString() {
        return "SearchNationalDTO{" + "province=" + province + '}';
    }

}
