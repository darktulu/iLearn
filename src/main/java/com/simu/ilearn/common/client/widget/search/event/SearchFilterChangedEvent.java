package com.simu.ilearn.common.client.widget.search.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.simu.ilearn.common.client.widget.search.type.SearchType;

import java.util.List;

public class SearchFilterChangedEvent extends GwtEvent<SearchFilterChangedEvent.SearchFilterChangedHandler> {
    public interface SearchFilterChangedHandler extends EventHandler {
        void onSearchFilterChanged(SearchFilterChangedEvent event);
    }

    public static Type<SearchFilterChangedHandler> TYPE = new Type<SearchFilterChangedHandler>();

    private final List<SearchType<?>> tokens;

    public SearchFilterChangedEvent(List<SearchType<?>> tokens) {
        this.tokens = tokens;
    }

    public static Type<SearchFilterChangedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, List<SearchType<?>> tokens) {
        source.fireEvent(new SearchFilterChangedEvent(tokens));
    }

    @Override
    public Type<SearchFilterChangedHandler> getAssociatedType() {
        return TYPE;
    }

    public List<SearchType<?>> getTokens() {
        return tokens;
    }

    @Override
    protected void dispatch(SearchFilterChangedHandler handler) {
        handler.onSearchFilterChanged(this);
    }
}
