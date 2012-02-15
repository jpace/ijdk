package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A formatter for logging messages.
 */
public class LogMessageFormat {
    private String format;
    private final int fileWidth;
    private final int lineWidth;
    
    public LogMessageFormat(int fileWidth, int lineWidth) {
        this.fileWidth = fileWidth;
        this.lineWidth = lineWidth;

        format = null;
    }

    public String format(String fileName, Integer lineNumber, String className, String methodName, String message) {
        StringBuilder sb = new StringBuilder();
        String fmt = "%-" + fileWidth + "s %" + lineWidth + "s";
        String val = String.format(fmt, fileName, lineNumber != null && lineNumber.intValue() >= 0 ? String.valueOf(lineNumber) : "");
        sb.append(val);

        return sb.toString();
    }

    /**
     * Returns the string, formatted. If <code>width</code> is null, then the
     * string is left-justified. Otherwise the string is justified left or
     * right, and snipped ("-" for the last character) if it exceeds the width,
     * and <code>snipIfLong</code> is true.
     */
    public static String format(Object value, Integer width, boolean justifyLeft, ANSIColorList colors, boolean snipIfLong) {
        String str = String.valueOf(value);
        if (width == null) {    
            return colors == null ? str : colors.toString(str);
        }
        else {
            int nSpaces = 0;
            int strlen = str.length();
            if (strlen > width) {
                str = str.substring(0, strlen - 1) + "-";
            }
            else {
                nSpaces = width - strlen;
            }
            String colstr = colors == null ? str : colors.toString(str);
            StringBuilder sb = new StringBuilder(colstr);
            int insertPoint = justifyLeft ? sb.length() : 0;
            for (int i : iter(nSpaces)) {
                sb.insert(insertPoint, ' ');
            }
            return sb.toString();
        }
    }
}
