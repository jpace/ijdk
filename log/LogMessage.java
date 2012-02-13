package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A log message.
 */
public class LogMessage {
    private final LogColors logColors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    private final LogColorSettings colorSettings;
    private final LogMessageSettings msgSettings;

    public LogMessage(LogColors logColors, 
                      StackTraceElement stackElement, 
                      StackTraceElement previousStackElement, 
                      LogColorSettings colorSettings,
                      LogMessageSettings msgSettings) {
        this.logColors = logColors;
        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
        this.colorSettings = colorSettings;
        this.msgSettings = msgSettings;
    }

    public void outputFileName(StringBuilder sb) {
        int fileWidth = msgSettings.fileWidth;
        
        String stackFileName = LogUtil.snip(stackElement.getFileName(), fileWidth);
        String fileName = isFileNameMatch() ? StringExt.repeat(' ', stackFileName.length()) : or(stackFileName, "");

        sb.append("[");

        int lineNum = stackElement.getLineNumber();
        String lnStr = lineNum >= 0 ? String.valueOf(lineNum) : "";
        ANSIColor color = or(logColors.fileColor, colorSettings.getFileColor(fileName));

        if (msgSettings.useColumns) {
            LogMessage.outputColumns(msgSettings, sb, color, fileName, lnStr);
        }
        else {
            String fileLineNum = fileName + ":" + lnStr;
            int width = isNull(color) ? fileWidth : fileWidth - fileName.length() - 1 - lnStr.length();

            LogUtil.appendPadded(sb, fileLineNum, color, width);
        }

        sb.append("] ");
    }


    public void outputClassAndMethod(StringBuilder sb) {
        sb.append("{");

        int classPadding = addClassForOutput(sb);

        sb.append('#');

        addMethodForOutput(sb, classPadding);

        sb.append("} ");
    }

    protected boolean isFileNameMatch() {
        return previousStackElement != null && ObjectExt.areEqual(previousStackElement.getFileName(), stackElement.getFileName());
    }

    public boolean isClassMatch() {
        return previousStackElement != null && previousStackElement.getClassName().equals(stackElement.getClassName());
    }

    public int addClassForOutput(StringBuilder sb) {
        int classWidth = msgSettings.classWidth;
        
        if (isClassMatch()) {
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

    protected boolean isMethodMatch() {
        return isClassMatch() && ObjectExt.areEqual(previousStackElement.getMethodName(), stackElement.getMethodName());
    }
    
    public int addMethodForOutput(StringBuilder sb, int classPadding) {
        int methodWidth = msgSettings.functionWidth;
        String methodName = stackElement.getMethodName();

        ANSIColor methodColor = or(logColors.methodColor, colorSettings.getMethodColor(stackElement.getClassName(), methodName));
        
        if (isMethodMatch()) {
            methodName = StringExt.repeat(' ', methodWidth);
            // no colors on repeated methods:
            methodColor = null;
        }
        else {
            methodName = LogUtil.snip(methodName, methodWidth);
        }

        LogUtil.append(sb, methodName, methodColor);

        int nSpaces = methodWidth - methodName.length();

        if (!msgSettings.useColumns) {
            LogUtil.addSpaces(sb, classPadding);
        }
        LogUtil.addSpaces(sb, nSpaces);

        return nSpaces;
    }

    public static String colorizeMessage(String msg, StackTraceElement stackElement, EnumSet<ANSIColor> msgColors, LogColorSettings colorSettings) {
        EnumSet<ANSIColor> colors = msgColors;
        
        if (isEmpty(colors)) {
            ANSIColor col = colorSettings.getColor(stackElement);
            if (isTrue(col)) {
                colors = EnumSet.of(col);
            }
        }

        if (isTrue(colors)) {
            StringBuilder sb = new StringBuilder();
            LogUtil.append(sb, msg, colors);
            return sb.toString();
        }
        
        return msg;
    }

    public static String unwindThroughEoln(String str) {
        while (str.length() > 0 && "\r\n".indexOf(StringExt.get(str, -1)) != -1) {
            str = StringExt.get(str, 0, -1);
        }
        return str;
    }

    public static String getMessage(String msg, StackTraceElement ste, EnumSet<ANSIColor> msgColors, LogColorSettings colorSettings) {
        // remove ending EOLN
        String newMsg = unwindThroughEoln(msg);
        
        if (colorSettings.useColor()) {
            newMsg = colorizeMessage(newMsg, ste, msgColors, colorSettings);
        }

        return newMsg;
    }

    public static void outputColumns(LogMessageSettings settings, StringBuilder sb, ANSIColor color, String fileName, String lnStr) {
        int fileWidth = settings.fileWidth;
        int lineWidth = settings.lineWidth;

        if (isNull(color)) {
            String fmt = "%-" + fileWidth + "s %" + lineWidth + "s";
            String val = String.format(fmt, fileName, lnStr);
            sb.append(val);
        }
        else {
            int nSpaces = fileWidth - fileName.length() + 1 + lineWidth - lnStr.length();
            // we append these separately, with no colors between the file name and line number.
            LogUtil.appendPadded(sb, fileName, color, nSpaces);
            LogUtil.append(sb, lnStr, color);
        }
    }
}