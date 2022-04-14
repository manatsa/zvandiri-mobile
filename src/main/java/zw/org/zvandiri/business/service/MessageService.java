package zw.org.zvandiri.business.service;




import java.util.Date;
import java.util.List;
import zw.org.zvandiri.business.domain.CatDetail;
import zw.org.zvandiri.business.domain.Message;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.util.dto.NameIdDTO;

/**
 * @author manatsachinyeruse@gmail.com
 */

public interface MessageService extends GenericService<Message> {

    public List<Message> getAllByCreatedBy(User createdBy);
    public List<Message> getAllByCreatedByAndDateCreatedBetween(User createdBy, Date start, Date end);
}