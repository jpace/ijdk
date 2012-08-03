package org.incava.ijdk.log.output;

import org.incava.ijdk.lang.ObjectExt;
import org.incava.ijdk.lang.StringExt;

public class MethodName extends Item {    
    public MethodName(ANSIColor color, StackElements stackElements, int methodWidth) {
        super(color, stackElements, methodWidth);
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

    public boolean isRepeated(StackElements stackElements) {
        StackTraceElement previous = stackElements.getPrevious();
        if (previous == null) {
            return false;
        }

        StackTraceElement current = stackElements.getCurrent();
        return (ObjectExt.areEqual(previous.getMethodName(), current.getMethodName()) &&
                ObjectExt.areEqual(previous.getClassName(), current.getClassName()));
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getMethodName();
    }
}
