package br.ufrn.imd.marcelo;

public class AVLTree<ValueType extends Indexable> {

    private AVLNode<ValueType> root = null;

    public AVLNode<ValueType> getRoot() {
        return root;
    }

    public void insert(ValueType value) {
        root = insert(new AVLNode<ValueType>(value), root);
    }

    public boolean isBalanced() {
        if (root == null) {
            return true;
        }

        return Math.abs(root.getBalanceFactor()) <= 1;
    }

    private AVLNode<ValueType> insert(AVLNode<ValueType> node, AVLNode<ValueType> parent) {
        if (parent == null) {
            return node;
        }

        if (node.getValue().getKey() == parent.getValue().getKey()) {
            parent.setValue(node.getValue());
            return parent;
        }

        if (node.getValue().getKey() < parent.getValue().getKey()) {
            parent.setLeft(insert(node, parent.getLeft()));
        } else {
            parent.setRight(insert(node, parent.getRight()));
        }

        return balance(parent);
    }

    private AVLNode<ValueType> balance(AVLNode<ValueType> parent) {
        if (parent.getBalanceFactor() >= -1 && parent.getBalanceFactor() <= 1) {
            return parent;
        }

        if (parent.getBalanceFactor() > 1) {
            //L
            if (parent.getLeft().getBalanceFactor() > 0) {
                //LL
                return rotateRight(parent);
            } else {
                //LR
                parent.setLeft(rotateLeft(parent.getLeft()));
                return rotateRight(parent);
            }
        } else {
            //R
            if (parent.getRight().getBalanceFactor() < 0) {
                //RR
                return rotateLeft(parent);
            } else {
                //RL
                parent.setRight(rotateRight(parent.getRight()));
                return rotateLeft(parent);
            }
        }
    }

    private AVLNode<ValueType> rotateRight(AVLNode<ValueType> parent) {
        AVLNode<ValueType> newRoot = parent.getLeft();
        parent.setLeft(newRoot.getRight());
        newRoot.setRight(parent);

        return newRoot;
    }

    private AVLNode<ValueType> rotateLeft(AVLNode<ValueType> parent) {
        AVLNode<ValueType> newRoot = parent.getRight();
        parent.setRight(newRoot.getLeft());
        newRoot.setLeft(parent);

        return newRoot;
    }

    public AVLNode<ValueType> search(int key) {
        return search(key, root);
    }

    private AVLNode<ValueType> search(int key, AVLNode<ValueType> node) {
        if (key == node.getValue().getKey()) {
            return node;
        }

        if (key < node.getValue().getKey()) {
            if (node.getLeft() != null) {
                return search(key, node.getLeft());
            }
        }

        if (key > node.getValue().getKey()) {
            if (node.getRight() != null) {
                return search(key, node.getRight());
            }
        }

        return null;
    }

}
