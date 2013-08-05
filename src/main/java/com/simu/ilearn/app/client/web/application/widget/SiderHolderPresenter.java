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

package com.simu.ilearn.app.client.web.application.widget;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;

public class SiderHolderPresenter extends PresenterWidget<SiderHolderPresenter.MyView> {
    public interface MyView extends View {
        void setSelectedMenu(String currentToken);
    }

    private final PlaceManager placeManager;

    @Inject
    SiderHolderPresenter(EventBus eventBus,
                         MyView view,
                         PlaceManager placeManager) {
        super(eventBus, view);

        this.placeManager = placeManager;
    }

    @Override
    protected void onReveal() {
        getView().setSelectedMenu(placeManager.getCurrentPlaceRequest().getNameToken());
    }
}
