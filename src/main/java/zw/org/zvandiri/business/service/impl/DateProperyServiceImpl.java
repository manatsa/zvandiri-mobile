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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.Settings;
import zw.org.zvandiri.business.service.DatePropertyService;
import zw.org.zvandiri.business.service.SettingsService;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@Repository
public class DateProperyServiceImpl implements DatePropertyService {

    @Resource
    private SettingsService settingsService;
    
    @Override
    public Map<String, String> getAllYearRanges() {
        Map<String, String> dates = new HashMap<>();
        dates.put("beneficiary", getBeneficiaryDateRange());
        dates.put("caregiver", getCaraGiverDateRange());
        dates.put("general", getCurrentYearRange());
        return dates;
    }
    
    private String getBeneficiaryDateRange() {
        Settings setting = settingsService.getItem();
        Calendar startYear = Calendar.getInstance();
        Calendar endYear = Calendar.getInstance();
        startYear.setTime(DateUtil.getDateFromAge(setting.getHeuMotherMaxAge()));
        endYear.setTime(DateUtil.getEndDate(setting.getPatientMinAge()));
        return startYear.get(Calendar.YEAR) + ":" + endYear.get(Calendar.YEAR);
    }
    
    private String getCaraGiverDateRange() {
        Calendar startYear = Calendar.getInstance();
        Calendar endYear = Calendar.getInstance();
        startYear.setTime(DateUtil.getDateFromAge(90));
        endYear.setTime(DateUtil.getEndDate(0));
        return startYear.get(Calendar.YEAR) + ":" + endYear.get(Calendar.YEAR);
    }
    
    private String getCurrentYearRange() {
        Calendar startYear = Calendar.getInstance();
        Calendar endYear = Calendar.getInstance();
        startYear.setTime(DateUtil.getDateFromAge(30));
        endYear.setTime(DateUtil.getEndDate(0));
        return startYear.get(Calendar.YEAR) + ":" + endYear.get(Calendar.YEAR);
    }
    
}