package com.simu.ilearn.app.client.web.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    SimplePanel headerDisplay;
    @UiField
    SimpleLayoutPanel menuDisplay;
    @UiField
    SimpleLayoutPanel mainDisplay;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (content != null) {
            if (slot == ApplicationPresenter.TYPE_SetMainContent) {
                mainDisplay.setWidget(content);
            } else if (slot == ApplicationPresenter.TYPE_SetHeaderContent) {
                headerDisplay.setWidget(content);
            } else if (slot == ApplicationPresenter.TYPE_SetMenuContent) {
                menuDisplay.setWidget(content);
            }
        }
    }
}
