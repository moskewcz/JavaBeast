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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
        testCollections();
        sorted();
        rotate(); 
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
    
    public static void testCollections(){
        List<Integer> list = new ArrayList<Integer>();  
        list.add(1);  
        list.add(2);  
          
        for (Integer integer : list) {  
            System.out.println(integer);  
        }  
        /*find max*/  
        int max = Collections.max(list);  
        System.out.println("max:"+max);  
          
        /*replace elements with e*/  
        Collections.fill(list, 6);  
        System.out.println("after filled:");  
        for (Integer integer : list) {  
            System.out.println(integer);  
        }  
          
        /*find the frequency of the element*/  
        int count = Collections.frequency(list, 6);  
        System.out.println("frequency:"+count);
    }
    
    public static void sorted(){
        List<Integer> list = new ArrayList<Integer>();  
        list.add(5);  
        list.add(2);  
        list.add(1);  
        list.add(9);  
        list.add(0);  
          
        System.out.println("before sort:");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        }  
        System.out.println();  
          
        /*sort*/  
        Collections.sort(list);  
          
        System.out.println("after sort");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        }
    }
    
    public static void rotate(){
        List<Integer> list = new ArrayList<Integer>();  
        list.add(5);  
        list.add(2);  
        list.add(1);  
        list.add(9);  
        list.add(0);  
          
        System.out.println("original seq:");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        }  
        System.out.println();  
          
        /*rotate according the distance*/  
        Collections.rotate(list, -1);  
        System.out.println("after rotate:");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        } 
    }
}
