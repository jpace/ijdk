package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.ObjectExt;
import org.incava.ijdk.lang.StringExt;

public abstract class Item {
    private final ANSIColorList colors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    protected final Integer width;    
    
    public Item(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, Integer width) {
        this(color == null ? null : new ANSIColorList(color), stackElement, previousStackElement, width);
    }

    public Item(ANSIColorList colors, StackTraceElement stackElement, StackTraceElement previousStackElement, Integer width) {
        this.colors = colors;
        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
        this.width = width;
    }

    public ANSIColorList getColors() {
        return isRepeated() ? null : colors;
    }

    /**
     * Returns the value, as this item represents in the logging line.
     */
    public abstract Object getValue(StackTraceElement stackElement);

    public boolean isRepeated() {
        return previousStackElement != null && ObjectExt.areEqual(getStackField(previousStackElement), getStackField(stackElement));
    }

    public String getSnipped(String str) {
        return StringExt.snip(str, width);
    }

    public int getWidth() {
        return width;
    }

    public boolean justifyLeft() {
        return true;
    }

    public boolean snipIfLong() {
        return true;
    }

    /**
     * Returns the element in the stack that this item represents.
     */
    public abstract String getStackField(StackTraceElement stackElement);

    public String getFormatted() {
        LogLineFormat llf = new LogLineFormat(width, justifyLeft(), getColors(), snipIfLong());
        Object value = getValue(stackElement);
        return llf.format(value);
    }
}
