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

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import sun.misc.ProxyGenerator;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B13_DynamicProxy {
    
    public static void main(String[] args) throws Exception{
        UserService userService  = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) myInvocationHandler.getProxy();
        proxy.add();
        
        String path = "./resources/$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", UserServiceImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            try {
                out = new FileOutputStream(path);
                out.write(classFile);
                out.flush();
            } finally {
                out.close();
            }
        } catch (Exception e) {  
            e.printStackTrace(); 
        }
        
        //reflection in $Proxy0.class
        UserService target = new UserServiceImpl();
        Class.forName("ca.javabeast.senior.UserService").getMethod("add", new Class[0]).invoke(target, null);
        
    }
    
}


//Original Object
interface UserService{
    public void add();
}

class UserServiceImpl implements UserService{

    @Override
    public void add() {
        System.out.println("----- add -----");  
    }
    
}

//autonomous transaction
class MyInvocationHandler implements InvocationHandler{

    private Object target;
            
    public MyInvocationHandler(Object target) {
        super();
        this.target=target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(),this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("log:before add");
        Object result = method.invoke(target, args);
        System.out.println("log:after add");
        return result;
    }

}