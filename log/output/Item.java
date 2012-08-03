package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.ObjectExt;
import org.incava.ijdk.lang.StringExt;
import static org.incava.ijdk.util.IUtil.*;

public abstract class Item {
    private final ANSIColorList colors;
    private final StackElements stackElements;

    protected final Integer width;    
    
    public Item(ANSIColor color, StackElements stackElements, Integer width) {
        this(color == null ? null : new ANSIColorList(color), stackElements, width);
    }

    public Item(ANSIColorList colors, StackElements stackElements, Integer width) {
        this.colors = colors;
        this.stackElements = stackElements;
        this.width = width;
    }

    public ANSIColorList getColors() {
        return isRepeated() ? null : colors;
    }

    /**
     * Returns the value, as this item represents in the logging line.
     */
    public abstract Object getValue(StackTraceElement stackElement);

    public boolean isRepeated(StackElements stackElements) {
        return stackElements.getPrevious() != null && ObjectExt.areEqual(getStackField(stackElements.getPrevious()), getStackField(stackElements.getCurrent()));
    }

    public boolean isRepeated() {
        return isRepeated(stackElements);
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
        ANSIColorList colors = getColors();
        
        Object value = getValue(stackElements.getCurrent());
        String str = String.valueOf(value);
        if (width == null) {    
            return colors == null ? str : colors.toString(str);
        }
        
        int nSpaces = 0;
        int strlen = str.length();
        if (snipIfLong() && strlen > width) {
            str = StringExt.snip(str, width);
        }
        else {
            nSpaces = width - strlen;
        }
        String colstr = colors == null ? str : colors.toString(str);
        StringBuilder sb = new StringBuilder(colstr);
        int insertPoint = justifyLeft() ? sb.length() : 0;
        for (int i : iter(nSpaces)) {
            sb.insert(insertPoint, ' ');
        }
        return sb.toString();
    }
}
