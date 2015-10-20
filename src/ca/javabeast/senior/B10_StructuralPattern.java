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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B10_StructuralPattern {
    
    public static void main(String[] args){
        Targetable target = new Adapter();
        Source source = new Source();
        Targetable target2= new Wrapper(source);
        Sourceable subsource1 = new SourceSub1();
        Sourceable subsource2 = new SourceSub2();
        target.method1();
        target.method2();
        target2.method1();
        target2.method2();
        subsource1.method1();
        subsource2.method2();
        
        Sourceable2 source2Decorator = new Decorator(new Source2());
        source2Decorator.method();
        
        Sourceable3 proxy = new Proxy();
        proxy.method();
        
        Computer computer = new Computer();  
        computer.startup();  
        computer.shutdown();
        
        Bridge bridge = new MyBridge();   
        Sourceable4 source41 = new SourceImpt1();  
        bridge.setSource(source41);  
        bridge.method();  
        Sourceable4 source42 = new SourceImpt2();  
        bridge.setSource(source42);  
        bridge.method();
        
        TreeNode root = new TreeNode("A");  
        TreeNode nodeB = new TreeNode("B");  
        TreeNode nodeC = new TreeNode("C");
        nodeB.add(nodeC);
        root.add(nodeB);
    }
    
}

//adapter
class Source{
    public void method1(){
        System.out.println("This is original method1");
    }
}

interface Targetable{
    public void method1();
    
    public void method2();
}

class Adapter extends Source implements Targetable{

    @Override
    public void method2() {
        System.out.println("this is the targetable method2 in adapter");
    }
    
}

//wrapper
class Wrapper implements Targetable{

    private Source source;
    
    public Wrapper(Source source){
        super();
        this.source=source;
    }
    @Override
    public void method1() {
        this.source.method1();
    }

    @Override
    public void method2() {
        System.out.println("This is the targatable method2 in wrapper");
    }
}

//sub adapter
interface Sourceable{
    public void method1();
    public void method2();
}

abstract class AbsWrapper implements Sourceable {
    @Override
    public void method1(){}
    
    @Override
    public void method2(){}
}

class SourceSub1 extends AbsWrapper {
    @Override
    public void method1(){
        System.out.println("the sourceable interface's first sub1");
    }
}

class SourceSub2 extends AbsWrapper {
    @Override
    public void method2(){
        System.out.println("the sourceable interface's second sub2");
    }
}

//decorator
interface Sourceable2{
    public void method();
}

class Source2 implements Sourceable2{

    @Override
    public void method() {
        System.out.println("this is original method");
    }
    
}

class Decorator implements Sourceable2{

    private Sourceable2 source2; 
            
    public Decorator(Sourceable2 source2){
        this.source2=source2;
    }
    
    @Override
    public void method() {
        System.out.println("before decorator");
        this.source2.method();
        System.out.println("after decorator");
    }
    
}

//proxy

interface Sourceable3{
    public void method();
}

class Source3 implements Sourceable3{

    @Override
    public void method() {
        System.out.println("the original method");
    }
    
}

class Proxy implements Sourceable3 {

	private Source3 source3;
	public Proxy(){
		super();
		this.source3 = new Source3();
	}
        
	@Override
	public void method() {
		before();
		this.source3.method();
		atfer();
	}
	private void atfer() {
		System.out.println("after proxy!");
	}
	private void before() {
		System.out.println("before proxy!");
	}
}

//facade
class CPU {
	
	public void startup(){
		System.out.println("cpu startup!");
	}
	
	public void shutdown(){
		System.out.println("cpu shutdown!");
	}
}

class Memory {
	
	public void startup(){
		System.out.println("memory startup!");
	}
	
	public void shutdown(){
		System.out.println("memory shutdown!");
	}
}

class Disk {
	
	public void startup(){
		System.out.println("disk startup!");
	}
	
	public void shutdown(){
		System.out.println("disk shutdown!");
	}
}

class Computer {
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	
	public Computer(){
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
	}
	
	public void startup(){
		System.out.println("start the computer!");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("start computer finished!");
	}
	
	public void shutdown(){
		System.out.println("begin to close the computer!");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("computer closed!");
	}
}

//Bridge
interface Sourceable4 {
	public void method();
}

class SourceImpt1 implements Sourceable4 {

	@Override
	public void method() {
		System.out.println("this is the first impt!");
	}
}

class SourceImpt2 implements Sourceable4 {

	@Override
	public void method() {
		System.out.println("this is the second impt!");
	}
}

abstract class Bridge{
    private Sourceable4 source;
    
    public void method(){
        this.source.method();
    }
    
    public Sourceable4 getSource(){
        return this.source;
    }
    
    public void setSource(Sourceable4 source){
        this.source=source;
    }
}

class MyBridge extends Bridge {
    @Override
    public void method(){  
        getSource().method();  
    } 
}

//Composite
class TreeNode {
	
    private String name;
    private TreeNode parent;
    private Vector<TreeNode> children = new Vector<TreeNode>();

    public TreeNode(String name){
            this.name = name;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public TreeNode getParent() {
            return parent;
    }

    public void setParent(TreeNode parent) {
            this.parent = parent;
    }

    //add child
    public void add(TreeNode node){
            children.add(node);
    }

    //remove child
    public void remove(TreeNode node){
            children.remove(node);
    }

    //return children
    public Enumeration<TreeNode> getChildren(){
            return children.elements();
    }
}

//Flyweight
class ConnectionPool {
	
    private Vector<Connection> pool;

    /*atributes*/
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";

    private int poolSize = 100;
    private static ConnectionPool instance = null;
    Connection conn = null;

    /*constructor*/
    private ConnectionPool() {
            pool = new Vector<Connection>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                    try {
                            Class.forName(driverClassName);
                            conn = DriverManager.getConnection(url, username, password);
                            pool.add(conn);
                    } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
    }

    /* return connection back to pool*/
    public synchronized void release() {
            pool.add(conn);
    }

    /* get connection from pool */
    public synchronized Connection getConnection() {
            if (pool.size() > 0) {
                    Connection conn = pool.get(0);
                    pool.remove(conn);
                    return conn;
            } else {
                    return null;
            }
    }
}



