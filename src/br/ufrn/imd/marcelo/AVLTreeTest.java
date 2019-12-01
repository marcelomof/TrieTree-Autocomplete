package br.ufrn.imd.marcelo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    public void NosSaoInseridosEBalanceadosNaAVL() {
        AVLTree<IndexableInt> tree = new AVLTree<>();

        tree.insert(new IndexableInt(1));
        tree.insert(new IndexableInt(2));
        tree.insert(new IndexableInt(3));

        Assert.assertEquals(tree.getRoot().getValue().getKey(), 2);
        Assert.assertEquals(tree.getRoot().getLeft().getValue().getKey(), 1);
        Assert.assertEquals(tree.getRoot().getRight().getValue().getKey(), 3);
    }

    @Test
    public void NosSaoEncontradosComMetodoSearch() {
        AVLTree<IndexableInt> tree = new AVLTree<>();

        tree.insert(new IndexableInt(1));
        tree.insert(new IndexableInt(2));

        Assert.assertEquals(tree.search(1).getValue().getKey(), 1);
        Assert.assertEquals(tree.search(2).getValue().getKey(), 2);
        Assert.assertNull(tree.search(3));
    }
}