package zw.org.zvandiri.business.util;

import zw.org.zvandiri.business.domain.BaseEntity;
import zw.org.zvandiri.business.domain.BaseName;
import zw.org.zvandiri.business.domain.util.*;
import zw.org.zvandiri.business.util.dto.SearchDTO;

import javax.persistence.Query;
import java.util.Set;

public class Reportutil {

    public static final Integer commonPatientQuery(StringBuilder builder,SearchDTO dto, int position) {
        //builder.append() "Select Distinct p from Patient p  ";

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic.district.province=:province");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district.province=:province");
                }
            }

            if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
                //System.err.println("################# DTO facilities is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic in :facilities");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic in :facilities");
                }
            } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
                //System.err.println("$$$$$$$$$$$$$$$$$$$$$$$ DTO districts is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district in :districts");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district in :districts");
                }
            } else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
                //System.err.println("^^^^^^^^^^^^^^^^^^^^^^^ DTO provinces is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic.district.province in :provinces");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district.province in :provinces");
                }
            }


            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append(" p.primaryClinic.district=:district");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("p.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and p.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("p.gender=:gender");
                    position++;
                } else {
                    builder.append(" and p.gender=:gender");
                }
            }

            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append(" p.status=:status");
                    position++;
                } else {
                    builder.append(" and p.status=:status");
                }
            }
        }

        return position;
    }

    public static final Integer commonCadreQuery(StringBuilder builder,SearchDTO dto, int position) {

        if (dto.getSearch(dto)) {
            builder.append(" where ");
            if (dto.getProvince() != null) {
                if (position == 0) {
                    builder.append("p.province=:province");
                    position++;
                } else {
                    builder.append(" and p.province=:province");
                }
            }

            if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
                //System.err.println("################# DTO facilities is not null");
                if (position == 0) {
                    builder.append(" p.primaryClinic in :facilities");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic in :facilities");
                }
            } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
                //System.err.println("$$$$$$$$$$$$$$$$$$$$$$$ DTO districts is not null");
                if (position == 0) {
                    builder.append(" p.district in :districts");
                    position++;
                } else {
                    builder.append(" and p.district in :districts");
                }
            } else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
                //System.err.println("^^^^^^^^^^^^^^^^^^^^^^^ DTO provinces is not null");
                if (position == 0) {
                    builder.append(" p.province in :provinces");
                    position++;
                } else {
                    builder.append(" and p.province in :provinces");
                }
            }


            if (dto.getDistrict() != null) {
                if (position == 0) {
                    builder.append(" p.district=:district");
                    position++;
                } else {
                    builder.append(" and p.district=:district");
                }
            }
            if (dto.getPrimaryClinic() != null) {
                if (position == 0) {
                    builder.append("p.primaryClinic=:primaryClinic");
                    position++;
                } else {
                    builder.append(" and p.primaryClinic=:primaryClinic");
                }
            }
            if (dto.getSupportGroup() != null) {
                if (position == 0) {
                    builder.append("p.supportGroup=:supportGroup");
                    position++;
                } else {
                    builder.append(" and p.supportGroup=:supportGroup");
                }
            }
            if (dto.getGender() != null) {
                if (position == 0) {
                    builder.append("p.gender=:gender");
                    position++;
                } else {
                    builder.append(" and p.gender=:gender");
                }
            }

            if (dto.getStatus() != null) {
                if (position == 0) {
                    builder.append(" p.status=:status");
                    position++;
                } else {
                    builder.append(" and p.status=:status");
                }
            }
        }

        return position;
    }

    public static final Query commonQueryParams(Query query, SearchDTO dto) {

        if (dto.getProvince() != null) {
            query.setParameter("province", dto.getProvince());
        }

        if (dto.getFacilities() != null && !dto.getFacilities().isEmpty()) {
            query.setParameter("facilities", dto.getFacilities());
        } else if (dto.getDistricts() != null && !dto.getDistricts().isEmpty()) {
            query.setParameter("districts", dto.getDistricts());
        }else if (dto.getProvinces() != null && !dto.getProvinces().isEmpty()) {
            query.setParameter("provinces", dto.getProvinces());
        }

        if (dto.getDistrict() != null) {
            query.setParameter("district", dto.getDistrict());
        }
        if (dto.getPrimaryClinic() != null) {
            query.setParameter("primaryClinic", dto.getPrimaryClinic());
        }
        if (dto.getSupportGroup() != null) {
            query.setParameter("supportGroup", dto.getSupportGroup());
        }
        if (dto.getGender() != null) {
            query.setParameter("gender", dto.getGender());
        }
        if (dto.getStatus() != null) {
            query.setParameter("status", dto.getStatus());
        }

        return query;
    }

    public static String StringsFromList(Set<IdentifiedRisk> risks){
        String result="";
        IdentifiedRisk[] risks1=new IdentifiedRisk[risks.size()];
        risks.toArray(risks1);
        for (IdentifiedRisk risk: risks1) {
            result+=risk.getName();
        }
        return result;
    }

    public static String SupportStringsFromList(Set<Support> risks){
        String result="";
        Support[] risks1=new Support[risks.size()];
        risks.toArray(risks1);
        for (Support risk: risks1) {
            result+=risk.getName();
        }
        return result;
    }


    public static String refferalStringsFromList(Set<Referral> risks){
        String result="";
        Referral[] risks1=new Referral[risks.size()];
        risks.toArray(risks1);
        for (Referral risk: risks1) {
            result+=risk.getName();
        }
        return result;
    }

    public static String diagnosisStringsFromList(Set<Diagnosis> risks){
        String result="";
        Diagnosis[] risks1=new Diagnosis[risks.size()];
        risks.toArray(risks1);
        for (Diagnosis risk: risks1) {
            result+=risk.getName();
        }
        return result;
    }

    public static String interventionsStringsFromList(Set<Intervention> risks){
        String result="";
        Intervention[] risks1=new Intervention[risks.size()];
        risks.toArray(risks1);
        for (Intervention risk: risks1) {
            result+=risk.getName();
        }
        return result;
    }



}
