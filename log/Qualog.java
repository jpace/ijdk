package org.incava.ijdk.log;

import java.io.*;
import java.util.*;


/**
 * <p>Provides quasi-logging support, more akin to debugging/development output
 * and trace statements than logging per se. Supports both tabular and
 * non-tabular output formats, the former being with the files, line numbers,
 * classes, and methods being arranged so that they line up vertically. That
 * format, I've found, is better for larger projects (500M+ LOC), in which class
 * names and package hierarchies tend to be larger. The non-tabular format seems
 * better for smaller projects.</p>
 *
 * <p>Colors can be enabled and disabled, and associated with classes, methods,
 * files, and levels. They are designed to work on terminals that support ANSI
 * escape codes. On platforms without this -- e.g., Windows -- colorization is
 * disabled.</p>
 *
 * <p>Unlike real logging mechanisms, there is no support for log rotations. I
 * recommend log4j for that. This package is mainly for programmers who want
 * trace statements from a Java program. See Kernighan and Pike for a defense of
 * those of us who develop and debug programs mainly relying on the print
 * statement.</p>
 *
 * <p>There is a serious performance hit to using this package, since each
 * output statement results in an exception being created.</p>
 */
public class Qualog {
    /**
     * The version of the Qualog module.
     */
    public final static String VERSION = "1.0.2";
    
    /**
     * An array denoting no colors.
     */
    public final static EnumSet<ANSIColor> NO_COLORS = EnumSet.noneOf(ANSIColor.class);

    /**
     * An object denoting no color.
     */
    public final static ANSIColor NO_COLOR = null;
    
    /**
     * The code for no color applied.
     */
    public final static ANSIColor NONE = ANSIColor.NONE;

    /**
     * The code for reset of colors and decorations.
     */
    public final static ANSIColor RESET = ANSIColor.RESET;

    /**
     * The code for bold decoration.
     */
    public final static ANSIColor BOLD = ANSIColor.BOLD;

    /**
     * The code for underscore (AKA underline).
     */
    public final static ANSIColor UNDERSCORE = ANSIColor.UNDERSCORE;

    /**
     * The code for underline (AKA underscore).
     */
    public final static ANSIColor UNDERLINE = ANSIColor.UNDERLINE;

    /**
     * The code for the blink attribute.
     */
    public final static ANSIColor BLINK = ANSIColor.BLINK;

    /**
     * The code for reversed text.
     */
    public final static ANSIColor REVERSE = ANSIColor.REVERSE;

    /**
     * The code for hidden text.
     */
    public final static ANSIColor CONCEALED = ANSIColor.CONCEALED;

    /**
     * The code for black text.
     */
    public final static ANSIColor BLACK = ANSIColor.BLACK;

    /**
     * The code for red text.
     */
    public final static ANSIColor RED = ANSIColor.RED;

    /**
     * The code for green text.
     */
    public final static ANSIColor GREEN = ANSIColor.GREEN;

    /**
     * The code for yellow text.
     */
    public final static ANSIColor YELLOW = ANSIColor.YELLOW;

    /**
     * The code for blue text.
     */
    public final static ANSIColor BLUE = ANSIColor.BLUE;

    /**
     * The code for magenta text.
     */
    public final static ANSIColor MAGENTA = ANSIColor.MAGENTA;

    /**
     * The code for cyan text.
     */
    public final static ANSIColor CYAN = ANSIColor.CYAN;

    /**
     * The code for white text.
     */
    public final static ANSIColor WHITE = ANSIColor.WHITE;

    /**
     * The code for black background.
     */
    public final static ANSIColor ON_BLACK = ANSIColor.ON_BLACK;

    /**
     * The code for red background.
     */
    public final static ANSIColor ON_RED = ANSIColor.ON_RED;

    /**
     * The code for green background.
     */
    public final static ANSIColor ON_GREEN = ANSIColor.ON_GREEN;

    /**
     * The code for yellow background.
     */
    public final static ANSIColor ON_YELLOW = ANSIColor.ON_YELLOW;

    /**
     * The code for blue background.
     */
    public final static ANSIColor ON_BLUE = ANSIColor.ON_BLUE;

    /**
     * The code for magenta background.
     */
    public final static ANSIColor ON_MAGENTA = ANSIColor.ON_MAGENTA;

    /**
     * The code for cyan background.
     */
    public final static ANSIColor ON_CYAN = ANSIColor.ON_CYAN;

    /**
     * The code for white background.
     */
    public final static ANSIColor ON_WHITE = ANSIColor.ON_WHITE;
    
    public final static String CLASS_WIDTH_PROPERTY_KEY = "qualog.classwidth";
    public final static String COLUMNAR_PROPERTY_KEY = "qualog.columnar";
    public final static String FILE_WIDTH_PROPERTY_KEY = "qualog.filewidth";
    public final static String LEVEL_PROPERTY_KEY = "qualog.level";
    public final static String LINE_WIDTH_PROPERTY_KEY = "qualog.linewidth";
    public final static String METHOD_WIDTH_PROPERTY_KEY = "qualog.methodwidth";
    public final static String SHOW_CLASSES_PROPERTY_KEY = "qualog.showclasses";
    public final static String SHOW_FILES_PROPERTY_KEY = "qualog.showfiles";
    public final static String VERBOSE_PROPERTY_KEY = "qualog.verbose";
    
    public final static QlLevel LEVEL0 = new QlLevel(0);
    public final static QlLevel LEVEL1 = new QlLevel(1);
    public final static QlLevel LEVEL2 = new QlLevel(2);
    public final static QlLevel LEVEL3 = new QlLevel(3);
    public final static QlLevel LEVEL4 = new QlLevel(4);
    public final static QlLevel LEVEL5 = new QlLevel(5);
    public final static QlLevel LEVEL6 = new QlLevel(6);
    public final static QlLevel LEVEL7 = new QlLevel(7);
    public final static QlLevel LEVEL8 = new QlLevel(8);
    public final static QlLevel LEVEL9 = new QlLevel(9);

    public static final int NO_OUTPUT = QlWriter.NO_OUTPUT;

    public static final int QUIET = QlWriter.QUIET;
    
    public static final int VERBOSE = QlWriter.VERBOSE;
    
    /**
     * The default number of stack trace elements to display in a stack.
     */
    protected static final int DEFAULT_STACK_DEPTH = 5;

    protected static QlWriter writer;

    protected static QlTimer timer;

    static {
        writer = new QlWriter();
        timer = new QlTimer();
        
        String verStr = System.getProperty(VERBOSE_PROPERTY_KEY);
        if (verStr == null) {
            verStr = System.getProperty("verbose");
        }

        if (verStr != null) {
            boolean verbose = Boolean.valueOf(verStr).booleanValue();
            QlLevel level = LEVEL5;

            String lvlStr = System.getProperty(LEVEL_PROPERTY_KEY);
            if (lvlStr != null) {
                level = new QlLevel((new Integer(lvlStr)).intValue());
            }

            if (verbose) {
                setOutput(VERBOSE, level);
                System.out.println("Qualog, version " + VERSION);
            }
        }
        
        if (System.getProperty("os.name").equals("Linux")) {
            writer.setUseColor(true);
        }
        
        String showFilesStr = System.getProperty(SHOW_FILES_PROPERTY_KEY);
        if (showFilesStr != null) {
            writer.showFiles = (new Boolean(showFilesStr)).booleanValue();
        }

        String showClassesStr = System.getProperty(SHOW_CLASSES_PROPERTY_KEY);
        if (showClassesStr != null) {
            writer.showClasses = new Boolean(showClassesStr);
        }

        String columnarStr = System.getProperty(COLUMNAR_PROPERTY_KEY);
        if (columnarStr != null) {
            writer.columns = new Boolean(columnarStr);
        }

        String fileWidthStr = System.getProperty(FILE_WIDTH_PROPERTY_KEY);
        if (fileWidthStr != null) {
            writer.fileWidth = new Integer(fileWidthStr);
        }

        String lineWidthStr = System.getProperty(LINE_WIDTH_PROPERTY_KEY);
        if (lineWidthStr != null) {
            writer.lineWidth = new Integer(lineWidthStr);
        }

        String classWidthStr = System.getProperty(CLASS_WIDTH_PROPERTY_KEY);
        if (classWidthStr != null) {
            writer.classWidth = new Integer(classWidthStr);
        }

        String methodWidthStr = System.getProperty(METHOD_WIDTH_PROPERTY_KEY);
        if (methodWidthStr != null) {
            writer.functionWidth = new Integer(methodWidthStr);
        }
    }

    public static boolean isLoggable(QlLevel level) {
        return writer.isLoggable(level);
    }

    public static void setDisabled(Class cls) {
        addFilter(new QlClassFilter(cls, null));
    }

    public static void addFilter(QlFilter filter) {
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
        tr.Ace.red("fileName: " + fileName);
        writer.setFileColor(fileName, color);
    }

    public static void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int funcWidth) {
        writer.set(columns, fileWidth, lineWidth, classWidth, funcWidth);
    }

    public static void setVerbose(boolean verbose) {
        setOutput(VERBOSE, verbose ? LEVEL5 : null);
    }

    public static void setQuiet(boolean quiet) {
        setOutput(QUIET, LEVEL5);
    }

    public static void setOutput(int type, QlLevel level) {
        writer.setOutput(type, level);
    }

    public static void setQuiet(QlLevel level) {
        writer.setOutput(QUIET, level);
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

    public static boolean end() {
        return timer.end();
    }

    public static boolean stack(QlLevel level, 
                                EnumSet<ANSIColor> msgColors,
                                String name,
                                Object obj,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        return writer.stack(level, msgColors, name, obj, fileColor, classColor, methodColor, numFrames);
    }

    public static boolean stack(EnumSet<ANSIColor> msgColors,
                                String msg, 
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        return stack(LEVEL5, msgColors, msg, fileColor, classColor, methodColor, numFrames);
    }

    public static boolean stack(QlLevel level,
                                ANSIColor msgColor,
                                String msg,
                                ANSIColor fileColor,
                                ANSIColor classColor,
                                ANSIColor methodColor,
                                int numFrames) {
        return stack(level, EnumSet.of(msgColor), msg, fileColor, classColor, methodColor, numFrames);
    }

    public synchronized static boolean stack(QlLevel lvl,
                                             EnumSet<ANSIColor> msgColor,
                                             String msg,
                                             ANSIColor fileColor,
                                             ANSIColor classColor,
                                             ANSIColor methodColor,
                                             int numFrames) {
        return writer.stack(lvl, msgColor, msg, fileColor, classColor, methodColor, numFrames);
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

    //--- autogenerated by makeqlog

    public static boolean stack(Object obj) {
        return stack(LEVEL5, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(ANSIColor color, Object obj) {
        return stack(LEVEL5, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(EnumSet<ANSIColor> colors, Object obj) {
        return stack(LEVEL5, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, Object obj) {
        return stack(level, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, ANSIColor color, Object obj) {
        return stack(level, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, EnumSet<ANSIColor> colors, Object obj) {
        return stack(level, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(String name, Object obj) {
        return stack(LEVEL5, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(ANSIColor color, String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(EnumSet<ANSIColor> colors, String name, Object obj) {
        return stack(LEVEL5, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, String name, Object obj) {
        return stack(level, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, ANSIColor color, String name, Object obj) {
        return stack(level, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(QlLevel level, EnumSet<ANSIColor> colors, String name, Object obj) {
        return stack(level, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, DEFAULT_STACK_DEPTH);
    }

    public static boolean stack(Object obj, int depth) {
        return stack(LEVEL5, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(ANSIColor color, Object obj, int depth) {
        return stack(LEVEL5, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(EnumSet<ANSIColor> colors, Object obj, int depth) {
        return stack(LEVEL5, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, Object obj, int depth) {
        return stack(level, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, ANSIColor color, Object obj, int depth) {
        return stack(level, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, EnumSet<ANSIColor> colors, Object obj, int depth) {
        return stack(level, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(String name, Object obj, int depth) {
        return stack(LEVEL5, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(ANSIColor color, String name, Object obj, int depth) {
        return stack(LEVEL5, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(EnumSet<ANSIColor> colors, String name, Object obj, int depth) {
        return stack(LEVEL5, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, String name, Object obj, int depth) {
        return stack(level, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, ANSIColor color, String name, Object obj, int depth) {
        return stack(level, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean stack(QlLevel level, EnumSet<ANSIColor> colors, String name, Object obj, int depth) {
        return stack(level, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, depth);
    }

    public static boolean none(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.NONE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean none(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.NONE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean none(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.NONE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean none(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.NONE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean bold(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BOLD), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean bold(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BOLD), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean bold(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BOLD), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean bold(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BOLD), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underscore(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.UNDERSCORE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underscore(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.UNDERSCORE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underscore(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.UNDERSCORE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underscore(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.UNDERSCORE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underline(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.UNDERLINE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underline(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.UNDERLINE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underline(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.UNDERLINE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean underline(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.UNDERLINE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blink(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLINK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blink(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLINK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blink(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLINK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blink(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLINK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean reverse(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.REVERSE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean reverse(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.REVERSE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean reverse(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.REVERSE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean reverse(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.REVERSE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean concealed(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.CONCEALED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean concealed(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.CONCEALED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean concealed(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.CONCEALED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean concealed(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.CONCEALED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean black(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLACK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean black(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLACK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean black(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLACK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean black(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLACK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean red(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.RED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean red(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.RED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean red(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.RED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean red(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.RED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean green(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.GREEN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean green(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.GREEN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean green(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.GREEN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean green(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.GREEN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean yellow(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.YELLOW), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean yellow(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.YELLOW), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean yellow(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.YELLOW), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean yellow(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.YELLOW), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blue(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLUE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blue(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLUE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blue(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.BLUE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean blue(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.BLUE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean magenta(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.MAGENTA), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean magenta(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.MAGENTA), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean magenta(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.MAGENTA), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean magenta(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.MAGENTA), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean cyan(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.CYAN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean cyan(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.CYAN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean cyan(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.CYAN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean cyan(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.CYAN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean white(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.WHITE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean white(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.WHITE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean white(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.WHITE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean white(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.WHITE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlack(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_BLACK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlack(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_BLACK), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlack(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_BLACK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlack(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_BLACK), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onRed(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_RED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onRed(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_RED), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onRed(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_RED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onRed(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_RED), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onGreen(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_GREEN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onGreen(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_GREEN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onGreen(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_GREEN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onGreen(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_GREEN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onYellow(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_YELLOW), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onYellow(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_YELLOW), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onYellow(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_YELLOW), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onYellow(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_YELLOW), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlue(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_BLUE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlue(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_BLUE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlue(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_BLUE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onBlue(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_BLUE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onMagenta(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_MAGENTA), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onMagenta(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_MAGENTA), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onMagenta(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_MAGENTA), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onMagenta(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_MAGENTA), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onCyan(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_CYAN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onCyan(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_CYAN), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onCyan(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_CYAN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onCyan(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_CYAN), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onWhite(Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_WHITE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onWhite(QlLevel level, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_WHITE), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onWhite(String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(ANSIColor.ON_WHITE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean onWhite(QlLevel level, String name, Object obj) {
        return stack(level, EnumSet.of(ANSIColor.ON_WHITE), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(Object obj) {
        return stack(LEVEL5, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(ANSIColor color, Object obj) {
        return stack(LEVEL5, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(EnumSet<ANSIColor> colors, Object obj) {
        return stack(LEVEL5, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, Object obj) {
        return stack(level, NO_COLORS, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, ANSIColor color, Object obj) {
        return stack(level, EnumSet.of(color), null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, EnumSet<ANSIColor> colors, Object obj) {
        return stack(level, colors, null, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(String name, Object obj) {
        return stack(LEVEL5, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(ANSIColor color, String name, Object obj) {
        return stack(LEVEL5, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(EnumSet<ANSIColor> colors, String name, Object obj) {
        return stack(LEVEL5, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, String name, Object obj) {
        return stack(level, NO_COLORS, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, ANSIColor color, String name, Object obj) {
        return stack(level, EnumSet.of(color), name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }

    public static boolean log(QlLevel level, EnumSet<ANSIColor> colors, String name, Object obj) {
        return stack(level, colors, name, obj, NO_COLOR, NO_COLOR, NO_COLOR, 1);
    }


    //--- end of autogenerated section.
    
    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }    

}
