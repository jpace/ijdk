package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.StringExt;

public class ClassName extends Item {    
    public ClassName(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int classWidth) {
        super(color, stackElement, previousStackElement, classWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return StringExt.repeat(' ', width);
        }

        String className = stackElement.getClassName();
        className = className.replaceFirst("(com|org)\\.\\w+\\.", "...");
        return getSnipped(className);
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getClassName();
    }
}
