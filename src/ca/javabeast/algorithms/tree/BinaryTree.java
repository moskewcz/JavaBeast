/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.javabeast.algorithms.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class BinaryTree {

    private Node root;

    private static class Stack {

        public Stack() {

        }

        public Node pop() {
            return null;

        }

        public void push(Node n) {

        }

        public int size() {
            return 0;
        }

    }

    class Node {

        private Node left;
        private Node right;
        private int value;

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node left() {
            return left;
        }

        public Node right() {
            return right;
        }

        public int value() {
            return value;
        }

        private void printValue() {
            System.out.print(value() + ",");
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node rotateRight() {
            Node newRoot = left;
            left = newRoot.right;
            newRoot.right = this;
            return newRoot;
        }

    }

    public abstract class ActorNode {

        private String name; 
        private Set<ActorNode> linkedActors;

        public ActorNode(String name) {
            this.name = name;
            linkedActors = new HashSet<ActorNode>();
        }

        public void linkCostar(ActorNode costar) {
            linkedActors.add(costar);
        }

        public Set<ActorNode> getLinkedActors() {
            return linkedActors;
        }
        
        
    }
    
    public class BaconNode extends ActorNode{

        private int baconNumber;
        public BaconNode(String name) {
            super(name);
            this.baconNumber=-1;
        }

        public int getBaconNumber() {
            return baconNumber;
        }

        public void setBaconNumber(int baconNumber) {
            this.baconNumber = baconNumber;
        }
        
        public void findShortestPath(){
            baconNumber=0;
            Queue<BaconNode> q = new LinkedList<>();
            BaconNode cur;
            q.add(this);
            while((cur=q.poll())!=null){
                for(ActorNode an : cur.getLinkedActors()){
                     BaconNode n = (BaconNode) an;
                     if(-1!=n.getBaconNumber()){
                         n.setBaconNumber(cur.getBaconNumber()+1);
                         q.add(n);
                     }
                }
            }
        }
        
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node root() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node findNode(int value) {
        Node cur = this.root;
        while (cur != null) {
            if (cur.value() == value) {
                break;
            } else if (cur.value() > value) {
                cur = cur.left();
            } else {
                cur = cur.right();
            }
        }

        return cur;
    }

    public static void preorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        root.printValue();
        preorderTraversal(root.left());
        preorderTraversal(root.right());
    }

    public static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left());
        root.printValue();
        inorderTraversal(root.right());
    }

    public static void postorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left());
        postorderTraversal(root.right());
        root.printValue();
    }

    public static int findHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(findHeight(root.left()), findHeight(root.right()));
    }

    //no recurision
    public static void preorderTraversal2(Node root) {
        Stack stack = new Stack();
        stack.push(root);
        while (stack.size() > 0) {
            Node curr = stack.pop();
            curr.printValue();
            Node n = curr.right();
            if (n != null) {
                stack.push(n);
            }
            n = curr.left();
            if (n != null) {
                stack.push(n);
            }
        }
    }

    public static Node findLowestCommonAncestor(Node root, int value1, int value2) {
        while (root != null) {
            int value = root.value();
            if (value > value1 && value > value2) {
                root = root.left();
            } else if (value < value1 && value < value2) {
                root = root.right();
            } else {
                return root;
            }
        }
        return null; // only if empty tree
    }

    public static Node heapifyBinaryTree(Node root) {
        int size = traverse(root, 0, null); // Count nodes
        Node[] nodeArray = new Node[size];
        traverse(root, 0, nodeArray); // Load nodes into array

        // Sort array of nodes based on their values, using Comparator object
        Arrays.sort(nodeArray, new Comparator<Node>() {
            @Override
            public int compare(Node m, Node n) {
                int mv = m.value(), nv = n.value();
                return (mv < nv ? -1 : (mv == nv ? 0 : 1));
            }
        });

        // Reassign children for each node
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;

            int right = left + 1;
            nodeArray[i].setLeft(left >= size ? null : nodeArray[left]);
            nodeArray[i].setRight(right >= size ? null : nodeArray[right]);
        }

        return nodeArray[0]; // Return new root node
    }

    public static int traverse(Node node, int count, Node[] arr) {
        if (node == null) {
            return count;
        }
        if (arr != null) {
            arr[count] = node;
        }
        count++;
        count = traverse(node.left(), count, arr);
        count = traverse(node.right(), count, arr);
        return count;
    }

}
