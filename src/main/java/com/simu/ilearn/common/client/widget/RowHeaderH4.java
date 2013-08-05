package com.simu.ilearn.common.client.widget;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;

public class RowHeaderH4 extends Composite {
    public interface Binder extends UiBinder<Widget, RowHeaderH4> {
    }

    @UiField
    HeadingElement label;

    @Inject
    RowHeaderH4(Binder uiBinder,
                @Assisted String labelTxt) {
        initWidget(uiBinder.createAndBindUi(this));

        label.setInnerText(labelTxt);
    }
}
