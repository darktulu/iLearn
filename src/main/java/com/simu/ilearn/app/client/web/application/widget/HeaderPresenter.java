package com.simu.ilearn.app.client.web.application.widget;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.simu.ilearn.common.client.event.RequestEvent;
import com.simu.ilearn.common.client.widget.search.AdvancedSearchFactory;
import com.simu.ilearn.common.client.widget.search.AdvancedSearchPresenter;
import com.simu.ilearn.common.client.widget.search.event.FireSearchEvent;
import com.simu.ilearn.common.client.widget.search.type.SearchType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView>
        implements HeaderUiHandlers, NavigationHandler, FireSearchEvent.FireSearchHandler,
        RequestEvent.RequestEventHandler {
    public interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
        void setUsername(String username);

        void enableAdvancedSearch(Boolean enable);

        void showSearch(Boolean visible);

        void setSearchTokens(List<SearchType<?>> tokens);

        void showAjaxLoader();

        void hideAjaxLoader();
    }

    private final PlaceManager placeManager;

    private AdvancedSearchPresenter advancedSearchPresenter;
    private Map<String, PresenterWidget<?>> searchPresenters;
    private List<String> placeWithoutSearch;
    private List<SearchType<?>> currentTokens;
    private List<AsyncCallback<?>> pendingRequest;

    @Inject
    public HeaderPresenter(EventBus eventBus,
                           MyView view,
                           PlaceManager placeManager,
                           AdvancedSearchFactory advancedSearchFactory) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.searchPresenters = new HashMap<String, PresenterWidget<?>>();
        this.placeWithoutSearch = new ArrayList<String>();
        this.pendingRequest = new ArrayList<AsyncCallback<?>>();

        advancedSearchPresenter = advancedSearchFactory.create(searchPresenters);

        getView().setUiHandlers(this);
    }

    @Override
    public void onNavigation(NavigationEvent event) {
        containAdvancedSearch();
    }

    @Override
    public void advancedSearch() {
        addToPopupSlot(advancedSearchPresenter, false);
    }

    @Override
    public void onFireSearch(FireSearchEvent event) {
        currentTokens = new ArrayList<SearchType<?>>();
        for (SearchType<?> token : event.getTokens()) {
            if (!token.isEmpty()) {
                currentTokens.add(token);
            }
        }

        getView().setSearchTokens(currentTokens);
    }

    @Override
    public void onRequestEvent(RequestEvent requestEvent) {
        if (requestEvent.getState() == RequestEvent.State.SENT) {
            pendingRequest.add(requestEvent.getRequest());
            getView().showAjaxLoader();
        } else {
            pendingRequest.remove(requestEvent.getRequest());
            if (pendingRequest.isEmpty()) {
                getView().hideAjaxLoader();
            }
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(NavigationEvent.getType(), this);
        addRegisteredHandler(FireSearchEvent.getType(), this);
        addRegisteredHandler(RequestEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        containAdvancedSearch();
        getView().setUsername("imrabti");
    }

    private void containAdvancedSearch() {
        String currentPlace = placeManager.getCurrentPlaceRequest().getNameToken();

        if (placeWithoutSearch.contains(currentPlace)) {
            getView().showSearch(false);
        } else {
            getView().showSearch(true);
            getView().enableAdvancedSearch(searchPresenters.containsKey(currentPlace));
        }
    }
}
