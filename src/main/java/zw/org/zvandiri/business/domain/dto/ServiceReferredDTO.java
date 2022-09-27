package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.ServicesReferred;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ServiceReferredDTO implements Serializable {
    private String id;
    private String name;

    public ServiceReferredDTO() {
    }

    public ServiceReferredDTO(String id) {
        this.id=id;
    }


    public ServiceReferredDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ServiceReferredDTO(ServicesReferred servicesReferred) {
        this.id = servicesReferred.getId();
        this.name = servicesReferred.getName();
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
