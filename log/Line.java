package org.incava.ijdk.log;

/**
 * A log message.
 */
public class Line {
    private final String message;
    private final LogColors colors;
    private final StackTraceElement stackElement;
    private final StackTraceElement previousStackElement;
    private final LogConfiguration config;
    
    public Line(String message,
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

            LineNumber lln = new LineNumber(color, stackElement, previousStackElement, config.getLineWidth());
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

        ClassName lcn = new ClassName(colors.getClassColor(), stackElement, previousStackElement, config.getClassWidth());
        sb.append(lcn.getFormatted());
        
        sb.append('#');

        MethodName lmn = new MethodName(colors.getMethodColor(), stackElement, previousStackElement, config.getFunctionWidth());
        sb.append(lmn.getFormatted());

        sb.append("} ");
    }

    public String getMessage() {
        Message lm = new Message(colors.getMessageColors(), stackElement, previousStackElement, message);
        return lm.getFormatted();
    }
}
