/*
 * Copyright 2017 jmuzinda.
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

/**
 *
 * @author jmuzinda
 */
public enum GestationalAge {
    
    TWELVE_WEEKS(1, "<12 wks"), THEIGHTEEN_TO_TWENTYFIVE_WEEKS(2, "13-25 wks"),
    TWENTYSIX_WEEKS(3,"26+ weeks"), NO_ANC(4, "No ANC"), UNKNOWN(5, "Unknown");
    
    private final String name;
    private final Integer code;
    
    private GestationalAge(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
    
    public static GestationalAge get(Integer code) {
        for(GestationalAge item : values()) {
            if(code.equals(item.getCode())){
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid parameter passed to method : "+ code);
    }
}
