package com.simu.ilearn.app.client.web.application.learn.widget;

import com.simu.ilearn.common.shared.vo.LearnVO;

public interface LearnWidgetFactory {
    LearnWidgetPresenter create(LearnVO learn);
}
