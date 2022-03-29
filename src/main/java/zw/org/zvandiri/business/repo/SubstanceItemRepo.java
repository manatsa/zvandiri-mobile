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
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Substance;
import zw.org.zvandiri.business.domain.SubstanceItem;

/**
 *
 * @author Judge Muzinda
 */
public interface SubstanceItemRepo extends JpaRepository<SubstanceItem, String> {
    
    @Query("from SubstanceItem s left join fetch s.patient left join fetch s.substance left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient order by s.substance.name ASC")
    public List<SubstanceItem> findByPatient(@Param("patient") Patient patient);
    
    @Query("from SubstanceItem s left join fetch s.patient left join fetch s.substance left join fetch s.modifiedBy left join fetch s.createdBy where s.patient=:patient and s.substance=:substance order by s.substance.name ASC")
    public SubstanceItem findByPatientAndSubstance(@Param("patient") Patient patient, @Param("substance") Substance substance);

    List<SubstanceItem> findByActive(@Param("active") Boolean aTrue);
}