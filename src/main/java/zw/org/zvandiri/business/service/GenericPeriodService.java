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
package zw.org.zvandiri.business.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Judge Muzinda
 * @param <T>
 */
public interface GenericPeriodService<T extends Serializable> extends GenericService<T> {
 
    public List<T> getActivePeriods();
    
    public T getLatestPeriod();
    
    public T getByStartAndEndDate(Date startDate, Date endDate);
    
    public T getByStartDate(Date startDate);
    
    public T constructPeriod(Date startDate);
    
    public List<T> getLastTwelveMonths();
}