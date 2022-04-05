package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Referer;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class RefererDTO {
    private String id;
    private String name;

    public RefererDTO() {
    }

    public RefererDTO(Referer referer) {
        this.id = referer.getId();
        this.name = referer.getName();
    }

    public RefererDTO(String id) {
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
}
