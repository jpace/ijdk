package org.incava.ijdk.log;

import java.util.regex.Pattern;
import org.incava.ijdk.lang.Range;

/**
 * Represents a filter for selective enabling or disabling of logging
 * statements.
 */
public class LogFilter {
    public static final Pattern NO_PATTERN = null;
    public static final Range NO_RANGE = null;
    
    private final LogLevel level;
    private final Pattern fileNamePat;
    private final Range lineNumberRng;    
    private final Pattern classNamePat;
    private final Pattern methodNamePat;

    public LogFilter(LogLevel level) {
        this(level, (Pattern)null, null, null, null);
    }

    public LogFilter(LogLevel level, Pattern fname, Range lnum, Pattern clsName, Pattern methName) {
        this.level = level;

        fileNamePat = fname;
        lineNumberRng = lnum;
        classNamePat = clsName;
        methodNamePat = methName;
    }

    public LogFilter(LogLevel level, String fname, Range lnum, String clsName, String methName) {
        this(level,
             fname == null    ? (Pattern)null : Pattern.compile(fname),
             lnum,
             clsName == null  ? (Pattern)null : Pattern.compile(clsName),
             methName == null ? (Pattern)null : Pattern.compile(methName));
    }

    /**
     * Returns the level.
     */
    public LogLevel getLevel() {
        return level;
    }

    /**
     * Returns whether the given parameters match this filter.
     */
    public boolean isMatch(String fileName, int lineNumber, String className, String methodName) {
        return (isMatch(fileNamePat,   fileName)   &&
                isMatch(lineNumberRng, lineNumber) &&
                isMatch(classNamePat,  className)  && 
                isMatch(methodNamePat, methodName));
    }

    private boolean isMatch(Pattern pattern, String name) {
        return pattern == null || pattern.matcher(name).matches();
    }

    private boolean isMatch(Range range, int num) {
        return range == null || range.includes(num);
    }

    public boolean isMatch(StackTraceElement ste) {
        return isMatch(ste.getFileName(), ste.getLineNumber(), ste.getClassName(), ste.getMethodName());
    }
}
