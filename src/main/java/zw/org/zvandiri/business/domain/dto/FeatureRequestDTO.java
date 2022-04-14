package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.FeatureRequest;
import zw.org.zvandiri.business.domain.util.FeaturePlatform;
import zw.org.zvandiri.business.util.dto.UserDTO;

import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class FeatureRequestDTO  {
    private String id;
    private String feature;
    private String purpose;
    @Enumerated
    private FeaturePlatform platform; // mobile or web
    private String details;
    private Date dateCreated;


    public FeatureRequestDTO() {
    }

    public FeatureRequestDTO(FeatureRequest featureRequest) {
        this.feature=featureRequest.getFeature();
        this.details=featureRequest.getDetails();
        this.platform=featureRequest.getPlatform();
        this.purpose=featureRequest.getPurpose();
        this.id=featureRequest.getId();
        this.dateCreated= featureRequest.getDateCreated();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public FeaturePlatform getPlatform() {
        return platform;
    }

    public void setPlatform(FeaturePlatform platform) {
        this.platform = platform;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
