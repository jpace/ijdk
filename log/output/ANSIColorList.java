package org.incava.ijdk.log.output;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * A list (set) of colors.
 */
public class ANSIColorList extends ArrayList<ANSIColor> {
    public static final long serialVersionUID = 1;
    
    public ANSIColorList(EnumSet<ANSIColor> colors) {
        addAll(colors);
    }

    public ANSIColorList(ANSIColor color) {
        add(color);
    }

    public ANSIColorList() {
    }

    /**
     * Wraps the given string in these colors, ending it with NONE (reset).
     */
    public String toString(String str) {
        StringBuffer sb = new StringBuffer();
        for (ANSIColor color : this) {
            sb.append(color);
        }
        sb.append(str);
        if (!isEmpty()) {
            sb.append(ANSIColor.NONE);
        }
        return sb.toString();
    }
}
