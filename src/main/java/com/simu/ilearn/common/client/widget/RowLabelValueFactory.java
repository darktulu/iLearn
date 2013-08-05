package com.simu.ilearn.common.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;

public interface RowLabelValueFactory {
    RowHeaderH1 createHeaderH1(String label);

    RowHeaderH2 createHeaderH2(String label);

    RowHeaderH3 createHeaderH3(String label);

    RowHeaderH4 createHeaderH4(String label);

    RowLabelValue createValueLabel(String label, SafeHtml value);
}
