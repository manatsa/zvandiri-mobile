/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package zw.org.zvandiri.mobile.api.resource;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import zw.org.zvandiri.business.domain.HIVSelfTesting;
import zw.org.zvandiri.business.domain.InvestigationTest;
import zw.org.zvandiri.business.domain.MentalHealthScreening;
import zw.org.zvandiri.business.domain.Mortality;
import zw.org.zvandiri.business.domain.Person;
import zw.org.zvandiri.business.domain.TbIpt;
import zw.org.zvandiri.business.service.HIVSelfTestingService;
import zw.org.zvandiri.business.service.InvestigationTestService;
import zw.org.zvandiri.business.service.MentalHealthScreeningService;
import zw.org.zvandiri.business.service.MortalityService;
import zw.org.zvandiri.business.service.PatientService;
import zw.org.zvandiri.business.service.PersonService;
import zw.org.zvandiri.business.service.TbIptService;
import zw.org.zvandiri.business.util.DateUtil;

*/
/**
 * @author tasu
 *//*

@Component
@Path("/mobile/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonProcessResource {

    @Resource
    private PatientService patientService;
    @Resource
    private HIVSelfTestingService hIVSelfTestingService;
    @Resource
    private MortalityService mortalityService;
    @Resource
    private TbIptService tbIptService;
    @Resource
    private PersonService personService;
    @Resource
    private MentalHealthScreeningService mentalHealthScreeningService;
    @Resource
    private InvestigationTestService investigationTestService;

    @POST
    @Path("/add-person")
    public ResponseEntity<Map<String, Object>> addPerson(Person person) {
        Map<String, Object> map = new HashMap<>();
        String id = "";
        try {
            person.setDateOfBirth(DateUtil.getDateFromStringRest(person.getDob()));
            id = personService.save(person).getId();
        } catch (Exception e) {
            map.put("message", "System error occurred saving person");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @POST
    @Path("/add-self-testing")
    public ResponseEntity<Map<String, Object>> addHivSelfTesting(HIVSelfTesting item) {
        Map<String, Object> map = new HashMap<>();
        try {
            item.setPerson(personService.get(item.getPerson().getId()));
            hIVSelfTestingService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "System error occurred saving hiv self testing");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", "Item saved");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @POST
    @Path("/add-mortality")
    public ResponseEntity<Map<String, Object>> addMortality(Mortality item) {
        Map<String, Object> map = new HashMap<>();
        try {
            item.setPatient(patientService.get(item.getPatient().getId()));
            mortalityService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "System error occurred saving mortality");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", "Item saved");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @POST
    @Path("/add-tb-ipt")
    public ResponseEntity<Map<String, Object>> addTbIpt(TbIpt item) {
        Map<String, Object> map = new HashMap<>();
        try {
            item.setPatient(patientService.get(item.getPatient().getId()));
            tbIptService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "System error occurred saving TbIpt");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", "Item saved");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @POST
    @Path("/add-mental-health-screening")
    public ResponseEntity<Map<String, Object>> addMentalHealthScreening(MentalHealthScreening item) {
        Map<String, Object> map = new HashMap<>();
        try {
            item.setPatient(patientService.get(item.getPatientId()));
            mentalHealthScreeningService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "System error occurred saving mental health screening");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", "Item saved");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @POST
    @Path("/add-investigation-test")
    public ResponseEntity<Map<String, Object>> addInvestigationTest(InvestigationTest item) {
        Map<String, Object> map = new HashMap<>();
        try {
            item.setPatient(patientService.get(item.getPatient().getId()));
            investigationTestService.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "System error occurred saving Dsd");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("message", "Item saved");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
*/
