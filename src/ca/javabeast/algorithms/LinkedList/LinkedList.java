/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.javabeast.algorithms.LinkedList;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class LinkedList<T> {

    private Element<T> head;
    private Element<T> tail;

    class Element<T> {

        private T value;
        private Element<T> next;

        public Element(T v, Element<T> n) {
            this.value = v;
            this.next = n;
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

    class Node<T> {

        private T value;
        private Node<T> prev;
        private Node<T> next;
        private Node<T> child;

        public Node(T v, Node<T> p,Node<T> n) {
            this.value = v;
            this.prev=p;
            this.next = n;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T value() {
            return value;
        }

        public Node<T> prev() {
            return prev;
        }

        public Node<T> next() {
            return next;
        }

        public void setChild(Node<T> child) {
            this.child = child;
        }

        public Node<T> child() {
            return child;
        }

        
         
    }

    public LinkedList(Element<T> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addFirst(T value) {
        Element<T> e = new Element<>(value, head);
        this.head = e;
    }

    public void addLast(T value) {
        Element<T> e = new Element<>(value, null);
        if (this.isEmpty()) {
            this.head = e;
        } else {
            Element<T> n = this.head.next();
            while (n.next() != null) {
                n = n.next();
                n.setNext(e);
            }
        }
    }

    public T removeFirst() {
        Element<T> h = this.head;
        if (!this.isEmpty()) {
            this.head = this.head.next();
        }
        h.setNext(null);
        return h.value();
    }

    public T removeLast() {
        Element<T> e = this.head;
        if (isEmpty()) {
        } else if (e.next() == null) {
            this.head = null;
        } else {
            while (e.next().next() != null) {
                e = e.next();
                Element<T> l = e.next();
                e.setNext(null);
                e = l;
            }
        }
        return e == null ? null : e.value();
    }

    public boolean remove(T value) {
        Element<T> e;
        if (head == null) {
            return false;
        }
        e = this.head;
        if (head.value() == value) {
            this.head = this.head.next();
            e.setNext(null);
            return true;
        }
        while (e != null) {
            if (e.next() != null && e.next().value() == value) {
                e.setNext(e.next().next());
                e.next().setNext(null);
                return true;
            }
            e = e.next();
        }
        return false;
    }

    public Element<T> find(T value) {
        Element<T> e = this.head;
        while (e != null && e.value() != value) {
            e = e.next();
        }
        return e;
    }

    public void clear() {
        Element<T> e = this.head;
        while (e != null) {
            Element<T> n = e.next();
            e.setValue(null);
            e.setNext(null);
            e = n;
        }
        this.head = null;
    }
    
    //maintain tail
    public boolean remove(Element<T> e){
        Element<T> cur = this.head;
        
        //e is null
        if(e==null){
            return false;
        }
        
        //remove head
        if(head.equals(e)){
            head=head.next();
            e.setNext(null);
            if(head==null)
                tail=null;
            return true;
        }
        
        //remove e after head
        while(cur!=null){
            if(e.equals(cur.next())){
                cur.setNext(cur.next().next());
                e.setNext(null);
                if(cur.next()==null){
                    tail=cur;
                }
                return true;
                    
            }
            cur = cur.next();
                
        }
        
        //when empty list and no e found
        return false;
    }

    public boolean addAfter(Element<T> e,T v){
        Element<T> cur = this.head;
        
        //add to the begining
        if (e==null){
            head = new Element<>(v,head);
            if(head.next()==null){
                tail=head;
            }
            return true;
        }
        
        //add after begining
        while(cur!=null){
            if(cur.equals(e)){
                cur.setNext(new Element<>(v,cur.next()));
                if(cur.next()==null){
                    tail=cur;
                }
                return true;
            }
            cur = cur.next();
        }
        
        //when empty list or no e found 
        return false;
    }
    
    
    //Mth-to-Last Element of a Linked List
    public Element<T> findMToLastElement(int m){
        Element<T> cur,mBehind = null;
        int i=0;
        
        //empty list
        if(head==null)
            return null;
        
        cur = head;
        //traversal the list
        while(cur.next()!=null){
            i++;
            cur=cur.next();
            if(i==m)
                mBehind = head;
            else if(i>m)
                mBehind=mBehind.next();
        }
        return mBehind;
    }
    
    //flatten
    public Node<T> flatten(Node<T> h,Node<T> t){
        Node<T> cur=t;
        //traversal the list
        while(cur!=null){
            if(cur.child()!=null){  //found a child and append the child list to the end of parent list
                t=append(cur.child(),t);
            }
            cur = cur.next();
        }
        return t;
    }
    
    private Node<T> append(Node<T> h,Node<T> t){
        t.setNext(h);
        h.setPrev(t);
        //found the end of the list
        while(h.next!=null){
            h=h.next();
        }
        return h;
    }
    
    public Node<T> unflatten (Node<T> t){
        Node<T> cur = t;
        
        //back traversal the list
        while(cur!=null){
            if(cur.child()!=null){  //found the child of cur position and cut the child list off to parent list
                t=cur.child().prev();
                t.setNext(null);
                cur.child().setPrev(null);
            }
            cur = cur.prev();
        }
        return t;
    }
    
    public boolean nullOrCycle(Element<T> h){
        Element<T> cur,cur2;
        cur=h;
        cur2=h.next();
        
        //if either cur2 or cur2.next() is null ,it is acyclic
        while(cur2 != null && cur2.next()!=null ){
            if(cur.equals(cur2) || cur.equals(cur2.next())){
                return true;
            }
            cur=cur.next();
            cur2=cur2.next().next();
        }
        return false;
    }
}
