package com.simu.ilearn.app.client.web.application.learn.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.simu.ilearn.app.client.web.application.learn.renderer.TagCellFactory;
import com.simu.ilearn.common.client.util.EditorView;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.MyEntityVO;
import com.simu.ilearn.common.shared.vo.TagVO;

import javax.inject.Inject;

import static com.google.gwt.query.client.GQuery.$;

public class LearnEditor extends Composite implements EditorView<LearnVO> {
    public interface Binder extends UiBinder<Widget, LearnEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<LearnVO, LearnEditor> {
    }

    @UiField
    TextBox title;
    @UiField
    TextArea content;

    private final Driver driver;

    @Inject
    LearnEditor(Binder uiBinder,
                Driver driver,
                TagCellFactory tagCellFactory,
                ListDataProvider<TagVO> dataProvider) {
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        $(title).id("title");
        $(content).id("content");

        title.getElement().setAttribute("placeholder", "Title");
        content.getElement().setAttribute("placeholder", "Add learn");
    }

    @Override
    public void edit(LearnVO learn) {
        driver.edit(learn);
    }

    @Override
    public LearnVO get() {
        LearnVO learn = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return learn;
        }
    }
}
