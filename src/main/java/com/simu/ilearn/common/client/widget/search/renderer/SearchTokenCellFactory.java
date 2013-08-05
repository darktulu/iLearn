package com.simu.ilearn.common.client.widget.search.renderer;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.simu.ilearn.common.client.widget.search.type.SearchType;

public interface SearchTokenCellFactory {
    SearchTokenCell create(Delegate<SearchType<?>> delegate);
}
