package zw.org.zvandiri.business.domain.dto;


import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class RequestDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;

    String patient;

    List<String> patients;

    String facility;

    String district;

    public RequestDTO() {
    }

    public RequestDTO(Date startDate, Date endDate, String patient, List<String> patients, String facility, String district) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.patients = patients;
        this.facility = facility;
        this.district=district;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", patient='" + patient + '\'' +
                ", patients=" + patients +
                ", facility=" + facility +
                ", district=" + district +
                '}';
    }
}
