package com.simu.ilearn.app.client.web.application.map;

import com.google.gwt.maps.client.LoadApi;
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
import com.simu.ilearn.app.client.web.application.map.GeolocalisationPresenter.MyProxy;
import com.simu.ilearn.app.client.web.application.map.GeolocalisationPresenter.MyView;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.security.LoggedInGatekeeper;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.LocationVO;

import javax.inject.Inject;
import java.util.ArrayList;

public class GeolocalisationPresenter extends Presenter<MyView, MyProxy>
        implements GeolocalisationUiHandlers {
    public interface MyView extends View, HasUiHandlers<GeolocalisationUiHandlers> {
        void setPositions(LocationVO location);

        void drawMap();

        Boolean isLoaded();
    }

    @ProxyStandard
    @NameToken(NameTokens.maps)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<GeolocalisationPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final LearnService learnService;

    @Inject
    public GeolocalisationPresenter(EventBus eventBus,
                                    MyView view,
                                    MyProxy proxy,
                                    LearnService learnService,
                                    DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.learnService = learnService;

        getView().setUiHandlers(this);
    }

    @Override
    public void checkLocalisations() {
        dispatcher.execute(learnService.loadAll(), new AsyncCallbackImpl<GetResults<LearnVO>>() {
            @Override
            public void onReceive(GetResults<LearnVO> response) {
                for (LearnVO learn : response.getPayload()) {
                    if (learn.getLocation() != null) {
                        getView().setPositions(learn.getLocation());
                    }
                }
            }
        });
    }

    @Override
    protected void onReveal() {
        if (getView().isLoaded()) {
            loadMapApi();
        } else {
            checkLocalisations();
        }
    }


    private void loadMapApi() {
        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                getView().drawMap();
            }
        };

        LoadApi.go(onLoad, loadLibraries, true);
    }
}
