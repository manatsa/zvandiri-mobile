package zw.org.zvandiri.business.service.impl;


import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.repo.MessageRepo;
import zw.org.zvandiri.business.repo.MortalityRepo;
import zw.org.zvandiri.business.service.MessageService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepo repo;
    @Resource
    private UserService userService;
    @Resource
    private PatientService patientService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Message> getAll() {
        return repo.findAll();
    }

    @Override
    public Message get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist :" + id);
        }
        return repo.findById(id).get();
    }

    @Override
    public void delete(Message t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        repo.delete(t);
    }


    @Override
    public Message save(Message t) {
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
    public List<Message> getAllByCreatedBy(User createdBy) {
        return repo.getAllByCreatedBy(createdBy);
    }

    @Override
    public List<Message> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end) {
        return repo.getAllByCreatedByAndDateCreatedBetween(createdBy,start,end);
    }

    @Override
    public List<Message> getPageable() {
        return null;
    }

    @Override
    public Boolean checkDuplicate(Message current, Message old) {
        return null;
    }
}
