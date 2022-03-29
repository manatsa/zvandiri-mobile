/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

*/
/**
 *
 * @author manatsachinyeruse@gmail.com
 *//*

public enum TPTStatus {

    CURRENTLY_ON_TPT(1), COMPLETED_TPT_WITHIN_3YEARS(2), NEVER_ON_TPT_IN_THE_PAST_3YEARS(3);

    private final Integer code;

    TPTStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static final TPTStatus get(Integer code){
        for(TPTStatus item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Parameter passed to method not recognized:" + code);
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name().replace("_", " ");
    }
}
*/
