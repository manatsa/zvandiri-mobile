/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licenss.
 * You may obtain a copy of the License at
 *
 *      http://www.apachs.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licenss.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Substance;

/**
 *
 * @author Judge Muzinda
 */
public interface SubstanceRepo extends JpaRepository<Substance, String> {
    
    @Query("from Substance s left join fetch s.createdBy left join fetch s.modifiedBy where s.active=:active Order By s.name ASC")
    public List<Substance> getOptAll(@Param("active") Boolean active);

    Substance findByName(@Param("name")String name);
}