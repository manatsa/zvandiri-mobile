package zw.org.zvandiri.business.domain.dto;

import zw.org.zvandiri.business.domain.BugReport;
import zw.org.zvandiri.business.domain.util.BugStatus;
import zw.org.zvandiri.business.util.dto.UserDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */

public class BugReportDTO  implements Serializable {
    private String id;
    private String bug;
    private String details;
    private BugStatus status;
    private Date resolveDate;
    private Date dateCreated;

    public BugReportDTO() {
    }

    public BugReportDTO(BugReport bugReport) {
        this.bug=bugReport.getBug();
        this.status=bugReport.getStatus();
        this.details=bugReport.getDetails();
        this.resolveDate=bugReport.getResolveDate();
        this.id=bugReport.getId();
        this.dateCreated=bugReport.getDateCreated();
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
