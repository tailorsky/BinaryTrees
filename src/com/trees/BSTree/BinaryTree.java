package com.trees.BSTree;

public class BinaryTree {
    public Node root;

    public BinaryTree(){
        root = null;
    }

    public void insertion(int key, String data){
        Node temp = new Node(key, data);
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
        nodeToDelete.data = successor.data;

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

    public void rightRotate(Node temp) {
        if (temp == null || temp.left_child == null) {
            System.out.println("Right rotation not possible");
            return;
        }
    
        Node newRoot = temp.left_child;
        Node parent = temp.parrent;

        temp.left_child = newRoot.right_child;
        if (newRoot.right_child != null) {
            newRoot.right_child.parrent = temp;
        }
    
        newRoot.right_child = temp;
        temp.parrent = newRoot;
    
        newRoot.parrent = parent;
        if (parent == null) {
            root = newRoot;
        } else if (parent.left_child == temp) {
            parent.left_child = newRoot;
        } else {
            parent.right_child = newRoot;
        }
    }
    

    public void printTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            printTree(node.right_child, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.key + " (" + node.data + ")");
            printTree(node.left_child, prefix + (isLeft ? "    " : "│   "), true);
        }
    }   
}
