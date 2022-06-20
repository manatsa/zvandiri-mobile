/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.util.CatsMentorship;
import zw.org.zvandiri.business.domain.util.PhoneStatus;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author jmuzinda
 */
@Entity 
/*@Table(indexes = {
		@Index(name = "cat_activity_cat_detail", columnList = "cat_detail"),
		@Index(name = "cat_activity_created_by", columnList = "created_by")
})*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatActivity extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name="cat_detail")
    private CatDetail catDetail;
    private String certificateNumber;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateIssued;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateReceivedMentorship;
    @Enumerated
    private CatsMentorship catsMentorship;
    @Enumerated
    private YesNo assignedPhone;
    private String phoneModel;
    private String serialNumber;
    @Enumerated
    private PhoneStatus phoneStatus;
    @Enumerated
    private YesNo issuedBicycle;
    @ManyToOne
    private District district;
    @Transient
    private Province province;
    @Column(columnDefinition = "text")
    private String description;

    public CatActivity() {
    }

    public CatActivity(CatDetail catDetail) {
        this.catDetail = catDetail;
    }

    public CatDetail getCatDetail() {
        return catDetail;
    }

    public void setCatDetail(CatDetail catDetail) {
        this.catDetail = catDetail;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Date getDateReceivedMentorship() {
        return dateReceivedMentorship;
    }

    public void setDateReceivedMentorship(Date dateReceivedMentorship) {
        this.dateReceivedMentorship = dateReceivedMentorship;
    }

    public CatsMentorship getCatsMentorship() {
        return catsMentorship;
    }

    public void setCatsMentorship(CatsMentorship catsMentorship) {
        this.catsMentorship = catsMentorship;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public YesNo getAssignedPhone() {
		return assignedPhone;
	}

	public void setAssignedPhone(YesNo assignedPhone) {
		this.assignedPhone = assignedPhone;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public PhoneStatus getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(PhoneStatus phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public YesNo getIssuedBicycle() {
		return issuedBicycle;
	}

	public void setIssuedBicycle(YesNo issuedBicycle) {
		this.issuedBicycle = issuedBicycle;
	}
    
}
