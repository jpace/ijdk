package org.incava.ijdk.util;

/**
 * A set of timed events.
 */
public class TimedEventSet {
    public long duration = 0L;

    public void add(long duration) {
        this.duration += duration;
    }
}
