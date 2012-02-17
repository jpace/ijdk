package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A log message.
 */
public class LogMessage {
    private final LogElement logElement;
    private final LogColors logColors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    private final LogMessageSettings msgSettings;
    private final LogMessage previousMessage;
    
    public LogMessage(LogElement logElement,
                      LogColors msgColors,
                      StackTraceElement stackElement, 
                      StackTraceElement previousStackElement, 
                      LogMessageSettings msgSettings) {
        this.logElement = logElement;
        this.logColors = msgColors;
        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
        this.msgSettings = msgSettings;
        this.previousMessage = null;
    }

    public String getLine(boolean isRepeatedFrame, boolean verboseOutput) {
        String msg = logElement.getMessage();
        StringBuilder sb = new StringBuilder();
        
        if (verboseOutput) {
            if (msgSettings.showFiles) {
                appendFileName(sb);
            }
            if (msgSettings.showClasses) {
                appendClassAndMethod(sb);
            }
        }
        String outputMsg = isRepeatedFrame ? "\"\"" : getMessage(msg);
        sb.append(outputMsg);

        return sb.toString();
    }

    public ANSIColor getFileNameColor() {
        return isRepeatedFileName() ? null : logColors.getFileColor();
    }

    public String getFileName() {
        int fileWidth = msgSettings.fileWidth;
        String stackFileName = LogUtil.snip(stackElement.getFileName(), fileWidth);

        if (isRepeatedFileName()) {
            return StringExt.repeat(' ', stackFileName.length());
        }
        
        return or(stackFileName, "");
    }

    public void appendFileName(StringBuilder sb) {
        int fileWidth = msgSettings.fileWidth;        
        String fileName = getFileName();

        sb.append("[");

        int lineNum = stackElement.getLineNumber();
        String lnStr = lineNum >= 0 ? String.valueOf(lineNum) : "";
        ANSIColor color = getFileNameColor();
        ANSIColorList colors = color == null ? null : new ANSIColorList(color);
        
        if (msgSettings.useColumns) {
            int lineWidth = msgSettings.lineWidth;
            String flstr = LogMessageFormat.format(fileName, fileWidth, true,  colors, true);
            String lnstr = LogMessageFormat.format(lnStr,    lineWidth, false, colors, false);
            sb.append(flstr).append(' ').append(lnstr);
        }
        else {
            String fileLineNum = LogUtil.snip(fileName, fileWidth) + ":" + lnStr;
            String flnstr      = LogMessageFormat.format(fileLineNum, fileWidth, true, colors, false);
            sb.append(flnstr);
        }

        sb.append("] ");
    }

    public void appendClassAndMethod(StringBuilder sb) {
        sb.append("{");
        addToLine(sb, msgSettings.classWidth, getClassName(), getClassColor());
        sb.append('#');
        addToLine(sb, msgSettings.functionWidth, getMethodName(), getMethodColor());
        sb.append("} ");
    }

    protected boolean isRepeatedFileName() {
        return previousStackElement != null && ObjectExt.areEqual(previousStackElement.getFileName(), stackElement.getFileName());
    }

    public boolean isRepeatedClass() {
        return previousStackElement != null && previousStackElement.getClassName().equals(stackElement.getClassName());
    }

    protected boolean isRepeatedMethod() {
        return isRepeatedClass() && ObjectExt.areEqual(previousStackElement.getMethodName(), stackElement.getMethodName());
    }

    public ANSIColor getClassColor() {
        return isRepeatedClass() ? null : logColors.getClassColor();
    }

    public String getClassName() {
        int classWidth = msgSettings.classWidth;
        
        if (isRepeatedClass()) {
            return StringExt.repeat(' ', classWidth);
        }

        String className = stackElement.getClassName();
        className = className.replaceFirst("(com|org)\\.\\w+\\.", "...");
        return LogUtil.snip(className, classWidth);
    }

    public void addToLine(StringBuilder sb, int width, String name, ANSIColor color) {
        ANSIColorList colors = color == null ? null : new ANSIColorList(color);
        String str = LogMessageFormat.format(name, width, true, colors, true);
        sb.append(str);
    }

    public void addClassForOutput(StringBuilder sb) {
        addToLine(sb, msgSettings.classWidth, getClassName(), getClassColor());
    }

    public ANSIColor getMethodColor() {
        return isRepeatedMethod() ? null : logColors.getMethodColor();
    }

    public String getMethodName() {
        int methodWidth = msgSettings.functionWidth;

        if (isRepeatedMethod()) {
            return StringExt.repeat(' ', methodWidth);
        }
        else {
            String methodName = stackElement.getMethodName();
            return LogUtil.snip(methodName, methodWidth);
        }
    }    

    public ANSIColorList getMessageColors() {
        return logColors.getMessageColors();
    }

    public String getMessage(String msg) {
        // remove ending EOLN
        String newMsg = StringExt.chomp(msg);
        ANSIColorList colors = getMessageColors();
        
        if (isTrue(colors)) {
            return LogMessageFormat.format(newMsg, null, true, colors, false);
        }

        return newMsg;
    }
}
