/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import zw.org.zvandiri.business.domain.Person;

import java.util.List;

/**
 *
 * @author tasu
 */
public interface PersonRepo extends JpaRepository<Person, String> {
    public List<Person> findByActive(@Param("active") boolean active);
}
