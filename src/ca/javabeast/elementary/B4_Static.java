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

import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B4_Static{
 
    Person3 person = new Person3("Test");
    static{
        System.out.println("B4_Static static");
    }
     
    public B4_Static(){
        System.out.println("B4_Static constructor");
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args){
        //System.out.println(new Person2(1952).isBornBoomer());
        //new B4_Static();
        new MyClass();
    }
}

//usage of static
class Person2{
    private Date birthDate;
    private static Date startDate,endDate;
    private static Calendar cld;
    static{
        cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, 1946);
        startDate = cld.getTime();
        cld.set(Calendar.YEAR, 1964);
        endDate = cld.getTime();
    }
     
    public Person2(int year) {
        cld.set(Calendar.YEAR, year);
        this.birthDate = cld.getTime();
    }
     
    boolean isBornBoomer() {
        return birthDate.compareTo(startDate)>=0 && birthDate.compareTo(endDate) < 0;
    }
}

//static block
class Base{
     
    static{
        System.out.println("base static");
    }
     
    public Base(){
        System.out.println("base constructor");
    }
}


class Person3{
    static{
        System.out.println("person static");
    }
    public Person3(String str) {
        System.out.println("person "+str);
    }
}

class MyClass extends B4_Static {
    Person3 person = new Person3("MyClass");
    static{
        System.out.println("myclass static");
    }
     
    public MyClass() {
        System.out.println("myclass constructor");
    }
}