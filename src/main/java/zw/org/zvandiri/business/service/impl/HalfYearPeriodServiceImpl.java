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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.org.zvandiri.business.domain.HalfYearPeriod;
import zw.org.zvandiri.business.repo.HalfYearPeriodRepo;
import zw.org.zvandiri.business.service.HalfYearPeriodService;
import static zw.org.zvandiri.business.util.DateUtil.*;

/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class HalfYearPeriodServiceImpl implements HalfYearPeriodService {

    @Resource
    private HalfYearPeriodRepo halfYearPeriodRepo;

    @Transactional
    @Override
    public HalfYearPeriod save(HalfYearPeriod t) {
        if (t.getId() == null) {
            t.setDateCreated(new Date());
            return halfYearPeriodRepo.save(t);
        }
        t.setDateModified(new Date());
        return halfYearPeriodRepo.save(t);
    }

    @Override
    public HalfYearPeriod get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return halfYearPeriodRepo.findById(id).get();
    }

    @Override
    public void delete(HalfYearPeriod t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        halfYearPeriodRepo.save(t);
    }

    @Override
    public List<HalfYearPeriod> getPageable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HalfYearPeriod> getAll() {
        return halfYearPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public Boolean checkDuplicate(HalfYearPeriod current, HalfYearPeriod old) {
        if (halfYearPeriodRepo.getByStartDateAndEndDate(current.getStartDate(), current.getEndDate()).size() >= 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<HalfYearPeriod> getActivePeriods() {
        return halfYearPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public HalfYearPeriod getLatestPeriod() {
        for (HalfYearPeriod p : getAll()) {
            return p;
        }
        return null;
    }

    @Override
    public HalfYearPeriod getByStartAndEndDate(Date startDate, Date endDate) {
        return halfYearPeriodRepo.getByByHalfYearPeriod(startDate, endDate);
    }

    @Override
    public HalfYearPeriod getByStartDate(Date startDate) {
        return halfYearPeriodRepo.findByStartDate(startDate);
    }

    @Override
    public HalfYearPeriod constructPeriod(Date date) {
        HalfYearPeriod period = new HalfYearPeriod();
        period.setStartDate(getHalfYear(date, getHalfYearFactor(date), Boolean.TRUE));
        period.setEndDate(getHalfYear(date, getHalfYearFactor(date), Boolean.FALSE));
        return period;
    }
    
    @Override
    @Deprecated
    public List<HalfYearPeriod> getLastTwelveMonths() {
        /**
         * inefficient method efficient method will be used in next version
         * method will be eliminated in next version
         */
        List<HalfYearPeriod> items = new ArrayList<>();
        int count = 0;
        for(HalfYearPeriod p : getAll()){
            if(count < 12) items.add(p);
            count++;
        }
        return items;
    }

    private int getHalfYearFactor(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(date.equals(getFirstStartDate(date)) || date.equals(getFirstEndDate(date)) || (date.after(getFirstStartDate(date)) && date.before(getFirstEndDate(date)))){
            return 1;
        }else if(date.equals(getLastStartDate(date)) || date.equals(getLastEndDate(date)) || (date.after(getLastStartDate(date)) && date.before(getLastEndDate(date)))){
            return 2;
        }
        throw new IllegalArgumentException("Date not fitting into any range");
    }

    private Date getFirstStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.JANUARY, 01);
        return cal.getTime();
    }

    private Date getFirstEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.JUNE, 30);
        return cal.getTime();
    }

    private Date getLastStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.JULY, 01);
        return cal.getTime();
    }

    private Date getLastEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        return cal.getTime();
    }
}
