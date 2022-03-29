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
public enum NumberOfANCVisit {
    
    ONE(1, "1"), TWO(2, "2"), THREE(3, "3"), FOUR_AND_ABOVE(4, "4+"), 
    UNKNOWN(5, "Unknown");
    private final String name;
    private final Integer code;
    
    private NumberOfANCVisit(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
    
    public static NumberOfANCVisit get(Integer code) {
        for(NumberOfANCVisit item : values()) {
            if(code.equals(item.getCode())){
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method : "+code);
    }
}
