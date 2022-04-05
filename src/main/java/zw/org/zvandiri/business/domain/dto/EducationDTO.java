package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Education;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class EducationDTO {
    private String id;
    private String name;

    public EducationDTO() {
    }

    public EducationDTO(Education education) {
        this.id = education.getId();
        this.name = education.getName();
    }

    public EducationDTO(String id) {
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
