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
public enum CatsMentorship {

    FACILITY_MENTORSHIP(1), CATS_COORDINATION(2), E_MENTORSHIP(3);

    private final Integer code;

    private CatsMentorship(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CatsMentorship get(Integer code) {
        for (CatsMentorship item : values()) {
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
