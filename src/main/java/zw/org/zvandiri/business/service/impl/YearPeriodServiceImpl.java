/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.YearPeriod;
import zw.org.zvandiri.business.repo.YearPeriodRepo;
import zw.org.zvandiri.business.service.YearPeriodService;
import static zw.org.zvandiri.business.util.DateUtil.*;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class YearPeriodServiceImpl implements YearPeriodService {

    @Resource
    private YearPeriodRepo yearPeriodRepo;

    @Transactional
    @Override
    public YearPeriod save(YearPeriod t) {
        if (t.getId() == null) {
            t.setDateCreated(new Date());
            return yearPeriodRepo.save(t);
        }
        t.setDateModified(new Date());
        return yearPeriodRepo.save(t);
    }

    @Override
    public YearPeriod get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return yearPeriodRepo.findById(id).get();
    }

    @Override
    public void delete(YearPeriod t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        yearPeriodRepo.save(t);
    }

    @Override
    public List<YearPeriod> getPageable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    @Deprecated
    public List<YearPeriod> getLastTwelveMonths() {
        /**
         * inefficient method efficient method will be used in next version
         * method will be eliminated in next version
         */
        List<YearPeriod> items = new ArrayList<>();
        int count = 0;
        for(YearPeriod p : getAll()){
            if(count < 12) items.add(p);
            count++;
        }
        return items;
    }

    @Override
    public List<YearPeriod> getAll() {
        return yearPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public Boolean checkDuplicate(YearPeriod current, YearPeriod old) {
        if (yearPeriodRepo.getByStartDateAndEndDate(current.getStartDate(), current.getEndDate()).size() >= 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<YearPeriod> getActivePeriods() {
        return yearPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public YearPeriod getLatestPeriod() {
        for (YearPeriod p : getAll()) {
            return p;
        }
        return null;
    }

    @Override
    public YearPeriod getByStartAndEndDate(Date startDate, Date endDate) {
        return yearPeriodRepo.getByByYearPeriod(startDate, endDate);
    }

    @Override
    public YearPeriod getByStartDate(Date startDate) {
        return yearPeriodRepo.findByStartDate(startDate);
    }

    @Override
    public YearPeriod constructPeriod(Date startDate) {
        YearPeriod period = new YearPeriod();
        period.setStartDate(getYearPeriod(startDate, Boolean.TRUE));
        period.setEndDate(getYearPeriod(startDate, Boolean.FALSE));
        return period;
    }
}
