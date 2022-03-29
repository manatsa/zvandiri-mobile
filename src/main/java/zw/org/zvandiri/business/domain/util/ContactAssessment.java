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
public enum ContactAssessment {

    CLINICAL(1), NON_CLINICAL(2);

    private final Integer code;

    private ContactAssessment(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ContactAssessment get(Integer code) {
        for (ContactAssessment item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method :" + code);
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name() != null ? getName() : null;
    }

}
