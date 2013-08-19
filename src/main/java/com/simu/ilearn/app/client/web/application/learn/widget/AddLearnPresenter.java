package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.rest.TagService;
import com.simu.ilearn.app.client.web.application.learn.event.LearnChangedEvent;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.TagVO;

import javax.inject.Inject;
import java.util.List;

public class AddLearnPresenter extends PresenterWidget<AddLearnPresenter.MyView> implements AddLearnUiHandlers {
    public interface MyView extends View, HasUiHandlers<AddLearnUiHandlers> {
        void editLearn(LearnVO learn);

        void initSuggestionList(List<TagVO> suggestions);
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private final TagService tagService;

    @Inject
    public AddLearnPresenter(EventBus eventBus, MyView view,
                             DispatchAsync dispatcher,
                             LearnService learnService,
                             TagService tagService) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.learnService = learnService;
        this.tagService = tagService;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveLearn(final LearnVO learn) {
        dispatcher.execute(learnService.create(learn), new AsyncCallbackImpl<GetResult<Long>>() {
            @Override
            public void onReceive(GetResult<Long> response) {
                getView().editLearn(new LearnVO());
                learn.setId(response.getPayload());
                LearnChangedEvent.fire(this, learn, LearnChangedEvent.MyType.ADD);
            }
        });
    }

    @Override
    protected void onReveal() {
        initSuggestions();
        getView().editLearn(new LearnVO());
    }

    private void initSuggestions() {
        dispatcher.execute(tagService.loadAll(), new AsyncCallbackImpl<GetResults<TagVO>>() {
            @Override
            public void onReceive(GetResults<TagVO> response) {
                getView().initSuggestionList(response.getPayload());
            }
        });
    }
}
