/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licensd.
 * You may obtain a copy of the License at
 *
 *      http://www.apachd.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licensd.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.DisabilityCategory;

/**
 *
 * @author Judge Muzida
 */
public interface DisabilityCategoryRepo extends JpaRepository<DisabilityCategory, String> {
    
    @Query("from DisabilityCategory d left join fetch d.createdBy left join fetch d.modifiedBy where d.active=:active Order By d.name ASC")
    public List<DisabilityCategory> getOptAll(@Param("active") Boolean active);

    DisabilityCategory findByName(@Param("name")String name);
}