package org.incava.ijdk.log.output;

/**
 * The current and previous stack trace elements.
 */
public class StackElements {
    private final StackTraceElement current;
    private final StackTraceElement previous;
    
    public StackElements(StackTraceElement current, StackTraceElement previous) {
        this.current = current;
        this.previous = previous;
    }

    public StackTraceElement getCurrent() {
        return current;
    }

    public StackTraceElement getPrevious() {
        return previous;
    }

    /**
     * Returns a new StackTrace with its "previous" as this object's current,
     * and "current" as the new current element.
     */
    public StackElements create(StackTraceElement newCurrent) {
        return new StackElements(newCurrent, this.current);
    }
}
