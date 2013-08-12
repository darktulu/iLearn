package com.simu.ilearn.app.client.web.application.learn.renderer;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.inject.assistedinject.Assisted;
import com.simu.ilearn.common.shared.vo.TagVO;

public interface TagCellFactory {
    TagCell create(@Assisted Delegate<TagVO> close);
}
