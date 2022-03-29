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
package zw.org.zvandiri.business.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.QuarterPeriod;

/**
 *
 * @author Judge Muzinda
 */
public interface QuarterPeriodRepo extends JpaRepository<QuarterPeriod, String> {
    
    @Query("from QuarterPeriod q where q.active=:active order by q.startDate DESC")
    public List<QuarterPeriod> getOptAll(@Param("active") Boolean active);

    @Query("from QuarterPeriod q where q.startDate=:startDate and q.endDate=:endDate")
    public List<QuarterPeriod> getByStartDateAndEndDate(
            @Param("startDate") Date start, @Param("endDate") Date endDate);

    @Query("from QuarterPeriod q where q.startDate=:startDate")
    public List<QuarterPeriod> getQuarterPeriodByStartDate(@Param("startDate") Date startDate);

    public QuarterPeriod findByStartDate(Date startDate);

    @Query("from QuarterPeriod q where q.startDate=:startDate and q.endDate=:endDate")
    public QuarterPeriod getByByQuarterPeriod(
            @Param("startDate") Date start, @Param("endDate") Date endDate);
}