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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.District;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.HalfYearPeriod;
import zw.org.zvandiri.business.domain.LevelOfCare;
import zw.org.zvandiri.business.domain.Period;
import zw.org.zvandiri.business.domain.Province;
import zw.org.zvandiri.business.domain.QuarterPeriod;
import zw.org.zvandiri.business.domain.SupportGroup;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.UserRole;
import zw.org.zvandiri.business.domain.YearPeriod;
import zw.org.zvandiri.business.domain.util.AgeGroup;
import zw.org.zvandiri.business.domain.util.CareLevel;
import zw.org.zvandiri.business.domain.util.CrossTabOption;
import zw.org.zvandiri.business.domain.util.FollowUp;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.Indicator;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.domain.util.PeriodType;
import zw.org.zvandiri.business.domain.util.Reason;
import zw.org.zvandiri.business.domain.util.ReferralStatus;
import zw.org.zvandiri.business.domain.util.Result;
import zw.org.zvandiri.business.domain.util.TbTreatmentOutcome;
import zw.org.zvandiri.business.domain.util.TbTreatmentStatus;
import zw.org.zvandiri.business.domain.util.TestType;
import zw.org.zvandiri.business.domain.util.UserLevel;
import zw.org.zvandiri.business.domain.util.UserType;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@ToString
public class SearchDTO implements Serializable {

    private Period period;
    private Province province;
    private District district, supportGroupDistrict;
    private Facility primaryClinic;
    private SupportGroup supportGroup;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    private Gender gender;
    private AgeGroup ageGroup;
    private LevelOfCare levelOfCare;
    private CareLevel careLevel;
    private PatientChangeEvent status = PatientChangeEvent.ACTIVE;
    private PeriodType periodType;
    private QuarterPeriod quarterPeriod;
    private HalfYearPeriod halfYearPeriod;
    private YearPeriod yearPeriod;
    private Set<CrossTabOption> crossTabOptions = new HashSet<>();
    private Indicator indicator;
    private ReferralStatus referralStatus;
    private YesNo yesNo;
    private FollowUp followUp;
    private User createdBy;
    private Integer start;
    private Integer max;
    private Integer maxViralLoad;
    private Integer minViralLoad;
    private Boolean isDueForVL;
    private Integer minCd4Count;
    private Set<PatientChangeEvent> statuses = new HashSet<>();
    private UserType userType;
    private TestType testType;
    private Reason reason;
    private YesNo hei = YesNo.NO;
    private Result result;
    private TbTreatmentStatus tbTreatmentStatus;
    private TbTreatmentOutcome tbTreatmentOutcome;
    private UserLevel userLevel;
    private Set<UserRole> userRoles = new HashSet<>();
    private Integer firstResult;
    private Integer pageSize;
    private List<District> districts;
    private List<Province> provinces;
    private List<Facility> facilities;

    public SearchDTO() {
    }

    public SearchDTO(Period period, Province province, District district, Facility primaryClinic,
            SupportGroup supportGroup, Date startDate, Date endDate, Gender gender, AgeGroup ageGroup,
            PeriodType periodType, YearPeriod yearPeriod, HalfYearPeriod halfYearPeriod, QuarterPeriod quarterPeriod,
            LevelOfCare levelOfCare, PatientChangeEvent status, District supportGroupDistrict, CareLevel careLevel,
            Set<CrossTabOption> crossTabOptions, ReferralStatus referralStatus, YesNo yesNo, FollowUp followUp,
            User createdBy, Indicator indicator, Integer start, Integer max, Set<PatientChangeEvent> statuses,
            Integer maxViralLoad, Integer minViralLoad, Boolean isDueForVL, Integer minCd4Count, UserType userType, TestType testType, Reason reason, YesNo hei,
            Result result, TbTreatmentStatus tbTreatmentStatus, TbTreatmentOutcome tbTreatmentOutcome,
            UserLevel userLevel, Set<UserRole> userRoles, Integer firstResult, Integer pageSize, List<Province> provinces, List<District> districts, List<Facility> facilities) {
        this.period = period;
        this.province = province;
        this.district = district;
        this.primaryClinic = primaryClinic;
        this.supportGroup = supportGroup;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.periodType = periodType;
        this.yearPeriod = yearPeriod;
        this.halfYearPeriod = halfYearPeriod;
        this.quarterPeriod = quarterPeriod;
        this.levelOfCare = levelOfCare;
        this.status = status;
        this.supportGroupDistrict = supportGroupDistrict;
        this.careLevel = careLevel;
        this.crossTabOptions = crossTabOptions;
        this.referralStatus = referralStatus;
        this.yesNo = yesNo;
        this.followUp = followUp;
        this.createdBy = createdBy;
        this.indicator = indicator;
        this.start = start;
        this.max = max;
        this.statuses = statuses;
        this.maxViralLoad = maxViralLoad;
        this.minViralLoad = minViralLoad;
        this.isDueForVL = isDueForVL;
        this.minCd4Count = minCd4Count;
        this.userType = userType;
        this.testType = testType;
        this.reason = reason;
        this.hei = hei;
        this.result = result;
        this.tbTreatmentStatus = tbTreatmentStatus;
        this.tbTreatmentOutcome = tbTreatmentOutcome;
        this.userLevel = userLevel;
        this.userRoles = userRoles;
        this.firstResult = firstResult;
        this.pageSize = pageSize;
        this.provinces=provinces;
        this.districts=districts;
        this.facilities=facilities;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
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

    public Facility getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(Facility primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public SupportGroup getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(SupportGroup supportGroup) {
        this.supportGroup = supportGroup;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public QuarterPeriod getQuarterPeriod() {
        return quarterPeriod;
    }

    public void setQuarterPeriod(QuarterPeriod quarterPeriod) {
        this.quarterPeriod = quarterPeriod;
    }

    public HalfYearPeriod getHalfYearPeriod() {
        return halfYearPeriod;
    }

    public void setHalfYearPeriod(HalfYearPeriod halfYearPeriod) {
        this.halfYearPeriod = halfYearPeriod;
    }

    public YearPeriod getYearPeriod() {
        return yearPeriod;
    }

    public void setYearPeriod(YearPeriod yearPeriod) {
        this.yearPeriod = yearPeriod;
    }

    public LevelOfCare getLevelOfCare() {
        return levelOfCare;
    }

    public void setLevelOfCare(LevelOfCare levelOfCare) {
        this.levelOfCare = levelOfCare;
    }

    public PatientChangeEvent getStatus() {
        return status;
    }

    public void setStatus(PatientChangeEvent status) {
        this.status = status;
    }

    public District getSupportGroupDistrict() {
        return supportGroupDistrict;
    }

    public void setSupportGroupDistrict(District supportGroupDistrict) {
        this.supportGroupDistrict = supportGroupDistrict;
    }

    public CareLevel getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(CareLevel careLevel) {
        this.careLevel = careLevel;
    }

    public Set<CrossTabOption> getCrossTabOptions() {
        return crossTabOptions;
    }

    public void setCrossTabOptions(Set<CrossTabOption> crossTabOptions) {
        this.crossTabOptions = crossTabOptions;
    }

    public ReferralStatus getReferralStatus() {
        return referralStatus;
    }

    public void setReferralStatus(ReferralStatus referralStatus) {
        this.referralStatus = referralStatus;
    }

    public YesNo getYesNo() {
        return yesNo;
    }

    public void setYesNo(YesNo yesNo) {
        this.yesNo = yesNo;
    }

    public FollowUp getFollowUp() {
        return followUp;
    }

    public void setFollowUp(FollowUp followUp) {
        this.followUp = followUp;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Set<PatientChangeEvent> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<PatientChangeEvent> statuses) {
        this.statuses = statuses;
    }

    public Integer getMaxViralLoad() {
        return maxViralLoad;
    }

    public void setMaxViralLoad(Integer maxViralLoad) {
        this.maxViralLoad = maxViralLoad;
    }

    public Integer getMinViralLoad() {
        return minViralLoad;
    }

    public void setMinViralLoad(Integer minViralLoad) {
        this.minViralLoad = minViralLoad;
    }

    public Boolean getIsDueForVL() {
        return isDueForVL;
    }

    public void setIsDueForVL(Boolean isDueForVL) {
        this.isDueForVL = isDueForVL;
    }
    
    public Integer getMinCd4Count() {
        return minCd4Count;
    }

    public void setMinCd4Count(Integer minCd4Count) {
        this.minCd4Count = minCd4Count;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public YesNo getHei() {
        return hei;
    }

    public void setHei(YesNo hei) {
        this.hei = hei;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public TbTreatmentStatus getTbTreatmentStatus() {
        return tbTreatmentStatus;
    }

    public void setTbTreatmentStatus(TbTreatmentStatus tbTreatmentStatus) {
        this.tbTreatmentStatus = tbTreatmentStatus;
    }

    public TbTreatmentOutcome getTbTreatmentOutcome() {
        return tbTreatmentOutcome;
    }

    public void setTbTreatmentOutcome(TbTreatmentOutcome tbTreatmentOutcome) {
        this.tbTreatmentOutcome = tbTreatmentOutcome;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getSearch(SearchDTO dto) {
        if ((dto.getStatuses() != null && !dto.getStatuses().isEmpty()) || dto.getProvince() != null
                || dto.getDistrict() != null || dto.getPrimaryClinic() != null
                || (dto.getStartDate() != null && dto.getEndDate() != null) || dto.getGender() != null
                || dto.getAgeGroup() != null || dto.getLevelOfCare() != null || dto.getCareLevel() != null
                || dto.getStatus() != null || dto.getReferralStatus() != null || dto.getYesNo() != null
                || dto.getFollowUp() != null || dto.getCreatedBy() != null || dto.getStart() != null
                || dto.getMax() != null || dto.getReason() != null || dto.getResult() != null
                || dto.getTbTreatmentStatus() != null || dto.getTbTreatmentOutcome() != null
                || dto.getUserLevel() != null || (dto.getDistricts()!=null && !dto.getDistricts().isEmpty())
                || (dto.getProvinces()!=null && !dto.getProvinces().isEmpty()) || (dto.getFacilities()!=null && !dto.getFacilities().isEmpty())
        ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String getQueryString(SearchDTO dto) {
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
        if (dto.getSupportGroupDistrict() != null) {
            if (position == 0) {
                query.append("?supportGroupDistrict=");
                query.append(dto.getSupportGroupDistrict().getId());
                position++;
            } else {
                query.append("&supportGroupDistrict=");
                query.append(dto.getSupportGroupDistrict().getId());
            }
        }
        if (dto.getPeriodType() != null) {
            if (position == 0) {
                query.append("?periodType=");
                query.append(dto.getPeriodType().getCode());
                position++;
            } else {
                query.append("&periodType=");
                query.append(dto.getPeriodType().getCode());
            }
        }
        if (dto.getQuarterPeriod() != null) {
            if (position == 0) {
                query.append("?quarterPeriod=");
                query.append(dto.getQuarterPeriod().getId());
                position++;
            } else {
                query.append("&quarterPeriod=");
                query.append(dto.getQuarterPeriod().getId());
            }
        }
        if (dto.getHalfYearPeriod() != null) {
            if (position == 0) {
                query.append("?halfYearPeriod=");
                query.append(dto.getHalfYearPeriod().getId());
                position++;
            } else {
                query.append("&halfYearPeriod=");
                query.append(dto.getHalfYearPeriod().getId());
            }
        }
        if (dto.getYearPeriod() != null) {
            if (position == 0) {
                query.append("?period=");
                query.append(dto.getYearPeriod().getId());
                position++;
            } else {
                query.append("&period=");
                query.append(dto.getYearPeriod().getId());
            }
        }
        if (dto.getLevelOfCare() != null) {
            if (position == 0) {
                query.append("?levelOfCare=");
                query.append(dto.getLevelOfCare().getId());
                position++;
            } else {
                query.append("&levelOfCare=");
                query.append(dto.getLevelOfCare().getId());
            }
        }
        if (dto.getStatus() != null) {
            if (position == 0) {
                query.append("?status=");
                query.append(dto.getStatus().getCode());
                position++;
            } else {
                query.append("&status=");
                query.append(dto.getStatus().getCode());
            }
        }
        if (dto.getTestType() != null) {
            if (position == 0) {
                query.append("?testType=");
                query.append(dto.getTestType().getCode());
                position++;
            } else {
                query.append("&testType=");
                query.append(dto.getTestType().getCode());
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
        if (dto.getAgeGroup() != null) {
            if (position == 0) {
                query.append("?ageGroup=");
                query.append(dto.getAgeGroup().getStart());
                position++;
            } else {
                query.append("&ageGroup=");
                query.append(dto.getAgeGroup().getStart());
            }
        }
        if (dto.getGender() != null) {
            if (position == 0) {
                query.append("?gender=");
                query.append(dto.getGender().getCode());
                position++;
            } else {
                query.append("&gender=");
                query.append(dto.getGender().getCode());
            }
        }
        if (dto.getFollowUp() != null) {
            if (position == 0) {
                query.append("?followUp=");
                query.append(dto.getFollowUp().getCode());
                position++;
            } else {
                query.append("&followUp=");
                query.append(dto.getFollowUp().getCode());
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
        if (dto.getCareLevel() != null) {
            if (position == 0) {
                query.append("?careLevel=");
                query.append(dto.getCareLevel().getCode());
            } else {
                query.append("&careLevel=");
                query.append(dto.getCareLevel().getCode());
            }
        }
        if (dto.getCreatedBy() != null) {
            if (position == 0) {
                query.append("?createdBy=");
                query.append(dto.getCreatedBy().getId());
            } else {
                query.append("&createdBy=");
                query.append(dto.getCreatedBy().getId());
            }
        }
        if (dto.getMaxViralLoad() != null) {
            if (position == 0) {
                query.append("?maxViralLoad=");
                query.append(dto.getMaxViralLoad());
            } else {
                query.append("&maxViralLoad=");
                query.append(dto.getMaxViralLoad());
            }
        }
        if (dto.getMinViralLoad() != null) {
            if (position == 0) {
                query.append("?minViralLoad=");
                query.append(dto.getMinViralLoad());
            } else {
                query.append("&minViralLoad=");
                query.append(dto.getMinViralLoad());
            }
        }
        if (dto.getIsDueForVL()!= null) {
            if (position == 0) {
                query.append("?isDueForVL=");
                query.append(dto.getIsDueForVL());
            } else {
                query.append("&isDueForVL=");
                query.append(dto.getIsDueForVL());
            }
        }
        if (dto.getMinCd4Count() != null) {
            if (position == 0) {
                query.append("?minCd4Count=");
                query.append(dto.getMinCd4Count());
            } else {
                query.append("&minCd4Count=");
                query.append(dto.getMinCd4Count());
            }
        }
        if (dto.getUserType() != null) {
            if (position == 0) {
                query.append("?userType=");
                query.append(dto.getUserType().getCode());
            } else {
                query.append("&userType=");
                query.append(dto.getUserType().getCode());
            }
        }
        if (dto.getReason() != null) {
            if (position == 0) {
                query.append("?reason=");
                query.append(dto.getReason().getCode());
            } else {
                query.append("&reason=");
                query.append(dto.getReason().getCode());
            }
        }

        if (dto.getHei() != null) {
            if (position == 0) {
                query.append("?hei=");
                query.append(dto.getHei().getCode());
            } else {
                query.append("&hei=");
                query.append(dto.getHei().getCode());
            }
        }

        if (dto.getResult() != null) {
            if (position == 0) {
                query.append("?result=");
                query.append(dto.getResult().getCode());
            } else {
                query.append("&result=");
                query.append(dto.getResult().getCode());
            }
        }
        if (dto.getTbTreatmentStatus() != null) {
            if (position == 0) {
                query.append("?tbTreatmentStatus=");
                query.append(dto.getTbTreatmentStatus().getCode());
            } else {
                query.append("&tbTreatmentStatus=");
                query.append(dto.getTbTreatmentStatus().getCode());
            }
        }
        if (dto.getTbTreatmentOutcome() != null) {
            if (position == 0) {
                query.append("?tbTreatmentOutcome=");
                query.append(dto.getTbTreatmentOutcome().getCode());
            } else {
                query.append("&tbTreatmentOutcome=");
                query.append(dto.getTbTreatmentOutcome().getCode());
            }
        }
        if (dto.getUserLevel() != null) {
            if (position == 0) {
                query.append("?userLevel=");
                query.append(dto.getUserLevel().getCode());
            } else {
                query.append("&userLevel=");
                query.append(dto.getUserLevel().getCode());
            }
        }
        return query.toString();
    }

    public SearchDTO getInstance(SearchDTO dto) {
        return new SearchDTO(dto.getPeriod(), dto.getProvince(), dto.getDistrict(), dto.getPrimaryClinic(),
                dto.getSupportGroup(), dto.getStartDate(), dto.getEndDate(), dto.getGender(), dto.getAgeGroup(),
                dto.getPeriodType(), dto.getYearPeriod(), dto.getHalfYearPeriod(), dto.getQuarterPeriod(),
                dto.getLevelOfCare(), dto.getStatus(), dto.getSupportGroupDistrict(), dto.getCareLevel(),
                dto.getCrossTabOptions(), dto.getReferralStatus(), dto.getYesNo(), dto.getFollowUp(),
                dto.getCreatedBy(), dto.getIndicator(), dto.getStart(), dto.getMax(), dto.getStatuses(),
                dto.getMaxViralLoad(), dto.getMinViralLoad(), dto.getIsDueForVL(), dto.getMinCd4Count(), dto.getUserType(), dto.getTestType(), dto.getReason(),
                dto.getHei(), dto.getResult(), dto.getTbTreatmentStatus(), dto.getTbTreatmentOutcome(),
                dto.getUserLevel(), dto.getUserRoles(), dto.getFirstResult(), dto.getPageSize(), dto.getProvinces(), dto.getDistricts(),dto.getFacilities());
    }

    /*public String toString(){
        return "Province: "+this.province+", District: "+this.district+", Facility: "+this.primaryClinic+", start date: "+this.startDate+", end date: "+this.endDate+", status: "+this.status+
                ", Districts :"+districts.toString()+" ,Provinces:"+provinces.toString()+" ,Facilities:"+facilities.toString();
    }*/
}