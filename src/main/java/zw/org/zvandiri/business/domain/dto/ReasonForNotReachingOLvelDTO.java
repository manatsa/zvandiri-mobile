package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.ReasonForNotReachingOLevel;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ReasonForNotReachingOLvelDTO implements Serializable {
    private String id;
    private String name;

    public ReasonForNotReachingOLvelDTO() {
    }

    public ReasonForNotReachingOLvelDTO(ReasonForNotReachingOLevel reasonForNotReachingOLevel) {
        this.id = reasonForNotReachingOLevel.getId();
        this.name = reasonForNotReachingOLevel.getName();
    }

    public ReasonForNotReachingOLvelDTO(String id) {
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
