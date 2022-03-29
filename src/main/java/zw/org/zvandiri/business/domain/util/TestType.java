/*
 * Copyright 2018 jmuzinda.
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
 * @author jmuzinda
 */
public enum TestType {
    
    VIRAL_LOAD(1), CD4_COUNT(2);
    
    private final Integer code;
    
    private TestType (Integer code) {
        this.code = code;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public static TestType get(Integer code) {
        for (TestType testType : values()) {
            if (testType.getCode().equals(code)) {
                return testType;
            }
        }
        throw new IllegalStateException("Illegal parameter passed to method : "+ code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
