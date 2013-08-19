package com.simu.ilearn.app.client.web.application.learn.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.inject.Inject;
import com.simu.ilearn.common.shared.vo.TagVO;

public class TagView extends AbstractCell<TagVO> {
    public interface Renderer extends UiRenderer {
        void render(SafeHtmlBuilder sb, String title, String description, String color);
    }

    private final Renderer uiRenderer;

    @Inject
    public TagView(Renderer uiRenderer) {
        this.uiRenderer = uiRenderer;
    }

    @Override
    public void render(Context cxt, TagVO tagVO, SafeHtmlBuilder sb) {
        uiRenderer.render(sb, tagVO.getTitle(), tagVO.getDescription(), tagVO.getColor());
    }
}
