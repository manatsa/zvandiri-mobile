/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import zw.org.zvandiri.business.util.dto.SearchDTO;

/**
 *
 * @author tasu
 */
public interface TbScreeningReportService {
    public Long get(SearchDTO dto);
    
    public Long getIndividualsOnTbTreatment(SearchDTO dto);
}
