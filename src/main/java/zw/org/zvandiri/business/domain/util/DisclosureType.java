package zw.org.zvandiri.business.domain.util;

import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum DisclosureType {
    PARTIAL(1),FULL_DISCLOSURE(2), NONE(3);

    private final Integer code;

    DisclosureType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static DisclosureType get(String code){
        switch(code){
            case "1":
                return PARTIAL;
            case "2":
                return FULL_DISCLOSURE;
            case "3":
                return NONE;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }

    public static final DisclosureType get(Integer code){
        for(DisclosureType item : values()){
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
