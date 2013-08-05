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

package com.simu.ilearn.app.client.web.application.home;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.simu.ilearn.common.client.mvp.ValidatedViewWithUiHandlers;
import com.simu.ilearn.common.client.widget.ValidationErrorPopup;
import com.simu.ilearn.common.shared.vo.MyEntityVO;
import com.simu.ilearn.app.client.web.application.home.ui.MyEntityEditor;

import javax.inject.Inject;
import java.util.List;

public class HomePageView extends ValidatedViewWithUiHandlers<HomeUiHandlers> implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @UiField(provided = true)
    MyEntityEditor myEntityEditor;
    @UiField(provided = true)
    CellTable<MyEntityVO> myTable;

    private final DateTimeFormat dateFormat;
    private final ListDataProvider<MyEntityVO> dataProvider;

    @Inject
    HomePageView(Binder uiBinder,
                 MyEntityEditor myEntityEditor,
                 ListDataProvider<MyEntityVO> dataProvider,
                 ValidationErrorPopup errorPopup) {
        super(errorPopup);

        this.myEntityEditor = myEntityEditor;
        this.dataProvider = dataProvider;
        this.myTable = new CellTable<MyEntityVO>();
        this.dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");

        initWidget(uiBinder.createAndBindUi(this));
        initCellTable();
        dataProvider.addDataDisplay(myTable);
    }

    @Override
    public void editUser(MyEntityVO myEntity) {
        myEntityEditor.edit(myEntity);
    }

    @Override
    public void setData(List<MyEntityVO> data) {
        dataProvider.getList().clear();
        dataProvider.getList().addAll(data);
        dataProvider.refresh();
    }

    @UiHandler("submit")
    void onSubmitClicked(ClickEvent event) {
        getUiHandlers().saveEntity(myEntityEditor.get());
    }

    private void initCellTable() {
        TextColumn<MyEntityVO> firstNameColumn = new TextColumn<MyEntityVO>() {
            @Override
            public String getValue(MyEntityVO object) {
                return object.getFirstName();
            }
        };
        myTable.addColumn(firstNameColumn, "First name");

        TextColumn<MyEntityVO> lastNameColumn = new TextColumn<MyEntityVO>() {
            @Override
            public String getValue(MyEntityVO object) {
                return object.getLastName();
            }
        };
        myTable.addColumn(lastNameColumn, "Last name");

        TextColumn<MyEntityVO> createdColumn = new TextColumn<MyEntityVO>() {
            @Override
            public String getValue(MyEntityVO object) {
                return dateFormat.format(object.getCreated());
            }
        };
        myTable.addColumn(createdColumn, "Date Creation");
    }
}
