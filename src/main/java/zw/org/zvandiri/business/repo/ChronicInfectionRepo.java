/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licensc.
 * You may obtain a copy of the License at
 *
 *      http://www.apachc.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licensc.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.ChronicInfection;

/**
 *
 * @author Judge Muzinda
 */
public interface ChronicInfectionRepo extends JpaRepository<ChronicInfection, String> {
    
    @Query("from ChronicInfection c left join fetch c.createdBy left join fetch c.modifiedBy where c.active=:active Order By c.name ASC")
    public List<ChronicInfection> getOptAll(@Param("active") Boolean active);

    ChronicInfection findByName(@Param("name")String name);
}