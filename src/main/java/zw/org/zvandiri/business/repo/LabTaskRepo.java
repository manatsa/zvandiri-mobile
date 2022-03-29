/*
 * Copyright 2017 User.
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
import zw.org.zvandiri.business.domain.ActionTaken;
import zw.org.zvandiri.business.domain.LabTask;

/**
 *
 * @author User
 */
public interface LabTaskRepo extends JpaRepository<LabTask, String> {
    
    @Query("from LabTask a left join fetch a.createdBy left join fetch a.modifiedBy where a.active=:active Order By a.name ASC")
    public List<LabTask> getOptAll(@Param("active") Boolean active);

    LabTask findByName(@Param("name")String name);
}
