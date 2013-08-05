package com.simu.ilearn.common.client.widget.search.vo;

import java.util.Date;

public class DateRange {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean isBetween() {
        return startDate !=  null && endDate != null;
    }

    public Boolean isLower() {
        return endDate != null && startDate == null;
    }

    public Boolean isBigger() {
        return startDate != null && endDate == null;
    }
}
