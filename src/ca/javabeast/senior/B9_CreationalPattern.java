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
package ca.javabeast.senior;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B9_CreationalPattern {
    
    public static void main(String[] args) {  
        SendFactory sf = new SendFactory();  
        Sender sender1 = sf.produce("sms");
        Sender sender2 = SendFactory.produceMail();
        Sender sender3 = new SendMailFactory().produce();
        sender1.Send(); 
        sender2.Send();  
        sender3.Send();
        Builder builder = new Builder();
        builder.produceMailSender(10);
    } 
    
}

interface Sender {
	public void Send();
}

class MailSender implements Sender {
	@Override
	public void Send() {
		System.out.println("this is mailsender!");
	}
}

class SmsSender implements Sender {

	@Override
	public void Send() {
		System.out.println("this is sms sender!");
	}
}

//Factory
class SendFactory {

    //Factory Method
    public Sender produce(String type) {
            if ("mail".equals(type)) {
                    return new MailSender();
            } else if ("sms".equals(type)) {
                    return new SmsSender();
            } else {
                    System.out.println("please input correct type");
                    return null;
            }
    }
    
//    public Sender produceMail(){  
//        return new MailSender();  
//    }  
//      
//    public Sender produceSms(){  
//        return new SmsSender();  
//    } 
    
    //Static Factory Method
    public static Sender produceMail(){  
        return new MailSender();  
    }  
      
    public static Sender produceSms(){  
        return new SmsSender();  
    } 
}

//Abstract Factory
interface Provider {  
    public Sender produce();  
} 

class SendMailFactory implements Provider {
	
	@Override
	public Sender produce(){
		return new MailSender();
	}
}

class SendSmsFactory implements Provider{

	@Override
	public Sender produce() {
		return new SmsSender();
	}
}

//Singleton
class Singleton{
    
    private static Singleton instance = null;
    
    private Singleton(){
        
    }
    
    //inner class singleton
    private static class SingletonFactory {  
        private static Singleton instance = new Singleton();  
    } 
    
    public static Singleton getInstance(){
        return SingletonFactory.instance;
    }
    
    public Object readResolve(){
        return getInstance();
    }
    
    //synchronized singleton
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new Singleton();  
        }  
    }  
  
    public static Singleton getInstance2() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }
    
    private Vector properties = null;  
  
    public Vector getProperties() {  
        return properties;  
    }
    
    public void updateProperties() {  
        Singleton shadow = new Singleton();  
        properties = shadow.getProperties();  
    }
}

//Builder
class Builder{
    private List<Sender> list = new ArrayList<Sender>();
    
    public void produceMailSender(int count){
        for(int i=0;i<count;i++){
            list.add(new MailSender());
        }
    }
    
    public void produceSmsSender(int count){
        for(int i=0;i<count;i++){
            list.add(new SmsSender());
        }
    }
}

//Prototype
class Prototype implements Cloneable,Serializable{
    private static final long serialVersionUID = 1L;
    private String str;
    private SerializableObject obj;
    
    
    public Object clone()throws CloneNotSupportedException{
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    public Object deepClone() throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
    
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public SerializableObject getObj() {
        return obj;
    }

    public void setObj(SerializableObject obj) {
        this.obj = obj;
    }
    
    
}

class SerializableObject implements Serializable{
    private static final long serialVersionUID = 1L;
    
}

