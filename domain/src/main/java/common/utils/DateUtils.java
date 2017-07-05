package common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by herdemir on 12.05.2015.
 */
public final class DateUtils {
    public static final int numberOfDayInAYear = 365;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String COMPARABLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private DateUtils() {
        throw new UnsupportedOperationException("This utility class cannot be instantiated.");
    }

    public static Date toDate(final LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(final LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return Date.from(localTime.atDate(LocalDate.ofEpochDay(0)).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(final LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(final Date date) {
        if (date == null) {
            return null;
        }
        return getZonedDateTime(date).toLocalDate();
    }

    public static LocalTime toLocalTime(final Date date) {
        if (date == null) {
            return null;
        }
        return getZonedDateTime(date).toLocalTime();
    }

    public static LocalDateTime toLocalDateTime(final Date date) {
        if (date == null) {
            return null;
        }
        return getZonedDateTime(date).toLocalDateTime();
    }

    private static ZonedDateTime getZonedDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault());
    }

    public static boolean isWeekend(LocalDate localDate) {
        return localDate != null &&
               (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    public static LocalDate nextWeekDay(LocalDate localDate) {
        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return localDate.plusDays(2);
        }
        if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return localDate.plusDays(1);
        }
        return localDate;
    }

    public static String toString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static LocalDate toLocalDate(String localDate) {
        if(localDate == null) {
            return null;
        }
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static LocalDateTime toLocalTimeDate(String localTimeDate) {
        if(localTimeDate == null) {
            return null;
        }
        return LocalDateTime.parse(localTimeDate, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static String toComparableString(LocalDate localDate) {
        return localDate.format((DateTimeFormatter.ofPattern(COMPARABLE_DATE_FORMAT)));
    }

    public static LocalDate parse(String date) {
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
