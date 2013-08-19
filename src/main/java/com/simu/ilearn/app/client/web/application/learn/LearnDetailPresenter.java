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

package com.simu.ilearn.app.client.web.application.learn;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.web.application.ApplicationPresenter;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.security.LoggedInGatekeeper;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class LearnDetailPresenter extends Presenter<LearnDetailPresenter.MyView, LearnDetailPresenter.MyProxy>
        implements LearnDetailUiHandlers {
    public interface MyView extends View, HasUiHandlers<LearnDetailUiHandlers> {
        void setData(LearnVO data);
    }

    @ProxyStandard
    @NameToken(NameTokens.detail)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<LearnDetailPresenter> {
    }

    private Long learnId;

    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private final PlaceManager placeManager;

    @Inject
    public LearnDetailPresenter(EventBus eventBus,
                                MyView view,
                                MyProxy proxy,
                                DispatchAsync dispatcher,
                                LearnService learnService,
                                PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.placeManager = placeManager;
        this.learnService = learnService;

        getView().setUiHandlers(this);
    }

    @Override
    public void goBackHome() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getHome()).build();
        placeManager.revealPlace(place);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        try {
            learnId = Long.parseLong(request.getParameter("id", ""));
        } catch (Exception e) {
            PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getHome()).build();
            placeManager.revealPlace(place);
        }
    }

    @Override
    protected void onReveal() {
        loadLearn(learnId);
    }

    private void loadLearn(Long learnId) {
        dispatcher.execute(learnService.loadOne(learnId), new AsyncCallbackImpl<GetResult<LearnVO>>() {
            @Override
            public void onReceive(GetResult<LearnVO> response) {
                getView().setData(response.getPayload());
            }
        });
    }
}
