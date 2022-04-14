package zw.org.zvandiri.business.domain;


import zw.org.zvandiri.business.domain.dto.BugReportDTO;
import zw.org.zvandiri.business.domain.util.BugStatus;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Entity
public class BugReport extends BaseEntity{
    private String bug;
    private String details;
    private BugStatus status;
    private Date resolveDate;

    public BugReport() {
    }

    public BugReport(String id) {
        super(id);
    }

    public BugReport(BugReportDTO bugReportDTO) {
        this.bug = bugReportDTO.getBug();
        this.details = bugReportDTO.getDetails();
        this.status = bugReportDTO.getStatus();
        this.resolveDate = bugReportDTO.getResolveDate();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }
}
