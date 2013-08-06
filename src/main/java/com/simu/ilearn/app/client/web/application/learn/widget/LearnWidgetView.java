package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.common.shared.vo.LearnVO;

public class LearnWidgetView extends ViewWithUiHandlers<LearnWidgetUiHandlers> implements LearnWidgetPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LearnWidgetView> {
    }

    @UiField
    Label title;
    @UiField
    Label summary;
    @UiField
    Label created;

    @Inject
    public LearnWidgetView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setData(LearnVO article) {
        DateTimeFormatRenderer dateRenderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat("dd-MM-yyyy ' à ' HH:mm"));
        title.setText(article.getTitle());
        summary.setText(article.getContent());
        created.setText(dateRenderer.render(article.getCreated()));
    }

    @UiHandler("title")
    void showDetail(ClickEvent event) {
        getUiHandlers().showDetail();
    }
}
