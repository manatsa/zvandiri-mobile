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
public enum VisitOutcome {
    
    SUCCESSFUL(1), DECEASED(2), CHANGED_LOCATION(3);
    
    private final Integer code;
    
    private VisitOutcome(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static VisitOutcome get(Integer code){
        for (VisitOutcome item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
    }
    
    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
