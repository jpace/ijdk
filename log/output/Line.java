package org.incava.ijdk.log.output;

import org.incava.ijdk.log.Configuration;

/**
 * A log message.
 */
public class Line {
    private final String message;
    private final ItemColors colors;
    private final StackElements stackElements;
    private final Configuration config;
    
    public Line(String message,
                ItemColors colors,
                StackTraceElement stackElement, 
                StackTraceElement previousStackElement, 
                Configuration config) {
        this.message = message;
        this.colors = colors;
        this.stackElements = new StackElements(stackElement, previousStackElement);
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
            FileName lfn = new FileName(color, stackElements, config.getFileWidth());
            String flstr = lfn.getFormatted();

            LineNumber lln = new LineNumber(color, stackElements, config.getLineWidth());
            String lnstr = lln.getFormatted();
            sb.append(flstr).append(' ').append(lnstr);
        }
        else {
            FileNameLineNumber lfnln = new FileNameLineNumber(color, stackElements, config.getFileWidth());
            sb.append(lfnln.getFormatted());
        }

        sb.append("] ");
    }

    public void appendClassAndMethod(StringBuilder sb) {
        sb.append("{");

        ClassName lcn = new ClassName(colors.getClassColor(), stackElements, config.getClassWidth());
        sb.append(lcn.getFormatted());
        
        sb.append('#');

        MethodName lmn = new MethodName(colors.getMethodColor(), stackElements, config.getFunctionWidth());
        sb.append(lmn.getFormatted());

        sb.append("} ");
    }

    public String getMessage() {
        Message lm = new Message(colors.getMessageColors(), stackElements, message);
        return lm.getFormatted();
    }
}
