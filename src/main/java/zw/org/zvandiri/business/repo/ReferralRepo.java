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

import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Referral;

/**
 *
 * @author Judge Muzinda
 */
public interface ReferralRepo extends JpaRepository<Referral, String> {
    
    public List<Referral> findByPatient(@Param("patient") Patient patient);
    
    @Query("Select Distinct(r) from Referral r left join fetch r.patient where r.patient=:patient and (r.referralDate) between :start and :end")
    public List<Referral> findByPatientAndContactDate(
            @Param("patient") Patient patient,
            @Param("start") Date start, @Param("end") Date end);

    List<Referral> findByActive(@Param("active")Boolean aTrue);
}