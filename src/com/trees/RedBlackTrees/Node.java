package com.trees.RedBlackTrees;

public class Node {
    public Node left_child, right_child, parent;
    public int key;
    public String data, color;

    public Node(int key, String data){
        this.key = key;
        this.data = data;
        this.color = "RED";
        this.parent = this.left_child = this.right_child = null;
    }

    void setRedColor(){
        this.color = "RED";
    }

    void setBlackColor(){
        this.color = "BLACK";
    }

    boolean isLeft(){
        return (this == parent.left_child);
    }

    public Node uncle(){
        if (parent == null || parent.parent == null) return null;

        if(this.isLeft()) return parent.parent.right_child;
        else return parent.parent.left_child;
    }
}
