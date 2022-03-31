package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Province;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ProvinceDTO {
    private String id;
    private String name;

    public ProvinceDTO() {
    }

    public ProvinceDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProvinceDTO(Province province) {
        this.id = province.getId();
        this.name = province.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
