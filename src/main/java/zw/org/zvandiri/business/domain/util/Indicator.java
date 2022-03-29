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

import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author Judge Muzinda
 */
public enum Indicator {

    NUMBER_OF_CONTACTS(1), NUMBER_OF_PATIENTS(2), NUMBER_OF_NEWLY_REGISTERED_PATIENTS(3),
    NUMBER_EXITING_THE_PROGRAM(4), NUMBER_NEWLY_INITIATED_ON_ART(5),
    NUMBER_CURRENTLY_ON_ART(6), NUMBER_ON_ART_GREATER_THAN_12_MONTHS(7);

    private final Integer code;

    private Indicator(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static Indicator get(Integer code){
        for(Indicator indicator : values()){
            if(code.equals(indicator.getCode())) return indicator;
        }
        throw new IllegalArgumentException("Illegal parameter passed to method :"+ code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
