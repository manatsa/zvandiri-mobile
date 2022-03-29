/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import java.util.List;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
public interface MortalityService extends GenericPatientHistoryService<Mortality>{
    
    public List<Mortality> get(SearchDTO searchDTO);
    Long count(SearchDTO searchDTO);
}
