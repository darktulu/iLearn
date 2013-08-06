package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.inject.Inject;

public class LearnWidgetPresenter extends PresenterWidget<LearnWidgetPresenter.MyView> implements LearnWidgetUiHandlers {
    public interface MyView extends View, HasUiHandlers<LearnWidgetUiHandlers> {
        void setData(LearnVO article);
    }

    private final PlaceManager placeManager;
    private LearnVO article;

    @Inject
    public LearnWidgetPresenter(final EventBus eventBus, final MyView view,
                                final PlaceManager placeManager,
                                @Assisted LearnVO article) {
        super(eventBus, view);

        this.article = article;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void showDetail() {
        PlaceRequest place = new PlaceRequest.Builder()
                .nameToken(NameTokens.getDetail())
                .with("id", article.getId().toString())
                .build();
        placeManager.revealPlace(place);
    }

    @Override
    protected void onReveal() {
        getView().setData(article);
    }
}
