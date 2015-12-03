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
public class Element<O> {

    private O value;
    private Element next;

    public Element(O v,Element<O> n) {
        this.value = v;
        this.next = n;
    }

    public void setValue(O value) {
        this.value = value;
    }

    public void setNext(Element<O> next) {
        this.next = next;
    }

    public O value() {
        return value;
    }

    public Element<O> next() {
        return next;
    }
}