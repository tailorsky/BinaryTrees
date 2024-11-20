package com.trees.AVLTree;

public class Node {
    int key;
    String data;
    Node left_child;
    Node right_child;
    int height;

    public Node(int key, String data){
        this.key = key;
        this.data = data;
        this.height = 1;
    }

    public String getData(){
        return this.data;
    }
}
