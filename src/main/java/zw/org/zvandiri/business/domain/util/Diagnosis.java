/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author tasu
 */
public enum Diagnosis {
    
    DEPRESSION(1), ANXIETY(2), PTSD(3), SUBSTANCE_ABUSE(4), PSYCHOSIS(5), OTHER(6);
   //COMMON_MENTAL_HEALTH_PROBLEMS(1), SUBSTANCE_ABUSE(2), PSYCHOSIS(3), OTHER(4);

    private final Integer code;

    Diagnosis(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static final Diagnosis get(Integer code){
        for(Diagnosis item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Parameter passed to method not recognized:" + code);
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name().replace("_", " ");
    }
}
