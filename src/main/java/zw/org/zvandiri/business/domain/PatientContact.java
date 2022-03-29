package zw.org.zvandiri.business.domain;


import lombok.ToString;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


@ToString
public class PatientContact extends BaseEntity implements Serializable {

   private Patient patient;
   private MentalHealthScreening mentalHealthScreening;
   private TbIpt tbIpt;
   private InvestigationTest investigationTest;
   private Contact contact;
   private Referral referral;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MentalHealthScreening getMentalHealthScreening() {
        return mentalHealthScreening;
    }

    public void setMentalHealthScreening(MentalHealthScreening mentalHealthScreening) {
        this.mentalHealthScreening = mentalHealthScreening;
    }

    public TbIpt getTbIpt() {
        return tbIpt;
    }

    public void setTbIpt(TbIpt tbIpt) {
        this.tbIpt = tbIpt;
    }

    public InvestigationTest getInvestigationTest() {
        return investigationTest;
    }

    public void setInvestigationTest(InvestigationTest investigationTest) {
        this.investigationTest = investigationTest;
    }

    public Referral getReferral() {
        return referral;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString(){
        Patient patient=contact.getPatient();
        return "Patient Details [\n"+
                "id:"+patient.getId()+",firstName:"+patient.getFirstName()+",lastName:"+patient.getLastName()+
                ",facility:"+patient.getPrimaryClinic().getName()+",district:"+patient.getPrimaryClinic().getDistrict().getName()+
                ",province:"+patient.getPrimaryClinic().getDistrict().getProvince().getName()+",Active:"+patient.getActive()+",status"+patient.getStatus()+"]\n\n " +
                "MH Details["+mentalHealthScreening.toString()+"]\n\n"+
                "TB Details ["+tbIpt.toString()+"]\n\n"+
                "VL Details ["+investigationTest.toString()+"]\n\n"+
                "Referral Details ["+referral.toString()+"]\n\n"+
                "Contact Details ["+contact.toString()+"]\n\n"

                ;
    }
}
