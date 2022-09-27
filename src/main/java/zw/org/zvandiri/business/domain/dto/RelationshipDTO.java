package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Relationship;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class RelationshipDTO implements Serializable {
    private String id;
    private String name;

    public RelationshipDTO() {
    }

    public RelationshipDTO(Relationship relationship) {
        this.id = relationship.getId();
        this.name = relationship.getName();
    }

    public RelationshipDTO(String id) {
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
