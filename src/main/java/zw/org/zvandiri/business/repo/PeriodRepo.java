/*
 * Copyright 2014 Edward Zengeni.
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
package zw.org.zvandiri.business.repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Period;

/**
 *
 * @author Edward Zengeni
 */
public interface PeriodRepo extends CrudRepository<Period, String> {

    @Override
    public List<Period> findAll();

    @Query("from Period r where r.active=:active order by r.startDate DESC")
    public List<Period> getOptAll(@Param("active") Boolean active);

    @Query("from Period r where r.startDate=:startDate and r.endDate=:endDate")
    public List<Period> getByStartDateAndEndDate(
            @Param("startDate") Date start, @Param("endDate") Date endDate);

    @Query("from Period r where r.startDate=:startDate")
    public Period getPeriodByStartDate(@Param("startDate") Date startDate);
    
    @Query("from Period r where r.startDate=:startDate and r.endDate=:endDate")
    public Period getByByPeriod(
            @Param("startDate") Date start, @Param("endDate") Date endDate);

}
