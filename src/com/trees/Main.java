package com.trees;

import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.BSTree.BinaryTree;
import com.trees.RedBlackTrees.Node;

public class Main {
    public static void main(String[] args) {
       BinaryTree tree = new BinaryTree();
       tree.insertion(20);
       tree.insertion(30);
       tree.insertion(10);
       tree.insertion(15);
       tree.insertion(69);

       tree.printTree(tree.root, " ", false);
       tree.breadthFirstTraversal(tree.root);
    }
}
