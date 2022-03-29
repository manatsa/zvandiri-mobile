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
package zw.org.zvandiri.business.domain.util;

import java.util.ArrayList;
import java.util.List;
import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author Judge Muznda
 */
public enum UserType {

    ZVANDIRI_STAFF(1), CATS(2), ADMINISTRATOR(3);

    private final Integer code;

    private UserType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static UserType get(Integer code) {
        switch (code) {
            case 1:
                return ZVANDIRI_STAFF;
            case 2:
                return CATS;
            case 3:
                return ADMINISTRATOR;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
    
    public static List<UserType> getZvandiriStaff(){
        List<UserType> items = new ArrayList<>();
        items.add(ZVANDIRI_STAFF);
        items.add(CATS);
        return items;
    }
}