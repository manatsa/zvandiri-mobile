package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.business.repo.UserRoleRepo;
import zw.org.zvandiri.business.service.UserRoleService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleRepo userRoleRepo;
    @Resource
    private UserService userService;

    @Override
    public List<UserRole> getAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole get(String id) {
        return userRoleRepo.findById(id).get();
    }

    @Override
    public void delete(UserRole t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        userRoleRepo.save(t);
    }

    @Override
    public List<UserRole> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public UserRole save(UserRole t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return userRoleRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return userRoleRepo.save(t);
    }

    @Override
    public UserRole getByName(String name) {
        return userRoleRepo.getUserRoleByName(name);
    }

    @Override
    public Boolean checkDuplicate(UserRole current, UserRole old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getName().equals(old.getName())) {
                if (userRoleRepo.getUserRoleByName(current.getName()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (userRoleRepo.getUserRoleByName(current.getName()) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<UserRole> findByNamesIn(Set<String> names) {
        return userRoleRepo.findByNamesIn(names);
    }

    @Override
    public Set<User> findUsersInRoles(Set<String> names) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&& : " + names);
        return userRoleRepo.findUsersInRoles(names);
    }

}
