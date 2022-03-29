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
 * @author Judge Muzinda
 */
public enum PatientChangeEvent {

    DECEASED(1), LOST_TO_FOLOWUP(2), GRADUATED(3), CHANGE_LOCATION(4), OPT_OUT(5), ACTIVE(6), REINSTATED(7), 
    HIGHLY_EXPOSED_TO_POSITIVE(8), OTHER(9);

    private final Integer code;

    private PatientChangeEvent(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static PatientChangeEvent get(Integer code) {
        for(PatientChangeEvent item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed tp method :" + code);
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

    public List<PatientChangeEvent> getDropItems() {
        List<PatientChangeEvent> items = new ArrayList<>();
        items.add(DECEASED);
        items.add(GRADUATED);
        items.add(OPT_OUT);
        items.add(REINSTATED);
        return items;
    }
    
    public List<PatientChangeEvent> getInactiveStatuses() {
        List<PatientChangeEvent> items = new ArrayList<>();
        items.add(DECEASED);
        items.add(GRADUATED);
        items.add(OPT_OUT);
        items.add(REINSTATED);
        return items;
    }
}
