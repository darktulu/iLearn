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

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.resource.message.MessageBundle;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.web.application.ApplicationPresenter;
import com.simu.ilearn.app.client.web.application.learn.widget.LearnWidgetFactory;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.widget.messages.Message;
import com.simu.ilearn.common.client.widget.messages.event.MessageEvent;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;
import java.util.List;

public class LearnPresenter extends Presenter<LearnPresenter.MyView, LearnPresenter.MyProxy>
        implements LearnUiHandlers {
    public interface MyView extends View, HasUiHandlers<LearnUiHandlers> {
        void setData(List<LearnVO> data);

        void editLearn(LearnVO learn);
    }

    @ProxyStandard
    @NameToken(NameTokens.learn)
    public interface MyProxy extends ProxyPlace<LearnPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private final LearnWidgetFactory learnWidgetFactory;
    private final MessageBundle messageBundle;

    public static final Object LEARN_LIST_SLOT = new Object();

    @Inject
    public LearnPresenter(EventBus eventBus,
                          MyView view,
                          MyProxy proxy,
                          DispatchAsync dispatcher,
                          LearnService learnService,
                          LearnWidgetFactory learnWidgetFactory,
                          MessageBundle messageBundle) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.learnService = learnService;
        this.learnWidgetFactory = learnWidgetFactory;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveLearn(LearnVO learn) {
        dispatcher.execute(learnService.create(learn), new AsyncCallbackImpl<ValidatedResponse>() {

            @Override
            public void onReceive(ValidatedResponse response) {
                loadEntities();
                getView().editLearn(new LearnVO());

                Message message = new Message.Builder(messageBundle.myEntitySaveSucess())
                        .style(AlertType.SUCCESS).build();
                MessageEvent.fire(this, message);
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().editLearn(new LearnVO());
        loadEntities();
    }

    private void loadEntities() {
        dispatcher.execute(learnService.loadAll(), new AsyncCallbackImpl<GetResults<LearnVO>>() {
            @Override
            public void onReceive(GetResults<LearnVO> response) {
                clearSlot(LEARN_LIST_SLOT);

                for (LearnVO learn : response.getPayload()) {
                    addToSlot(LEARN_LIST_SLOT, learnWidgetFactory.create(learn));
                }
            }
        });
    }
}
