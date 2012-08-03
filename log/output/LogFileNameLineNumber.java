package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.StringExt;
import static org.incava.ijdk.util.IUtil.*;

public class LogFileNameLineNumber extends Item {    
    public LogFileNameLineNumber(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int fileWidth) {
        super(color, stackElement, previousStackElement, fileWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        String fileName = stackElement.getFileName();
        fileName = fileName.replace(".java", "");

        if (isRepeated()) {
            fileName = StringExt.repeat(' ', fileName.length());
        }
        
        fileName = or(fileName, "");

        int lineNum = stackElement.getLineNumber();
        String lnStr = lineNum >= 0 ? String.valueOf(lineNum) : "";

        String value = getSnipped(fileName) + ":" + lnStr;
        
        return value;
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getFileName();
    }

    public boolean justifyLeft() {
        //$$$ todo: switch to JustifyType
        return true;
    }

    public boolean snipIfLong() {
        return false;
    }
}
