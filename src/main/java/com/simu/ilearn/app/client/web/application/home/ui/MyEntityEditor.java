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

package com.simu.ilearn.app.client.web.application.home.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.simu.ilearn.common.client.util.EditorView;
import com.simu.ilearn.common.shared.vo.MyEntityVO;

import javax.inject.Inject;

import static com.google.gwt.query.client.GQuery.$;

public class MyEntityEditor extends Composite implements EditorView<MyEntityVO> {
    public interface Binder extends UiBinder<Widget, MyEntityEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<MyEntityVO, MyEntityEditor> {
    }

    @UiField
    TextBox firstName;
    @UiField
    TextBox lastName;

    private final Driver driver;

    @Inject
    MyEntityEditor(Binder uiBinder,
                   Driver driver) {
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        $(firstName).id("firstName");
        $(lastName).id("lastName");
    }

    @Override
    public void edit(MyEntityVO myEntity) {
        firstName.setFocus(true);
        driver.edit(myEntity);
    }

    @Override
    public MyEntityVO get() {
        MyEntityVO myEntity = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return myEntity;
        }
    }
}
