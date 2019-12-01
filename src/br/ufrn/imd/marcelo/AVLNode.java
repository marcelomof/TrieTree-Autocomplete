package br.ufrn.imd.marcelo;

public class AVLNode<ValueType extends Indexable> {
    private ValueType value;
    private AVLNode<ValueType> left;
    private AVLNode<ValueType> right;

    public AVLNode(ValueType value) {
        setValue(value);
    }

    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }

    public AVLNode<ValueType> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<ValueType> left) {
        this.left = left;
    }

    public AVLNode<ValueType> getRight() {
        return right;
    }

    public void setRight(AVLNode<ValueType> right) {
        this.right = right;
    }

    public int getBalanceFactor() {
        int leftHeight = left == null ? 0 : 1 + getLeft().getHeight();
        int rightHeight = right == null ? 0 : 1 + getRight().getHeight();

        return leftHeight - rightHeight;
    }

    private int getHeight() {
        if (left == null && right == null) {
            return 0;
        }

        if (left == null) {
            return 1 + right.getHeight();
        }

        if (right == null) {
            return 1 + left.getHeight();
        }

        return 1 + Math.max(right.getHeight(), left.getHeight());
    }
}