package zw.org.zvandiri.business.domain.dto;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.MentalHealthScreening;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.IdentifiedRisk;
import zw.org.zvandiri.business.domain.util.Support;
import zw.org.zvandiri.business.domain.util.YesNo;

import javax.persistence.ElementCollection;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

/**
 * @author manatsachinyeruse@gmail.com
 */


@ToString
public class MentalHealthScreeningDTO {
    private String patient;
    @Enumerated
    private YesNo screenedForMentalHealth;
    @Enumerated
    private YesNo risk;
    @ElementCollection
    private Set<IdentifiedRisk> identifiedRisks;
    @Enumerated
    private YesNo support;
    @ElementCollection
    private Set<Support> supports;
    @Enumerated
    private YesNo referral;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateScreened;

    public MentalHealthScreeningDTO() {
    }

    public MentalHealthScreeningDTO(MentalHealthScreening mentalHealthScreening) {
        this.screenedForMentalHealth = mentalHealthScreening.getScreenedForMentalHealth();
        this.risk = mentalHealthScreening.getRisk();
        this.identifiedRisks = mentalHealthScreening.getIdentifiedRisks();
        this.support = mentalHealthScreening.getSupport();
        this.supports = mentalHealthScreening.getSupports();
        this.referral = mentalHealthScreening.getReferral();
        this.dateScreened = mentalHealthScreening.getDateScreened();
        this.patient=mentalHealthScreening.getPatient().getId();
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public YesNo getScreenedForMentalHealth() {
        return screenedForMentalHealth;
    }

    public void setScreenedForMentalHealth(YesNo screenedForMentalHealth) {
        this.screenedForMentalHealth = screenedForMentalHealth;
    }

    public YesNo getRisk() {
        return risk;
    }

    public void setRisk(YesNo risk) {
        this.risk = risk;
    }

    public Set<IdentifiedRisk> getIdentifiedRisks() {
        return identifiedRisks;
    }

    public void setIdentifiedRisks(Set<IdentifiedRisk> identifiedRisks) {
        this.identifiedRisks = identifiedRisks;
    }

    public YesNo getSupport() {
        return support;
    }

    public void setSupport(YesNo support) {
        this.support = support;
    }

    public Set<Support> getSupports() {
        return supports;
    }

    public void setSupports(Set<Support> supports) {
        this.supports = supports;
    }

    public YesNo getReferral() {
        return referral;
    }

    public void setReferral(YesNo referral) {
        this.referral = referral;
    }

    public Date getDateScreened() {
        return dateScreened;
    }

    public void setDateScreened(Date dateScreened) {
        this.dateScreened = dateScreened;
    }
}
