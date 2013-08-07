package com.simu.ilearn.app.client.web.application.learn.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.simu.ilearn.common.client.util.EditorView;
import com.simu.ilearn.common.shared.vo.LearnVO;

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
                Driver driver) {
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
