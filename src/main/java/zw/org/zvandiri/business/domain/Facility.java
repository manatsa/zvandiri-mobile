/*
 * Copyright 2015 Judge Muzinda.
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "facility_district", columnList = "district")
})*/
public class Facility extends BaseName {

    @ManyToOne
    @JoinColumn(name="district")
    //@JsonIgnoreProperties(value = { "uuid", "createdBy", "modifiedBy", "dateCreated","dateModified","version","deleted","description" })
    private District district;
    @Transient
    private Province province;

    public Facility() {
    }

    public Facility(String id) {
        super(id);
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id="+getId()+
                "name="+getName()+
                "district=" + district.getName() +
                '}';
    }
}
