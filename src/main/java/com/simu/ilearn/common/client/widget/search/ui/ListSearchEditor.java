package com.simu.ilearn.common.client.widget.search.ui;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.Assisted;
import com.simu.ilearn.common.client.widget.search.type.ListSearch;

import javax.inject.Inject;

public class ListSearchEditor extends Composite implements SearchEditor<ListSearch> {
    public interface Binder extends UiBinder<Widget, ListSearchEditor> {
    }

    @UiField
    Label label;
    @UiField
    ListBox value;

    private ListSearch listSearch;

    @Inject
    ListSearchEditor(Binder uiBinder,
                     @Assisted ListSearch listSearch) {
        this.listSearch = listSearch;

        initWidget(uiBinder.createAndBindUi(this));

        label.setText(listSearch.getLabel());
        // TODO : Find a way to set selected value...
    }

    @Override
    public ListSearch getValue() {
        // TODO : update the object with new data
        return listSearch;
    }

    @Override
    public void setValue(ListSearch value) {
        // TODO : set the value.
    }
}
