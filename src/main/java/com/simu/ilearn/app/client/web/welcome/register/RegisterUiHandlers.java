package com.simu.ilearn.app.client.web.welcome.register;

import com.gwtplatform.mvp.client.UiHandlers;
import com.simu.ilearn.common.shared.vo.UserVO;

public interface RegisterUiHandlers extends UiHandlers {
    void register(UserVO user);
}
