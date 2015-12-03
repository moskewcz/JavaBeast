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
public class LinkedList<O> {

    private Element<O> head;

    public LinkedList(Element head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addFirst(O value) {
        Element<O> e = new Element<>(value, head);
        this.head = e;
    }

    public void addLast(O value) {
        Element<O> e = new Element<>(value, null);
        if (this.isEmpty()) {
            this.head = e;
        } else {
            Element<O> n = this.head.next();
            while (n.next() != null) {
                n = n.next();
                n.setNext(e);
            }
        }
    }

    public O removeFirst() {
        Element<O> h = this.head;
        if (!this.isEmpty()) {
            this.head = this.head.next();
        }
        h.setNext(null);
        return h.value();
    }

    public O removeLast() {
        Element<O> e = this.head;
        if (isEmpty()) {
        } else if (e.next() == null) {
            this.head = null;
        } else {
            while (e.next().next() != null) {
                e = e.next();
                Element<O> l = e.next();
                e.setNext(null);
                e = l;
            }
        }
        return e == null ? null : e.value();
    }

    public Element<O> find(O value) {
        Element<O> e = this.head;
        while (e != null && e.value() != value) {
            e = e.next();
        }
        return e;
    }

}
