package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.app.client.web.application.learn.ui.LearnEditor;
import com.simu.ilearn.common.shared.vo.LearnVO;

public class AddLearnView extends ViewWithUiHandlers<AddLearnUiHandlers> implements AddLearnPresenter.MyView {
    public interface Binder extends UiBinder<Widget, AddLearnView> {
    }

    @UiField(provided = true)
    LearnEditor learnEditor;

    @Inject
    public AddLearnView(final Binder uiBinder, LearnEditor learnEditor) {
        this.learnEditor = learnEditor;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void editLearn(LearnVO learn) {
        learnEditor.edit(learn);
    }

    @UiHandler("submit")
    public void onSaveClicked(ClickEvent event) {
        getUiHandlers().saveLearn(learnEditor.get());
    }
}
