package com.simu.ilearn.common.client.widget.search;

import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.simu.ilearn.common.client.widget.search.AdvancedSearchPresenter.MyView;
import com.simu.ilearn.common.client.widget.search.event.FireSearchEvent;

import javax.inject.Inject;
import java.util.Map;

public class AdvancedSearchPresenter extends PresenterWidget<MyView>
        implements AdvancedSearchUiHandlers, FireSearchEvent.FireSearchHandler {
    public interface MyView extends PopupView, HasUiHandlers<AdvancedSearchUiHandlers> {
    }

    public static final Object TYPE_SetSearchContent = new Object();

    private final PlaceManager placeManager;
    private final Map<String, PresenterWidget<?>> searchPresenters;

    @Inject
    AdvancedSearchPresenter(EventBus eventBus,
                            MyView view,
                            PlaceManager placeManager,
                            @Assisted Map<String, PresenterWidget<?>> searchPresenters) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.searchPresenters = searchPresenters;

        getView().setUiHandlers(this);
    }

    @Override
    public void onFireSearch(FireSearchEvent event) {
        getView().hide();
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(FireSearchEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        String currentNameToken = placeManager.getCurrentPlaceRequest().getNameToken();
        if (searchPresenters.containsKey(currentNameToken)) {
            setInSlot(TYPE_SetSearchContent, searchPresenters.get(currentNameToken));
        }
    }
}
