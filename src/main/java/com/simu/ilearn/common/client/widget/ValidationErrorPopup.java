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

package com.simu.ilearn.common.client.widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class ValidationErrorPopup extends PopupPanel {
    public interface Binder extends UiBinder<Widget, ValidationErrorPopup> {
    }

    @UiField
    Label message;

    @Inject
    ValidationErrorPopup(Binder uiBinder) {
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("");
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
