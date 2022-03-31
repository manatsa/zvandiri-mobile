package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Position;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class PositionDTO {
    private String id;
    private String name;

    public PositionDTO() {
    }

    public PositionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public PositionDTO(Position position) {
        this.id = position.getId();
        this.name = position.getName();
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
