package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.web.application.learn.event.LearnChangedEvent;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.shared.dispatch.NoResult;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class LearnWidgetPresenter extends PresenterWidget<LearnWidgetPresenter.MyView> implements LearnWidgetUiHandlers {
    public interface MyView extends View, HasUiHandlers<LearnWidgetUiHandlers> {
        void setData(LearnVO article);
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private LearnVO learn;

    @Inject
    public LearnWidgetPresenter(EventBus eventBus, MyView view,
                                PlaceManager placeManager,
                                DispatchAsync dispatcher,
                                LearnService learnService,
                                @Assisted LearnVO learn) {
        super(eventBus, view);

        this.learn = learn;
        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.learnService = learnService;

        getView().setUiHandlers(this);
    }

    @Override
    public void showDetail() {
        PlaceRequest place = new PlaceRequest.Builder()
                .nameToken(NameTokens.getDetail())
                .with("id", learn.getId().toString())
                .build();
        placeManager.revealPlace(place);
    }

    @Override
    public void delete() {
        dispatcher.execute(learnService.delete(learn.getId()), new AsyncCallbackImpl<NoResult>() {
            @Override
            public void onReceive(NoResult response) {
                LearnChangedEvent.fire(this, learn, LearnChangedEvent.MyType.DELETE);
            }
        });
    }

    @Override
    public void archive() {
        dispatcher.execute(learnService.archive(learn.getId()), new AsyncCallbackImpl<NoResult>() {
            @Override
            public void onReceive(NoResult response) {
                LearnChangedEvent.fire(this, learn, LearnChangedEvent.MyType.ARCHIVED);
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().setData(learn);
    }
}
