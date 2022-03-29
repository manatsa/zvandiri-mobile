/*
 * Copyright 2018 jmuzinda.
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
package zw.org.zvandiri.business.util;

import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author jmuzinda
 */
public interface IRepoConstant {
    
    public final String USER_CONSTANT = "left join fetch u.userRoles left join fetch u.createdBy left join fetch u.modifiedBy left join fetch u.province left join fetch u.district";
    
    public final String USER_ROLE_CONSTANT = "left join fetch p.createdBy left join fetch p.modifiedBy left join fetch p.users";
    
    public final String PATIENT_CONSTANT = "left join fetch p.disabilityCategorys left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup left join fetch p.relationship left join fetch p.mobileOwnerRelation left join fetch p.secondaryMobileownerRelation left join fetch p.motherOfHei left join fetch p.reasonForNotReachingOLevel left join fetch p.modifiedBy left join fetch p.createdBy";


}