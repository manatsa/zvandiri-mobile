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
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.ArvHist;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Patient;

/**
 *
 * @author Judge Muzinda
 */
@Repository
public interface ArvHistRepo extends JpaRepository<ArvHist, String> {
 
    @Query("from ArvHist a left join fetch a.patient left join fetch a.modifiedBy left join fetch a.createdBy where a.patient=:patient order by a.dateCreated DESC")
    public List<ArvHist> findByPatient(@Param("patient") Patient patient);
    
    @Query("Select count(Distinct a.patient.id) from ArvHist a")
    @Override
    public long count();

    List<ArvHist> findByActive(@Param("active")Boolean aTrue);
}