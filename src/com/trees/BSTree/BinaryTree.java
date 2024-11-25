package com.trees.BSTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public Node root;

    public BinaryTree(){
        root = null;
    }

    public void insertion(int key){
        Node temp = new Node(key);
        if(root == null){
            root = temp;
            root.parrent = null;
            return;
        }
        Node walkingNode = root;
        while (true) {
            if (key > walkingNode.key) {
                if (walkingNode.right_child == null) {
                    walkingNode.right_child = temp;
                    temp.parrent = walkingNode;
                    return;
                }
                walkingNode = walkingNode.right_child;
            }
            if (key < walkingNode.key) {
                if (walkingNode.left_child == null){
                    walkingNode.left_child = temp;
                    temp.parrent = walkingNode;
                    return;
                }
                walkingNode = walkingNode.left_child;
            }
        }
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

    public void delete(int key){
        Node nodeToDelete = find(key);
        if (nodeToDelete == null) return;

        //Случай для листьев
        if (nodeToDelete.left_child == null && nodeToDelete.right_child == null) {
            if (nodeToDelete == root) {
                root = null;
            } else {
                if (nodeToDelete == nodeToDelete.parrent.left_child) {
                    nodeToDelete.parrent.left_child = null;
                } else {
                    nodeToDelete.parrent.right_child = null;
                }
            }
            return;
        }
        //Случай для одного ребенка
        if (nodeToDelete.left_child == null || nodeToDelete.right_child == null) {
            Node child = (nodeToDelete.left_child != null) ? nodeToDelete.left_child : nodeToDelete.right_child;

            if (nodeToDelete == root) {
                root = child;
            } else {
                if (nodeToDelete == nodeToDelete.parrent.left_child) {
                    nodeToDelete.parrent.left_child = child;
                } else {
                    nodeToDelete.parrent.right_child = child;
                }
                child.parrent = nodeToDelete.parrent;
            }
            return;
        }
        
        //Случай для двух детей
        Node successor = findMin(nodeToDelete.right_child);

        nodeToDelete.key = successor.key;

        if (successor.parrent.left_child == successor) {
            successor.parrent.left_child = successor.right_child;
        } else {
            successor.parrent.right_child = successor.right_child;
        }

        if (successor.right_child != null) {
            successor.right_child.parrent = successor.parrent;
        }
    }

    private Node findMin(Node node) {
        while (node.left_child != null) {
            node = node.left_child;
        }
        return node;
    }
    
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left_child);
        int rightHeight = getHeight(node.right_child);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public int getTreeHeight() {
        return getHeight(root);
    }
    

    public void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            printTree(node.right_child, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.key);
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
