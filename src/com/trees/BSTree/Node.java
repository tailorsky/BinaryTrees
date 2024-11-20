package com.trees.BSTree;

public class Node {
    int key;
    String data;
    Node right_child;
    Node left_child;
    Node parrent;

    Node(int key, String data){
        this.key = key;
        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}
