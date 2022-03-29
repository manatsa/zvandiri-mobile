/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.service;

import java.util.List;
import zw.org.zvandiri.business.domain.CatActivity;

/**
 *
 * @author jmuzinda
 */
public interface CatActivityService extends GenericService<CatActivity> {
 
    public List<CatActivity> getByCat(String catId);
    
    public CatActivity getLatest(String catId);
}
