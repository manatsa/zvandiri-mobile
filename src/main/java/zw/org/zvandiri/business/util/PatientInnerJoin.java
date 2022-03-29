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
public interface PatientInnerJoin {
 
    public final String PATIENT_INNER_JOIN = "left join fetch p.disabilityCategorys left join fetch p.education left join fetch p.educationLevel left join fetch p.referer left join fetch p.primaryClinic left join fetch p.supportGroup left join fetch p.relationship left join fetch p.mobileOwnerRelation left join fetch p.secondaryMobileownerRelation left join fetch p.createdBy left join fetch p.modifiedBy";

    public final String SIMMPLE_PATIENT_INNER_JOIN = " left join fetch p.primaryClinic left join fetch p.supportGroup  left join fetch p.createdBy left join fetch p.modifiedBy";

    public final String PATIENT_CHILD_INNER_JOIN = "left join fetch c.patient.disabilityCategorys left join fetch c.patient.education left join fetch c.patient.educationLevel left join fetch c.patient.referer left join fetch c.patient.primaryClinic left join fetch c.patient.supportGroup left join fetch c.patient.relationship left join fetch c.patient.mobileOwnerRelation left join fetch c.patient.secondaryMobileownerRelation left join fetch c.patient.createdBy left join fetch c.patient.modifiedBy";
    
    public final String PATIENT_FULL_ASSOC_FETCH = "left join fetch p.dependents left join fetch p.medicalHists left join fetch p.chronicInfectionItems left join fetch p.hivConInfectionItems left join fetch p.arvHists left join fetch p.mentalHealthItems left join fetch p.obstercHists left join fetch p.socialHists left join fetch p.substanceItems left join fetch p.investigationTests left join fetch p.tbIpts left join fetch p.mortalitys left join fetch p.mentalHealthScreenings";
    //public final String PATIENT_FULL_ASSOC_FETCH = "join fetch p.disabilityCategorys join fetch p.dependents join fetch p.medicalHists join fetch p.chronicInfectionItems join fetch p.hivConInfectionItems join fetch p.arvHists join fetch p.mentalHealthItems join fetch p.srhHists join fetch p.obstercHists join fetch p.socialHists join fetch p.substanceItems join fetch p.familys join fetch p.investigationTests join fetch p.contacts join fetch p.eidTests join fetch p.referrals join fetch p.tbIpts join fetch p.mortalitys join fetch p.mentalHealthScreenings";
}