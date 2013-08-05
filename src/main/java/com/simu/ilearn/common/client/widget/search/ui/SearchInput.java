package com.simu.ilearn.common.client.widget.search.ui;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.simu.ilearn.common.client.resource.style.TokenListStyle;
import com.simu.ilearn.common.client.widget.search.renderer.SearchTokenCellFactory;
import com.simu.ilearn.common.client.widget.search.type.SearchType;

import javax.inject.Inject;

import java.util.List;

import static com.google.gwt.query.client.GQuery.$;

public class SearchInput extends Composite {
    public interface Binder extends UiBinder<Widget, SearchInput> {
    }

    @UiField(provided = true)
    CellList<SearchType<?>> tokenList;
    @UiField
    TextBox searchToken;
    @UiField
    Button advancedSearch;

    private final ListDataProvider<SearchType<?>> dataProvider;

    @Inject
    SearchInput(Binder uiBinder,
                SearchTokenCellFactory searchTokenCellFactory,
                TokenListStyle tokenListStyle) {
        tokenList = new CellList<SearchType<?>>(searchTokenCellFactory.create(setupRemoveAction()), tokenListStyle);
        dataProvider = new ListDataProvider<SearchType<?>>();

        initWidget(uiBinder.createAndBindUi(this));

        dataProvider.addDataDisplay(tokenList);
    }

    public void setPlaceholder(String placeholder) {
        $(searchToken).attr("placeholder", placeholder);
    }

    public void showAdvancedSearch(Boolean visible) {
        advancedSearch.setVisible(visible);
    }

    public void setTokenList(List<SearchType<?>> tokens) {
        dataProvider.setList(tokens);
        tokenList.setRowCount(tokens.size());
        dataProvider.refresh();
    }

    public HandlerRegistration addAdvancedSearchHandler(ClickHandler handler) {
        return advancedSearch.addClickHandler(handler);
    }

    private Delegate<SearchType<?>> setupRemoveAction() {
        return new Delegate<SearchType<?>>() {
            @Override
            public void execute(SearchType<?> token) {
                dataProvider.getList().remove(token);
                dataProvider.refresh();
            }
        };
    }
}
