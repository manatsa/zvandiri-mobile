package zw.org.zvandiri.business.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.User;

import java.util.Date;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Repository
public interface MessageRepo extends JpaRepository<Message, String> {
    public List<Message> getAllByCreatedBy(@Param("createdBy")User createdBy);
    @Query("from Message where createdBy=:createdBy and dateCreated between :start and :end")
    public List<Message> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end);
}
