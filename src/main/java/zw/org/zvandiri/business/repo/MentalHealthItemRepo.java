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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.MentalHealth;
import zw.org.zvandiri.business.domain.MentalHealthItem;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author Judge Muzinda
 */
public interface MentalHealthItemRepo extends JpaRepository<MentalHealthItem, String> {
    
    @Query("from MentalHealthItem m left join fetch m.patient left join fetch m.mentalHealth left join fetch m.modifiedBy left join fetch m.createdBy where m.patient=:patient order by m.mentalHealth.name ASC")
    public List<MentalHealthItem> findByPatient(@Param("patient") Patient patient);
    
    @Query("from MentalHealthItem m left join fetch m.patient left join fetch m.mentalHealth left join fetch m.modifiedBy left join fetch m.createdBy where m.patient=:patient and m.mentalHealth=:mentalHealth order by m.mentalHealth.name ASC")
    public MentalHealthItem findByPatientAndMentalHealth(@Param("patient") Patient patient,@Param("mentalHealth") MentalHealth mentalHealth);

    public List<MentalHealthItem> findByActive(@Param("active") boolean active);

}