package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Location;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class LocationDTO {
    private String id;
    private String name;

    public LocationDTO() {
    }

    public LocationDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
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
