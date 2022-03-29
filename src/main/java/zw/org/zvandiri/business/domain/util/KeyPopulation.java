package zw.org.zvandiri.business.domain.util;


import zw.org.zvandiri.business.util.StringUtils;

/**
 * @author manatsachinyeruse@gmail.com
 */


public enum KeyPopulation {
    YOUNG_WOMEN_SELLING_SEX(1), DRUG_ABUSER(2), OTHER(3);

    private final Integer code;

    KeyPopulation(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static KeyPopulation get(String code){
        if(code=="")return null;
        switch(code){
            case "1":
                return YOUNG_WOMEN_SELLING_SEX;
            case "2":
                return DRUG_ABUSER;
            case "3":
                return OTHER;
            default:
                throw new IllegalArgumentException("Illegal parameter passed to method :"+code);
        }
    }

    public static KeyPopulation get(Integer code){
        if(code==null)return null;
        switch(code){
            case 1:
                return YOUNG_WOMEN_SELLING_SEX;
            case 2:
                return DRUG_ABUSER;
            case 3:
                return OTHER;
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
