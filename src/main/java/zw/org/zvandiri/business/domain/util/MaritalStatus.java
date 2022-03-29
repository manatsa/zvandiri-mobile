package zw.org.zvandiri.business.domain.util;


import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum MaritalStatus {
    MARRIED(1), SINGLE_OR_CHILD(2);

    private final Integer code;

    MaritalStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static MaritalStatus get(String code){
        if(code=="") return null;
        switch(code){
            case "1":
                return MARRIED;
            case "2":
                return SINGLE_OR_CHILD;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }


    public static MaritalStatus get(Integer code){
        if(code==null)return null;
        switch(code){
            case 1:
                return MARRIED;
            case 2:
                return SINGLE_OR_CHILD;
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
