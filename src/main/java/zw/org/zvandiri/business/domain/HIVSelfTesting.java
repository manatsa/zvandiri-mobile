/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.Result;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author tasu
 */
@Entity @JsonIgnoreProperties(ignoreUnknown = true)
/*@Table(indexes = {
		@Index(name = "hiv_self_testing_person", columnList = "person")
})*/
public class HIVSelfTesting extends BaseEntity{
    
    @Enumerated
    private Result testedAtHealthFacilityResult;
    @Enumerated
    private YesNo selfTestKitDistributed;
    @Enumerated
    private Result hisSelfTestingResult;
    @Enumerated
    private Result homeBasedTestingResult;
    @Enumerated
    private Result confirmatoryTestingResult;
    @Enumerated
    private YesNo artInitiation;
    @ManyToOne
    @JoinColumn(name="person")
    private Person person;

    public HIVSelfTesting(Person person) {
        this.person = person;
    }
    
    public HIVSelfTesting(){
        
    }

    public Result getTestedAtHealthFacilityResult() {
        return testedAtHealthFacilityResult;
    }

    public void setTestedAtHealthFacilityResult(Result testedAtHealthFacilityResult) {
        this.testedAtHealthFacilityResult = testedAtHealthFacilityResult;
    }

    public YesNo getSelfTestKitDistributed() {
        return selfTestKitDistributed;
    }

    public void setSelfTestKitDistributed(YesNo selfTestKitDistributed) {
        this.selfTestKitDistributed = selfTestKitDistributed;
    }

    public Result getHisSelfTestingResult() {
        return hisSelfTestingResult;
    }

    public void setHisSelfTestingResult(Result hisSelfTestingResult) {
        this.hisSelfTestingResult = hisSelfTestingResult;
    }

    public Result getHomeBasedTestingResult() {
        return homeBasedTestingResult;
    }

    public void setHomeBasedTestingResult(Result homeBasedTestingResult) {
        this.homeBasedTestingResult = homeBasedTestingResult;
    }

    public Result getConfirmatoryTestingResult() {
        return confirmatoryTestingResult;
    }

    public void setConfirmatoryTestingResult(Result confirmatoryTestingResult) {
        this.confirmatoryTestingResult = confirmatoryTestingResult;
    }

    public YesNo getArtInitiation() {
        return artInitiation;
    }

    public void setArtInitiation(YesNo artInitiation) {
        this.artInitiation = artInitiation;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
