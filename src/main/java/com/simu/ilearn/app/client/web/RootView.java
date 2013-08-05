package com.simu.ilearn.app.client.web;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class RootView extends ViewImpl implements RootPresenter.MyView {
    public interface Binder extends UiBinder<Widget, RootView> {
    }

    @UiField
    SimpleLayoutPanel main;
    @UiField
    SimplePanel messageDisplay;

    @Inject
    RootView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == RootPresenter.TYPE_SetMainContent) {
            main.setWidget(content);
        } else if (slot == RootPresenter.TYPE_SetMessageContent) {
            messageDisplay.setWidget(content);
        }
    }
}
