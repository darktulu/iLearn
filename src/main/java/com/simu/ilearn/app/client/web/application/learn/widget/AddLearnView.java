package com.simu.ilearn.app.client.web.application.learn.widget;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.simu.ilearn.app.client.web.application.learn.renderer.TagCellFactory;
import com.simu.ilearn.app.client.web.application.learn.ui.LearnEditor;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.TagVO;

public class AddLearnView extends ViewWithUiHandlers<AddLearnUiHandlers> implements AddLearnPresenter.MyView {
    public interface Binder extends UiBinder<Widget, AddLearnView> {
    }

    @UiField(provided = true)
    LearnEditor learnEditor;
    @UiField
    @Editor.Ignore
    TextBox tag;
    @UiField(provided = true)
    CellList<TagVO> tags;

    private final ListDataProvider<TagVO> dataProvider;

    @Inject
    public AddLearnView(final Binder uiBinder, LearnEditor learnEditor,
                        ListDataProvider<TagVO> dataProvider, TagCellFactory tagCellFactory) {
        this.learnEditor = learnEditor;
        this.dataProvider = dataProvider;

        this.tags = new CellList<TagVO>(tagCellFactory.create());

        initWidget(uiBinder.createAndBindUi(this));

        dataProvider.addDataDisplay(tags);
    }

    @Override
    public void editLearn(LearnVO learn) {
        learnEditor.edit(learn);
    }

    @UiHandler("submit")
    public void onSaveClicked(ClickEvent event) {
        LearnVO learn = learnEditor.get();
        learn.setTags(dataProvider.getList());
        getUiHandlers().saveLearn(learn);
    }

    @UiHandler("tag")
    void onPasswordKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            TagVO tag = new TagVO();
            tag.setTitle(this.tag.getValue());
            dataProvider.getList().add(tag);
        }
    }
}
