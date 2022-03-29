/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author jmuzinda
 */
public enum MentalHealthScreeningType {
    INITIAL_SCREENING(1), RESCREENING(2);
    
    private final Integer code;
    
    private MentalHealthScreeningType(Integer code) {
        this.code = code;
    }
    
    public Integer getCode () {
        return code;
    }
    
    public static MentalHealthScreeningType get(Integer code) {
        for(MentalHealthScreeningType item : values()) {
            if(code.equals(item.getCode())){
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method : "+ code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name() != null ? getName() : null;
    }
}
