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
 * @author manatsachinyeruse@gmail.com
 */
public enum BugStatus {

    RESOLVED(1), PENDING(2), IMPOSSIBLE(3), OTHER(4);

    private final Integer code;

    private BugStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static BugStatus get(Integer code) {
        switch (code) {
            case 1:
                return RESOLVED;
            case 2:
                return PENDING;
            case 3:
                return IMPOSSIBLE;
            case 4:
                return OTHER;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
        }
    }
    


    
    public static List<BugStatus> getItems() {
        return new ArrayList<>(Arrays.asList(new BugStatus[] {
            RESOLVED, PENDING, IMPOSSIBLE, OTHER
        }));
    }
}
