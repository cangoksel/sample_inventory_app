package common.utils;

import com.google.common.collect.Range;

/**
 * Created by herdemir on 06.11.2015.
 */
public final class Ranges {
    private Ranges() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static <T extends Comparable<?>> Range<T> buildOpen(T start, T end) {
        final Range<T> range;
        if (start == null && end == null) {
            range = Range.all();
        } else if (start == null) {
            range = Range.lessThan(end);
        } else if (end == null) {
            range = Range.greaterThan(start);
        } else {
            range = Range.open(start, end);
        }
        return range;
    }

    public static <T extends Comparable<?>> Range<T> buildClosed(T start, T end) {
        final Range<T> range;
        if (start == null && end == null) {
            range = Range.all();
        } else if (start == null) {
            range = Range.atMost(end);
        } else if (end == null) {
            range = Range.atLeast(start);
        } else {
            range = Range.closed(start, end);
        }
        return range;
    }

    public static <T extends Comparable<?>> Range<T> buildOpenClosed(T start, T end) {
        final Range<T> range;
        if (start == null && end == null) {
            range = Range.all();
        } else if (start == null) {
            range = Range.lessThan(end);
        } else if (end == null) {
            range = Range.atLeast(start);
        } else {
            range = Range.openClosed(start, end);
        }
        return range;
    }

    public static <T extends Comparable<?>> Range<T> buildClosedOpen(T start, T end) {
        final Range<T> range;
        if (start == null && end == null) {
            range = Range.all();
        } else if (start == null) {
            range = Range.atMost(end);
        } else if (end == null) {
            range = Range.greaterThan(start);
        } else {
            range = Range.closedOpen(start, end);
        }
        return range;
    }
}
