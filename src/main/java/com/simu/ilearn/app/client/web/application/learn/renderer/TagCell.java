package com.simu.ilearn.app.client.web.application.learn.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.simu.ilearn.common.shared.vo.TagVO;

public class TagCell extends AbstractCell<TagVO> {
    public interface Renderer extends UiRenderer {
        void render(SafeHtmlBuilder sb, String title, String description);

        void onBrowserEvent(TagCell o, NativeEvent e, Element p);
    }

    private TagVO selectedTag;
    private Delegate<TagVO> close;
    private final Renderer uiRenderer;

    @Inject
    public TagCell(Renderer uiRenderer, @Assisted Delegate<TagVO> close) {
        super(BrowserEvents.CLICK);

        this.uiRenderer = uiRenderer;
        this.close = close;
    }

    @Override
    public void render(Context cxt, TagVO tagVO, SafeHtmlBuilder sb) {
        this.selectedTag = tagVO;
        uiRenderer.render(sb, tagVO.getTitle(), tagVO.getDescription());
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, TagVO value, NativeEvent event,
                               ValueUpdater<TagVO> valueUpdater) {
        selectedTag = value;
        uiRenderer.onBrowserEvent(this, event, parent);
    }

    @UiHandler("close")
    void onCloseClicked(ClickEvent event) {
        close.execute(selectedTag);
    }
}
