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
package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author Judge Muzinda
 */
public enum UserLevel {
    NATIONAL(1), PROVINCE(2), DISTRICT(3), FACILITY(4);
    
    private final Integer code;
    
    private UserLevel(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static UserLevel get(Integer code){
        switch(code){
            case 1:
                return NATIONAL;
            case 2:
                return PROVINCE;
            case 3:
                return DISTRICT;
            case 4:
                return FACILITY;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}