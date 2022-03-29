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
public enum Support {
    
    BY_ZM_ZI(1), CATS(2), MENTAL_HEALTH_SPECIALIST(3);

    private final Integer code;

    private Support(Integer code) {
        this.code = code;
    }

    public static Support get(Integer code) {
        for (Support item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name().replace("_", " ");
    }
}
