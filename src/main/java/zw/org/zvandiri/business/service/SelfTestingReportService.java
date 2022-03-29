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
public interface SelfTestingReportService {

    public Long countByArtInitiation(SearchDTO dto);

    public Long countByHIvSelfTesting(SearchDTO dto);

    public Long countHomeBased(SearchDTO dto);

    public Long countFacilityBased(SearchDTO dto);

    public Long countIndividualsMobilizedForTesting(SearchDTO dto);
    
    public Long countIndividualsTestingPostive(SearchDTO dto);
    
    public Long countIndividualsTestingForHIV(SearchDTO dto);
}
