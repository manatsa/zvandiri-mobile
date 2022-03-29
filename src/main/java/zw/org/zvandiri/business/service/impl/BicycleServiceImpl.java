package zw.org.zvandiri.business.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.repo.BicycleRepo;
import zw.org.zvandiri.business.service.BicycleService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.UUIDGen;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
public class BicycleServiceImpl implements BicycleService {

    @Resource
    BicycleRepo bicycleRepo;
    @Resource
    UserService userService;

    @Override
    public Bicycle save(Bicycle t) {
        if (t.getId() == null || StringUtils.isBlank(t.getId())) {
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            System.err.println("Bike TO BE SAVED: "+t.toString());
            return bicycleRepo.save(t);
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        System.err.println("Existing Bike TO BE SAVED: "+t.toString());
        return bicycleRepo.save(t);
    }

    @Override
    public Bicycle getByCadre(Cadre cadre) {
        if(cadre!=null)
            return bicycleRepo.getByCadre(cadre);
        throw new IllegalArgumentException("The value of Patient is not allowed to be null!");
    }

    @Override
    public Bicycle get(String id) {
        if(id==null)
            throw new IllegalArgumentException("ID cannot be null");

        return bicycleRepo.findById(id).get();
    }

    @Override
    public Optional<Bicycle> getIfNotNull(String id) {
        if(id==null || id.trim().isEmpty())
            return Optional.ofNullable(new Bicycle());

        return Optional.ofNullable(bicycleRepo.findById(id).get());
    }
}
