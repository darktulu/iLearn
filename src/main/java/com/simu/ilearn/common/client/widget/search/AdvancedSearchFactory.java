package com.simu.ilearn.common.client.widget.search;

import com.google.inject.assistedinject.Assisted;
import com.gwtplatform.mvp.client.PresenterWidget;

import java.util.Map;

public interface AdvancedSearchFactory {
    AdvancedSearchPresenter create(@Assisted Map<String, PresenterWidget<?>> searchPresenters);
}
