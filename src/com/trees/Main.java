package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
       RedBlackTree tree = new RedBlackTree();
       tree.insert(10, "ABOBUS");
       tree.insert(5, "NEABOBUS");
       tree.insert(40, "NEABOBUS");

       tree.insert(80, "NEABOBUS");
       tree.insert(30, "NEABOBUS");
       tree.insert(100, "ABOBUS");
       tree.insert(54, "NEABOBUS");
       tree.insert(2, "NEABOBUS");

       tree.insert(1, "NEABOBUS");

       tree.delete(40);

       tree.printTree(tree.root, " ", false);
    }
}
