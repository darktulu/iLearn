package com.simu.ilearn.app.client.web.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.simu.ilearn.app.client.web.RootPresenter;
import com.simu.ilearn.app.client.web.application.widget.HeaderPresenter;
import com.simu.ilearn.app.client.web.application.widget.MenuPresenter;

import javax.inject.Inject;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
    public interface MyView extends View {
    }

    @ProxyStandard
    public interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
    public static final Object TYPE_SetHeaderContent = new Object();
    public static final Object TYPE_SetMenuContent = new Object();

    private final HeaderPresenter headerPresenter;
    private final MenuPresenter menuPresenter;

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         HeaderPresenter headerPresenter,
                         MenuPresenter menuPresenter) {
        super(eventBus, view, proxy, RootPresenter.TYPE_SetMainContent);

        this.headerPresenter = headerPresenter;
        this.menuPresenter = menuPresenter;
    }

    @Override
    protected void onReveal() {
        setInSlot(TYPE_SetHeaderContent, headerPresenter);
        setInSlot(TYPE_SetMenuContent, menuPresenter);
    }
}
