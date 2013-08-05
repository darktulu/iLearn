package com.simu.ilearn.common.client.widget.search.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface SearchEditor<T> extends IsWidget {
    T getValue();

    void setValue(T value);
}
