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

import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.GenericPeriod;
import zw.org.zvandiri.business.domain.util.PeriodType;
import zw.org.zvandiri.business.service.PeriodTypeService;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
public class PeriodTypeServiceImpl implements PeriodTypeService {

    @Override
    public GenericPeriod get(SearchDTO item) {
        if(item.getPeriodType() != null){
            if(item.getPeriodType().equals(PeriodType.MONTHLY)){
                return item.getPeriod();
            }else if(item.getPeriodType().equals(PeriodType.QUARTERLY)){
                return item.getQuarterPeriod();
            }else if(item.getPeriodType().equals(PeriodType.HALF_YEARLY)){
                return item.getHalfYearPeriod();
            }else if(item.getPeriodType().equals(PeriodType.YEARLY)){
                return item.getYearPeriod();
            }
        }
        throw new IllegalStateException("Application in an inconsistent state :"+item);
    }
}