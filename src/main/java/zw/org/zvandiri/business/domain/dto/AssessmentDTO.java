package zw.org.zvandiri.business.domain.dto;


import zw.org.zvandiri.business.domain.Assessment;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class AssessmentDTO {
    private String id;
    private String contactAssessment;
    private String name;

    public AssessmentDTO() {
    }

    public AssessmentDTO(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.contactAssessment=type;
    }

    public AssessmentDTO(Assessment assessment) {
        this.id = assessment.getId();
        this.name = assessment.getName();
        this.contactAssessment=assessment.getContactAssessment().getName().trim();
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

    public String getContactAssessment() {
        return contactAssessment;
    }

    public void setContactAssessment(String contactAssessment) {
        this.contactAssessment = contactAssessment;
    }

    @Override
    public String toString() {
        return "AssessmentDTO{" +
                "id='" + id + '\'' +
                ", contactAssessment='" + contactAssessment + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
