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
