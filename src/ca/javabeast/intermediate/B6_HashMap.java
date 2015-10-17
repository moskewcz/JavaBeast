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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B6_HashMap {
    
    public static void main(String[] args){
        int data[] = { 2, 5, 2, 3, 5, 2, 3, 5, 2, 3, 5, 2, 3, 5, 2,  
                7, 8, 8, 7, 8, 7, 9, 0 }; 
        sortValue(data);
    }
    
    /**
     * sort the elements based on the occurrence in a array
     */
    public static void sortValue(int [] array){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<array.length;i++){
            int v = array[i];
            map.put(array[i], map.get(v)==null?1:map.get(v)+1);
        }
        
        List<Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Entry<Integer,Integer>>(){

            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return (o1.getValue() - o2.getValue());
            }
            
        });
        for(Entry e : list){
            System.out.println(e.getKey()+":"+e.getValue());
        }
    }
}
