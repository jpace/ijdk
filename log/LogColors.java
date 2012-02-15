package org.incava.ijdk.log;

import java.util.EnumSet;
import static org.incava.ijdk.util.IUtil.*;

public class LogColors {
    public final ANSIColorList msgColors;
    public final ANSIColor fileColor;
    public final ANSIColor classColor;
    public final ANSIColor methodColor;

    public LogColors(ANSIColorList msgColors,
                     ANSIColor fileColor,
                     ANSIColor classColor,
                     ANSIColor methodColor) {
        this.msgColors = msgColors;
        this.fileColor = fileColor;
        this.classColor = classColor;
        this.methodColor = methodColor;
    }

    public LogColors(EnumSet<ANSIColor> msgColors) {
        this(new ANSIColorList(msgColors), null, null, null);
    }

    public ANSIColorList getMessageColors() {
        return msgColors;
    }
}
