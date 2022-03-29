/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.util.dto.demographic;

import java.io.Serializable;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.SupportGroup;

/**
 *
 * @author jmuzinda
 */
public class PatientAddressDTO implements Serializable {
    
    private Patient patient;
    private String address;
    private String address1;
    private Province province;
    private District district;
    private District supportGroupDistrict;
    private SupportGroup supportGroup;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public District getSupportGroupDistrict() {
        return supportGroupDistrict;
    }

    public void setSupportGroupDistrict(District supportGroupDistrict) {
        this.supportGroupDistrict = supportGroupDistrict;
    }

    public SupportGroup getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(SupportGroup supportGroup) {
        this.supportGroup = supportGroup;
    }
    
    public Patient getInstance (PatientAddressDTO a) {
        Patient p = a.getPatient();
        p.setAddress(a.getAddress());
        p.setAddress1(a.getAddress1());
        p.setProvince(a.getProvince());
        p.setDistrict(a.getDistrict());
        p.setSupportGroupDistrict(a.getSupportGroupDistrict());
        p.setSupportGroup(a.getSupportGroup());
        return p;
    }
    
}
