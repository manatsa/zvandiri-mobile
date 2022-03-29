package zw.org.zvandiri.business.domain;


import zw.org.zvandiri.business.domain.util.VisitOutcome;

import java.io.Serializable;

/**
 * @author manatsachinyeruse@gmail.com
 */


public class ContactVisitOutcome extends BaseEntity implements Serializable {

    private VisitOutcome visitOutcome;

    public VisitOutcome getVisitOutcome() {
        return visitOutcome;
    }

    public void setVisitOutcome(VisitOutcome visitOutcome) {
        this.visitOutcome = visitOutcome;
    }
}
