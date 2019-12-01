package br.ufrn.imd.marcelo;

public class TrieNode implements Indexable {
    private Character value;
    private boolean isWord;
    private AVLTree<TrieNode> children;

    public TrieNode(Character value) {
        this(value, false);
    }

    public TrieNode(Character value, boolean isWord) {
        this.value = value;
        this.isWord = isWord;
        children = new AVLTree<>();
    }

    public Character getValue() {
        return value;
    }

    public boolean getIsWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public AVLTree<TrieNode> getChildren() {
        return children;
    }

    @Override
    public int getKey() {
        if(value == null) {
            return 0;
        }

        return value;
    }
}
