package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.AVLTree.BalancedTreeAVL;
import com.trees.BSTree.BinaryTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
        BalancedTreeAVL tree = new BalancedTreeAVL();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(8);
        tree.insert(9);
        tree.insert(46);
        tree.insert(98);
        tree.insert(97);
        tree.insert(96);
        tree.insert(95);

        tree.printTree(tree.root, "", false);

        tree.postorderTraversal(tree.root);
        System.out.println("\n");
        tree.inorderTraversal(tree.root);
        System.out.println("\n");
        tree.preorderTraversal(tree.root);
    }
}
