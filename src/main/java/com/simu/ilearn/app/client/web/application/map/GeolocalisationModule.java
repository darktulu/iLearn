package com.simu.ilearn.app.client.web.application.map;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.simu.ilearn.app.client.web.application.learn.LearnPresenter;
import com.simu.ilearn.app.client.web.application.learn.LearnUiHandlers;
import com.simu.ilearn.app.client.web.application.map.GeolocalisationPresenter.MyProxy;
import com.simu.ilearn.app.client.web.application.map.GeolocalisationPresenter.MyView;

public class GeolocalisationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(GeolocalisationUiHandlers.class).to(GeolocalisationPresenter.class);

        bindPresenter(GeolocalisationPresenter.class, MyView.class, GeolocalisationView.class, MyProxy.class);
    }
}
