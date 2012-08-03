package org.incava.ijdk.log.output;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

public class LogFileName extends Item {    
    public LogFileName(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int fileWidth) {
        super(color, stackElement, previousStackElement, fileWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        String fileName = stackElement.getFileName();
        fileName = fileName.replace(".java", "");
        String stackFileName = getSnipped(fileName);

        if (isRepeated()) {
            return StringExt.repeat(' ', stackFileName.length());
        }
        
        return or(stackFileName, "");
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getFileName();
    }
}
