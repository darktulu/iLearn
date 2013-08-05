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
import com.github.gwtbootstrap.client.ui.NavWidget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SiderHolderView extends ViewImpl implements SiderHolderPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SiderHolderView> {
    }

    @UiField
    NavWidget imports;
    @UiField
    NavWidget declarations;
    @UiField
    NavLink attestations;

    @Inject
    public SiderHolderView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setSelectedMenu(String currentToken) {
        clearActive();
    }

    @UiHandler("imports")
    void onImportsClicked(ClickEvent event) {
        clearActive();
        imports.setActive(true);
    }

    @UiHandler("declarations")
    void onDeclarationClicked(ClickEvent event) {
        clearActive();
        declarations.setActive(true);
    }

    @UiHandler("attestations")
    void onSettingsClicked(ClickEvent event) {
        clearActive();
        attestations.setActive(true);
    }

    private void clearActive() {
        imports.setActive(false);
        attestations.setActive(false);
        declarations.setActive(false);
    }
}
