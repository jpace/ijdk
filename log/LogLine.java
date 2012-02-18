package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A log message.
 */
public class LogLine {
    private final LogElement logElement;
    private final LogColors logColors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    private final LogLineSettings msgSettings;
    private final LogLine previousMessage;
    
    public LogLine(LogElement logElement,
                   LogColors msgColors,
                   StackTraceElement stackElement, 
                   StackTraceElement previousStackElement, 
                   LogLineSettings msgSettings) {
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
            if (msgSettings.showFiles()) {
                appendFileName(sb);
            }
            if (msgSettings.showClasses()) {
                appendClassAndMethod(sb);
            }
        }
        String outputMsg = isRepeatedFrame ? "\"\"" : getMessage(msg);
        sb.append(outputMsg);

        return sb.toString();
    }

    public void appendFileName(StringBuilder sb) {
        sb.append("[");

        ANSIColor color = logColors.getFileColor();
        
        if (msgSettings.useColumns()) {
            LogFileName lfn = new LogFileName(color, stackElement, previousStackElement, msgSettings.getFileWidth());
            String flstr = lfn.getFormatted();

            LogLineNumber lln = new LogLineNumber(color, stackElement, previousStackElement, msgSettings.getLineWidth());
            String lnstr = lln.getFormatted();
            sb.append(flstr).append(' ').append(lnstr);
        }
        else {
            LogFileNameLineNumber lfnln = new LogFileNameLineNumber(color, stackElement, previousStackElement, msgSettings.getFileWidth());
            sb.append(lfnln.getFormatted());
        }

        sb.append("] ");
    }

    public void appendClassAndMethod(StringBuilder sb) {
        sb.append("{");

        LogClassName lcn = new LogClassName(logColors.getClassColor(), stackElement, previousStackElement, msgSettings.getClassWidth());
        sb.append(lcn.getFormatted());
        
        sb.append('#');

        LogMethodName lmn = new LogMethodName(logColors.getMethodColor(), stackElement, previousStackElement, msgSettings.getFunctionWidth());
        sb.append(lmn.getFormatted());

        sb.append("} ");
    }

    public String getMessage(String msg) {
        LogMessage lm = new LogMessage(logColors.getMessageColors(), stackElement, previousStackElement, msg);
        return lm.getFormatted();
    }
}
