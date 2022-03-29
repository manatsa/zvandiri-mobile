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
import zw.org.zvandiri.business.domain.Assessment;
import zw.org.zvandiri.business.domain.ExternalReferral;

/**
 *
 * @author Judge Muzinda
 */
public interface ExternalReferralRepo extends JpaRepository<ExternalReferral, String> {
 
    @Query("from ExternalReferral e left join fetch e.createdBy left join fetch e.modifiedBy where e.active=:active Order By e.name ASC")
    public List<ExternalReferral> getOptAll(@Param("active") Boolean active);

    ExternalReferral findByName(@Param("name")String name);
}