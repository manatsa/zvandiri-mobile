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
public enum YesNo {
    
    YES(1), NO(2);
    
    private final Integer code;
    
    private YesNo(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static YesNo get(Integer code){
        switch(code){
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }
    
    public String getName(){
        return super.name();
    }

    @Override
    public String toString() {
        return super.name() != null ? getName() : null;
    }
    
}