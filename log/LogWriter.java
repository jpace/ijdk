package org.incava.ijdk.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.util.IUtil;
import static org.incava.ijdk.util.IUtil.*;

/**
 * <p>Writes the logging output, applying filters and decorations. The
 * <code>Log</code> and <code>Logger</code> classes offers cleaner and more
 * thorough interfaces than this class.</p>
 *
 * @see org.incava.ijdk.log.Log
 * @see org.incava.ijdk.log.Logger
 */
public class LogWriter {
    private LogLineSettings lineSettings = new LogLineSettings();

    // this writes to stdout even in Gradle and Ant, which redirect stdout:
    private PrintWriter out = new PrintWriter(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);

    private List<String> packagesSkipped = list("org.incava.ijdk.log", "org.incava.qualog");
    private List<String> classesSkipped = list("tr.Ace");
    private List<String> methodsSkipped = IUtil.<String>list();
    
    private LogOutputType outputType = LogOutputType.NONE;
    private LogColorSettings colorSettings = new LogColorSettings();
    private LogLine previousLine = null;    
    private StackTraceElement prevStackElement = null;    
    private Thread prevThread = null;
    private LogLevel level = Log.LEVEL9;
    private List<LogFilter> filters = new ArrayList<LogFilter>();

    /**
     * Adds a filter to be applied for output.
     *
     * @see org.incava.ijdk.log.LogFilter
     */
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    public void setColumns(boolean cols) {
        lineSettings.useColumns = cols;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setDisabled(Class cls) {
        addFilter(new LogClassFilter(cls, null));
    }

    public void setClassColor(String className, ANSIColor color) {
        colorSettings.setClassColor(className, color);
    }

    public void setPackageColor(String pkgName, ANSIColor color) {
        colorSettings.setPackageColor(pkgName, color);
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        colorSettings.setMethodColor(className, methodName, color);
    }

    public void setFileColor(String fileName, ANSIColor color) {
        colorSettings.setFileColor(fileName, color);
    }

    public void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        lineSettings = new LogLineSettings();
        lineSettings.fileWidth = fileWidth;
        lineSettings.lineWidth = lineWidth;
        lineSettings.classWidth = classWidth;
        lineSettings.functionWidth = functionWidth;
        lineSettings.useColumns = columns;
    }

    /**
     * Sets the output type and level. Either verbose or quiet can be enabled.
     */
    public void setOutput(LogOutputType type, LogLevel level) {
        this.outputType = type;
        this.level      = level;
    }

    public boolean verbose() {
        return outputType.equals(LogOutputType.VERBOSE);
    }

    public void addClassSkipped(Class cls) {
        addClassSkipped(cls.getName());
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    /**
     * Sets parameters to their defaults.
     */
    public void clear() {
        this.colorSettings = new LogColorSettings();
        this.prevStackElement = null;
        this.prevThread = null;
        this.level = Log.LEVEL9;
        this.filters = new ArrayList<LogFilter>();
    }

    /**
     * Resets the thread and stack element.
     */
    protected void reset() {
        this.prevThread = Thread.currentThread();
        this.prevStackElement = null;
    }

    public boolean stack(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        if (!isLoggable(level)) {
            return true;
        }

        LogElement le = LogElementFactory.create(level, logColors, name, obj, numFrames);
        return le.stack(this);
    }

    public boolean isSkipped(StackTraceElement ste) {
        String className = ste.getClassName();
        if (classesSkipped.contains(className) || methodsSkipped.contains(ste.getMethodName())) {
            return true;
        }
        
        for (String pkgName : packagesSkipped) {
            if (className.startsWith(pkgName + ".")) {
                return true;
            }
        }
        return false;
    }

    public boolean isLoggable(LogLevel lvl) {
        return level.isLoggable(outputType, lvl);
    }

    /**
     * Returns the index in the stack where logging (stacks) should be
     * displayed. Returns -1 if the end of the stack is reached and no logging
     * should occur.
     */
    public synchronized int findStackStart(StackTraceElement[] stack) {
        for (int fi = 0; fi < stack.length; ++fi) {
            if (!isSkipped(stack[fi])) {
                return fi;
            }
        }
        return stack.length;
    }

    /**
     * Logs the element. Assumes the level is already matched.
     */
    public synchronized boolean stack(LogElement le) {
        // when we're switching threads, reset to a null state.
        if (!Thread.currentThread().equals(prevThread)) {
            reset();
        }

        // only show 1 frame in quiet mode:
        int numFrames = outputType.equals(LogOutputType.QUIET) ? 1 : le.getNumFrames();
        StackTraceElement[] stack = getStack(numFrames);

        int fi = findStackStart(stack);
        for (int framesShown = 0; fi < stack.length && framesShown < numFrames; ++fi, ++framesShown) {
            StackTraceElement stackElement = stack[fi];

            if (framesShown == 0 && !isLoggable(stackElement)) {
                return true;
            }

            LogColors elmtColors = le.getColors();

            // the colors of the message part, not the whole line:
            ANSIColorList msgColors = getMessageColors(elmtColors, stackElement);
            LogColors lineColors = new LogColors(msgColors,
                                                 or(elmtColors.getFileColor(), colorSettings.getFileColor(stackElement.getFileName())),
                                                 or(elmtColors.getClassColor(), colorSettings.getClassColor(stackElement.getClassName())),
                                                 or(elmtColors.getMethodColor(), colorSettings.getMethodColor(stackElement.getClassName(), stackElement.getMethodName())));
            
            LogLine line = new LogLine(le, lineColors, stackElement, prevStackElement, lineSettings);
            out.println(line.getLine(framesShown > 0, outputType.equals(LogOutputType.VERBOSE)));
            prevStackElement = stackElement;
        }
        return true;
    }

    private ANSIColorList getMessageColors(LogColors elmtColors, StackTraceElement ste) {
        if (!colorSettings.useColor()) {
            return null;
        }
        
        // the colors of the message part, not the whole line:
        ANSIColorList msgColors = elmtColors.getMessageColors();

        if (isEmpty(msgColors)) {
            ANSIColor col = or(colorSettings.getMethodColor(ste.getClassName(), ste.getMethodName()),
                               colorSettings.getClassColor(ste.getClassName()),
                               colorSettings.getFileColor(ste.getFileName()));

            if (isTrue(col)) {
                msgColors = new ANSIColorList(col);
            }
        }

        return msgColors;
    }

    public boolean isLoggable(StackTraceElement stackElement) {
        boolean isLoggable = true;
        for (LogFilter filter : filters) {
            LogLevel flevel = filter.getLevel();
            if (filter.isMatch(stackElement)) {
                isLoggable = flevel != null && level.compareTo(flevel) >= 0;
            }
        }
        return isLoggable;
    }
    
    public void setUseColor(boolean useColor) {
        colorSettings.setUseColor(useColor);
    }

    public int getLineWidth() {
        return lineSettings.lineWidth;
    }

    public int getFileWidth() {
        return lineSettings.fileWidth;
    }

    public int getFunctionWidth() {
        return lineSettings.functionWidth;
    }

    public int getClassWidth() {
        return lineSettings.classWidth;
    }

    public void setLineWidth(int lnWidth) {
        lineSettings.lineWidth = lnWidth;
    }

    public void setFileWidth(int flWidth) {
        lineSettings.fileWidth = flWidth;
    }

    public void setFunctionWidth(int funcWidth) {
        lineSettings.functionWidth = funcWidth;
    }

    public void setClassWidth(int clsWidth) {
        lineSettings.classWidth = clsWidth;
    }

    public void setShowClasses(boolean showCls) {
        lineSettings.showClasses = showCls;
    }

    public void setShowFiles(boolean showFls) {
        lineSettings.showFiles = showFls;
    }

    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
