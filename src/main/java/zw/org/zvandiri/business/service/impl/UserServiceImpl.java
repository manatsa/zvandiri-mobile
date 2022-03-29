package zw.org.zvandiri.business.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.UserType;
import zw.org.zvandiri.business.repo.UserRepo;
import zw.org.zvandiri.business.repo.UserRoleRepo;
import zw.org.zvandiri.business.service.UserRoleService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Lazy
    @Resource
    private UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;
    @Lazy
    @Resource
    private UserRoleService userRoleService;

    @Override
    public List<User> getAll() {
        return userRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public User get(String id) {
        return userRepo.findUser(id);
    }

    @Override
    public void delete(User t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        t.setDeleted(Boolean.TRUE);
        userRepo.save(t);
    }

    @Override
    public List<User> getPageable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public User save(User t) {
        if (t.getId() == null) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(getCurrentUser());
            t.setDateCreated(new Date());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(t.getPassword());
            t.setPassword(hashedPassword);
            return userRepo.save(t);
        }
        t.setModifiedBy(getCurrentUser());
        t.setDateModified(new Date());
        return userRepo.save(t);
    }

    @Override
    @Transactional
    public User changePassword(User t) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(t.getPassword());
        t.setPassword(hashedPassword);
        t.setModifiedBy(getCurrentUser());
        t.setDateModified(new Date());
        return userRepo.save(t);
    }

    @Override
    public Boolean checkDuplicate(User current, User old) {
        if (current.getId() == null) {
            if (findByUserName(current.getUserName()) != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public User findByUserName(String name) {
        return userRepo.findByUserName(name, Boolean.TRUE);
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();
        if (username == null) {
            return null;
        }
        User user = findByUserName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String) {
            String principal = (String) authentication.getPrincipal();
            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public List<User> getByUserType() {
        return userRepo.findByUserType(UserType.getZvandiriStaff());
    }

    @Override
    public List<User> searchUsers(String [] names) {
        if (names.length == 1) {
            return userRepo.findByUserNameLike(names[0]+"%");
        }
        return userRepo.findByNames(names[0], names[1]);
    }

    @Override
    public List<User> getUsers(SearchDTO dto) {
        StringBuilder builder = new StringBuilder("Select Distinct u from User u inner join u.userRoles");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                builder.append(" where u.province=:province");
                position++;
            } else {
                builder.append(" and u.province=:province");
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                builder.append(" where u.district=:district");
                position++;
            } else {
                builder.append(" and u.district=:district");
            }
        }
        if (dto.getUserType() != null) {
            if (position == 0) {
                builder.append(" where u.userType=:userType");
                position++;
            } else {
                builder.append(" and u.userType=:userType");
            }
        }
        if (dto.getUserLevel() != null) {
            if (position == 0) {
                builder.append(" where u.userLevel=:userLevel");
                position++;
            } else {
                builder.append(" and u.userLevel=:userLevel");
            }
        }
        if (dto.getUserRoles() != null && !dto.getUserRoles().isEmpty()) {
            if (position == 0) {
                builder.append(" where :userRoles member of u.userRoles");
                position++;
            } else {
                builder.append(" and :userRoles member of u.userRoles");
            }
        }
        builder.append(" order by u.lastName, u.firstName ASC");
        TypedQuery<User> query = entityManager.createQuery(builder.toString(), User.class);
        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }
        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getUserType() != null) {
            query.setParameter("userType", dto.getUserType());
        }
        if (dto.getUserLevel() != null) {
            query.setParameter("userLevel", dto.getUserLevel());
        }
        if (dto.getUserRoles() != null && !dto.getUserRoles().isEmpty()) {
            query.setParameter("userRoles", dto.getUserRoles());
        }
        logger.info("Query statement here \n\n\n " + builder.toString());
        System.out.println("Query statement here \n\n\n " + builder.toString());
        return query.getResultList();
    }
}
