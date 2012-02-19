package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * A log message.
 */
public class LogLine {
    private final String message;
    private final LogColors colors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    private final LogConfiguration config;
    
    public LogLine(String message,
                   LogColors colors,
                   StackTraceElement stackElement, 
                   StackTraceElement previousStackElement, 
                   LogConfiguration config) {
        this.message = message;
        this.colors = colors;
        this.stackElement = stackElement;
        this.previousStackElement = previousStackElement;
        this.config = config;
    }

    public String getLine(boolean isRepeatedFrame, boolean verboseOutput) {
        StringBuilder sb = new StringBuilder();
        
        if (verboseOutput) {
            if (config.showFiles()) {
                appendFileName(sb);
            }
            if (config.showClasses()) {
                appendClassAndMethod(sb);
            }
        }
        String outputMsg = isRepeatedFrame ? "\"\"" : getMessage();
        sb.append(outputMsg);

        return sb.toString();
    }

    public void appendFileName(StringBuilder sb) {
        sb.append("[");

        ANSIColor color = colors.getFileColor();
        
        if (config.useColumns()) {
            LogFileName lfn = new LogFileName(color, stackElement, previousStackElement, config.getFileWidth());
            String flstr = lfn.getFormatted();

            LogLineNumber lln = new LogLineNumber(color, stackElement, previousStackElement, config.getLineWidth());
            String lnstr = lln.getFormatted();
            sb.append(flstr).append(' ').append(lnstr);
        }
        else {
            LogFileNameLineNumber lfnln = new LogFileNameLineNumber(color, stackElement, previousStackElement, config.getFileWidth());
            sb.append(lfnln.getFormatted());
        }

        sb.append("] ");
    }

    public void appendClassAndMethod(StringBuilder sb) {
        sb.append("{");

        LogClassName lcn = new LogClassName(colors.getClassColor(), stackElement, previousStackElement, config.getClassWidth());
        sb.append(lcn.getFormatted());
        
        sb.append('#');

        LogMethodName lmn = new LogMethodName(colors.getMethodColor(), stackElement, previousStackElement, config.getFunctionWidth());
        sb.append(lmn.getFormatted());

        sb.append("} ");
    }

    public String getMessage() {
        LogMessage lm = new LogMessage(colors.getMessageColors(), stackElement, previousStackElement, message);
        return lm.getFormatted();
    }
}
