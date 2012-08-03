package org.incava.ijdk.log.output;

import java.util.EnumSet;
import static org.incava.ijdk.util.IUtil.*;

public class LogColors {
    private final ANSIColorList msgColors;
    private final ANSIColor fileColor;
    private final ANSIColor classColor;
    private final ANSIColor methodColor;

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

    public ANSIColor getFileColor() {
        return fileColor;
    }

    public ANSIColor getClassColor() {
        return classColor;
    }

    public ANSIColor getMethodColor() {
        return methodColor;
    }
}
