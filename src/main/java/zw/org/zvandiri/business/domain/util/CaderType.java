package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum CaderType {
    CATS(1), YMM(2), YMD(3), FCI(4), OTHER(5),;

    private final Integer code;

    private CaderType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static CaderType get(Integer code) {
        for (CaderType item : values()) {
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
