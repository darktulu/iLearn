package com.simu.ilearn.common.client.widget.search.ui;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.Assisted;
import com.simu.ilearn.common.client.widget.search.type.TextSearch;

import javax.inject.Inject;

public class TextSearchEditor extends Composite implements SearchEditor<TextSearch> {
    interface Binder extends UiBinder<Widget, TextSearchEditor> {
    }

    @UiField
    Label label;
    @UiField
    TextBox value;

    private TextSearch textSearch;

    @Inject
    TextSearchEditor(Binder uiBinder,
                     @Assisted TextSearch textSearch) {
        this.textSearch = textSearch;

        initWidget(uiBinder.createAndBindUi(this));

        label.setText(textSearch.getLabel());
        value.setText(textSearch.getValue());
    }

    @Override
    public TextSearch getValue() {
        textSearch.setValue(value.getText());
        return textSearch;
    }

    @Override
    public void setValue(TextSearch value) {
        // TODO : set the value.
    }
}
