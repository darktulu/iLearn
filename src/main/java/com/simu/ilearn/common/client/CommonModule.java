package com.simu.ilearn.common.client;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.simu.ilearn.common.client.resource.ResourceLoader;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.rest.ValidatedAsyncCallbackImpl;
import com.simu.ilearn.common.client.resource.SharedResources;
import com.simu.ilearn.common.client.resource.message.SharedMessageBundle;
import com.simu.ilearn.common.client.security.HasRoleGatekeeper;
import com.simu.ilearn.common.client.security.LoggedInGatekeeper;
import com.simu.ilearn.common.client.widget.DnDUploadFactory;
import com.simu.ilearn.common.client.widget.RowLabelValueFactory;
import com.simu.ilearn.common.client.widget.messages.MessageModule;
import com.simu.ilearn.common.client.widget.search.SearchModule;

public class CommonModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new MessageModule());
        install(new SearchModule());

        bind(SharedMessageBundle.class).in(Singleton.class);
        bind(SharedResources.class).in(Singleton.class);
        bind(LoggedInGatekeeper.class).in(Singleton.class);
        bind(HasRoleGatekeeper.class).in(Singleton.class);

        bind(ResourceLoader.class).asEagerSingleton();

        requestStaticInjection(AsyncCallbackImpl.class);
        requestStaticInjection(ValidatedAsyncCallbackImpl.class);

        install(new GinFactoryModuleBuilder().build(RowLabelValueFactory.class));
        install(new GinFactoryModuleBuilder().build(DnDUploadFactory.class));
    }
}
