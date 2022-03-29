/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Person;
import zw.org.zvandiri.business.repo.PersonRepo;
import zw.org.zvandiri.business.service.PersonService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author tasu
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonRepo personRepo;
    @Resource
    private UserService userService;

    @Override
    @Transactional
    public Person save(Person t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return personRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return personRepo.save(t);
    }

    @Override
    public List<Person> getAll() {
        return personRepo.findByActive(Boolean.TRUE);
    }

    @Override
    public Person get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return personRepo.findById(id).get();
    }

    @Override
    public void delete(Person t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        save(t);
    }

    @Override
    public List<Person> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkDuplicate(Person current, Person old) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
