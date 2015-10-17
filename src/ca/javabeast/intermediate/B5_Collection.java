/*
 * Copyright 2015 Alpenliebe <alpseinstein@gmail.com>.
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
package ca.javabeast.intermediate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B5_Collection {
    
    public static void main(String[] args){
        int [] a = {2,3,2,2,1,4,2,2,2,7,9,6,2,2,3,1,0}; 
        printN(a);
    }
    
    /**
     * print the elements that the occurrence is over n/2 in a array
     */
    public static void printN(int [] array){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            //if(map.containsKey(array[i]))
            map.put(array[i], map.get(array[i])==null?1:map.get(array[i])+1);
        }
        
        Set<Integer> set = map.keySet();
        for (Integer key : set){
            if (map.get(key) >= array.length/2)
                System.out.println(key+":"+map.get(key));
        }
    }
}
