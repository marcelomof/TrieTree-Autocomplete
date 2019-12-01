package br.ufrn.imd.marcelo;

public class TrieTree {
    private TrieNode root;

    public TrieTree() {
        this.root = new TrieNode(null);
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        insert(word, root);
    }

    // Insert
    private void insert(String word, TrieNode node) {
        if(word == null || node == null) {
            return;
        }

        TrieNode next;
        if(node.getChildren().getRoot() == null) {
            next = new TrieNode(word.charAt(0));
            node.getChildren().insert(next);
        }
        else if(node.getChildren().search(word.charAt(0)) == null) {
            next = new TrieNode(word.charAt(0));
            node.getChildren().insert(next);
        }
        else {
            next = node.getChildren().search(word.charAt(0)).getValue();
        }

        if(word.length() > 1) {
            insert(word.substring(1), next);
        }
        else {
            next.setIsWord(true);
        }
    }

    // Search
    // SearchByPrefix
    // Remove
}
