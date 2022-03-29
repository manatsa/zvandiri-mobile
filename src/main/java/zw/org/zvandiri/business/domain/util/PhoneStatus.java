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
public enum PhoneStatus {
	
	WORKING(1), NOT_WORKING(2), LOST_OR_STOLEN(3);

    private final Integer code;

    private PhoneStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static PhoneStatus get(Integer code) {
        for (PhoneStatus item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Un recognised code passed to method : " + code);
    }
    
    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }
}
