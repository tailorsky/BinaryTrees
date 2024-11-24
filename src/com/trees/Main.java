package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
       RedBlackTree tree = new RedBlackTree();
       tree.insert(2);
       tree.insert(3);
       tree.insert(4);

       tree.printTree(tree.root, " ", false);
    }
}
