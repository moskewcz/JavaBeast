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
        
        new ExtendsTest(); 
        
        ProxyTest pt = new ProxyTest();
        pt.p(20);
        pt.p2(50);
        
        Component ct = new Component();  
        System.out.println(ct);
        
        //break loop
        loop: for (int i = 0; i < 10; i++) {  
            for (int j = 0; j < 10; j++) {  
                for (int k = 0; k < 10; k++) {  
                    for (int n = 0; n < 10; n++) {  
                        if (n == 6) {  
                            break loop;  
                        }  
                        System.out.print(n);  
                    }  
                }  
            }  
        }  
        System.out.println("\nI'm here!"); 
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


/*=====================================================*/
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


/*====================================================*/
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

//inner class extended
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

/*===============================================*/
//initalize the parent class
class AAA {  
    public AAA() {  
        System.out.println("A()!");  
    }  
}  
  
class BBB extends AAA {  
    public BBB() {  
        System.out.println("B()!");  
    }  
}  
  
class ExtendsTest extends BBB {  
  
    public ExtendsTest() {  
        System.out.println("ExtendsTest()!");  
    }  
} 

//proxy

class ProxyTest {
	Source source = new Source();
	void p(int n){
		source.a(n);
	}
	void p2(int n){
		source.b(n);
	}
	
}

class Source{
	void a(int n){
		System.out.println("this is : "+n);
	}
	void b(int n){
		System.out.println("this is : "+n);
	}
}

//component

class Soap{  
    private String s;  
    Soap(){  
        System.out.println("soap");  
        s = "constructor";  
    }  
    public String toString(){  
        return s;  
    }  
}  
  
class Component {  
    private String s1 = "happy",s2="Happy",s3,s4;  
    private Soap castille;  
    private int i;  
    public Component(){  
        s3 = "joy";  
        castille = new Soap();  
    }  
    {  
        i = 88;  
    }  
    public String toString(){  
        if(s4 == null){  
            s4 = "Joy";  
        }  
            return "s1 = " + s1 + "\n" +  
                   "s2 = " + s2 + "\n" +  
                   "s3 = " + s3 + "\n" +  
                   "s4 = " + s4 + "\n" +  
                   "i = " + i + "\n" +  
                   "castille = " + castille;  
  
    }  
}
