package com.simu.ilearn.app.client.web.welcome.register;

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
import com.simu.ilearn.common.client.mvp.ValidatedViewWithUiHandlers;
import com.simu.ilearn.common.client.util.EditorView;
import com.simu.ilearn.common.client.widget.ValidationErrorPopup;
import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.app.client.resource.message.MessageBundle;

import static com.google.gwt.query.client.GQuery.$;

public class RegisterView extends ValidatedViewWithUiHandlers<RegisterUiHandlers>
        implements RegisterPresenter.MyView, EditorView<UserVO> {
    public interface Binder extends UiBinder<Widget, RegisterView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<UserVO, RegisterView> {
    }

    @UiField
    TextBox email;
    @UiField
    TextBox username;
    @UiField
    PasswordTextBox password;
    @UiField
    @Ignore
    Label registrationError;

    private final MessageBundle messageBundle;
    private final Driver driver;

    @Inject
    public RegisterView(final Binder uiBinder, final Driver driver,
                        final MessageBundle messageBundle,
                        final ValidationErrorPopup validationErrorPopup) {
        super(validationErrorPopup);

        this.messageBundle = messageBundle;
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        $(email).id("email");
        $(email).id("username");
        $(email).id("password");
    }

    @Override
    public void edit(UserVO user) {
        email.setFocus(true);
        driver.edit(user);
        registrationError.setVisible(false);
    }

    @Override
    public UserVO get() {
        UserVO user = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

    @UiHandler("register")
    void onRegisterClicked(ClickEvent event) {
        processRegisterAction();
    }

    @UiHandler("password")
    void onPasswordKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            processRegisterAction();
        }
    }

    private void processRegisterAction() {
        UserVO user = get();

        if (!Strings.isNullOrEmpty(user.getEmail()) && !Strings.isNullOrEmpty(user.getUsername()) &&
                !Strings.isNullOrEmpty(user.getPassword())) {
            getUiHandlers().register(user);
            registrationError.setVisible(false);
        } else {
            registrationError.setVisible(true);
            registrationError.setText(messageBundle.registerInfoMissing());
        }
    }
}
