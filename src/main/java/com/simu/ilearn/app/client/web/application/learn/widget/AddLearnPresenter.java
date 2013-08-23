package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.gwt.core.client.Callback;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.simu.ilearn.app.client.rest.LearnService;
import com.simu.ilearn.app.client.rest.TagService;
import com.simu.ilearn.app.client.web.application.learn.event.LearnChangedEvent;
import com.simu.ilearn.app.client.web.application.learn.event.LocalisationChangedEvent;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.LocationVO;
import com.simu.ilearn.common.shared.vo.TagVO;

import javax.inject.Inject;
import java.util.List;

public class AddLearnPresenter extends PresenterWidget<AddLearnPresenter.MyView>
        implements AddLearnUiHandlers {
    public interface MyView extends View, HasUiHandlers<AddLearnUiHandlers> {
        void editLearn(LearnVO learn);

        void initSuggestionList(List<TagVO> suggestions);
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;
    private final TagService tagService;

    private LocationVO location;

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
        learn.setLocation(location);
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

    @Override
    public void setLocalisation(Boolean active) {
        if (active) {
            Geolocation.getIfSupported().getCurrentPosition(new Callback<Position, PositionError>() {
                @Override
                public void onFailure(PositionError reason) {
                    Window.alert("failed");
                }

                @Override
                public void onSuccess(Position result) {
                    location = new LocationVO();
                    location.setLatitude(result.getCoordinates().getLatitude());
                    location.setLongitude(result.getCoordinates().getLongitude());
                    location.setAccuracy(result.getCoordinates().getAccuracy());
                    location.setAltitude(result.getCoordinates().getAltitude());
                    location.setAltitudeAccuracy(result.getCoordinates().getAltitudeAccuracy());
                    location.setHeading(result.getCoordinates().getHeading());
                    location.setSpeed(result.getCoordinates().getSpeed());
                }
            });
        } else {
            location = null;
        }
    }
}
