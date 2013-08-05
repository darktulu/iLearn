package com.simu.ilearn.common.client.widget.search.ui;

import com.simu.ilearn.common.client.widget.search.type.ListSearch;
import com.simu.ilearn.common.client.widget.search.type.TextSearch;

public interface SearchEditorFactory {
    TextSearchEditor create(TextSearch textSearch);

    ListSearchEditor create(ListSearch listSearch);
}
