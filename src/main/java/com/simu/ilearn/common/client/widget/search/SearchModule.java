package com.simu.ilearn.common.client.widget.search;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.simu.ilearn.common.client.widget.search.renderer.SearchTokenCellFactory;
import com.simu.ilearn.common.client.widget.search.ui.SearchEditorFactory;

public class SearchModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSharedView(AdvancedSearchPresenter.MyView.class, AdvancedSearchView.class);

        install(new GinFactoryModuleBuilder().build(AdvancedSearchFactory.class));
        install(new GinFactoryModuleBuilder().build(SearchTokenCellFactory.class));
        install(new GinFactoryModuleBuilder().build(SearchEditorFactory.class));
    }
}
