package zw.org.zvandiri.business.domain;

import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.Condition;
import zw.org.zvandiri.business.domain.util.PhoneStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Entity
/*@Table(indexes = {
        @Index(name = "cadre_phone", columnList = "cadre"),
        @Index(name = "cadre_imei1", columnList = "imei1"),
        @Index(name = "cadre_msisdn1", columnList = "msisdn1")
})*/
public class MobilePhone extends BaseEntity{
    @NotBlank(message = "At least one phone number is needed")
    private String msisdn1;
    private String msisdn2;
    @NotBlank(message = "At least one IMEI number is needed")
    private String imei1;
    private String imei2;
    @NotBlank(message = "Phone make can not be empty!")
    private String phoneMake;
    @NotBlank(message = "Phone model can not be empty!")
    private String phoneModel;
    private String phoneIssues;
    @NotBlank(message = "Phone serial number can not be empty!")
    private String serialNumber;
    @Enumerated
    //@NotBlank(message = "Please select condition of the phone")
    private Condition phoneCondition=Condition.NEW_ONE;
    @Enumerated
    //@NotBlank(message = "Please select status of the phone")
    private PhoneStatus phoneStatus=PhoneStatus.WORKING;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Please enter date then phone was/is issued.")
    private Date dateIssued;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateRecovered;
    @OneToOne(cascade = CascadeType.ALL)
    private Cadre cadre;


    public MobilePhone( String serialNumber, String misdn1, String misdn2, String imei1, String imei2, String make, String model, Condition condition, PhoneStatus phoneStatus, Date dateIssued, Date dateRecovered, Cadre cadre) {
        super();
        this.serialNumber=serialNumber;
        this.msisdn1 = misdn1;
        this.msisdn2 = misdn2;
        this.imei1 = imei1;
        this.imei2 = imei2;
        this.phoneMake = make;
        this.phoneModel = model;
        this.phoneCondition = condition;
        this.phoneStatus = phoneStatus;
        this.dateIssued = dateIssued;
        this.dateRecovered = dateRecovered;
        this.cadre=cadre;

    }

    public MobilePhone() {
       super();
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMsisdn1() {
        return msisdn1;
    }

    public void setMsisdn1(String msisdn1) {
        this.msisdn1 = msisdn1;
    }

    public String getMsisdn2() {
        return msisdn2;
    }

    public void setMsisdn2(String msisdn2) {
        this.msisdn2 = msisdn2;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public String getPhoneMake() {
        return phoneMake;
    }

    public void setPhoneMake(String phoneMake) {
        this.phoneMake = phoneMake;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneIssues() {
        return phoneIssues;
    }

    public void setPhoneIssues(String phoneIssues) {
        this.phoneIssues = phoneIssues;
    }

    public Condition getPhoneCondition() {
        return phoneCondition;
    }

    public void setPhoneCondition(Condition phoneCondition) {
        this.phoneCondition = phoneCondition;
    }

    public PhoneStatus getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(PhoneStatus phoneStatus) {
        this.phoneStatus = phoneStatus;
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
}
