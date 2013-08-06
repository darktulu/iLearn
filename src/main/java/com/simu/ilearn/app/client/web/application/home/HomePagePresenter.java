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

package com.simu.ilearn.app.client.web.application.home;

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.resource.message.MessageBundle;
import com.simu.ilearn.app.client.rest.MyEntityService;
import com.simu.ilearn.app.client.web.application.ApplicationPresenter;
import com.simu.ilearn.common.client.mvp.ValidatedView;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.rest.ValidatedAsyncCallbackImpl;
import com.simu.ilearn.common.client.widget.messages.Message;
import com.simu.ilearn.common.client.widget.messages.event.MessageEvent;
import com.simu.ilearn.common.shared.vo.MyEntityVO;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy>
        implements HomeUiHandlers {
    public interface MyView extends ValidatedView, HasUiHandlers<HomeUiHandlers> {
        void editUser(MyEntityVO myEntity);

        void setData(List<MyEntityVO> data);
    }

    @ProxyStandard
    @NameToken(NameTokens.learn)
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    private final DispatchAsync dispatcher;
    private final MyEntityService myEntityService;
    private final MessageBundle messageBundle;

    @Inject
    public HomePagePresenter(EventBus eventBus,
                             MyView view,
                             MyProxy proxy,
                             DispatchAsync dispatcher,
                             MyEntityService myEntityService,
                             MessageBundle messageBundle) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.myEntityService = myEntityService;
        this.messageBundle = messageBundle;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveEntity(MyEntityVO myEntity) {
        dispatcher.execute(myEntityService.create(myEntity), new ValidatedAsyncCallbackImpl<ValidatedResponse>() {
            @Override
            public void onValidationError(Map<String, String> errors) {
                getView().clearErrors();
                getView().showErrors(errors);
            }

            @Override
            public void onReceive(ValidatedResponse response) {
                getView().clearErrors();
                loadEntities();

                Message message = new Message.Builder(messageBundle.myEntitySaveSucess())
                        .style(AlertType.SUCCESS).build();
                MessageEvent.fire(this, message);
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().editUser(new MyEntityVO());
        loadEntities();
    }

    private void loadEntities() {
        dispatcher.execute(myEntityService.loadAll(), new AsyncCallbackImpl<GetResults<MyEntityVO>>() {
            @Override
            public void onReceive(GetResults<MyEntityVO> response) {
                getView().setData(response.getPayload());
            }
        });
    }
}
