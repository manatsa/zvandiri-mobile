/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import javax.persistence.Entity;

/**
 *
 * @author jmuzinda
 */
@Entity
public class ServiceOffered  extends BaseName {   

    public ServiceOffered() {
    }

    public ServiceOffered(String id) {
        super(id);
    }
    
}
