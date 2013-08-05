package com.simu.ilearn.common.client.resource.style;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableStyle extends CellTable.Resources {
    @Source({"com/simu/ilearn/common/client/resource/style/cellTableStyle.css"})
    Style cellTableStyle();

    interface Style extends CellTable.Style {
    }
}
