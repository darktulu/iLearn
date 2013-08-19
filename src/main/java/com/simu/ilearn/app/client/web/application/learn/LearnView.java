package com.simu.ilearn.app.client.web.application.learn;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

public class LearnView extends ViewWithUiHandlers<LearnUiHandlers> implements LearnPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LearnView> {
    }

    @UiField
    HTMLPanel tablePanel;
    @UiField
    HTMLPanel addPanel;

    @Inject
    public LearnView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void addToSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            if (content != null) {
                tablePanel.add(content);
            }
        } else {
            super.addToSlot(slot, content);
        }
    }

    @Override
    public void removeFromSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            if (content != null) {
                tablePanel.remove(content);
            }
        } else {
            super.removeFromSlot(slot, content);
        }
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            tablePanel.clear();
            if (content != null) {
                tablePanel.add(content);
            }
        } else if (slot == LearnPresenter.LEARN_ADD_SLOT) {
            addPanel.add(content);
        } else {
            super.setInSlot(slot, content);
        }
    }
}
