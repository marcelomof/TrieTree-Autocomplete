package br.ufrn.imd.marcelo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Vector;

import static org.junit.Assert.*;

public class TrieTreeTest {

    @Test
    public void InsercaoDePalavras() {
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
    public void InsercaoDePalavraPrefixaDeOutra() {
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

    @Test
    public void MetodoFindEncontraPalavraExistente() {
        TrieTree tree = new TrieTree();

        tree.insert("java");

        Assert.assertNotNull(tree.find("java"));
        Assert.assertNull(tree.find("javo"));
    }

    @Test
    public void AutocompletarRetornaListaComTodasAsPalavrasComPrefixo() {
        TrieTree tree = new TrieTree();

        tree.insert("java");
        tree.insert("jogo");
        tree.insert("ja");
        tree.insert("poo");

        Vector<String> words = tree.autocomplete("j");
        assertEquals(words.size(), 3);
        assertEquals(words.elementAt(0), "ja");
        assertEquals(words.elementAt(1), "java");
        assertEquals(words.elementAt(2), "jogo");
    }

    @Test
    public void AutocompletarRetornarListaComNPalavrasComPrefixo() {
        TrieTree tree = new TrieTree();

        tree.insert("java");
        tree.insert("jogo");
        tree.insert("ja");
        tree.insert("poo");

        Vector<String> words = tree.autocomplete("j", 2);
        assertEquals(words.size(), 2);
        assertEquals(words.elementAt(0), "ja");
        assertEquals(words.elementAt(1), "java");
    }
}