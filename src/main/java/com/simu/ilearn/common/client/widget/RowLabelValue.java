package com.simu.ilearn.common.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;

public class RowLabelValue extends Composite {
    public interface Binder extends UiBinder<Widget, RowLabelValue> {
    }

    @UiField
    Label label;
    @UiField
    HTML value;

    @Inject
    RowLabelValue(Binder uiBinder,
                  @Assisted String labelTxt,
                  @Assisted SafeHtml valueTxt) {
        initWidget(uiBinder.createAndBindUi(this));

        label.setText(labelTxt);
        value.setHTML(valueTxt);
    }
}
