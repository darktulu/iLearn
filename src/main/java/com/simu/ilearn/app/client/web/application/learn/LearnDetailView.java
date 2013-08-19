package com.simu.ilearn.app.client.web.application.learn;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class LearnDetailView extends ViewWithUiHandlers<LearnDetailUiHandlers> implements LearnDetailPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LearnDetailView> {
    }

    @UiField
    TextBox title;
    @UiField
    TextArea content;

    @Inject
    public LearnDetailView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setData(LearnVO data) {
        title.setText(data.getTitle());
        content.setText(data.getContent());
    }

    @UiHandler("goBack")
    public void onBackClicked(ClickEvent event) {
        getUiHandlers().goBackHome();
    }
}
