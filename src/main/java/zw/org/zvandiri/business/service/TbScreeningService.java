/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import java.util.List;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.TbScreening;

/**
 *
 * @author tasu
 */
public interface TbScreeningService extends GenericService<TbScreening>{
    
    public List<TbScreening> getByPatient(Patient patient);
    
    public TbScreening getLatest(Patient patient);
}
