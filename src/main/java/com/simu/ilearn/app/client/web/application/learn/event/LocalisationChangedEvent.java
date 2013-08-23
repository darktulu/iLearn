package com.simu.ilearn.app.client.web.application.learn.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class LocalisationChangedEvent extends GwtEvent<LocalisationChangedEvent.LocalisationChangedHandler> {
    public interface LocalisationChangedHandler extends EventHandler {
        void onLocalisationClicked(LocalisationChangedEvent event);
    }

    private Boolean active;

    private static final Type<LocalisationChangedHandler> TYPE = new Type<LocalisationChangedHandler>();

    public LocalisationChangedEvent() {
    }

    public LocalisationChangedEvent(Boolean active) {
        this.active = active;
    }

    public static Type<LocalisationChangedHandler> getType() {
        return TYPE;
    }

    public Boolean getActive() {
        return active;
    }

    public static void fire(HasHandlers source, Boolean active) {
        source.fireEvent(new LocalisationChangedEvent(active));
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new LocalisationChangedEvent());
    }

    @Override
    public Type<LocalisationChangedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LocalisationChangedHandler handler) {
        handler.onLocalisationClicked(this);
    }
}
