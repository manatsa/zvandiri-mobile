package zw.org.zvandiri.business.domain.util;


import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum OrphanStatus {
    NONE(1),DOUBLE(2), MATERNAL(3), PATERNAL(4);

    private final Integer code;

    OrphanStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static OrphanStatus get(String code){
        switch(code){
            case "1":
                return NONE;
            case "2":
                return DOUBLE;
            case "3":
                return MATERNAL;
            case "4":
                return PATERNAL;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }

    public static final OrphanStatus get(Integer code){
        for(OrphanStatus item : values()){
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
