package zw.org.zvandiri.business.service.impl.parallel;

import zw.org.zvandiri.business.util.DateUtil;
import zw.org.zvandiri.business.util.dto.LastContactedDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LastContactedDTOTask extends RecursiveTask<List<LastContactedDTO>> {

    List<Object[]> objects;
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public LastContactedDTOTask(List<Object[]> objects) {
        this.objects = objects;
    }

    @Override
    protected List compute() {

        if(objects.size() <= 500){
            return computeDirectly();
        }else{
            int midpoint=this.objects.size()/2;
            LastContactedDTOTask first=new LastContactedDTOTask(this.objects.subList(0, midpoint));
            LastContactedDTOTask second=new LastContactedDTOTask(this.objects.subList(midpoint, this.objects.size()));

            first.fork();
            List<LastContactedDTO> results=second.compute();
            results.addAll(first.join());
            return results;
        }


    }

    private List<LastContactedDTO> computeDirectly(){
        List<LastContactedDTO> lastContactedDTOS=new ArrayList<>();
        if(objects.size() >0)
        {
            int obs=0;
            for(Object[] arr: objects){
                if(arr!=null && arr.length>0)
                {
                    LastContactedDTO dto=new LastContactedDTO();
                    dto.setOiNumber(arr[0]!=null ? arr[0].toString() : "");
                    dto.setFullName(arr[1]!=null ? arr[1].toString() : "");
                    try{
                        dto.setDob(arr[2]!=null ? dateFormat.parse(arr[2].toString().trim()) : null);
                        //System.out.println("----------------- Good date:"+arr[2].toString());
                    }catch(Exception e){
                        System.err.println("----------------- error date:"+arr[2].toString());
                        e.printStackTrace();
                    }

                    try {
                        dto.setDateJoined(arr[2]!=null ?(dateFormat.parse(arr[3].toString().trim())) : null);
                        //System.out.println("----------------- Good date:"+arr[3].toString());
                    }catch(Exception e){
                        System.err.println("----------------- error date:"+arr[3].toString());
                        e.printStackTrace();
                    }


                    dto.setGender(arr[4]!=null ? arr[4].toString() : null);
                    dto.setAddress(arr[5]!=null ? arr[5].toString() : "");
                    dto.setMobileNumber(arr[6] !=null ? arr[6].toString() : "");
                    try {
                        dto.setContactDate(arr[7]!=null ? dateFormat.parse(arr[7].toString().trim()) : null);
                        //System.out.println("----------------- Good date:"+arr[7].toString());
                    }catch(Exception e){
                        System.err.println("----------------- error date:"+arr[7].toString());
                        e.printStackTrace();
                    }
                    dto.setFollowup(arr[8]!=null ? arr[8].toString() : "");
                    dto.setFacility(arr[9] !=null ? arr[9].toString(): "");
                    dto.setDistrict(arr[10] !=null ? arr[10].toString() : "");
                    dto.setProvince(arr[11]!=null ? arr[11].toString() : "");

                    lastContactedDTOS.add(dto);
                }else{
                    System.err.println("Object array on position "+obs+" is null");
                }
                obs++;
            }
        }else{
            System.err.println(" <<<<<<<<<<<<<<< The last contacted objects list is empty! >>>>>>>>>>>>>>>>>>>>>>>");
        }


        return lastContactedDTOS;
    }
}
