package org.incava.ijdk.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A log message.
 */
public class LogMessage {
    public static String colorizeMessage(String msg, StackTraceElement stackElement, EnumSet<ANSIColor> msgColors, LogColorSettings colorSettings) {
        EnumSet<ANSIColor> colors = msgColors;
        
        if (isEmpty(colors)) {
            ANSIColor col = or(colorSettings.getMethodColor(stackElement.getClassName(), stackElement.getMethodName()),
                               colorSettings.getClassColor(stackElement.getClassName()),
                               colorSettings.getFileColor(stackElement.getFileName()));
            if (isTrue(col)) {
                colors = EnumSet.of(col);
            }
        }

        if (isTrue(colors)) {
            StringBuilder sb = new StringBuilder();
            LogUtil.append(sb, msg, colors);
            return sb.toString();
        }
        
        return msg;
    }

    public static String unwindThroughEoln(String str) {
        while (str.length() > 0 && "\r\n".indexOf(StringExt.get(str, -1)) != -1) {
            str = StringExt.get(str, 0, -1);
        }
        return str;
    }

    public static String getMessage(String msg, StackTraceElement ste, EnumSet<ANSIColor> msgColors, LogColorSettings colorSettings) {
        // remove ending EOLN
        msg = unwindThroughEoln(msg);
        
        if (colorSettings.useColor()) {
            msg = colorizeMessage(msg, ste, msgColors, colorSettings);
        }

        return msg;
    }
}
