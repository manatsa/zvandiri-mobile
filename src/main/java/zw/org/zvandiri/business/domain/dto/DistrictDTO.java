package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.District;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class DistrictDTO implements Serializable {
    private String id;
    private String name;
    private String province;

    public DistrictDTO() {
    }

    public DistrictDTO(String id, String name, String province) {
        this.id = id;
        this.name = name;
        this.province=province;
    }

    public DistrictDTO(District district) {
        this.id = district.getId();
        this.name = district.getName();
        this.province=district.getProvince().getId();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
