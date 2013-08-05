package com.simu.ilearn.common.client.widget.search.type;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.simu.ilearn.common.client.widget.search.vo.DateRange;
import com.simu.ilearn.common.shared.constants.GlobalParameters;

public class DateSearch extends SearchType<DateRange> {
    private final DateTimeFormat dateTimeFormat;

    public DateSearch(String label, String key) {
        super(label, key);

        this.dateTimeFormat = DateTimeFormat.getFormat(GlobalParameters.DATE_FORMAT);
    }

    @Override
    public String tokenRepresentation() {
        StringBuilder builder = new StringBuilder("");
        if (getValue().isBetween()) {
            builder.append(dateTimeFormat.format(getValue().getStartDate()));
            builder.append("<");
            builder.append(getKey());
            builder.append("<");
            builder.append(dateTimeFormat.format(getValue().getEndDate()));
        } else if (getValue().isBigger()) {
            builder.append(dateTimeFormat.format(getValue().getStartDate()));
            builder.append("<");
            builder.append(getKey());
        } else if (getValue().isLower()) {
            builder.append(getKey());
            builder.append("<");
            builder.append(dateTimeFormat.format(getValue().getEndDate()));
        }

        return builder.toString();
    }

    @Override
    public Boolean isEmpty() {
        return getValue() == null;
    }
}
