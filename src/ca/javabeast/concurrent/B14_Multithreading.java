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
package ca.javabeast.concurrent;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B14_Multithreading {
    public static void main(String[] args){
        //startThread1();
        //startThread2();
        startThread3();
    }
    
    public static void startThread1(){
        new MyThread1().start();
        new MyThread1().start();
    }
    
    public static void startThread2(){
        Thread t1 = new Thread(new MyThread2());
        Thread t2 = new Thread(new MyThread2());
        t1.start();
        t2.start();
    }
    
    public static void startThread3(){
        Generator g = new Generator();
        Thread t1 = new Thread(new MyThread3(g));
        Thread t2 = new Thread(new MyThread3(g));
        t1.start();
        t2.start();
    }
}

class MyThread1 extends Thread{
    public void run(){
        for(int i=0; i<20; i++){  
            System.out.println(Thread.currentThread().getName()+"-"+i);  
        } 
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<20; i++){  
            System.out.println(Thread.currentThread().getName()+"-"+i);  
        }
    }
    
}

class MyThread3 implements Runnable{

    private Generator g;
    
    public MyThread3(Generator g){
        this.g=g;
    }
    
    @Override
    public void run() {
        for(int i=0; i<20; i++){  
            System.out.println(Thread.currentThread().getName()+"-"+g.getX2());  
        }
    }
    
}

class Generator{
    private final AtomicLong value = new AtomicLong(0);
    
    private int x = 0;
    
    public void getValue(){
        value.incrementAndGet();
    }
    
    public int getX(){
        Lock lock = new ReentrantLock();  
        lock.lock();  
        try {   
          // update object state 
            return  x++;
        }  
        finally {  
          lock.unlock();   
        } 
            
    }
    
    public synchronized int getX2(){
        return  x++;    
    }
    
}