package zw.org.zvandiri.business.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Judge Muzinda
 */
@Entity
public class Province extends BaseName {

    @JsonIgnore
    @OneToMany(mappedBy = "province", cascade = CascadeType.REMOVE)
    private Set<District> districts = new HashSet<>();
    
    public Province() {
    }

    public Province(String id) {
        super(id);
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name=" + getName() +
                '}';
    }
}
