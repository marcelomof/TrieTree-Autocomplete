package br.ufrn.imd.marcelo;

import java.util.Vector;

public class TrieTree {
    private TrieNode root;

    public TrieTree() {
        this.root = new TrieNode(null);
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        insert(word, 0, root);
    }

    // Insert
    private void insert(String word, int sub, TrieNode node) {
        if(word == null) {
            return;
        }

        TrieNode next;
        if(node.getChildren().getRoot() == null) {
            next = new TrieNode(word.charAt(sub));
            node.getChildren().insert(next);
        }
        else if(node.getChildren().search(word.charAt(sub)) == null) {
            next = new TrieNode(word.charAt(sub));
            node.getChildren().insert(next);
        }
        else {
            next = node.getChildren().search(word.charAt(sub)).getValue();
        }

        if(sub < word.length() - 1) {
            insert(word, sub+1, next);
        }
        else {
            next.setIsWord(true);
            next.setWord(word);
        }
    }

    // Search
    public TrieNode find(String word) {
        return find(word, root);
    }

    private TrieNode find(String word, TrieNode node) {
        if(word == null) {
            return null;
        }

        TrieNode next;
        if(node.getChildren().getRoot() == null) {
            return null;
        }
        else if(node.getChildren().search(word.charAt(0)) == null) {
            return null;
        }
        else {
            next = node.getChildren().search(word.charAt(0)).getValue();
        }

        if(word.length() > 1) {
            return find(word.substring(1), next);
        }
        else {
            return next;
        }
    }

    // SearchByPrefix
    public Vector<String> autocomplete(String prefix) {
        if(prefix == null) {
            return null;
        }

        TrieNode occurrence = find(prefix);
        if(occurrence == null) {
            return null;
        }

        Vector<String> words = new Vector<>();
        inOrderCompletions(prefix, words, occurrence.getChildren().getRoot());

        if(words.isEmpty()) {
            return null;
        }
        return words;
    }

    private void inOrderCompletions(String prefix, Vector<String> words, AVLNode<TrieNode> node) {
        String next = prefix + node.getValue().getValue();

        if (node.getLeft() != null) {
            inOrderCompletions(prefix, words, node.getLeft());
        }

        if(node.getValue().getIsWord()) {
            // GAMBIARRA
            words.add(node.getValue().getWord());
        }

        if(node.getValue().getChildren().getRoot() != null) {
            inOrderCompletions(next, words, node.getValue().getChildren().getRoot());
        }

        if (node.getRight() != null) {
            inOrderCompletions(prefix, words, node.getRight());
        }
    }

    // Remove
}
