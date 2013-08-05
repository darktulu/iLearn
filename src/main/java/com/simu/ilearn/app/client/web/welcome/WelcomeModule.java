package com.simu.ilearn.app.client.web.welcome;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.simu.ilearn.app.client.web.welcome.login.LoginPresenter;
import com.simu.ilearn.app.client.web.welcome.login.LoginUiHandlers;
import com.simu.ilearn.app.client.web.welcome.login.LoginView;
import com.simu.ilearn.app.client.web.welcome.register.RegisterPresenter;
import com.simu.ilearn.app.client.web.welcome.register.RegisterUiHandlers;
import com.simu.ilearn.app.client.web.welcome.register.RegisterView;

public class WelcomeModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(LoginUiHandlers.class).to(LoginPresenter.class);
        bind(RegisterUiHandlers.class).to(RegisterPresenter.class);

        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);
        bindPresenter(RegisterPresenter.class, RegisterPresenter.MyView.class, RegisterView.class,
                RegisterPresenter.MyProxy.class);
    }
}
