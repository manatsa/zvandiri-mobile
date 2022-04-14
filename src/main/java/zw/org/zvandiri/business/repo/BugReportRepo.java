package zw.org.zvandiri.business.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.BugReport;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.BugStatus;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Repository
public interface BugReportRepo extends JpaRepository<BugReport, String> {
    public List<BugReport> getAllByCreatedBy(@Param("createdBy")User createdBy);
    @Query("from BugReport where createdBy=:createdBy and dateCreated between :start and :end")
    public List<BugReport> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end);
    public List<BugReport> getAllByCreatedByAndStatus(User createdBy,BugStatus status);
    public List<BugReport> getAllByStatus(BugStatus status);
}
