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

package com.simu.ilearn.common.client.mvp;

import com.google.common.base.Strings;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;
import com.simu.ilearn.common.client.widget.ValidationErrorPopup;

import java.util.Map;

import static com.google.gwt.query.client.GQuery.$;

public abstract class ValidatedPopupViewImpl extends PopupViewImpl implements ValidatedPopupView {
    private final ValidationErrorPopup errorPopup;

    protected ValidatedPopupViewImpl(EventBus eventBus, final ValidationErrorPopup errorPopup) {
        super(eventBus);
        this.errorPopup = errorPopup;
    }

    @Override
    public void showErrors(Map<String, String> violations) {
        for (String violation : violations.keySet()) {
            String path = violation;
            String message = violations.get(path);

            if (Strings.isNullOrEmpty(violation)) {
                path = message.split("#")[0];
                message = message.split("#")[1];
            }

            String fieldId = "#" + path;
            $(fieldId).attr("message", message);
            $(fieldId).addClass("errorField");

            if ($(fieldId).parent().hasClass("input-prepend")) {
                $(fieldId).parent().parent().parent().addClass("error");
            } else {
                $(fieldId).parent().parent().addClass("error");
            }

            $(fieldId).focus(new Function() {
                @Override
                public void f(Element e) {
                    showErrorFieldPopup(e);
                }
            });

            $(fieldId).blur(new Function() {
                @Override
                public void f(Element e) {
                    errorPopup.hide();
                }
            });
        }
    }

    @Override
    public void clearErrors() {
        $(".errorField").each(new Function() {
            @Override
            public void f(Element e) {
                String fieldId = "#" + e.getId();
                $(fieldId).unbind(Event.ONFOCUS);
                $(fieldId).unbind(Event.ONBLUR);
                $(fieldId).removeClass("errorField");

                if ($(fieldId).parent().hasClass("input-prepend")) {
                    $(fieldId).parent().parent().parent().removeClass("error");
                } else {
                    $(fieldId).parent().parent().removeClass("error");
                }
            }
        });
    }

    protected void showErrorFieldPopup(final Element element) {
        errorPopup.setMessage(element.getAttribute("message"));
        errorPopup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                int left = element.getAbsoluteLeft();
                int top = element.getAbsoluteTop() - errorPopup.getOffsetHeight();
                errorPopup.setPopupPosition(left, top);
            }
        });
    }
}
