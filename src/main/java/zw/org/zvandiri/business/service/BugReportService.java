package zw.org.zvandiri.business.service;


import zw.org.zvandiri.business.domain.BugReport;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.BugStatus;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

public interface BugReportService extends GenericService<BugReport> {

    public List<BugReport> getAllByCreatedBy(User createdBy);
    public List<BugReport> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end);
    public List<BugReport> getAllByCreatedByAndStatus(User createdBy, BugStatus status);
    public List<BugReport> getAllByStatus(BugStatus status);
}