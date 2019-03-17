package org.incava.ijdk.util;


/**
 * Times an event, from when the object is created, until when the
 * <code>end</code> method is invoked.
 */
public class TimedEvent {
    private final String name;
    private final TimedEventSet set;
    private final long startTime;

    public long duration;
    
    public TimedEvent(String name, TimedEventSet set) {
        this.name = name;
        this.set = set;
        this.startTime = System.currentTimeMillis();
    }

    public TimedEvent(String name) {
        this(name, null);
    }

    public TimedEvent(TimedEventSet set) {
        this(null, set);
    }

    public TimedEvent() {
        this(null, null);
    }

    public void end() {
        duration = System.currentTimeMillis() - startTime;
        if (set != null) {
            set.add(duration);
        }
    }

    public void close() {
        end();
        System.err.println(name + ": " + duration);
    }
}
