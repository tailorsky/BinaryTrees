package com.trees.AVLTree;

import java.util.LinkedList;
import java.util.Queue;

public class BalancedTreeAVL {
    public Node root;

    public BalancedTreeAVL(){
        this.root = null;
    }

    int getHeight(Node N){
        if(N == null){
            return 0;
        }
        return N.height;
    }

    public Node find(int key){
        Node walkingNode = root;
        while (true) {
            if (key == walkingNode.key) {
                return walkingNode;
            }
            if (key > walkingNode.key) {
                walkingNode = walkingNode.right_child;
            }
            if (key < walkingNode.key) {
                walkingNode = walkingNode.left_child;
            }
            if (walkingNode == null) { 
                return null;
            }
        }
    }

    Node rightRotation(Node y){
        Node x = y.left_child;
        Node T2 = x.right_child;

        x.right_child = y;
        y.left_child = T2;

        y.height = 1 + Math.max(getHeight(y.left_child), getHeight(y.right_child));
        x.height = 1 + Math.max(getHeight(x.left_child), getHeight(x.right_child));

        return x;
    }

    Node leftRotation(Node y){
        Node x = y.right_child;
        Node T2 = x.left_child;
        
        x.left_child = y;
        y.right_child = T2;

        y.height = 1 + Math.max(getHeight(y.left_child), getHeight(y.right_child));
        x.height = 1 + Math.max(getHeight(x.left_child), getHeight(x.right_child));

        return x;
    }

    int getBalance(Node temp){
        if (temp == null) return 0;
        return getHeight(temp.left_child) - getHeight(temp.right_child);
    }

    public void insert(int key){
        root = insertRecursive(root, key);
    }

    public Node insertRecursive(Node node, int key){
        if (node == null){
            return new Node(key);
        }

        if (key < node.key){
            node.left_child = insertRecursive(node.left_child, key);
        }
        else if (key > node.key){
            node.right_child = insertRecursive(node.right_child, key);
        }
        else 
            return node;

        node.height = 1 + Math.max(getHeight(node.left_child), getHeight(node.right_child));
        
        int balance = getBalance(node);

        if (balance > 1 && key < node.left_child.key)
            return rightRotation(node);
        if (balance < -1 && key > node.right_child.key)
            return leftRotation(node);
        if (balance > 1 && key > node.left_child.key){
            node.left_child = leftRotation(node.left_child);
            return rightRotation(node);
        }
        if (balance < -1 && key < node.right_child.key){
            node.right_child = rightRotation(node.right_child);
            return leftRotation(node);
        }
        
        return node;
    }

    public void delete(int key){
        root = deleteRecursive(root, key);
    }

    Node deleteRecursive(Node root, int key){
        if (root == null){
            return root;
        }
        if (key < root.key){
            root.left_child = deleteRecursive(root.left_child, key);
        }
        else if (key > root.key){
            root.right_child = deleteRecursive(root.right_child, key);
        }
        else{
            if (root.right_child == null || root.left_child == null){
                Node temp = root.left_child != null ? root.left_child : root.right_child;
                if (temp == null){
                    temp = root;
                    root = null;
                    return root;
                }
                else{
                    root = temp;
                }
            }
            else {
                Node temp = findMin(root.right_child);
                root.key = temp.key;
                root.right_child = deleteRecursive(root.right_child, temp.key);
            }
        }

        if (root == null) return null;
        
        root.height = Math.max(getHeight(root.left_child), getHeight(root.right_child)) + 1;
        int balance = getBalance(root);


        if (balance > 1 && getBalance(root.left_child) >= 0)
            return rightRotation(root);
        if (balance < -1 && getBalance(root.left_child) < 0){
            root.left_child = leftRotation(root.left_child);
            return rightRotation(root);
        }
        if (balance > 1 && getBalance(root.right_child) <= 0)
            return leftRotation(root);
        if (balance < -1 && getBalance(root.right_child) <= 0){
            root.right_child = rightRotation(root.right_child);
            return leftRotation(root);
        }
        return root;
    }

    private Node findMin(Node node) {
        Node current = node;

        while (current.left_child != null)
            current = current.left_child;

        return current;
    }

    public void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            printTree(node.right_child, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.key + "(" + node.height+ ")");
            printTree(node.left_child, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

        public void wideTraversal(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.key);
            
            if (current.left_child != null) {
                queue.add(current.left_child);
            }
            if (current.right_child != null) {
                queue.add(current.right_child);
            }
        }
    }   
}
