package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class LineItemsDTO implements Serializable {
    List<PatientDTO> patients;
    List<ContactDTO> contacts;
    List<MentalHealthScreeningDTO> mentalHealthScreenings;
    List<TbTPTDTO> tbIpts;
    List<VLCD4DTO> investigationTests;
    List<ReferralDTO> referrals;

    public LineItemsDTO() {
    }

    public LineItemsDTO(List<PatientDTO> patients,List<ContactDTO> contacts, List<MentalHealthScreeningDTO> mentalHealthScreenings, List<TbTPTDTO> tbIpts, List<VLCD4DTO> investigationTests, List<ReferralDTO> referrals) {
        this.patients=patients;
        this.contacts = contacts;
        this.mentalHealthScreenings = mentalHealthScreenings;
        this.tbIpts = tbIpts;
        this.investigationTests = investigationTests;
        this.referrals = referrals;
    }

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public List<MentalHealthScreeningDTO> getMentalHealthScreenings() {
        return mentalHealthScreenings;
    }

    public void setMentalHealthScreenings(List<MentalHealthScreeningDTO> mentalHealthScreenings) {
        this.mentalHealthScreenings = mentalHealthScreenings;
    }

    public List<TbTPTDTO> getTbIpts() {
        return tbIpts;
    }

    public void setTbIpts(List<TbTPTDTO> tbIpts) {
        this.tbIpts = tbIpts;
    }

    public List<VLCD4DTO> getInvestigationTests() {
        return investigationTests;
    }

    public void setInvestigationTests(List<VLCD4DTO> investigationTests) {
        this.investigationTests = investigationTests;
    }

    public List<ReferralDTO> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<ReferralDTO> referrals) {
        this.referrals = referrals;
    }

    @Override
    public String toString() {
        return "LineItemsDTO{" +
                "patients=" + patients +
                ", contacts=" + contacts +
                ", mentalHealthScreenings=" + mentalHealthScreenings +
                ", tbIpts=" + tbIpts +
                ", investigationTests=" + investigationTests +
                ", referrals=" + referrals +
                '}';
    }
}
