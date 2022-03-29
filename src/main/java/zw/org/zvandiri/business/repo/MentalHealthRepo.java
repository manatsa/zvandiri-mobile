/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licensm.
 * You may obtain a copy of the License at
 *
 *      http://www.apachm.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licensm.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.MentalHealth;

/**
 *
 * @author Judge Muzinda
 */
public interface MentalHealthRepo extends JpaRepository<MentalHealth, String> {
 
    @Query("from MentalHealth m left join fetch m.createdBy left join fetch m.modifiedBy where m.active=:active Order By m.name ASC")
    public List<MentalHealth> getOptAll(@Param("active") Boolean active);

    MentalHealth findByName(@Param("name")String name);
}