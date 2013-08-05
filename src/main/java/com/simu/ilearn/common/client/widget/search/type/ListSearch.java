package com.simu.ilearn.common.client.widget.search.type;

import com.simu.ilearn.common.client.widget.search.vo.ListItem;

public class ListSearch extends SearchType<ListItem> {
    public ListSearch(String label, String key) {
        super(label, key);
    }

    public ListSearch(String label, String key, ListItem value) {
        super(label, key, value);
    }

    @Override
    public String tokenRepresentation() {
        return getValue().getLabel();
    }

    @Override
    public Boolean isEmpty() {
        return getValue() == null;
    }
}
