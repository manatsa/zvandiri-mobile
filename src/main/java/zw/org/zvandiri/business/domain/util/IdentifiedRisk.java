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
public enum IdentifiedRisk {
    
    //DEPRESSION(1), ANXIETY(2), POST_TRAUMATIC_STRESS(3), SUBSTANCE_ABUSE(4), SUICIDE(5), PSYCHOSIS(6);
    COMMON_MENTAL_HEALTH_PROBLEMS(1), SUBSTANCE_ABUSE(2), SUICIDE(3), PSYCHOSIS(4), DEPRESSION(5), ANXIETY(6), POST_TRAUMATIC_STRESS(7);

    private final Integer code;

    private IdentifiedRisk(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    
    public final static IdentifiedRisk get(Integer code){
        for(IdentifiedRisk risk : values()){
            if(code.equals(risk.getCode())){
                return risk;
            }
        }
        throw new IllegalArgumentException("Parameter passed to method not recognized: " + code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    public String concatenate(IdentifiedRisk risk){
        return this.getName()+","+risk.getName();
    }
}
