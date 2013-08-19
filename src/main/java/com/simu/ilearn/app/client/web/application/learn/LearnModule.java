package com.simu.ilearn.app.client.web.application.learn;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.simu.ilearn.app.client.web.application.learn.renderer.TagCellFactory;
import com.simu.ilearn.app.client.web.application.learn.renderer.TagViewFactory;
import com.simu.ilearn.app.client.web.application.learn.widget.*;

public class LearnModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(LearnUiHandlers.class).to(LearnPresenter.class);

        bindPresenter(LearnPresenter.class, LearnPresenter.MyView.class, LearnView.class, LearnPresenter.MyProxy.class);
        bindPresenter(LearnDetailPresenter.class, LearnDetailPresenter.MyView.class,
                LearnDetailView.class, LearnDetailPresenter.MyProxy.class);

        bindPresenterWidget(AddLearnPresenter.class, AddLearnPresenter.MyView.class, AddLearnView.class);

        bindSharedView(LearnWidgetPresenter.MyView.class, LearnWidgetView.class);

        install(new GinFactoryModuleBuilder().build(LearnWidgetFactory.class));
        install(new GinFactoryModuleBuilder().build(TagCellFactory.class));
        install(new GinFactoryModuleBuilder().build(TagViewFactory.class));
    }
}
