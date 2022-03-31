package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.ServiceOffered;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ServiceOfferredDTO {
    private String id;
    private String name;

    public ServiceOfferredDTO() {
    }

    public ServiceOfferredDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ServiceOfferredDTO(ServiceOffered serviceOffered) {
        this.id = serviceOffered.getId();
        this.name = serviceOffered.getName();
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
