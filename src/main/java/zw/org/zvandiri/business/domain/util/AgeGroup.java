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
package zw.org.zvandiri.business.domain.util;

/**
 *
 * @author Judge Muzinda
 */
public enum AgeGroup {
    
    UNDER_ONE(0, 1, "<1"), ONE_TO_FOUR(1, 4, "1-4"),FIVE_TO_NINE(5, 9, "5-9"), TEN_TO_FOURTEEN(10, 14, "10-14"),
    FIFTEEN_TO_NINETEEN(15, 19, "15-19"), TWENTY_TO_TWENTY_FOUR(20, 24, "20-24"), ABOVE_TWENTY_FIVE(25, 125, ">25");
	
	/*
	 * <1, 1-4, 5-9, 10-14, 15-19, 20-24, > 25
	 */
    
    private final Integer start;
    private final Integer end;
    private final String name;

    private AgeGroup(Integer start, Integer end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }
    
    public static AgeGroup get(Integer start) {
    	for (AgeGroup item : values()) {
    		if (item.getStart().equals(start)) {
    			return item;
    		}
    	}
    	throw new IllegalArgumentException("Illegal parameter passed to method :"+start);
    }
    
    
    
    public static Integer getAgeRange(int age){
        if(age >= 0 && age < 1){
            return 0;
        } else if(age >= 1 && age < 4){
            return 1;
        }else if(age >= 5 && age < 9){
            return 5;
        }else if(age >= 10 && age < 15){
            return 10;
        } else if(age >= 15 && age < 20){
            return 15;
        }else if(age >= 20 && age < 24){
            return 20;
        }
        return 25;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAltName(){
        return start+"-"+end;
    }
}