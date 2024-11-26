package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.BSTree.BinaryTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
       tree.insert(20);
       tree.insert(30);
       tree.insert(5);
       tree.insert(15);
       tree.insert(69);
       tree.insert(25);
       tree.insert(10);

       tree.printTree(tree.root, " ", false);
       tree.depthTraversal(tree.root);
       //tree.wideTraversal(tree.root);
    }
}
