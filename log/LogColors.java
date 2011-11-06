package org.incava.ijdk.log;

import java.util.*;
import static org.incava.ijdk.util.IUtil.*;


public class LogColors {
    public final EnumSet<ANSIColor> msgColors;

    public final ANSIColor fileColor;

    public final ANSIColor classColor;

    public final ANSIColor methodColor;

    public LogColors(EnumSet<ANSIColor> msgColors,
                     ANSIColor fileColor,
                     ANSIColor classColor,
                     ANSIColor methodColor) {
        this.msgColors = msgColors;
        this.fileColor = fileColor;
        this.classColor = classColor;
        this.methodColor = methodColor;
    }

    public LogColors(EnumSet<ANSIColor> msgColors) {
        this(msgColors, null, null, null);
    }
}
