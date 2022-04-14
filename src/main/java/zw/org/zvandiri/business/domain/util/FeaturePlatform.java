package zw.org.zvandiri.business.domain.util;


import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum FeaturePlatform {
    ALL(1),WEB(2), MOBILE(3), OTHER(4);

    private final Integer code;

    FeaturePlatform(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static FeaturePlatform get(String code){
        if(code=="")return null;
        switch(code){
            case "1":
                return ALL;
            case "2":
                return WEB;
            case "3":
                return MOBILE;
            case "4":
                return OTHER;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }

    public static final FeaturePlatform get(Integer code){
        if(code==null)return null;
        for(FeaturePlatform item : values()){
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
