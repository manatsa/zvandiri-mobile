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
public enum ArtStarted {
    ITEM_1(1, "On ART before this pregnancy"),ITEM_2(2, "Started in this pregancy"),
    ITEM_3(3, "Stopped ART"), ITEM_4(4, "Started ART post delivery"),
    ITEM_5(5, "Never on ART"), ITEM_6(6, "Unknown");
    
    private final Integer code;
    private final String name;
    
    private ArtStarted(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    public static ArtStarted get(Integer code){
        for(ArtStarted item : values()) {
            if(code.equals(item.getCode())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method : "+code);
    }
}
