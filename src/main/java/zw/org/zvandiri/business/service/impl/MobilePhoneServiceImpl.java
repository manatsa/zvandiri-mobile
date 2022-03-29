package zw.org.zvandiri.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.MobilePhone;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.repo.MobilePhoneRepository;
import zw.org.zvandiri.business.service.MobilePhoneService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
public class MobilePhoneServiceImpl implements MobilePhoneService {

    @Resource
    private UserService userService;
    @Resource
    MobilePhoneRepository phoneRepository;


    @Override
    @Transactional
    public MobilePhone save(MobilePhone t) {
        if (t.getId() == null || StringUtils.isBlank(t.getId())) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return phoneRepository.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return phoneRepository.save(t);

    }

    @Override
    public MobilePhone getByCadre(Cadre cadre) {
        if(cadre!=null)
            return phoneRepository.getByCadre(cadre);
        throw new IllegalArgumentException("The value of Patient is not allowed to be null!");
    }

    @Override
    public MobilePhone get(String id) {
        if(id==null || id.trim().isEmpty())
            throw new IllegalArgumentException("Mobile Phone ID cannot be null");

        return phoneRepository.findById(id).get();
    }

    @Override
    public Optional<MobilePhone> getIfNotNull(String id) {
        System.err.println("MOBILE ID: "+id);
        if(id==null || id.trim().isEmpty())
            return Optional.ofNullable(new MobilePhone());
MobilePhone phone=phoneRepository.findById(id).get();
        System.err.println("Retrieved:"+phone);
        return Optional.of(phone);
    }
}
