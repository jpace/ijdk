package org.incava.ijdk.log.output;

public class LineNumber extends Item {    
    public LineNumber(ANSIColor color, StackTraceElement stackElement, StackTraceElement previousStackElement, int lineWidth) {
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
