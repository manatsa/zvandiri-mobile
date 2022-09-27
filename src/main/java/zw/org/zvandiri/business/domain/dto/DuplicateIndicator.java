package zw.org.zvandiri.business.domain.dto;


import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class DuplicateIndicator {
    private String theDate;
    private String patient;

    public DuplicateIndicator() {
    }

    public DuplicateIndicator( String patient, String theDate) {
        this.theDate = theDate;
        this.patient = patient;
    }

    public String getTheDate() {
        return theDate;
    }

    public void setTheDate(String theDate) {
        this.theDate = theDate;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Duplicate{" +
                "theDate=" + theDate +
                ", patient='" + patient + '\'' +
                '}';
    }
}
