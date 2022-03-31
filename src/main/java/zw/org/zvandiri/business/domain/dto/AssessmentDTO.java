package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Assessment;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class AssessmentDTO {
    private String id;
    private String name;

    public AssessmentDTO() {
    }

    public AssessmentDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AssessmentDTO(Assessment assessment) {
        this.id = assessment.getId();
        this.name = assessment.getName();
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
