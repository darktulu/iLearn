package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.common.shared.constants.GlobalParameters;
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
    @UiField
    SimpleLayoutPanel panel;
    @UiField
    HTMLPanel toolbar;

    @Inject
    public LearnWidgetView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        panel.addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                toolbar.setVisible(false);
            }
        }, MouseOutEvent.getType());

        panel.addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                toolbar.setVisible(true);
            }
        }, MouseOverEvent.getType());
    }

    @Override
    public void setData(LearnVO article) {
        DateTimeFormat dateRenderer = DateTimeFormat.getFormat(GlobalParameters.DATE_TIME_FORMAT);
        title.setText(article.getTitle());
        summary.setText(article.getContent());
        created.setText(dateRenderer.format(article.getCreated()));
    }

    @UiHandler("title")
    void showDetail(ClickEvent event) {
        getUiHandlers().showDetail();
    }

    @UiHandler("delete")
    void onDeleteClicked(ClickEvent event) {
        getUiHandlers().delete();
    }

    @UiHandler("archive")
    void onArchiveClicked(ClickEvent event) {
        getUiHandlers().archive();
    }
}
