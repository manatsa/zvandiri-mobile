package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.EducationLevel;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class EducationLevelDTO {
    private String id;
    private String name;

    public EducationLevelDTO() {
    }

    public EducationLevelDTO(EducationLevel educationLevel) {
        this.id = educationLevel.getId();
        this.name = educationLevel.getName();
    }

    public EducationLevelDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
