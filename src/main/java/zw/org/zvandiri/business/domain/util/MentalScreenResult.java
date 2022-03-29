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
public enum MentalScreenResult {
    IMPROVEMENT(1), NO_IMPROVEMENT(2);

    private final Integer code;

    private MentalScreenResult(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static final MentalScreenResult get(Integer code) {
        for (MentalScreenResult item : values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Parameter passed to method not recognized: " + code);
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }
}
