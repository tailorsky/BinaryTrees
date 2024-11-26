package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.BSTree.BinaryTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insertion(1);
        tree.insertion(2);
        tree.insertion(3);
        tree.insertion(4);
        tree.printTree(tree.root, " ", false);
    }
}
