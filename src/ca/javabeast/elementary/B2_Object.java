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
    
    class BA{  
        int a = 10;  
        void b(){  
            System.out.println("this is A.b()!");  
        }  
    } 
    
    /**
     * 
     * @param args 
     */     
    public static void main(String[] args){
       //Build b = new Build();
        System.out.println(a("egg", ":niu", ":baby!"));
        Hero h = new Hero();  
        t(h);  
        u(h);  
        v(h);  
        w(h);
        
        B2_Object bo = new B2_Object();  
        B2_Object.BA aa = bo.new BA();  
        aa.b();
        
        /*non-static inner class*/  
        InnerClass ic = new InnerClass();  
        InnerClass.A a = ic.new A();  
        a.a();  
          
        /*static inner class*/  
        InnerClass.C c = new InnerClass.C();  
        c.c(); 
        
        //extends inner class
        CC cc = new CC();  
        takesA(cc);  
        takesB(cc.makeB()); 
    }
    
    static void takesA(AA a){}  
    static void takesB(BB b){}
    
    public static String a(String... value) {  
        return "welcome to you :" + value[0] + value[1] + value[2];  
    }
    
    public static void t(CanFight x){x.fight();}  
      
    public static void u(CanSwim x){x.swim();}  
      
    public static void v(CanFly x){x.fly();}  
      
    public static void w(ActionCharacter x){x.fight();} 
}

class InnerClass {  
    class A{  
        void a(){  
            System.out.println("this is A.a()!");  
        }  
    }  
    static class C{  
        void c(){  
            System.out.println("this is C.c()!");  
        }  
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


interface CanFight {void fight();}  
interface CanFly {void fly();}  
interface CanSwim {void swim();}  
class ActionCharacter {public void fight(){}}  
class Hero extends ActionCharacter implements CanFight, CanFly, CanSwim {  
  
    @Override  
    public void swim() {}  
  
    @Override  
    public void fly() { }  
  
} 

//extends inner class
class AA{  
      
}  
abstract class BB{  
      
}  
class CC extends AA{  
    BB makeB(){  
        return new BB(){  
              
        };  
    }  
} 