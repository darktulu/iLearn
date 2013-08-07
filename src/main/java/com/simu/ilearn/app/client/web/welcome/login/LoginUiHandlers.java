package com.simu.ilearn.app.client.web.welcome.login;

import com.gwtplatform.mvp.client.UiHandlers;
import com.simu.ilearn.common.shared.dto.UserCredentials;

public interface LoginUiHandlers extends UiHandlers {
    void login(UserCredentials credentials);

    void bounceToRegister();
}
