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
public enum ReferalType {
    HIV_STI_PREVENTION(1), OI_ART_SERVICES(2), SRH_SERVICES(3),
    PSYCHO_SOCIAL_SUPPORT(4), LABORATORY_DIAGNOSES(5),
    TB_SERVICES(6), LEGAL_SUPPORT(7);
    
    private final Integer code;
    
    private ReferalType(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static ReferalType get(Integer code){
        for(ReferalType item : values()){
            if(code.equals(item.getCode())) return item;
        }
        throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}