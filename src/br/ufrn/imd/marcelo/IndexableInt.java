package br.ufrn.imd.marcelo;

public class IndexableInt implements Indexable{
    private Integer value;

    public IndexableInt() {
        this(0);
    }

    public IndexableInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getKey() {
        return value;
    }
}
