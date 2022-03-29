/*
 * Copyright 2015 Edward Zengeni.
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

/**
 *
 * @author Edward Zengeni
 */
public enum Gender {

    MALE(1), FEMALE(2), OTHER(3);

    private final Integer code;

    private Gender(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static Gender get(Integer code) {
        switch (code) {
            case 1:
                return MALE;
            case 2:
                return FEMALE;
            case 3:
                return OTHER;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
    
    public String getName(){
        //return StringUtils.toCamelCase3(super.name());
        return getAltName();
    }
    
    public String getAltName() {
        if (this == MALE) {
            return "M";
        }else if (this == FEMALE) {
            return "F";
        }else if (this == OTHER) {
            return "O";
        }
        return null;
    }
    
    public static List<Gender> getItems() {
        return new ArrayList<>(Arrays.asList(new Gender [] {
            MALE, FEMALE, OTHER
        }));
    }
}
