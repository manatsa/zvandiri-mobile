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
public enum Referral {
    
    PRIMARY_COUNSELLOR(1), MENTAL_HEALTH_NURSE(2), DMO(3), OUTPATIENT_PSYCHIATRY(4), INPATIENT_PSYCHIATRY(5);

    private final Integer code;

    private Referral(Integer code) {
        this.code = code;
    }

    public static Referral get(Integer code) {
        for (Referral item : values()) {
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
