package com.github.cangoksel.common.utils;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by usuicmez on 26.06.2015.
 */

@Setter
@Getter
public class DateRange {
    private Date from;
    private Date to;

    public Date getNow() {
        return new Date();
    }

    public boolean filter(LocalDate filteredDate) {
        final LocalDate fromLocalDate = DateUtils.toLocalDate(from);
        final LocalDate toLocalDate = DateUtils.toLocalDate(to);

        final Range<LocalDate> range = Ranges.buildClosed(fromLocalDate, toLocalDate);

        return range.contains(filteredDate);
    }
}