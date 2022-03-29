/*
 * Copyright 2017 Judge Muzinda.
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
package zw.org.zvandiri.business.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author Judge Muzinda
 */
public enum DateRangeItem {

    PAST_ONE_WEEK(1, 7), PAST_TWO_WEEKS(1, 14), PAST_ONE_MONTH(1, 30),
    PAST_THREE_MONTHS(1, 90), PAST_SIX_MONTHS(1, 180), PAST_TWELVE_MONTHS(1, 365),
    PAST_TWENTY_FOUR_MONTHS(1, 730), PAST_THIRTY_SIX_MONTHS(1, 1095), 
    ABOVE_THIRTY_SIX_MONTHS(1096, 30000);

    private final Integer start;
    private final Integer end;

    private DateRangeItem(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public static DateRangeItem get(Integer end) {
        switch(end){
            case 7:
                return PAST_ONE_WEEK;
            case 14:
                return PAST_TWO_WEEKS;
            case 30:
                return PAST_ONE_MONTH;
            case 90:
                return PAST_THREE_MONTHS;
            case 180:
                return PAST_SIX_MONTHS;
            case 365:
                return PAST_TWELVE_MONTHS;
            case 730:
                return PAST_TWENTY_FOUR_MONTHS;
            case 1095:
                return PAST_THIRTY_SIX_MONTHS;
            case 30000:
                return ABOVE_THIRTY_SIX_MONTHS;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+end);
        }
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }
    
    public List<DateRangeItem> getShortRange(){
        DateRangeItem [] items = {PAST_ONE_WEEK,PAST_TWO_WEEKS, PAST_ONE_MONTH};
        return new ArrayList<>(Arrays.asList(items));
    }
    
    public List<DateRangeItem> getLongRange(){
        DateRangeItem [] items = {PAST_ONE_MONTH, PAST_THREE_MONTHS, PAST_SIX_MONTHS, PAST_TWELVE_MONTHS};
        return new ArrayList<>(Arrays.asList(items));
    }
}
