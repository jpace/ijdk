package org.incava.ijdk.log.output;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.log.ClassFilter;
import org.incava.ijdk.log.Configuration;
import org.incava.ijdk.log.Filter;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.Log;
import org.incava.ijdk.log.types.LogElement;
import org.incava.ijdk.log.types.LogElementFactory;
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
public class Writer {
    private Configuration config = new Configuration();

    // this writes to stdout even in Gradle and Ant, which redirect stdout:
    private PrintWriter out = new PrintWriter(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);

    private List<String> packagesSkipped = list("org.incava.ijdk.log", "org.incava.qualog");
    private List<String> classesSkipped = list("tr.Ace");
    private List<String> methodsSkipped = IUtil.<String>list();
    
    private OutputType outputType = OutputType.NONE;
    private StackTraceElement prevStackElement = null;    
    private Thread prevThread = null;
    private Level level = new Level(9);
    private List<Filter> filters = IUtil.<Filter>list();

    /**
     * Adds a filter to be applied for output.
     *
     * @see org.incava.ijdk.log.Filter
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void setColumns(boolean cols) {
        config.setUseColumns(cols);
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setDisabled(Class cls) {
        addFilter(new ClassFilter(cls, null));
    }

    public void setClassColor(String className, ANSIColor color) {
        config.setClassColor(className, color);
    }

    public void setPackageColor(String pkgName, ANSIColor color) {
        config.setPackageColor(pkgName, color);
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        config.setMethodColor(className, methodName, color);
    }

    public void setFileColor(String fileName, ANSIColor color) {
        config.setFileColor(fileName, color);
    }

    public void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        config.setFileWidth(fileWidth);
        config.setLineWidth(lineWidth);
        config.setClassWidth(classWidth);
        config.setFunctionWidth(functionWidth);
        config.setUseColumns(columns);
    }

    /**
     * Sets the output type and level. Either verbose or quiet can be enabled.
     */
    public void setOutput(OutputType type, Level level) {
        this.outputType = type;
        this.level      = level;
    }

    public boolean verbose() {
        return outputType.equals(OutputType.VERBOSE);
    }

    public void addClassSkipped(Class cls) {
        addClassSkipped(cls.getName());
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    public void addPackageSkipped(String pkgName) {
        packagesSkipped.add(pkgName);
    }

    /**
     * Sets parameters to their defaults.
     */
    public void clear() {
        this.config = new Configuration();
        this.prevStackElement = null;
        this.prevThread = null;
        this.level = Log.LEVEL9;
        this.filters = new ArrayList<Filter>();
    }

    /**
     * Resets the thread and stack element.
     */
    public void reset() {
        this.prevThread = Thread.currentThread();
        this.prevStackElement = null;
    }

    public boolean stack(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        if (!isLoggable(level)) {
            return true;
        }

        LogElement le = LogElementFactory.create(level, colors, name, obj, numFrames);
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

    public boolean isLoggable(Level lvl) {
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

    public ANSIColor getFileColor(ItemColors elmtColors, StackTraceElement stackElement) {
        return or(elmtColors.getFileColor(), config.getFileColor(stackElement.getFileName()));
    }

    public ANSIColor getClassColor(ItemColors elmtColors, StackTraceElement stackElement) {
        return or(elmtColors.getClassColor(), config.getClassColor(stackElement.getClassName()));
    }

    public ANSIColor getMethodColor(ItemColors elmtColors, StackTraceElement stackElement) {
        return or(elmtColors.getMethodColor(), config.getMethodColor(stackElement.getClassName(), stackElement.getMethodName()));
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
        int numFrames = outputType.equals(OutputType.QUIET) ? 1 : le.getNumFrames();
        StackTraceElement[] stack = getStack(numFrames);

        int fi = findStackStart(stack);
        for (int framesShown = 0; fi < stack.length && framesShown < numFrames; ++fi, ++framesShown) {
            StackTraceElement stackElement = stack[fi];

            if (framesShown == 0 && !isLoggable(stackElement)) {
                return true;
            }

            ItemColors elmtColors = le.getColors();

            // the colors of the message part, not the whole line:
            ANSIColorList msgColors = getMessageColors(elmtColors, stackElement);
            ItemColors lineColors = new ItemColors(msgColors,
                                                   getFileColor(elmtColors, stackElement),
                                                   getClassColor(elmtColors, stackElement),
                                                   getMethodColor(elmtColors, stackElement));
            
            Line line = new Line(le.getMessage(), lineColors, stackElement, prevStackElement, config);
            out.println(line.getLine(framesShown > 0, outputType.equals(OutputType.VERBOSE)));
            prevStackElement = stackElement;
        }
        return true;
    }

    private ANSIColorList getMessageColors(ItemColors elmtColors, StackTraceElement ste) {
        if (!config.useColor()) {
            return null;
        }
        
        // the colors of the message part, not the whole line:
        ANSIColorList msgColors = elmtColors.getMessageColors();

        if (isEmpty(msgColors)) {
            ANSIColor col = or(config.getMethodColor(ste.getClassName(), ste.getMethodName()),
                               config.getClassColor(ste.getClassName()),
                               config.getFileColor(ste.getFileName()));

            if (isTrue(col)) {
                msgColors = new ANSIColorList(col);
            }
        }

        return msgColors;
    }

    public boolean isLoggable(StackTraceElement stackElement) {
        boolean isLoggable = true;
        for (Filter filter : filters) {
            Level flevel = filter.getLevel();
            if (filter.isMatch(stackElement)) {
                isLoggable = flevel != null && level.compareTo(flevel) >= 0;
            }
        }
        return isLoggable;
    }
    
    public void setUseColor(boolean useColor) {
        config.setUseColor(useColor);
    }

    public void setLineWidth(int lnWidth) {
        config.setLineWidth(lnWidth);
    }

    public void setFileWidth(int flWidth) {
        config.setFileWidth(flWidth);
    }

    public void setFunctionWidth(int funcWidth) {
        config.setFunctionWidth(funcWidth);
    }

    public void setClassWidth(int clsWidth) {
        config.setClassWidth(clsWidth);
    }

    public void setShowClasses(boolean showCls) {
        config.setShowClasses(showCls);
    }

    public void setShowFiles(boolean showFls) {
        config.setShowFiles(showFls);
    }

    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
