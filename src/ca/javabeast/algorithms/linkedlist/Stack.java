/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.javabeast.algorithms.linkedlist;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Stack<T> {
    private Element<T> head;
    class Element<T>{
        private T value;
        private Element<T> next;

        public Element(T value) {
            this.value = value;
        }

        
        public Element(T value, Element<T> next) {
            this.value = value;
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setNext(Element<T> next) {
            this.next = next;
        }

        public T value() {
            return value;
        }

        public Element<T> next() {
            return next;
        }
        
        
    }

    public Stack() {
    }

    public Stack(Element<T> head) {
        this.head = head;
    }
 
    public void push(T v){
        Element<T> e = this.head;
        if(e==null){
            this.head=new Element<>(v);
        } else {
            while(e.next()!=null){
                e=e.next();
            }
            e.setNext(new Element<>(v));
        }
    }
    
    public boolean pop(T v){
        Element<T> e = this.head;
        if(e==null){
            return false;
        } else if(e.next()==null) {
            this.head=null;
            v=e.value();
            return true;
        } else {
            while(e.next().next()!=null){
                e=e.next();
            }
            v=e.next().value();
            e.setNext(null);
            return true;
        }
    }
    
    public boolean clear(){
        Element<T> e;
        while(this.head!=null){
            e=this.head.next();
            this.head.setNext(null);
            this.head=e;
        }
        return true;
    }
}
