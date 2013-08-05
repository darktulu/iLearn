/**
 * Copyright 2012 Leyton Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.simu.ilearn.app.client.gin;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.gwtplatform.dispatch.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.client.rest.RestApplicationPath;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.simu.ilearn.common.client.CommonModule;
import com.simu.ilearn.app.client.place.NameTokens;
import com.simu.ilearn.app.client.resource.Resources;
import com.simu.ilearn.app.client.resource.message.MessageBundle;
import com.simu.ilearn.app.client.web.RootModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new CommonModule());
        install(new RootModule());
        install(new RestDispatchAsyncModule.Builder().build());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.home);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.home);
        bindConstant().annotatedWith(RestApplicationPath.class).to("/rest");
        bindConstant().annotatedWith(Names.named("importUrl")).to("/rest/import");

        bind(Resources.class).in(Singleton.class);
        bind(MessageBundle.class).in(Singleton.class);

        bind(ResourceLoader.class).asEagerSingleton();
    }
}
