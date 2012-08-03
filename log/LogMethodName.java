package org.incava.ijdk.log;

import org.incava.ijdk.lang.ObjectExt;
import org.incava.ijdk.lang.StringExt;

public class LogMethodName extends Item {    
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;

    public LogMethodName(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int methodWidth) {
        super(color, stackElement, previousStackElement, methodWidth);

        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return StringExt.repeat(' ', width);
        }
        else {
            String methodName = stackElement.getMethodName();
            return getSnipped(methodName);
        }
    }

    public boolean isRepeated() {
        return (previousStackElement != null &&
                ObjectExt.areEqual(previousStackElement.getMethodName(), stackElement.getMethodName()) &&
                ObjectExt.areEqual(previousStackElement.getClassName(), stackElement.getClassName()));
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getMethodName();
    }
}
