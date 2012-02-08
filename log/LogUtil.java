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

    public static void appendPadded(StringBuilder sb, String str, int width) {
        appendPadded(sb, str, EnumSet.noneOf(ANSIColor.class), width);
    }

    public static void appendPadded(StringBuilder sb, String str, EnumSet<ANSIColor> colors, int width) {
        String snipped = snip(str, width);
        append(sb, str, colors);
        addSpaces(sb, width - snipped.length());
    }

    public static void appendPadded(StringBuilder sb, String str, ANSIColor color, int width) {
        appendPadded(sb, str, toColorSet(color), width);
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
        append(sb, str, toColorSet(color));
    }

    public static void addSpaces(StringBuilder sb, int num) {
        sb.append(StringExt.repeat(' ', num));
    }

    public static String snip(String str, int len) {
        return len <= 0 ? "" : str.length() > len ? str.substring(0, len - 1) + '-' : str;
    }

    public static EnumSet<ANSIColor> toColorSet(ANSIColor color) {
        return color == null ? EnumSet.noneOf(ANSIColor.class) : EnumSet.of(color);
    }
}
