package zw.org.zvandiri.business.domain;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.Condition;
import zw.org.zvandiri.business.domain.util.PhoneStatus;
import zw.org.zvandiri.business.domain.util.RecordSource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Entity
/*@Table(indexes = {
        @Index(name = "cadre_bicycle", columnList = "cadre"),
        @Index(name = "cadre_bike_type", columnList = "bikeType")
})*/
public class Bicycle extends BaseEntity{
    @NotNull(message = "Bicycle type cannot be empty.")
    private String bikeType;
    private String bikeIssues;
    @Enumerated
    private Condition bikeCondition=Condition.NEW_ONE;
    @Enumerated
    private PhoneStatus bikeStatus=PhoneStatus.WORKING;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateIssued;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateRecovered;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="cadre")
    private Cadre cadre;

    public Bicycle(String type, Condition condition, PhoneStatus bikeStatus, Date dateIssued, Date dateRecovered, Cadre cadre) {
        super();
        this.bikeType = type;
        this.bikeCondition = condition;
        this.bikeStatus = bikeStatus;
        this.dateIssued = dateIssued;
        this.dateRecovered = dateRecovered;
        this.cadre=cadre;
    }

    public Bicycle() {

    }

    public Bicycle(Cadre cadre) {
        this.cadre = cadre;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBikeIssues() {
        return bikeIssues;
    }

    public void setBikeIssues(String bikeIssues) {
        this.bikeIssues = bikeIssues;
    }

    public Condition getBikeCondition() {
        return bikeCondition;
    }

    public void setBikeCondition(Condition bikeCondition) {
        this.bikeCondition = bikeCondition;
    }

    public PhoneStatus getBikeStatus() {
        return bikeStatus;
    }

    public void setBikeStatus(PhoneStatus bikeStatus) {
        this.bikeStatus = bikeStatus;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Date getDateRecovered() {
        return dateRecovered;
    }

    public void setDateRecovered(Date dateRecovered) {
        this.dateRecovered = dateRecovered;
    }

    public Cadre getCadre() {
        return cadre;
    }

    public void setCadre(Cadre cadre) {
        this.cadre = cadre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseEntity)) {
            return false;
        }
        return this.getId().equals(((BaseEntity) obj).getId());
    }


}
