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

import zw.org.zvandiri.business.domain.ChronicInfection;
import zw.org.zvandiri.business.domain.HivCoInfection;
import zw.org.zvandiri.business.domain.MentalHealth;
import zw.org.zvandiri.business.domain.OrphanStatus;
import zw.org.zvandiri.business.domain.Substance;
import zw.org.zvandiri.business.domain.util.ARVDrugRegimen;
import zw.org.zvandiri.business.domain.util.AbuseOutcome;
import zw.org.zvandiri.business.domain.util.DrugIntervention;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
public class SearchPatientDTO extends SearchDTO {
    
    private OrphanStatus orphanStatus;
    private YesNo hospitalized;
    private ChronicInfection chronicInfection;
    private HivCoInfection hivCoInfection;
    private ARVDrugRegimen aRVDrugRegimen;
    private YesNo defaultedARV;
    private MentalHealth mentalHealth;
    private YesNo receiveProfessionalHelp;
    private YesNo mentalHealthHospitalized;
    private YesNo hadSex;
    private YesNo sexuallyActive;
    private YesNo condomUse;
    private YesNo birthControll;
    private YesNo beenPregnant;
    private YesNo abused;
    private AbuseOutcome outcome;
    private YesNo toldSomeone;
    private YesNo safetyNow;
    private Substance substance;
    private YesNo currentSubstance;
    private YesNo lifeTimeSubstance;
    private DrugIntervention intervention;

    public OrphanStatus getOrphanStatus() {
        return orphanStatus;
    }

    public void setOrphanStatus(OrphanStatus orphanStatus) {
        this.orphanStatus = orphanStatus;
    }

    public YesNo getHospitalized() {
        return hospitalized;
    }

    public void setHospitalized(YesNo hospitalized) {
        this.hospitalized = hospitalized;
    }

    public ChronicInfection getChronicInfection() {
        return chronicInfection;
    }

    public void setChronicInfection(ChronicInfection chronicInfection) {
        this.chronicInfection = chronicInfection;
    }

    public HivCoInfection getHivCoInfection() {
        return hivCoInfection;
    }

    public void setHivCoInfection(HivCoInfection hivCoInfection) {
        this.hivCoInfection = hivCoInfection;
    }

    public ARVDrugRegimen getaRVDrugRegimen() {
        return aRVDrugRegimen;
    }

    public void setaRVDrugRegimen(ARVDrugRegimen aRVDrugRegimen) {
        this.aRVDrugRegimen = aRVDrugRegimen;
    }

    public YesNo getDefaultedARV() {
        return defaultedARV;
    }

    public void setDefaultedARV(YesNo defaultedARV) {
        this.defaultedARV = defaultedARV;
    }

    public MentalHealth getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(MentalHealth mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public YesNo getReceiveProfessionalHelp() {
        return receiveProfessionalHelp;
    }

    public void setReceiveProfessionalHelp(YesNo receiveProfessionalHelp) {
        this.receiveProfessionalHelp = receiveProfessionalHelp;
    }

    public YesNo getMentalHealthHospitalized() {
        return mentalHealthHospitalized;
    }

    public void setMentalHealthHospitalized(YesNo mentalHealthHospitalized) {
        this.mentalHealthHospitalized = mentalHealthHospitalized;
    }

    public YesNo getHadSex() {
        return hadSex;
    }

    public void setHadSex(YesNo hadSex) {
        this.hadSex = hadSex;
    }

    public YesNo getSexuallyActive() {
        return sexuallyActive;
    }

    public void setSexuallyActive(YesNo sexuallyActive) {
        this.sexuallyActive = sexuallyActive;
    }

    public YesNo getCondomUse() {
        return condomUse;
    }

    public void setCondomUse(YesNo condomUse) {
        this.condomUse = condomUse;
    }

    public YesNo getBirthControll() {
        return birthControll;
    }

    public void setBirthControll(YesNo birthControll) {
        this.birthControll = birthControll;
    }

    public YesNo getBeenPregnant() {
        return beenPregnant;
    }

    public void setBeenPregnant(YesNo beenPregnant) {
        this.beenPregnant = beenPregnant;
    }

    public YesNo getAbused() {
        return abused;
    }

    public void setAbused(YesNo abused) {
        this.abused = abused;
    }

    public AbuseOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(AbuseOutcome outcome) {
        this.outcome = outcome;
    }

    public YesNo getToldSomeone() {
        return toldSomeone;
    }

    public void setToldSomeone(YesNo toldSomeone) {
        this.toldSomeone = toldSomeone;
    }

    public YesNo getSafetyNow() {
        return safetyNow;
    }

    public void setSafetyNow(YesNo safetyNow) {
        this.safetyNow = safetyNow;
    }

    public Substance getSubstance() {
        return substance;
    }

    public void setSubstance(Substance substance) {
        this.substance = substance;
    }

    public YesNo getCurrentSubstance() {
        return currentSubstance;
    }

    public void setCurrentSubstance(YesNo currentSubstance) {
        this.currentSubstance = currentSubstance;
    }

    public YesNo getLifeTimeSubstance() {
        return lifeTimeSubstance;
    }

    public void setLifeTimeSubstance(YesNo lifeTimeSubstance) {
        this.lifeTimeSubstance = lifeTimeSubstance;
    }

    public DrugIntervention getIntervention() {
        return intervention;
    }

    public void setIntervention(DrugIntervention intervention) {
        this.intervention = intervention;
    }
    
    @Override
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
    
    public SearchPatientDTO getInstance(SearchPatientDTO dto){
        return null;
    }
}