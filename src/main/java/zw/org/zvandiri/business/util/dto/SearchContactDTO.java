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
package zw.org.zvandiri.business.util.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.ActionTaken;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.LevelOfCare;
import zw.org.zvandiri.business.domain.Location;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.SupportGroup;
import zw.org.zvandiri.business.domain.util.FollowUp;
import zw.org.zvandiri.business.domain.util.Reason;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
public class SearchContactDTO implements Serializable {
    
    private Province province;
    private District district;
    private SupportGroup supportGroup;
    private Facility primaryClinic;
    private ActionTaken actionTaken;
    private Reason reason;
    private FollowUp followUp;
    private LevelOfCare levelOfCare;
    private Location location;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    public SearchContactDTO() {
    }

    public SearchContactDTO(Province province, District district, SupportGroup supportGroup, Facility primaryClinic, ActionTaken actionTaken, Reason reason, FollowUp followUp, LevelOfCare levelOfCare, Location location, Date startDate, Date endDate) {
        this.province = province;
        this.district = district;
        this.supportGroup = supportGroup;
        this.primaryClinic = primaryClinic;
        this.actionTaken = actionTaken;
        this.reason = reason;
        this.followUp = followUp;
        this.levelOfCare = levelOfCare;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public SupportGroup getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(SupportGroup supportGroup) {
        this.supportGroup = supportGroup;
    }

    public Facility getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(Facility primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public ActionTaken getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(ActionTaken actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public FollowUp getFollowUp() {
        return followUp;
    }

    public void setFollowUp(FollowUp followUp) {
        this.followUp = followUp;
    }

    public LevelOfCare getLevelOfCare() {
        return levelOfCare;
    }

    public void setLevelOfCare(LevelOfCare levelOfCare) {
        this.levelOfCare = levelOfCare;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getQueryString(SearchContactDTO dto) {
        StringBuilder query = new StringBuilder("");
        int position = 0;
        if (dto.getProvince() != null) {
            if (position == 0) {
                query.append("?province=");
                query.append(dto.getProvince().getId());
                position++;
            } else {
                query.append("&province=");
                query.append(dto.getProvince().getId());
            }
        }
        if (dto.getPrimaryClinic() != null) {
            if (position == 0) {
                query.append("?primaryClinic=");
                query.append(dto.getPrimaryClinic().getId());
                position++;
            } else {
                query.append("&primaryClinic=");
                query.append(dto.getPrimaryClinic().getId());
            }
        }
        if (dto.getDistrict() != null) {
            if (position == 0) {
                query.append("?district=");
                query.append(dto.getDistrict().getId());
                position++;
            } else {
                query.append("&district=");
                query.append(dto.getDistrict().getId());
            }
        }
        if (dto.getSupportGroup() != null) {
            if (position == 0) {
                query.append("?supportGroup=");
                query.append(dto.getSupportGroup().getId());
                position++;
            } else {
                query.append("&supportGroup=");
                query.append(dto.getSupportGroup().getId());
            }
        }
        if (dto.getEndDate() != null && dto.getStartDate() != null) {
            if (position == 0) {
                query.append("?startDate=");
                query.append(DateUtil.getStringFromDate(dto.getStartDate()));
                query.append("&endDate=");
                query.append(DateUtil.getStringFromDate(dto.getEndDate()));
                position++;
            } else {
                query.append("&startDate=");
                query.append(DateUtil.getStringFromDate(dto.getStartDate()));
                query.append("&endDate=");
                query.append(DateUtil.getStringFromDate(dto.getEndDate()));
            }
        }
        return query.toString();
    }
    
    public SearchContactDTO getInstance(SearchContactDTO dto){
        return new SearchContactDTO(
        dto.getProvince(), dto.getDistrict(), dto.getSupportGroup(), dto.getPrimaryClinic(),
                dto.getActionTaken(), dto.getReason(), dto.getFollowUp(), dto.getLevelOfCare(),
                dto.getLocation(), dto.getStartDate(), dto.getEndDate()
        );
    }
}