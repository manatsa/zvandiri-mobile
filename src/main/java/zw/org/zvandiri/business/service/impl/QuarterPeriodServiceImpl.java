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
import zw.org.zvandiri.business.domain.QuarterPeriod;
import zw.org.zvandiri.business.repo.QuarterPeriodRepo;
import zw.org.zvandiri.business.service.QuarterPeriodService;
import static zw.org.zvandiri.business.util.DateUtil.*;
/**
 *
 * @author Judge Muzinda
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class QuarterPeriodServiceImpl implements QuarterPeriodService {
    
    @Resource
    private QuarterPeriodRepo quarterPeriodRepo;
    
    @Transactional
    @Override
    public QuarterPeriod save(QuarterPeriod t) {
        if (t.getId() == null) {
            t.setDateCreated(new Date());
            return quarterPeriodRepo.save(t);
        }
        t.setDateModified(new Date());
        return quarterPeriodRepo.save(t);
    }

    @Override
    public QuarterPeriod get(String id) {
        if (id == null) {
            throw new IllegalStateException("Item to be does not exist");
        }
        return quarterPeriodRepo.findById(id).get();
    }

    @Override
    public void delete(QuarterPeriod t) {
        if (t.getId() == null) {
            throw new IllegalStateException("Item to be deleted is in an inconsistent state");
        }
        t.setActive(Boolean.FALSE);
        quarterPeriodRepo.save(t);
    }

    @Override
    public List<QuarterPeriod> getPageable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<QuarterPeriod> getAll() {
        return quarterPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public Boolean checkDuplicate(QuarterPeriod current, QuarterPeriod old) {
        if (quarterPeriodRepo.getByStartDateAndEndDate(current.getStartDate(), current.getEndDate()).size() >= 1) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<QuarterPeriod> getActivePeriods() {
        return quarterPeriodRepo.getOptAll(Boolean.TRUE);
    }

    @Override
    public QuarterPeriod getLatestPeriod() {
        for (QuarterPeriod p : getAll()) {
            return p;
        }
        return null;
    }

    @Override
    public QuarterPeriod getByStartAndEndDate(Date startDate, Date endDate) {
        return quarterPeriodRepo.getByByQuarterPeriod(startDate, endDate);
    }

    @Override
    public QuarterPeriod getByStartDate(Date startDate) {
        return quarterPeriodRepo.findByStartDate(startDate);
    }
    
    @Override
    @Deprecated
    public List<QuarterPeriod> getLastTwelveMonths() {
        /**
         * inefficient method efficient method will be used in next version
         * method will be eliminated in next version
         */
        List<QuarterPeriod> items = new ArrayList<>();
        int count = 0;
        for(QuarterPeriod p : getAll()){
            if(count < 12) items.add(p);
            count++;
        }
        return items;
    }

    @Override
    public QuarterPeriod constructPeriod(Date date) {
        QuarterPeriod period = new QuarterPeriod();
        period.setStartDate(getQuarter(date, getQuarterFactor(date), Boolean.TRUE));
        period.setEndDate(getQuarter(date, getQuarterFactor(date), Boolean.FALSE));
        return period;
    }
    
    private int getQuarterFactor(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(date.equals(getFirstStartDate(date)) || date.equals(getFirstEndDate(date)) || (date.after(getFirstStartDate(date)) && date.before(getFirstEndDate(date)))){
            return 1;
        }else if(date.equals(getSecondStartDate(date)) || date.equals(getSecondEndDate(date)) || (date.after(getSecondStartDate(date)) && date.before(getSecondEndDate(date)))){
            return 2;
        }else if(date.equals(getThirdStartDate(date)) || date.equals(getThirdEndDate(date)) ||(date.after(getThirdStartDate(date)) && date.before(getThirdEndDate(date)))){
            return 3;
        }else if(date.equals(getFourthStartDate(date)) || date.equals(getFourthEndDate(date)) || (date.after(getFourthStartDate(date)) && date.before(getFourthEndDate(date)))){
            return 4;
        }
        throw new IllegalArgumentException("Date not fitting into any given range");
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
        cal.set(cal.get(Calendar.YEAR), Calendar.MARCH, 31);
        return cal.getTime();
    }
    
    private Date getSecondStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.APRIL, 01);
        return cal.getTime();
    }

    private Date getSecondEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.JUNE, 30);
        return cal.getTime();
    }
    
    private Date getThirdStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.JULY, 01);
        return cal.getTime();
    }

    private Date getThirdEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.SEPTEMBER, 30);
        return cal.getTime();
    }
    
    private Date getFourthStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.OCTOBER, 01);
        return cal.getTime();
    }

    private Date getFourthEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        return cal.getTime();
    }

    
}