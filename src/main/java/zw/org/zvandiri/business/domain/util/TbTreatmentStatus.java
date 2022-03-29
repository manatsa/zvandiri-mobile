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
public enum TbTreatmentStatus {
    ACTIVE_ON_TB_TREATMENT(1), DEFAULTED_ON_TB_TREATMENT(2), COMPLETED_TREATMENT(3);
    private final Integer code;

    private TbTreatmentStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static TbTreatmentStatus get(Integer code) {
        for(TbTreatmentStatus item : values()){
            if(item.getCode().equals(code))
                return item;
        }
        throw new IllegalArgumentException("Unknown parameter passes to method: " + code);
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }
}
