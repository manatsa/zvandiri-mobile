/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licensi.
 * You may obtain a copy of the License at
 *
 *      http://www.apachi.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licensi.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.InternalReferral;

/**
 *
 * @author Judge Muzinda
 */
public interface InternalReferralRepo extends JpaRepository<InternalReferral, String> {
    
    @Query("from InternalReferral i left join fetch i.createdBy left join fetch i.modifiedBy where i.active=:active Order By i.name ASC")
    public List<InternalReferral> getOptAll(@Param("active") Boolean active);

    InternalReferral findByName(@Param("name")String name);
}