package org.incava.ijdk.log;

import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

public class LogMessage extends AbstractLogItem {    
    private final String message;
    
    public LogMessage(ANSIColorList colors, StackTraceElement stackElement, StackTraceElement previousStackElement, String msg) {
        super(colors, stackElement, previousStackElement, null);
        this.message = StringExt.chomp(msg);
    }

    public boolean isRepeated() {
        return false;
    }

    public Object getValue(StackTraceElement stackElement) {
        return message;
    }

    public boolean snipIfLong() {
        return false;
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return null;
    }
}
