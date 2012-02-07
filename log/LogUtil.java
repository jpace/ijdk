package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Various not-really-part-of-logging utilities. Should be migrated to ijdk.
 */
public class LogUtil {
    public static StringBuilder repeat(StringBuilder sb, int len, char ch) {
        sb.append(StringExt.repeat(ch, len));
        return sb;
    }

    public static void appendPadded(StringBuilder sb, String str, int maxSize) {
        if (str.length() > maxSize) {
            sb.append(str.substring(0, maxSize - 1)).append("-");
        }
        else {
            sb.append(str);
            addSpaces(sb, maxSize - str.length());
        }
    }

    public static void append(StringBuilder sb, String str, EnumSet<ANSIColor> colors) {
        if (isTrue(colors)) {
            for (ANSIColor col : colors) {
                sb.append(col.toString());
            }
            sb.append(str);
            sb.append(ANSIColor.NONE);
        }
        else {
            sb.append(str);
        }
    }

    public static void append(StringBuilder sb, String str, ANSIColor color) {
        append(sb, str, color == null ? EnumSet.noneOf(ANSIColor.class) : EnumSet.of(color));
    }

    public static void addSpaces(StringBuilder sb, int num) {
        sb.append(StringExt.repeat(' ', num));
    }
}
