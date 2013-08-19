package com.simu.ilearn.app.client.web.application.learn;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.web.application.ApplicationPresenter;
import com.simu.ilearn.app.client.web.application.learn.event.LearnChangedEvent;
import com.simu.ilearn.app.client.web.application.learn.widget.AddLearnPresenter;
import com.simu.ilearn.app.client.web.application.learn.widget.LearnWidgetFactory;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.security.LoggedInGatekeeper;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class LearnPresenter extends Presenter<LearnPresenter.MyView, LearnPresenter.MyProxy>
        implements LearnUiHandlers, LearnChangedEvent.LearnChangedHandler {
    public interface MyView extends View, HasUiHandlers<LearnUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<LearnPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private final LearnWidgetFactory learnWidgetFactory;

    public static final Object LEARN_LIST_SLOT = new Object();
    public static final Object LEARN_ADD_SLOT = new Object();

    @Inject
    public LearnPresenter(EventBus eventBus,
                          MyView view,
                          MyProxy proxy,
                          DispatchAsync dispatcher,
                          LearnService learnService,
                          LearnWidgetFactory learnWidgetFactory,
                          AddLearnPresenter addLearnPresenter) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.learnService = learnService;
        this.learnWidgetFactory = learnWidgetFactory;

        getView().setUiHandlers(this);

        setInSlot(LEARN_ADD_SLOT, addLearnPresenter);
    }


    @Override
    public void onLearnChanged(LearnChangedEvent event) {
        if (event.getMyType() == LearnChangedEvent.MyType.ADD) {
            loadLearn(event.getLearn().getId());
        } else {
            loadEntities();
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(LearnChangedEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
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

    private void loadLearn(Long learnId) {
        dispatcher.execute(learnService.loadOne(learnId), new AsyncCallbackImpl<GetResult<LearnVO>>() {
            @Override
            public void onReceive(GetResult<LearnVO> response) {
                addToSlot(LEARN_LIST_SLOT, learnWidgetFactory.create(response.getPayload()));
            }
        });
    }
}
