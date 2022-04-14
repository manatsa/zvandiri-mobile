package zw.org.zvandiri.business.domain;


import zw.org.zvandiri.business.domain.dto.MessageDTO;

import javax.persistence.Entity;

/**
 * @author manatsachinyeruse@gmail.com
 */

@Entity
public class Message extends BaseEntity{
    private String message;
    private String details;

    public Message() {
    }

    public Message(String id) {
        super(id);
    }

    public Message(MessageDTO messageDTO) {
        this.message = messageDTO.getMessage();
        this.details = messageDTO.getDetails();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
