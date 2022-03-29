package zw.org.zvandiri.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.util.dto.NameIdDTO;

import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


@RestController
public class TestController {

    @RequestMapping("/test")
public NameIdDTO test(){
        NameIdDTO nameIdDTO=new NameIdDTO();
        nameIdDTO.setName("Manatsa Chinyeruse");
        nameIdDTO.setStatus(PatientChangeEvent.ACTIVE);
        nameIdDTO.setGender(Gender.MALE);
        nameIdDTO.setDateOfBirth(new Date());
        return nameIdDTO;
    }
}
