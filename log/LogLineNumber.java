package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

public class LogLineNumber extends AbstractLogItem {    
    public LogLineNumber(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int lineWidth) {
        super(color, stackElement, previousStackElement, lineWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        return getStackField(stackElement);
    }

    public String getStackField(StackTraceElement stackElement) {
        int lineNum = stackElement.getLineNumber();
        return lineNum >= 0 ? String.valueOf(lineNum) : "";
    }

    public boolean justifyLeft() {
        //$$$ todo: switch to JustifyType
        return false;
    }

    public boolean snipIfLong() {
        return false;
    }
}
