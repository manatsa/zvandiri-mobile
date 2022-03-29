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
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.CatDetail;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.util.dto.NameIdDTO;

/**
 *
 * @author Judge Muzinda
 */
@Repository
public interface CatDetailRepo extends JpaRepository<CatDetail, String> {
    
    @Query("from CatDetail c left join fetch c.patient left join fetch c.primaryClinic where c.patient=:patient")
    public CatDetail findByPatient(@Param("patient") Patient patient);
   
    @Query("from CatDetail c left join fetch c.patient left join fetch c.primaryClinic left join fetch c.createdBy left join c.modifiedBy where c.patient.deleted=false and c.patient.active=true and c.email=:email")
    public CatDetail findByEmails(@Param("email") String email);
    
    @Query("from CatDetail c left join fetch c.patient left join fetch c.primaryClinic where c.id=:id")
    public CatDetail findByIds(@Param("id") String id);

    @Query("Select new zw.org.zvandiri.business.util.dto.NameIdDTO(CONCAT(p.lastName,' ', p.firstName), p.id, p.dateOfBirth, p.gender, p.status, p.active, p.primaryClinic.id) from Patient p where p.primaryClinic=:facility and p.deleted=:deleted and p.active=:active")
    public List<NameIdDTO> getFacilityPatients(@Param("facility") Facility primaryClinic, @Param("active") boolean active, @Param("deleted") boolean deleted);

    List<CatDetail> findByActive(@Param("active")Boolean aTrue);

    CatDetail findByEmail(@Param("Email") String email);
}