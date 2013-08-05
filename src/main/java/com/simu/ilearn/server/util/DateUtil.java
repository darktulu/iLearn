package com.simu.ilearn.server.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static Boolean checkOverlapping(Date firstStart, Date firstEnd, Date lastStart, Date lastEnd, int carence) {
        // check what's the first start date
        if (firstStart.before(lastStart)) {
            // check if there's overlapping the firstEnd must not be null if null there's overlapping
            if (firstEnd == null) return true;
            // check overlapping
            Calendar firstEndCalendar = new GregorianCalendar();
            firstEndCalendar.setTime(firstEnd);
            firstEndCalendar.add(Calendar.DAY_OF_YEAR, carence);
            return firstEndCalendar.getTime().compareTo(lastStart) >= 0;
        } else if (firstStart.after(lastStart)) {
            // check if there's overlapping the lastEnd must not be null if null there's overlapping
            if (lastEnd == null) return true;
            // check overlapping
            Calendar lastEndCalendar = new GregorianCalendar();
            lastEndCalendar.setTime(lastEnd);
            lastEndCalendar.add(Calendar.DAY_OF_YEAR, carence);
            return lastEndCalendar.getTime().compareTo(firstStart) >= 0;
        } else {
            return true;
        }
    }

    public static Date firstDayOfMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH)).getTime();
    }

    public static Date lastDayOfMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).getTime();
    }
}
