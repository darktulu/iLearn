package com.simu.ilearn.app.client.web.welcome.login;

import com.google.common.base.Strings;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.common.client.util.EditorView;
import com.simu.ilearn.common.shared.dto.UserCredentials;
import com.simu.ilearn.app.client.resource.message.MessageBundle;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView,
        EditorView<UserCredentials> {
    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<UserCredentials, LoginView> {
    }

    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;
    @UiField
    @Ignore
    Label loginError;

    private final MessageBundle messageBundle;
    private final Driver driver;

    @Inject
    public LoginView(final Binder uiBinder, final MessageBundle messageBundle,
                     final Driver driver) {
        this.messageBundle = messageBundle;
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(UserCredentials credentials) {
        username.setFocus(true);
        driver.edit(credentials);
        loginError.setVisible(false);
    }

    @Override
    public UserCredentials get() {
        UserCredentials credentials = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return credentials;
        }
    }

    @Override
    public void displayLoginError(Boolean visible) {
        loginError.setVisible(visible);
        loginError.setText(messageBundle.wrongLoginOrPassword());
    }

    @UiHandler("login")
    void onLoginClicked(ClickEvent event) {
        processLoginAction();
    }

    @UiHandler("password")
    void onPasswordKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            processLoginAction();
        }
    }

    @UiHandler("register")
    void onRegisterClicked(ClickEvent event) {
        getUiHandlers().bounceToRegister();
    }

    private void processLoginAction() {
        UserCredentials credentials = get();

        if (!Strings.isNullOrEmpty(credentials.getUsername()) && !Strings.isNullOrEmpty(credentials.getPassword())) {
            getUiHandlers().login(credentials);
            loginError.setVisible(false);
        } else {
            loginError.setVisible(true);
            loginError.setText(messageBundle.loginPasswordRequired());
        }
    }
}
