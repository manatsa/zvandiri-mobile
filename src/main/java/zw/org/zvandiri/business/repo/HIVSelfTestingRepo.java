/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.HIVSelfTesting;
import zw.org.zvandiri.business.domain.Person;

/**
 *
 * @author tasu
 */
public interface HIVSelfTestingRepo extends JpaRepository<HIVSelfTesting, String> {
    
    public List<HIVSelfTesting> findByPerson(Person person);

    List<HIVSelfTesting> findByActive(@Param("active")Boolean aTrue);
}
