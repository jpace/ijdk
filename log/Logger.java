package org.incava.ijdk.log;

import java.io.*;
import java.util.*;
import org.incava.ijdk.util.PropertyExt;
import static org.incava.ijdk.util.IUtil.*;


/**
 * Base class of the Log class, which expands the interface via generated
 * methods. This class contains the core functionality, at a lower-level.
 *
 * @see Log
 */
public class Logger {
    /**
     * The version of the log module.
     */
    public final static String VERSION = "1.1.1";
    
    public final static String CLASS_WIDTH_PROPERTY_KEY = "org.incava.ijdk.log.classwidth";
    public final static String COLUMNAR_PROPERTY_KEY = "org.incava.ijdk.log.columnar";
    public final static String FILE_WIDTH_PROPERTY_KEY = "org.incava.ijdk.log.filewidth";
    public final static String LEVEL_PROPERTY_KEY = "org.incava.ijdk.log.level";
    public final static String LINE_WIDTH_PROPERTY_KEY = "org.incava.ijdk.log.linewidth";
    public final static String METHOD_WIDTH_PROPERTY_KEY = "org.incava.ijdk.log.methodwidth";
    public final static String SHOW_CLASSES_PROPERTY_KEY = "org.incava.ijdk.log.showclasses";
    public final static String SHOW_FILES_PROPERTY_KEY = "org.incava.ijdk.log.showfiles";
    public final static String VERBOSE_PROPERTY_KEY = "org.incava.ijdk.log.verbose";
    
    public final static LogLevel LEVEL0 = new LogLevel(0);
    public final static LogLevel LEVEL1 = new LogLevel(1);
    public final static LogLevel LEVEL2 = new LogLevel(2);
    public final static LogLevel LEVEL3 = new LogLevel(3);
    public final static LogLevel LEVEL4 = new LogLevel(4);
    public final static LogLevel LEVEL5 = new LogLevel(5);
    public final static LogLevel LEVEL6 = new LogLevel(6);
    public final static LogLevel LEVEL7 = new LogLevel(7);
    public final static LogLevel LEVEL8 = new LogLevel(8);
    public final static LogLevel LEVEL9 = new LogLevel(9);

    public static final LogOutputType NO_OUTPUT = LogOutputType.NONE;

    public static final LogOutputType QUIET = LogOutputType.QUIET;
    
    public static final LogOutputType VERBOSE = LogOutputType.VERBOSE;
    
    /**
     * The default number of stack trace elements to display in a stack.
     */
    protected static final int DEFAULT_STACK_DEPTH = 5;

    protected static LogWriter writer;

    protected static LogTimer timer;

    /**
     * Sets verbose from system property settings.
     */
    protected static void setVerbosity() {
        String verStr = System.getProperty(VERBOSE_PROPERTY_KEY, System.getProperty("verbose"));

        if (isNull(verStr)) {
            return;
        }

        boolean verbose = Boolean.valueOf(verStr);
        LogLevel level = LEVEL5;
            
        String lvlStr = System.getProperty(LEVEL_PROPERTY_KEY);
        if (isNotNull(lvlStr)) {
            level = new LogLevel(new Integer(lvlStr));
        }

        if (verbose) {
            setOutput(LogOutputType.VERBOSE, level);
            System.out.println("Log, version " + VERSION);
        }
    }

    static {
        writer = new LogWriter();
        timer = new LogTimer();

        setVerbosity();
        
        if (System.getProperty("os.name").equals("Linux")) {
            writer.setUseColor(true);
        }
        
        Boolean showFiles = PropertyExt.getBoolean(SHOW_FILES_PROPERTY_KEY);
        if (isNotNull(showFiles)) {
            writer.showFiles = showFiles;
        }

        Boolean showClasses = PropertyExt.getBoolean(SHOW_CLASSES_PROPERTY_KEY);
        if (isNotNull(showClasses)) {
            writer.showClasses = showClasses;
        }

        Boolean columnar = PropertyExt.getBoolean(COLUMNAR_PROPERTY_KEY);
        if (isNotNull(columnar)) {
            writer.columns = columnar;
        }

        Integer fileWidth = PropertyExt.getInteger(FILE_WIDTH_PROPERTY_KEY);
        if (isNotNull(fileWidth)) {
            writer.fileWidth = fileWidth;
        }

        Integer lineWidth = PropertyExt.getInteger(LINE_WIDTH_PROPERTY_KEY);
        if (isNotNull(lineWidth)) {
            writer.lineWidth = lineWidth;
        }

        Integer classWidth = PropertyExt.getInteger(CLASS_WIDTH_PROPERTY_KEY);
        if (isNotNull(classWidth)) {
            writer.classWidth = classWidth;
        }

        Integer methodWidth = PropertyExt.getInteger(METHOD_WIDTH_PROPERTY_KEY);
        if (isNotNull(methodWidth)) {
            writer.functionWidth = methodWidth;
        }
    }
    
    public static boolean isLoggable(LogLevel level) {
        return writer.isLoggable(level);
    }

    public static void setDisabled(Class cls) {
        addFilter(new LogClassFilter(cls, null));
    }

    public static void addFilter(LogFilter filter) {
        writer.addFilter(filter);
    }

    public static void setOut(PrintWriter out) {
        writer.out = out;
    }

    public static void setFileWidth(int fileWidth) {
        writer.fileWidth = fileWidth;
    }

    public static void setClassWidth(int classWidth) {
        writer.classWidth = classWidth;
    }

    public static void setLineWidth(int lineWidth) {
        writer.lineWidth = lineWidth;
    }

    public static void setFunctionWidth(int functionWidth) {
        writer.functionWidth = functionWidth;
    }

    public static void setClassColor(String className, ANSIColor color) {
        writer.setClassColor(className, color);
    }

    public static void setClassColor(ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String className = stack[2].getClassName();
        setClassColor(className, color);
    }

    public static void setPackageColor(ANSIColor color) {
    }

    public static void setPackageColor(String pkg, ANSIColor color) {
    }

    public static void setMethodColor(String methodName, ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String className = stack[2].getClassName();
        writer.setMethodColor(className, methodName, color);
    }

    public static void setMethodColor(String className, String methodName, ANSIColor color) {
        writer.setMethodColor(className, methodName, color);
    }

    public static void clearClassColor(String className) {
        writer.clearClassColor(className);
    }

    public static void setFileColor(String fileName, ANSIColor color) {
        writer.setFileColor(fileName, color);
    }

    public static void setFileColor(ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String fileName = stack[2].getFileName();
        writer.setFileColor(fileName, color);
    }

    public static void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int funcWidth) {
        writer.set(columns, fileWidth, lineWidth, classWidth, funcWidth);
    }

    public static void setVerbose() {
        setVerbose(true);
    }

    public static void setVerbose(boolean verbose) {
        setOutput(LogOutputType.VERBOSE, verbose ? LEVEL5 : null);
    }

    public static void setQuiet() {
        setQuiet(true);
    }

    public static void setQuiet(boolean quiet) {
        setOutput(LogOutputType.QUIET, LEVEL5);
    }

    public static void setOutput(LogOutputType type, LogLevel level) {
        writer.setOutput(type, level);
    }

    public static void setQuiet(LogLevel level) {
        writer.setOutput(LogOutputType.QUIET, level);
    }

    public static boolean verbose() {
        return writer.verbose();
    }

    public static void setColumns(boolean cols) {
        writer.setColumns(cols);
    }
    
    public static void addClassSkipped(Class cls) {
        writer.addClassSkipped(cls);
    }
    
    public static void addClassSkipped(String clsName) {
        writer.addClassSkipped(clsName);
    }

    public static void reset() {
        writer.reset();
    }

    public static void clear() {
        writer.clear();
    }

    public static int findStackStart(StackTraceElement[] stack) {
        return writer.findStackStart(stack);
    }

    public static boolean time(String msg) {
        return timer.start(msg);
    }

    public static boolean time() {
        return timer.start();
    }

    public static boolean start(String msg) {
        return timer.start(msg);
    }

    public static boolean start() {
        return timer.start();
    }

    public static boolean end(String msg) {
        return timer.end(msg);
    }

    public static boolean stack(LogLevel level, EnumSet<ANSIColor> msgColors, String name, Object obj, int numFrames) {
        LogColors logColors = new LogColors(msgColors);
        return stack(level, logColors, name, obj, numFrames);
    }

    public static boolean log(LogLevel level, EnumSet<ANSIColor> msgColors, String name, Object obj) {
        LogColors logColors = new LogColors(msgColors);
        return stack(level, logColors, name, obj, 1);
    }

    public static boolean stack(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        return writer.stack(level, logColors, name, obj, numFrames);
    }

    public static boolean inspect(LogLevel level, EnumSet<ANSIColor> msgColors, String name, Object obj, int numFrames) {
        LogColors logColors = new LogColors(msgColors);
        if (isNull(obj)) {
            return stack(level, logColors, name, obj, numFrames);
        }
        else {
            Map<String, Object> objMap = LogObject.inspect(obj);
            return stack(level, logColors, name, objMap, numFrames);
        }
    }

    /**
     * Writes an empty log message.
     */
    public static boolean log() {
        return log("");
    }

    /**
     * Writes an empty stack message.
     */
    public static boolean stack() {
        return stack("");
    }

    public static boolean stack(Object obj) {
        return stack(LEVEL5, EnumSet.noneOf(ANSIColor.class), null, obj, DEFAULT_STACK_DEPTH);
    }

    public static boolean log(Object obj) {
        return stack(LEVEL5, EnumSet.noneOf(ANSIColor.class), null, obj, 1);
    }

    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
