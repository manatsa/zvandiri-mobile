package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */

public class MessageDTO implements Serializable {
    private String id;
    private String message;
    private String details;
    private Date dateCreated;

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        this.id=message.getId();
        this.message=message.getMessage();
        this.details=message.getDetails();
        this.dateCreated=message.getDateCreated();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
