package zw.org.zvandiri.business.domain;


import zw.org.zvandiri.business.domain.dto.FeatureRequestDTO;
import zw.org.zvandiri.business.domain.util.FeaturePlatform;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Entity
public class FeatureRequest extends BaseEntity{
    private String feature;
    private String purpose;
    @Enumerated
    private FeaturePlatform platform; // mobile or web
    private String details;

    public FeatureRequest() {
    }

    public FeatureRequest(String id) {
        super(id);
    }

    public FeatureRequest(FeatureRequestDTO featureRequestDTO) {
        this.feature = featureRequestDTO.getFeature();
        this.purpose = featureRequestDTO.getPurpose();
        this.platform = featureRequestDTO.getPlatform();
        this.details = featureRequestDTO.getDetails();
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
}
