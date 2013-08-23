package com.simu.ilearn.app.client.web.application.learn.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.simu.ilearn.common.shared.vo.LearnVO;

public interface AddLearnUiHandlers extends UiHandlers {
    void saveLearn(LearnVO learn);

    void setLocalisation(Boolean active);
}
