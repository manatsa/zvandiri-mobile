/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import java.util.Date;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.Gender;

/**
 *
 * @author jmuzinda
 */
public class PatientDemographicDTO implements Serializable {
    
    private Patient patient;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private Date dateOfBirth;
    private String oiNumber;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOiNumber() {
        return oiNumber;
    }

    public void setOiNumber(String oiNumber) {
        this.oiNumber = oiNumber;
    }
    
    public Patient getInstance (PatientDemographicDTO d) {
        Patient p = d.getPatient();
        p.setFirstName(d.getFirstName());
        p.setLastName(d.getLastName());
//        p.setMiddleName(d.getMiddleName());
        p.setGender(d.getGender());
        p.setoINumber(d.getOiNumber());
        p.setDateOfBirth(d.getDateOfBirth());
        return p;
    }
}
