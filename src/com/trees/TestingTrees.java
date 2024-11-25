package com.trees;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.trees.AVLTree.BalancedTreeAVL;
import com.trees.RedBlackTrees.RedBlackTree;
import com.trees.BSTree.BinaryTree;

public class TestingTrees {
    public static void main(String[] args) {
        int step = 1000;
        int maxKeys = 20000;
        String filePath_AVL = "tree_heights_AVL.csv";
        String filePath_RB = "tree_heights_RB.csv";

        String filePath_BS = "tree_heights_BS.csv";


        ArrayList<Integer> keys = generateKeys(maxKeys);
        ArrayList<Integer> randKeys = generateRandomKeys(maxKeys);

        BalancedTreeAVL avlTree = new BalancedTreeAVL();
        processTree(avlTree, keys, step, maxKeys, filePath_AVL, "AVL");

        RedBlackTree rbTree = new RedBlackTree();
        processTree(rbTree, keys, step, maxKeys, filePath_RB, "RedBlack");

        BinaryTree bsTree = new BinaryTree();
        processTree(bsTree, randKeys, step, maxKeys, filePath_BS, "Binary");
    }

    public static ArrayList<Integer> generateKeys(int maxKeys) {
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 1; i <= maxKeys; i++) {
            keys.add(i);
        }
        return keys;
    }

    public static ArrayList<Integer> generateRandomKeys(int maxKeys) {
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 1; i <= maxKeys; i++) {
            keys.add(i);
        }
        Collections.shuffle(keys, new Random());
        return keys;
    }

    public static void processTree(Object tree, ArrayList<Integer> keys, int step, int maxKeys, String filePath, String treeType) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Keys," + treeType + "Height\n");

            for (int numKeys = step; numKeys <= maxKeys; numKeys += step) {
                for (int i = (numKeys - step); i < numKeys; i++) {
                    if (tree instanceof BalancedTreeAVL) {
                        ((BalancedTreeAVL) tree).insert(keys.get(i));
                    } else if (tree instanceof RedBlackTree) {
                        ((RedBlackTree) tree).insert(keys.get(i));
                    }
                    else if (tree instanceof BinaryTree) {
                        ((BinaryTree) tree).insertion(keys.get(i));
                    }
                }

                int height;
                if (tree instanceof BalancedTreeAVL) {
                    height = ((BalancedTreeAVL) tree).root.getHeight();
                } else if (tree instanceof RedBlackTree) {
                    height = ((RedBlackTree) tree).getHeight(((RedBlackTree) tree).root);
                } else if (tree instanceof BinaryTree) {
                    height = ((BinaryTree) tree).getHeight(((BinaryTree) tree).root);
                } else {
                    throw new IllegalArgumentException("Unknown tree type");
                }

                writer.write(numKeys + "," + height + "\n");
                System.out.println("Keys: " + numKeys + ", " + treeType + " Height: " + height);
            }

            System.out.println("Данные для " + treeType + " записаны в файл: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
