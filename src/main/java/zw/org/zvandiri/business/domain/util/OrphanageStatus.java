package zw.org.zvandiri.business.domain.util;


import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum OrphanageStatus {
    NONE(1),DOUBLE(2), MATERNAL(3), PATERNAL(4);

    private final Integer code;

    OrphanageStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


    public static OrphanageStatus get(Integer code){
        if(code==null)return null;
        switch(code){
            case 1:
                return NONE;
            case 2:
                return DOUBLE;
            case 3:
                return MATERNAL;
            case 4:
                return PATERNAL;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return super.name().replace("_", " ");
    }
}
