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
    private final LogColorSettings colorSettings;
    private final LogMessageSettings msgSettings;
    private final LogMessage previousMessage;

    public LogMessage(LogElement logElement,
                      StackTraceElement stackElement, 
                      StackTraceElement previousStackElement, 
                      LogColorSettings colorSettings,
                      LogMessageSettings msgSettings) {
        this.logElement = logElement;
        this.logColors = logElement.getColors();
        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
        this.colorSettings = colorSettings;
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

    public void appendFileName(StringBuilder sb) {
        int fileWidth = msgSettings.fileWidth;
        
        String stackFileName = LogUtil.snip(stackElement.getFileName(), fileWidth);
        String fileName = isRepeatedFileName() ? StringExt.repeat(' ', stackFileName.length()) : or(stackFileName, "");

        sb.append("[");

        int lineNum = stackElement.getLineNumber();
        String lnStr = lineNum >= 0 ? String.valueOf(lineNum) : "";
        ANSIColor color = or(logColors.fileColor, colorSettings.getFileColor(fileName));
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

        int classPadding = addClassForOutput(sb);

        sb.append('#');

        addMethodForOutput(sb, classPadding);

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

    public int addClassForOutput(StringBuilder sb) {
        int classWidth = msgSettings.classWidth;
        
        if (isRepeatedClass()) {
            LogUtil.addSpaces(sb, classWidth);
            return 0;
        }

        String className = stackElement.getClassName();
        ANSIColor classColor = or(logColors.classColor, colorSettings.getClassColor(className));
        
        className = className.replaceFirst("(com|org)\\.\\w+\\.", "...");
        className = LogUtil.snip(className, classWidth);

        LogUtil.append(sb, className, classColor);

        int nSpaces = classWidth - className.length();

        if (msgSettings.useColumns) {
            LogUtil.addSpaces(sb, nSpaces);
        }

        return nSpaces;
    }
    
    public void addMethodForOutput(StringBuilder sb, int classPadding) {
        int methodWidth = msgSettings.functionWidth;
        String methodName = stackElement.getMethodName();

        ANSIColor color = or(logColors.methodColor, colorSettings.getMethodColor(stackElement.getClassName(), methodName));
        
        if (isRepeatedMethod()) {
            methodName = StringExt.repeat(' ', methodWidth);
            // no colors on repeated methods:
            color = null;
        }
        else {
            methodName = LogUtil.snip(methodName, methodWidth);
        }

        ANSIColorList colors = color == null ? null : new ANSIColorList(color);

        String methstr = LogMessageFormat.format(methodName, methodWidth, true, colors, true);
        sb.append(methstr);
    }

    private String colorizeMessage(String msg) {
        ANSIColorList colors = logColors.getMessageColors();
        
        if (isEmpty(colors)) {
            ANSIColor col = colorSettings.getColor(stackElement);
            if (isTrue(col)) {
                colors = new ANSIColorList(col);
            }
        }

        return LogMessageFormat.format(msg, null, true, colors, false);
    }

    public String getMessage(String msg) {
        // remove ending EOLN
        String newMsg = StringExt.chomp(msg);
        
        if (colorSettings.useColor()) {
            newMsg = colorizeMessage(newMsg);
        }

        return newMsg;
    }
}
