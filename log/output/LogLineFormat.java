package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.StringExt;
import org.incava.ijdk.log.ANSIColorList;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Formats the string. If <code>width</code> is null, then the string is
 * left-justified. Otherwise the string is justified left or right, and snipped
 * ("-" for the last character) if it exceeds the width, and
 * <code>snipIfLong</code> is true.
 */
public class LogLineFormat {
    private final Integer width;
    private final boolean justifyLeft;
    private final ANSIColorList colors;
    private final boolean snipIfLong;    

    public LogLineFormat(Integer width, boolean justifyLeft, ANSIColorList colors, boolean snipIfLong) {
        this.width = width;
        this.justifyLeft = justifyLeft;
        this.colors = colors;
        this.snipIfLong = snipIfLong;
    }

    public String format(Object value) {
        String str = String.valueOf(value);
        if (width == null) {    
            return colors == null ? str : colors.toString(str);
        }
        
        int nSpaces = 0;
        int strlen = str.length();
        if (snipIfLong && strlen > width) {
            str = StringExt.snip(str, width);
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
