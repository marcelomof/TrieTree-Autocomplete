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
        if(word == null) {
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
    public void autocomplete(String prefix) {
        if(prefix == null) {
            return;
        }

        TrieNode occurrence = find(prefix);
        if(occurrence == null) {
            return;
        }

        if(occurrence.getIsWord()) {
            System.out.println(prefix);
        }

        preOrderCompletions(prefix, occurrence.getChildren().getRoot());
    }

    private void preOrderCompletions(String prefix, AVLNode<TrieNode> node) {
        String next = prefix + node.getValue().getValue();

        if(node.getValue().getIsWord()) {
            System.out.println(next);
        }

        if(node.getValue().getChildren().getRoot() != null) {
            preOrderCompletions(next, node.getValue().getChildren().getRoot());
        }

        if (node.getLeft() != null) {
            preOrderCompletions(prefix, node.getLeft());
        }

        if (node.getRight() != null) {
            preOrderCompletions(prefix, node.getRight());
        }
    }

    // Remove
}
