package zw.org.zvandiri.controllers;


import zw.org.zvandiri.business.domain.BaseEntity;
import zw.org.zvandiri.business.domain.BaseName;

import java.io.Serializable;
import java.util.List;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class Response implements Serializable {
    private int code;
    private BaseEntity baseEntity;
    private List<BaseEntity> baseEntities;
    private BaseName baseName;
    private String message;
    private String description;

    public Response() {
    }

    public Response(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public List<BaseEntity> getBaseEntities() {
        return baseEntities;
    }

    public void setBaseEntities(List<BaseEntity> baseEntities) {
        this.baseEntities = baseEntities;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }

    public BaseName getBaseName() {
        return baseName;
    }

    public void setBaseName(BaseName baseName) {
        this.baseName = baseName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
