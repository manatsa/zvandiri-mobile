/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author tasu
 */
public enum Test {
    
    YES(1), NO(2);
    
    private final Integer code;
    
    private Test(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return code;
    }
    
    public static Test get(Integer code){
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
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name() != null ? getName() : null;
    }
}
