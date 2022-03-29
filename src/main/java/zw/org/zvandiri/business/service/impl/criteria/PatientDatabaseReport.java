/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service.impl.criteria;

/**
 *
 * @author tasu
 */
public class PatientDatabaseReport {
    private final String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public PatientDatabaseReport(String id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
}
