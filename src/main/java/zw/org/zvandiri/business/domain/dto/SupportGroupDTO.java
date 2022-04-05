package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.SupportGroup;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class SupportGroupDTO {
    private String id;
    private String district;
    private String name;

    public SupportGroupDTO() {
    }

    public SupportGroupDTO(SupportGroup supportGroup) {
        this.id = supportGroup.getId();
        this.name = supportGroup.getName();
        this.district=(supportGroup.getDistrict()!=null)?supportGroup.getDistrict().getId():null;
    }

    public SupportGroupDTO(String id) {
        this.id = id;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
