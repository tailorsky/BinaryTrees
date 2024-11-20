package com.trees.RedBlackTrees;

public class RedBlackTree {
    public Node root;
    Node NIL;

    public RedBlackTree(){
        NIL = new Node(0, null);
        NIL.setBlackColor();
        NIL.left_child = NIL.right_child = NIL;
        root = NIL;
    }

    public void rightRotate(Node x){
        Node y = x.left_child;
        Node T2 = y.right_child;
        x.left_child = T2;

        if (T2 != NIL){
            T2.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == NIL){
            root = y;
        }
        else if (x == x.parent.left_child){
            x.parent.left_child = y;
        }
        else {
            x.parent.right_child = y;
        }
        y.right_child = x;
        x.parent = y;
    }

    void leftRotate(Node x){
        Node y = x.right_child;
        Node T2 = y.left_child;
        
        x.right_child = T2;

        if (T2 != NIL){
            T2.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == NIL)
            root = y;
        else if (x == x.parent.left_child)
            x.parent.left_child = y;
        else
            x.parent.right_child = y;
        y.left_child = x;
        x.parent = y;
    }

    void fixInsertion(Node nNode) {
        while (nNode != root && nNode.parent.color.equals("RED")) {
            Node parent = nNode.parent;
            Node grandparent = parent.parent;
            Node uncle = (grandparent != null) ? (parent.isLeft() ? grandparent.right_child : grandparent.left_child) : NIL;
    
            if (uncle != null && uncle.color.equals("RED")) {
                parent.setBlackColor();
                uncle.setBlackColor();
                grandparent.setRedColor();
                nNode = grandparent;
            } else {
                if (parent.isLeft()) {
                    if (nNode == parent.right_child) {
                        nNode = parent;
                        leftRotate(nNode);
                    }
                    parent.setBlackColor();
                    grandparent.setRedColor();
                    rightRotate(grandparent);
                } else {
                    if (nNode == parent.left_child) {
                        nNode = parent;
                        rightRotate(nNode);
                    }
                    parent.setBlackColor();
                    grandparent.setRedColor();
                    leftRotate(grandparent);
                }
            }
        }
        root.setBlackColor();
    }
    
    
    public void insert(int key, String data) {
        Node nNode = new Node(key, data);
        nNode.left_child = NIL;
        nNode.right_child = NIL;
        if (root == NIL) {
            root = nNode;
            nNode.setBlackColor();
            return;
        }
    
        Node walkingNode = root;
        while (true) {
            if (key > walkingNode.key) {
                if (walkingNode.right_child == NIL) {
                    walkingNode.right_child = nNode;
                    nNode.parent = walkingNode;
                    break;
                }
                walkingNode = walkingNode.right_child;
            } else if (key < walkingNode.key) {
                if (walkingNode.left_child == NIL) {
                    walkingNode.left_child = nNode;
                    nNode.parent = walkingNode;
                    break;
                }
                walkingNode = walkingNode.left_child;
            }
        }
        fixInsertion(nNode);
    }

    public Node find(int key){
        Node walkingNode = root;
        while (true) {
            if (key == walkingNode.key) {
                return walkingNode;
            }
            if (key > walkingNode.key) {
                walkingNode = walkingNode.right_child;
            }
            if (key < walkingNode.key) {
                walkingNode = walkingNode.left_child;
            }
            if (walkingNode == null) { 
                return null;
            }
        }
    }

    void fixDelete(Node nNode){
        while (nNode != root && nNode.color.equals("BLACK")) {
            if (nNode == nNode.parent.left_child) {
                Node sibling = nNode.parent.right_child;
    
                // Случай 1: Брат узла красный
                if (sibling.color.equals("RED")) {
                    sibling.setBlackColor();
                    nNode.parent.setRedColor();
                    leftRotate(nNode.parent);
                    sibling = nNode.parent.right_child;
                }
    
                // Случай 2: Брат узла чёрный и оба его ребёнка чёрные
                if (sibling.left_child.color.equals("BLACK") && sibling.right_child.color.equals("BLACK")) {
                    sibling.setRedColor();
                    nNode = nNode.parent; // Поднимаемся выше
                } else {
                    // Случай 3: Брат чёрный, левый ребёнок красный, правый чёрный
                    if (sibling.right_child.color.equals("BLACK")) {
                        sibling.left_child.setBlackColor();
                        sibling.setRedColor();
                        rightRotate(sibling);
                        sibling = nNode.parent.right_child;
                    }
    
                    // Случай 4: Брат чёрный и правый ребёнок красный
                    sibling.color = nNode.parent.color;
                    nNode.parent.setBlackColor();
                    sibling.right_child.setBlackColor();
                    leftRotate(nNode.parent);
                    nNode = root;
                }
            } else {
                // Аналогично для правого поддерева (симметрия)
                Node sibling = nNode.parent.left_child;
    
                if (sibling.color.equals("RED")) {
                    sibling.setBlackColor();
                    nNode.parent.setRedColor();
                    rightRotate(nNode.parent);
                    sibling = nNode.parent.left_child;
                }
    
                if (sibling.left_child.color.equals("BLACK") && sibling.right_child.color.equals("BLACK")) {
                    sibling.setRedColor();
                    nNode = nNode.parent;
                } else {
                    if (sibling.left_child.color.equals("BLACK")) {
                        sibling.right_child.setBlackColor();
                        sibling.setRedColor();
                        leftRotate(sibling);
                        sibling = nNode.parent.left_child;
                    }
    
                    sibling.color = nNode.parent.color;
                    nNode.parent.setBlackColor();
                    sibling.left_child.setBlackColor();
                    rightRotate(nNode.parent);
                    nNode = root;
                }
            }
        }
        nNode.setBlackColor();
    }

    public void delete(int key){
        Node nodeToDelete = find(key);
        if (nodeToDelete == NIL) return;

        if (nodeToDelete.left_child == NIL && nodeToDelete.right_child == NIL) {
            if (nodeToDelete == root) {
                root = null;
            } else {
                if (nodeToDelete == nodeToDelete.parent.left_child) {
                    nodeToDelete.parent.left_child = NIL;
                } else {
                    nodeToDelete.parent.right_child = NIL;
                }
            }
            return;
        }

        if (nodeToDelete.left_child == NIL || nodeToDelete.right_child == NIL) {
            Node child = (nodeToDelete.left_child != NIL) ? nodeToDelete.left_child : nodeToDelete.right_child;

            if (nodeToDelete == root) {
                root = child;
            } else {
                if (nodeToDelete == nodeToDelete.parent.left_child) {
                    nodeToDelete.parent.left_child = child;
                } else {
                    nodeToDelete.parent.right_child = child;
                }
                child.parent = nodeToDelete.parent;
            }
            return;
        }
        
        Node successor = findMin(nodeToDelete.right_child);

        nodeToDelete.key = successor.key;
        nodeToDelete.data = successor.data;

        if (successor.parent.left_child == successor) {
            successor.parent.left_child = successor.right_child;
        } else {
            successor.parent.right_child = successor.right_child;
        }

        if (successor.right_child != NIL) {
            successor.right_child.parent = successor.parent;
        }
    }

    private Node findMin(Node node) {
        while (node.left_child != NIL) {
            node = node.left_child;
        }
        return node;
    }

    public void printTree(Node node, String prefix, boolean isLeft) {
        if (node != NIL) {
            printTree(node.right_child, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.key + " (" + node.data + ")" + "(" + node.color.charAt(0)+ ")");
            printTree(node.left_child, prefix + (isLeft ? "    " : "│   "), true);
        }
    }   
}
