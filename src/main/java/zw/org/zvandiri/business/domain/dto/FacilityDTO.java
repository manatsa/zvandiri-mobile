package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class FacilityDTO implements Serializable {
    private String id;
    private String name;
    private String district;

    public FacilityDTO() {
    }

    public FacilityDTO(String id, String name, String district) {
        this.id = id;
        this.name = name;
        this.district=district;
    }

    public FacilityDTO(Facility facility) {
        this.id = facility.getId();
        this.name = facility.getName();
        this.district=facility.getDistrict().getId();
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
        return district;
    }

    public void setProvince(String district) {
        this.district = district;
    }
}
