package com.simu.ilearn.common.client.widget.search.type;

import com.google.common.base.Strings;

public class TextSearch extends SearchType<String> {
    public TextSearch(String label, String key) {
        super(label, key);
    }

    public TextSearch(String label, String key, String value) {
        super(label, key, value);
    }

    @Override
    public String tokenRepresentation() {
        return getValue();
    }

    @Override
    public Boolean isEmpty() {
        return Strings.isNullOrEmpty(getValue());
    }
}
