/**
 * Copyright 2012 Nuvola Inc.
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

package com.simu.ilearn.app.client.web.application.widget;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MenuView extends ViewImpl implements MenuPresenter.MyView {
    public interface Binder extends UiBinder<Widget, MenuView> {
    }

    @UiField
    NavLink archive;
    @UiField
    NavLink home;
    @UiField
    NavLink maps;

    @Inject
    public MenuView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
