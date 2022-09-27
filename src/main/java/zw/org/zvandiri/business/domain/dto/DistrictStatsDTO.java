package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class DistrictStatsDTO implements Serializable {
    private List<FacilityRecord> facilityRecord;

    public DistrictStatsDTO() {
    }

    public DistrictStatsDTO(List<FacilityRecord> facilityRecord) {
        this.facilityRecord = facilityRecord;
    }

    public List<FacilityRecord> getFacilityRecord() {
        return facilityRecord;
    }

    public void setFacilityRecord(List<FacilityRecord> facilityRecord) {
        this.facilityRecord = facilityRecord;
    }

    public static List<FacilityRecord> createFacilityRecordsList(List<Facility> facilities,List<Patient> patients, List<Contact> contacts, List<MentalHealthScreening> mentalHealthScreenings,
                                                                 List<TbIpt> tbIpts, List<Referral> referrals, List<InvestigationTest> vls){
        List<FacilityRecord> facilityRecords=new ArrayList<>();
        for (Facility facility: facilities
             ) {
            facilityRecords.add(createFacilityRecordInstance(facility, patients, contacts, mentalHealthScreenings,tbIpts,referrals,vls));
        }
        return facilityRecords;
    }

    public static  FacilityRecord createFacilityRecordInstance(Facility facility, List<Patient> patients, List<Contact> contacts, List<MentalHealthScreening> mentalHealthScreenings,
                                                        List<TbIpt> tbIpts, List<Referral> referrals, List<InvestigationTest> vls){

        List<Patient> facilityPatients=patients.stream().filter(patient -> patient.getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());
        List<Contact> facilityContacts =contacts.stream().filter(contact -> contact.getPatient().getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());
        List<MentalHealthScreening> facilityMHs =mentalHealthScreenings.stream().filter(mh -> mh.getPatient().getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());
        List<TbIpt> facilityTBs =tbIpts.stream().filter(tb -> tb.getPatient().getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());
        List<Referral> facilityreferrals =referrals.stream().filter(refs -> refs.getPatient().getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());
        List<InvestigationTest> facilityvls =vls.stream().filter(vl -> vl.getPatient().getPrimaryClinic().getId().equals(facility.getId())).collect(Collectors.toList());

        FacilityRecord facilityRecord=new FacilityRecord(facility.getName(), facilityPatients.size(), facilityContacts.size(), facilityMHs.size(), facilityTBs.size(), facilityreferrals.size(), facilityvls.size() );

        return facilityRecord;
    }
}


