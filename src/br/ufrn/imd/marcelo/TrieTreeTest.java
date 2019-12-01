package br.ufrn.imd.marcelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTreeTest {

    @Test
    public void PalavraEhInserida() {
        TrieTree tree = new TrieTree();

        tree.insert("java");

        TrieNode nodeJ = tree.getRoot().getChildren().getRoot().getValue();
        TrieNode nodeA1 = nodeJ.getChildren().getRoot().getValue();
        TrieNode nodeV = nodeA1.getChildren().getRoot().getValue();
        TrieNode nodeA2 = nodeV.getChildren().getRoot().getValue();

        Assert.assertEquals(nodeJ.getValue().charValue(), 'j');
        Assert.assertEquals(nodeA1.getValue().charValue(), 'a');
        Assert.assertEquals(nodeV.getValue().charValue(), 'v');
        Assert.assertEquals(nodeA2.getValue().charValue(), 'a');
    }

    @Test
    public void PalavraPrefixoDeOutra() {
        TrieTree tree = new TrieTree();

        tree.insert("java");
        tree.insert("ja");

        TrieNode nodeJ = tree.getRoot().getChildren().getRoot().getValue();
        TrieNode nodeA1 = nodeJ.getChildren().getRoot().getValue();
        TrieNode nodeV = nodeA1.getChildren().getRoot().getValue();
        TrieNode nodeA2 = nodeV.getChildren().getRoot().getValue();

        Assert.assertFalse(nodeJ.getIsWord());
        Assert.assertFalse(nodeV.getIsWord());

        Assert.assertTrue(nodeA1.getIsWord());
        Assert.assertTrue(nodeA2.getIsWord());
    }
}