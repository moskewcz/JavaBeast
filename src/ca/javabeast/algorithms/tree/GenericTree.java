/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.javabeast.algorithms.tree;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class GenericTree {

    abstract class Node {

        private Node[] children;

        public Node(Node[] children) {
            this.children = children;
        }

        public int getNumChildren() {
            return children.length;
        }

        public Node getChild(int index) {
            return children[index];
        }
    }

    class IntNode extends Node {

        private int value;

        public IntNode(Node[] children, int value) {

            super(children);
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    private IntNode root;

    public GenericTree(IntNode root) {
        this.root = root;
    }

    public IntNode getRoot() {
        return root;
    }

    public void setRoot(IntNode root) {
        this.root = root;
    }
    
}
