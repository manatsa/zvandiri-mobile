/*
 * Copyright 2017 Judge Muzinda.
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

/**
 *
 * @author Judge Muzinda
 */
public interface ContactInnerJoin {

    public final String CONTACT_INNER_JOIN = "left join fetch c.patient left join fetch c.location "
            + "left join fetch c.internalReferral left join fetch c.externalReferral "
            + "left join fetch c.clinicalAssessments left join fetch c.stables "
            + "left join fetch c.enhanceds left join fetch c.nonClinicalAssessments "
            + "left join fetch c.parent left join fetch c.referredPerson ";
}
