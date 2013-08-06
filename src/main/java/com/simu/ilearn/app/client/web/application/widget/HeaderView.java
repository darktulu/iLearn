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

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.common.client.widget.search.type.SearchType;
import com.simu.ilearn.common.client.widget.search.ui.SearchInput;
import com.simu.ilearn.app.client.resource.message.MessageBundle;

import javax.inject.Inject;
import java.util.List;

import static com.google.gwt.query.client.GQuery.$;

public class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements HeaderPresenter.MyView {
    public interface Binder extends UiBinder<Widget, HeaderView> {
    }

    @UiField
    Label username;
    @UiField
    Label reload;
    @UiField(provided = true)
    SearchInput search;
    @UiField
    Button searchButton;

    private final MessageBundle messageBundle;

    @Inject
    HeaderView(Binder uiBinder,
               MessageBundle messageBundle,
               SearchInput search) {
        this.search = search;
        this.messageBundle = messageBundle;

        initWidget(uiBinder.createAndBindUi(this));

        search.addAdvancedSearchHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().advancedSearch();
            }
        });

        $(search).id("searchBox");
    }

    @Override
    public void showSearch(Boolean visible) {
        search.setVisible(visible);
        searchButton.setVisible(visible);
    }

    @Override
    public void enableAdvancedSearch(Boolean enable) {
        search.showAdvancedSearch(enable);
    }

    @Override
    public void setUsername(String login) {
        username.setText(messageBundle.welcomeMessage(login));
    }

    @Override
    public void showAjaxLoader() {
        reload.addStyleName("icon-spin");
    }

    @Override
    public void hideAjaxLoader() {
        reload.removeStyleName("icon-spin");
    }

    @Override
    public void setSearchTokens(List<SearchType<?>> tokens) {
        search.setTokenList(tokens);
    }

    @UiHandler("logout")
    void onLogoutClicked(ClickEvent event) {
        getUiHandlers().logout();
    }
}
