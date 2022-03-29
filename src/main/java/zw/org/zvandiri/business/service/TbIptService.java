/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import java.util.List;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.TbIpt;
import zw.org.zvandiri.business.domain.util.TbIdentificationOutcome;
import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
public interface TbIptService extends GenericService<TbIpt> {

    boolean existsOnTbTreatment(Patient patient, TbIdentificationOutcome yesNo);

    List<TbIpt> get(SearchDTO dto);

    Long count(SearchDTO dto);
    List<TbIpt> getByPatient(Patient patient);
    
    TbIpt getLatest(Patient patient);

}
