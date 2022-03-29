package zw.org.zvandiri.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.Period;
import zw.org.zvandiri.business.repo.PeriodRepo;
import zw.org.zvandiri.business.service.PeriodService;
import zw.org.zvandiri.business.service.UserService;
import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.UUIDGen;

/**
 *
 * @author Edward Zengeni
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PeriodServiceImpl implements PeriodService {
    
    @Resource
    private PeriodRepo periodRepo;
    @Resource
    private UserService userService;
    
    @Transactional
    @Override
    public Period save(Period t) {
        if (t.getId() == null) {
            t.setDateCreated(new Date());
            t.setId(UUIDGen.generateUUID());
            t.setCreatedBy(userService.getCurrentUser());
            return periodRepo.save(t);
        }
        t.setDateModified(new Date());
        t.setModifiedBy(userService.getCurrentUser());
        return periodRepo.save(t);
    }
    
    @Override
    public Period get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return periodRepo.findById(id).get();
    }
    
    @Override
    public void delete(Period t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        periodRepo.save(t);
    }
    
    @Override
    public List<Period> getPageable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Period> getAll() {
        return periodRepo.getOptAll(Boolean.TRUE);
    }
    
    @Override
    public Boolean checkDuplicate(Period current, Period old) {
        if (periodRepo.getByStartDateAndEndDate(current.getStartDate(), current.getEndDate()).size() >= 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    @Override
    public Period getLatestPeriod() {
        for (Period p : getAll()) {
            return p;
        }
        throw new IllegalStateException("Method invocation in error");
    }
    
    @Override
    public Period getCurrentPeriod(Date currentDate) {
        return getLatestPeriod();
    }
    
    @Override
    public List<Period> getActivePeriods() {
        return periodRepo.getOptAll(Boolean.TRUE);
    }  

    @Override
    public Period getByStartAndEndDate(Date startDate, Date endDate) {
        return periodRepo.getByByPeriod(startDate, endDate);
    }

    @Override
    @Deprecated
    public List<Period> getLastTwelveMonths() {
        /**
         * inefficient method efficient method will be used in next version
         * method will be eliminated in next version
         */
        List<Period> items = new ArrayList<>();
        int count = 0;
        for(Period p : getAll()){
            if(count < 12) items.add(p);
            count++;
        }
        return items;
    }

    @Override
    public List<Period> getPastPeriod(int monthsPast) {
        List<Period> items = new ArrayList<>();
        int count = 0;
        for(Period p : getAll()){
            if(count < monthsPast) items.add(p);
            count++;
        }
        return items;
    }

    @Override
    public Period getByStartDate(Date startDate) {
        return periodRepo.getPeriodByStartDate(startDate);
    }

    @Override
    public Period constructPeriod(Date startDate) {
        Period period = new Period();
        period.setStartDate(DateUtil.getPeriodStart(startDate));
        period.setEndDate(DateUtil.getPeriodEnd(startDate));
        return period;
    }
    
}