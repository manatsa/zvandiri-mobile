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
public enum CauseOfDeath {
    
    TB(1), CRYPROCOCCAL_MENENGITIS(2), CANCER(3), ANAEMIA(4), ACUTE_INFECTION(5), OTHERS(6);

    private final Integer code;

    private CauseOfDeath(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CauseOfDeath get(Integer code) {
        for (CauseOfDeath item : values()) {
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
