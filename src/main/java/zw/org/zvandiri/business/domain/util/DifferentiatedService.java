package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

public enum DifferentiatedService {

    FARGS(1), CARG_GROUP(2), FAST_TRACK(3), ART_REFILL_CLUB(4);

    private final Integer code;

    private DifferentiatedService(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static DifferentiatedService get(Integer code) {
        for (DifferentiatedService item : values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Illegal parameter passed to method : " + code);
    }

    public String getName() {
        return StringUtils.toCamelCase3(super.name());
    }

}
