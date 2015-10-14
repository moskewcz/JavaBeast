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
package ca.javabeast.elementary;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B2_Object {
    /**
     * 
     * @param args 
     */     
    public static void main(String[] args){
       //Build b = new Build();
        System.out.println(a("egg", ":niu", ":baby!")); 
    }
    public static String a(String... value) {  
        return "welcome to you :" + value[0] + value[1] + value[2];  
    }
}

class Person {  
    
    public Person(int id) {  
        System.out.println("person(" + id + ")");  
    }  
  
    
    public static void get(){
        String s = null;
        System.out.println("person(" + s + ")");
    }
    
}  
  
class Build {  
    /*static block*/  
    static{  
        System.out.println("this is static block!");  
    }  
    /*non-static block*/  
    {  
        System.out.println("this is non-static block!");  
    }  
    static Person p1 = new Person(1);//------------1-----------  
  
    public Build() {  
        System.out.println("this is build's block!");  
        Person p2 = new Person(2);  
    }  
  
    Person p3 = new Person(3);  
  
} 
