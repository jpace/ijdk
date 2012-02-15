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

    public static void appendPadded(StringBuilder sb, String str, ANSIColorList colors, int width) {
        String snipped = snip(str, width);
        append(sb, str, colors);
        addSpaces(sb, width - snipped.length());
    }

    public static void appendPadded(StringBuilder sb, String str, int width) {
        appendPadded(sb, str, toColorList(null), width);
    }

    public static void appendPadded(StringBuilder sb, String str, ANSIColor color, int width) {
        appendPadded(sb, str, toColorList(color), width);
    }

    public static void append(StringBuilder sb, String str, ANSIColorList colors) {
        sb.append(isTrue(colors) ? colors.toString(str) : str);
    }

    public static void append(StringBuilder sb, String str, ANSIColor color) {
        append(sb, str, color == null ? null : new ANSIColorList(color));
    }

    public static void addSpaces(StringBuilder sb, int num) {
        sb.append(StringExt.repeat(' ', num));
    }

    public static String snip(String str, int len) {
        return len <= 0 ? "" : str.length() > len ? str.substring(0, len - 1) + '-' : str;
    }

    public static ANSIColorList toColorList(ANSIColor color) {
        return color == null ? null : new ANSIColorList(color);
    }
}
