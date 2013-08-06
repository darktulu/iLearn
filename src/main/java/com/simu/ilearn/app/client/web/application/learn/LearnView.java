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

package com.simu.ilearn.app.client.web.application.learn;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.app.client.web.application.learn.ui.LearnEditor;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.MyEntityVO;

import javax.inject.Inject;
import java.util.List;

public class LearnView extends ViewWithUiHandlers<LearnUiHandlers> implements LearnPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LearnView> {
    }

    @UiField(provided = true)
    LearnEditor learnEditor;
    @UiField
    HTMLPanel tablePanel;

    @Inject
    public LearnView(Binder uiBinder, LearnEditor learnEditor) {
        this.learnEditor = learnEditor;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setData(List<LearnVO> data) {

    }

    @Override
    public void editLearn(LearnVO learn) {
        learnEditor.edit(learn);
    }

    @Override
    public void addToSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            if (content != null) {
                tablePanel.add(content);
            }
        } else {
            super.addToSlot(slot, content);
        }
    }

    @Override
    public void removeFromSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            if (content != null) {
                tablePanel.remove(content);
            }
        } else {
            super.removeFromSlot(slot, content);
        }
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == LearnPresenter.LEARN_LIST_SLOT) {
            tablePanel.clear();
            if (content != null) {
                tablePanel.add(content);
            }
        } else {
            super.setInSlot(slot, content);
        }
    }

    @UiHandler("submit")
    public void onSaveClicked(ClickEvent event) {
        getUiHandlers().saveLearn(learnEditor.get());
    }
}
