package com.simu.ilearn.common.client.rest;

import com.google.common.base.Strings;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.simu.ilearn.common.client.event.RequestEvent;
import com.simu.ilearn.common.shared.dispatch.Response;

public abstract class AsyncCallbackImpl<T extends Response> implements AsyncCallback<T>, HasHandlers {
    @Inject
    protected static EventBus eventBus;

    public AsyncCallbackImpl() {
        RequestEvent.fire(this, RequestEvent.State.SENT, this);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }

    @Override
    public void onSuccess(T response) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);

        if (Strings.isNullOrEmpty(response.getExceptionMessage())) {
            onReceive(response);
        } else {
            Exception ex = new Exception(response.getExceptionMessage());
            onFailure(ex);
        }
    }

    @Override
    public void onFailure(Throwable exception) {
        RequestEvent.fire(this, RequestEvent.State.RECEIVED, this);

        Window.alert("ERROR : " + exception.getMessage());
    }

    public abstract void onReceive(T response);
}
