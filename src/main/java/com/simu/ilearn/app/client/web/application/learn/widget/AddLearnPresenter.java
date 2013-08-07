package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.web.application.learn.event.LearnChangedEvent;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class AddLearnPresenter extends PresenterWidget<AddLearnPresenter.MyView> implements AddLearnUiHandlers {
    public interface MyView extends View, HasUiHandlers<AddLearnUiHandlers> {
        void editLearn(LearnVO learn);
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;

    @Inject
    public AddLearnPresenter(EventBus eventBus, MyView view,
                             DispatchAsync dispatcher,
                             LearnService learnService) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.learnService = learnService;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveLearn(final LearnVO learn) {
        dispatcher.execute(learnService.create(learn), new AsyncCallbackImpl<ValidatedResponse>() {
            @Override
            public void onReceive(ValidatedResponse response) {
                getView().editLearn(new LearnVO());
                LearnChangedEvent.fire(this, learn);
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().editLearn(new LearnVO());
    }
}
