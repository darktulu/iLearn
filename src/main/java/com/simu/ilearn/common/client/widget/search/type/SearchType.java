package com.simu.ilearn.common.client.widget.search.type;

public abstract class SearchType<T> {
    private String label;
    private String key;
    private T value;

    protected SearchType(String label, String key) {
        this.label = label;
        this.key = key;
    }

    protected SearchType(String label, String key, T value) {
        this.label = label;
        this.key = key;
        this.value = value;
    }

    public abstract String tokenRepresentation();

    public abstract Boolean isEmpty();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
