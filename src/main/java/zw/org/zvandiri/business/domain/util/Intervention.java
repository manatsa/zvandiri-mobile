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
public enum Intervention {
    
    COUNSELLING(1), MEDICATION(2), MEDICATION_AND_COUNSELLING(3), OTHER(4);

    private final Integer code;

    Intervention(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static final Intervention get(Integer code){
        for(Intervention item : values()){
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
