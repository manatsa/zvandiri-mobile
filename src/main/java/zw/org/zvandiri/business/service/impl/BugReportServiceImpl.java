package zw.org.zvandiri.business.service.impl;


import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.BugReport;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.BugStatus;
import zw.org.zvandiri.business.repo.BugReportRepo;
import zw.org.zvandiri.business.repo.MessageRepo;
import zw.org.zvandiri.business.service.BugReportService;
import zw.org.zvandiri.business.service.MessageService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Service
public class BugReportServiceImpl implements BugReportService {

    @Resource
    private BugReportRepo repo;
    @Resource
    private UserService userService;

    @Override
    public List<BugReport> getAll() {
        return repo.findAll();
    }

    @Override
    public BugReport get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(BugReport t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        repo.delete(t);
    }


    @Override
    public BugReport save(BugReport t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public List<BugReport> getAllByCreatedBy(User createdBy) {
        return repo.getAllByCreatedBy(createdBy);
    }

    @Override
    public List<BugReport> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end) {
        return repo.getAllByCreatedByAndDateCreatedBetween(createdBy,start,end);
    }

    @Override
    public List<BugReport> getAllByCreatedByAndStatus(User createdBy, BugStatus status) {
        return repo.getAllByCreatedByAndStatus(createdBy,status);
    }

    @Override
    public List<BugReport> getAllByStatus(BugStatus status) {
        return repo.getAllByStatus(status);
    }


    @Override
    public List<BugReport> getPageable() {
        return null;
    }

    @Override
    public Boolean checkDuplicate(BugReport current, BugReport old) {
        return null;
    }
}
