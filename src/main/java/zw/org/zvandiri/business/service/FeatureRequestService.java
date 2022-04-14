package zw.org.zvandiri.business.service;


import zw.org.zvandiri.business.domain.FeatureRequest;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.User;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

public interface FeatureRequestService extends GenericService<FeatureRequest> {

    public List<FeatureRequest> getAllByCreatedBy(User createdBy);
    public List<FeatureRequest> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end);
}