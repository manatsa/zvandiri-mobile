package zw.org.zvandiri.business.domain.dto;


import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class FacilityRecord implements Serializable {
    private String facility;
    private Integer patients;
    private Integer contacts;
    private Integer mhScreenings;
    private Integer tbScreenings;
    private Integer referrals;
    private Integer vls;

    public FacilityRecord() {
    }

    public FacilityRecord(String facility, Integer patients, Integer contacts, Integer mhScreenings, Integer tbScreenings, Integer referrals, Integer vls) {
        this.facility = facility;
        this.patients = patients;
        this.contacts = contacts;
        this.mhScreenings = mhScreenings;
        this.tbScreenings = tbScreenings;
        this.referrals = referrals;
        this.vls=vls;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Integer getPatients() {
        return patients;
    }

    public void setPatients(Integer patients) {
        this.patients = patients;
    }

    public Integer getContacts() {
        return contacts;
    }

    public void setContacts(Integer contacts) {
        this.contacts = contacts;
    }

    public Integer getMhScreenings() {
        return mhScreenings;
    }

    public void setMhScreenings(Integer mhScreenings) {
        this.mhScreenings = mhScreenings;
    }

    public Integer getTbScreenings() {
        return tbScreenings;
    }

    public void setTbScreenings(Integer tbScreenings) {
        this.tbScreenings = tbScreenings;
    }

    public Integer getReferrals() {
        return referrals;
    }

    public void setReferrals(Integer referrals) {
        this.referrals = referrals;
    }

    public Integer getVls() {
        return vls;
    }

    public void setVls(Integer vls) {
        this.vls = vls;
    }

    @Override
    public String toString() {
        return "FacilityRecord{" +
                "facility='" + facility + '\'' +
                ", patients=" + patients +
                ", contacts=" + contacts +
                ", mhScreenings=" + mhScreenings +
                ", tbScreenings=" + tbScreenings +
                ", referrals=" + referrals +
                ", vls=" + vls +
                '}';
    }
}