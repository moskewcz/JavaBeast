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

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B8_Arrays {
    
    public static void main(String[] args){
        sortInteger();
        sortReference();
    } 
    
    /**
     * Arrays.sort use the DualPivotQuickSort to sort data with primitive data type
     */
    public static void sortInteger(){
        int data[] = { 10, 8, 9, 1, 2, 5, 98, 3, 7, 66 };  
        Arrays.sort(data);  
        for (int i : data) {  
            System.out.print(i + " ");  
        }
        System.out.println();
    }
    
    /**
     * Arrays.sort uses mergeSort to sort data with reference data type 
     */
    public static void sortReference(){
        User[] users = new User[]{new User("egg", "male", 26), new User("Kity", "Female", 25), new User("Pole", "male", 23), new User("Jack", "male", 28)};  
          
        Arrays.sort(users, new Comparator<User>(){

            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });  
          
        for (User user : users) {  
            System.out.println("name: " + user.getName() + " ,age: "+user.getAge());  
        }
    }
    
}

class User {  
    private String name;  
    private String gender;  
    private int age;  
  
    public User(String name, String gender, int age) {  
        this.name = name;  
        this.gender = gender;  
        this.age = age;  
    }  
  
    /** 
     * @return the name 
     */  
    public String getName() {  
        return name;  
    }  
  
    /** 
     * @param name 
     *            the name to set 
     */  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    /** 
     * @return the gender 
     */  
    public String getGender() {  
        return gender;  
    }  
  
    /** 
     * @param gender 
     *            the gender to set 
     */  
    public void setGender(String gender) {  
        this.gender = gender;  
    }  
  
    /** 
     * @return the age 
     */  
    public int getAge() {  
        return age;  
    }  
  
    /** 
     * @param age 
     *            the age to set 
     */  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
}  
