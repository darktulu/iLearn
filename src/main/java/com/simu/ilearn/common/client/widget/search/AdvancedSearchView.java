package com.simu.ilearn.common.client.widget.search;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;

import javax.inject.Inject;

import static com.google.gwt.query.client.GQuery.$;

public class AdvancedSearchView extends PopupViewWithUiHandlers<AdvancedSearchUiHandlers>
        implements AdvancedSearchPresenter.MyView {
    public interface Binder extends UiBinder<PopupPanel, AdvancedSearchView> {
    }

    @UiField
    PopupPanel popup;
    @UiField
    SimplePanel searchPanel;

    @Inject
    AdvancedSearchView(EventBus eventBus,
                       Binder uiBinder) {
        super(eventBus);

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (content != null) {
            if (slot == AdvancedSearchPresenter.TYPE_SetSearchContent) {
                searchPanel.setWidget(content);
            }
        }
    }

    @Override
    public void show() {
        Widget searchBox = $("#searchBox").widget();
        Integer left = searchBox.getAbsoluteLeft();
        Integer top = searchBox.getAbsoluteTop() + searchBox.getOffsetHeight() - 1;
        setPosition(left, top);
        super.show();
    }
}