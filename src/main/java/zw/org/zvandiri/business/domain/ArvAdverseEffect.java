/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.Source;
import zw.org.zvandiri.business.domain.util.Status;

/**
 *
 * @author Judge Muzinda
 */
@Entity
/*@Table(indexes = {
		@Index(name = "arv_adverse_effect_arv_hist", columnList = "arv_hist"),
		@Index(name = "arv_adverse_effect_date_commenced", columnList = "dateCommenced")
})*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArvAdverseEffect extends BaseEntity {
 
    @ManyToOne
    @JoinColumn(name="arv_hist", updatable = false, insertable = false)
    private ArvHist arvHist;
    private String event;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateCommenced;
    @Enumerated
    private Status status;
    @Enumerated
    private Source source;

    public ArvAdverseEffect() {
    }

    public ArvAdverseEffect(ArvHist arvHist) {
        this.arvHist = arvHist;
    }

    public ArvHist getArvHist() {
        return arvHist;
    }

    public void setArvHist(ArvHist arvHist) {
        this.arvHist = arvHist;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDateCommenced() {
        return dateCommenced;
    }

    public void setDateCommenced(Date dateCommenced) {
        this.dateCommenced = dateCommenced;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
    
}
