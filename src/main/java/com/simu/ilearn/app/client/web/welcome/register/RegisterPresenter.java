package com.simu.ilearn.app.client.web.welcome.register;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.rest.RegistrationService;
import com.simu.ilearn.app.client.web.RootPresenter;
import com.simu.ilearn.common.client.mvp.ValidatedView;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.vo.UserVO;

public class RegisterPresenter extends Presenter<RegisterPresenter.MyView, RegisterPresenter.MyProxy>
        implements RegisterUiHandlers {
    public interface MyView extends ValidatedView, HasUiHandlers<RegisterUiHandlers> {
        void edit(UserVO user);
    }

    @ProxyStandard
    @NameToken(NameTokens.register)
    public interface MyProxy extends ProxyPlace<RegisterPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final RegistrationService registrationService;
    private final PlaceManager placeManager;

    @Inject
    public RegisterPresenter(EventBus eventBus,
                             MyView view,
                             MyProxy proxy,
                             DispatchAsync dispatcher,
                             RegistrationService registrationService,
                             PlaceManager placeManager) {
        super(eventBus, view, proxy, RootPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.registrationService = registrationService;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void bounceToLogin() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getLogin()).build();
        placeManager.revealPlace(place);
    }

    @Override
    public void register(UserVO user) {
        dispatcher.execute(registrationService.register(user), new AsyncCallbackImpl<GetResult<Long>>() {
            @Override
            public void onReceive(GetResult<Long> response) {
                getView().clearErrors();
                PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getLogin()).build();
                placeManager.revealPlace(place);
            }
        });
    }

    protected void onReveal() {
        getView().edit(new UserVO());
    }
}
