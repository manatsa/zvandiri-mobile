/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.domain;

import javax.persistence.Entity; import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import zw.org.zvandiri.business.service.LocationService;;

/**
 *
 * @author Judge Muzinda
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
public class Location extends BaseName {


    public Location() {
    }

    public Location(String id) {
        super(id);
    }

//    public Location(String id) {
//        Location l=locationService.get(id);
//        System.err.println(l);
//        this.setActive(l.getActive());
//        this.setCreatedBy(l.getCreatedBy());
//        this.setDateCreated(l.getDateCreated());
//        this.setDeleted(l.getDeleted());
//        this.setDescription(l.getDescription());
//        this.setDateModified(l.getDateModified());
//        this.setId(id);
//        this.setModifiedBy(l.getModifiedBy());
//        this.setName(l.getName());
//        this.setUuid(l.getUuid());
//        this.setVersion(l.getVersion());
//    }
    
}
