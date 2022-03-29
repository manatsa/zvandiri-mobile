/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licensh.
 * You may obtain a copy of the License at
 *
 *      http://www.apachh.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licensh.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.HivCoInfection;

/**
 *
 * @author Judge Muzinda
 */
public interface HivCoInfectionRepo extends JpaRepository<HivCoInfection, String> {
    
    @Query("from HivCoInfection h left join fetch h.createdBy left join fetch h.modifiedBy where h.active=:active Order By h.name ASC")
    public List<HivCoInfection> getOptAll(@Param("active") Boolean active);

    HivCoInfection findByName(@Param("name") String name);
}