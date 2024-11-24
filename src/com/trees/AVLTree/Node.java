package com.trees.AVLTree;

public class Node {
    int key;
    Node left_child;
    Node right_child;
    int height;

    public Node(int key){
        this.key = key;
        this.height = 1;
    }

    public int getHeight(){
        return this.height;
    }
}
