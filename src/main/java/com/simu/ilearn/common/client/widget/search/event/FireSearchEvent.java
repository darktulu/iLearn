package com.simu.ilearn.common.client.widget.search.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.simu.ilearn.common.client.widget.search.type.SearchType;

import java.util.List;

public class FireSearchEvent extends GwtEvent<FireSearchEvent.FireSearchHandler> {
    public interface FireSearchHandler extends EventHandler {
        void onFireSearch(FireSearchEvent event);
    }

    public static Type<FireSearchHandler> TYPE = new Type<FireSearchHandler>();

    private final String nameToken;
    private final List<SearchType<?>> tokens;

    public FireSearchEvent(String nameToken,
                           List<SearchType<?>> tokens) {
        this.nameToken = nameToken;
        this.tokens = tokens;
    }

    public static Type<FireSearchHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, String nameToken, List<SearchType<?>> tokens) {
        source.fireEvent(new FireSearchEvent(nameToken, tokens));
    }

    @Override
    public Type<FireSearchHandler> getAssociatedType() {
        return TYPE;
    }

    public String getNameToken() {
        return nameToken;
    }

    public List<SearchType<?>> getTokens() {
        return tokens;
    }

    @Override
    protected void dispatch(FireSearchHandler handler) {
        handler.onFireSearch(this);
    }
}
