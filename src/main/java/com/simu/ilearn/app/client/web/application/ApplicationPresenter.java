/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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
import com.simu.ilearn.app.client.web.application.widget.SiderHolderPresenter;

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
    public static final Object TYPE_SetSiderContent = new Object();

    private final HeaderPresenter headerPresenter;
    private final SiderHolderPresenter siderHolderPresenter;

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         HeaderPresenter headerPresenter,
                         SiderHolderPresenter siderHolderPresenter) {
        super(eventBus, view, proxy, RootPresenter.TYPE_SetMainContent);

        this.headerPresenter = headerPresenter;
        this.siderHolderPresenter = siderHolderPresenter;
    }

    @Override
    protected void onReveal() {
        setInSlot(TYPE_SetHeaderContent, headerPresenter);
        setInSlot(TYPE_SetSiderContent, siderHolderPresenter);
    }
}
