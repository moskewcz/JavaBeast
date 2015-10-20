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

import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B11_BehaviorPattern {

    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();
        System.out.println(cal.calculate(exp));

        String exp2 = "8+8";
        AbstractCalculator2 cal2 = new Plus2();
        System.out.println(cal2.calculate(exp2, "\\+"));

        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());
        sub.operation();
        
        Collection collection = new MyCollection();  
        Iterator it = collection.iterator();  
        while(it.hasNext()){  
            System.out.println(it.next());  
        }  
        
        MyHandler h1 = new MyHandler("h1");  
        MyHandler h2 = new MyHandler("h2");  
        MyHandler h3 = new MyHandler("h3");  
        h1.setHandler(h2);  
        h2.setHandler(h3);  
        h1.operator();
        
        Receiver receiver = new Receiver();  
        Command cmd = new MyCommand(receiver);  
        Invoker invoker = new Invoker(cmd);  
        invoker.action();
        
        Original origi = new Original("egg");
        Storage storage = new Storage(origi.createMemento());  
        // modified value  
        System.out.println("init：" + origi.getValue());  
        origi.setValue("hen");  
        System.out.println("modified：" + origi.getValue());  
        // restore value  
        origi.restoreMemento(storage.getMemento());  
        System.out.println("restored：" + origi.getValue());
        
        State state = new State();  
        Context context = new Context(state);  
        //set state 1  
        state.setValue("state1");  
        context.method();  
        //set state 2  
        state.setValue("state2");  
        context.method();
        
        Visitor visitor = new MyVisitor();  
        Subject2 sub2 = new MySubject2();  
        sub2.accept(visitor);
        
        Mediator mediator = new MyMediator();  
        mediator.createMediator();  
        mediator.workAll(); 
        
        //9+2-8
        int result = new Minus3().interpret((new Context3(new Plus3().interpret(new Context3(9, 2)), 8)));  
        System.out.println(result); 
    }

}

//Strategy
interface ICalculator {

    public int calculate(String exp);
}

abstract class AbstractCalculator {

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}

class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}

class Minus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "-");
        return arrayInt[0] - arrayInt[1];
    }

}

//Template Method
abstract class AbstractCalculator2 {

    public final int calculate(String exp, String opt) {
        int array[] = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    abstract public int calculate(int num1, int num2);

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}

class Plus2 extends AbstractCalculator2 {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

//Observer
interface Observer {

    public void update();
}

class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}

class Observer2 implements Observer {

    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }

}

interface Subject {

    /*add new Observer*/
    public void add(Observer observer);

    /*remove Observer*/
    public void del(Observer observer);

    /*notify Observers*/
    public void notifyObservers();

    /*self operation*/
    public void operation();
}

abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()) {
            enumo.nextElement().update();
        }
    }

}

class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}

//Iterator
interface Collection {

    public Iterator iterator();

    /*get element*/
    public Object get(int i);

    /*get size*/
    public int size();
}

interface Iterator {

    //move backward  

    public Object previous();

    //move forward  
    public Object next();

    public boolean hasNext();

    //get first element  
    public Object first();
}

class MyCollection implements Collection {

    public String string[] = {"A", "B", "C", "D", "E"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }
}

class MyIterator implements Iterator {

    private Collection collection;
    private int pos = -1;

    public MyIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos > 0) {
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection.size() - 1) {
            pos++;
        }
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        if (pos < collection.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object first() {
        pos = 0;
        return collection.get(pos);
    }

}

//Chain of Responsibility
interface Handler {
	public void operator();
}

abstract class AbstractHandler {
	
	private Handler handler;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}

class MyHandler extends AbstractHandler implements Handler {

	private String name;

	public MyHandler(String name) {
		this.name = name;
	}

	@Override
	public void operator() {
		System.out.println(name+"deal!");
		if(getHandler()!=null){
			getHandler().operator();
		}
	}
}


//Command
interface Command {
	public void exe();
}

class MyCommand implements Command {

	private Receiver receiver;
	
	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void exe() {
		receiver.action();
	}
}

class Receiver {
	public void action(){
		System.out.println("command received!");
	}
}

class Invoker {
	
	private Command command;
	
	public Invoker(Command command) {
		this.command = command;
	}

	public void action(){
		command.exe();
	}
}

//Memento
class Original {
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Original(String value) {
		this.value = value;
	}

	public Memento createMemento(){
		return new Memento(value);
	}
	
	public void restoreMemento(Memento memento){
		this.value = memento.getValue();
	}
}

class Memento {
	
	private String value;

	public Memento(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

class Storage {
	
	private Memento memento;
	
	public Storage(Memento memento) {
		this.memento = memento;
	}

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
}

//State
class State {
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void method1(){
		System.out.println("execute the first opt!");
	}
	
	public void method2(){
		System.out.println("execute the second opt!");
	}
}

class Context {

	private State state;

	public Context(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void method() {
		if (state.getValue().equals("state1")) {
			state.method1();
		} else if (state.getValue().equals("state2")) {
			state.method2();
		}
	}
}

//Visitor
interface Visitor {  
    public void visit(Subject2 sub);  
}  

class MyVisitor implements Visitor {

	@Override
	public void visit(Subject2 sub) {
		System.out.println("visit the subject2："+sub.getSubject());
	}
}

interface Subject2 {
	public void accept(Visitor visitor);
	public String getSubject();
}

class MySubject2 implements Subject2 {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}
}

//Mediator
interface Mediator {  
    public void createMediator();  
    public void workAll();  
} 

class MyMediator implements Mediator {

	private User user1;
	private User user2;
	
	public User getUser1() {
		return user1;
	}

	public User getUser2() {
		return user2;
	}

	@Override
	public void createMediator() {
		user1 = new User1(this);
		user2 = new User2(this);
	}

	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}
}

abstract class User {
	
	private Mediator mediator;
	
	public Mediator getMediator(){
		return mediator;
	}
	
	public User(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void work();
}

class User1 extends User {

	public User1(Mediator mediator){
		super(mediator);
	}
	
	@Override
	public void work() {
		System.out.println("user1 exe!");
	}
}

class User2 extends User {

	public User2(Mediator mediator){
		super(mediator);
	}
	
	@Override
	public void work() {
		System.out.println("user2 exe!");
	}
}

//Interpreter
interface Expression {  
    public int interpret(Context3 context);  
}  

class Plus3 implements Expression {  
  
    @Override  
    public int interpret(Context3 context) {  
        return context.getNum1()+context.getNum2();  
    }  
}

class Minus3 implements Expression {  
  
    @Override  
    public int interpret(Context3 context) {  
        return context.getNum1()-context.getNum2();  
    }  
}  

class Context3 {
	
	private int num1;
	private int num2;
	
	public Context3(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	
}