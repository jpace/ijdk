package org.incava.ijdk.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.incava.ijdk.lang.StringExt;

/**
 * <p>Writes the logging output, applying filters and decorations. The
 * <code>Log</code> class offers a much cleaner and more thorough interface
 * than this class.</p>
 *
 * @see org.incava.qualog.Log
 */
public class LogWriter {
    public static final int NO_OUTPUT = 0;

    public static final int QUIET = 1;
    
    public static final int VERBOSE = 2;
    
    public int fileWidth = 25;

    public boolean columns = true;

    public int lineWidth = 5;

    public int functionWidth = 25;

    public int classWidth = 35;

    public boolean showFiles = true;

    public boolean showClasses = true;

    // this writes to stdout even in Gradle and Ant, which redirect stdout.

    public PrintWriter out = new PrintWriter(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);

    public List<String> packagesSkipped = new ArrayList<String>(Arrays.asList(
                                                                    new String[] {
                                                                        "org.incava.ijdk.log",
                                                                        "org.incava.qualog",
                                                                    }));
    
    public List<String> classesSkipped = new ArrayList<String>(Arrays.asList(
                                                                   new String[] {
                                                                       "tr.Ace"
                                                                   }));
    
    public List<String> methodsSkipped = new ArrayList<String>(Arrays.asList(
                                                                   new String[] {
                                                                   }));
    
    private int outputType = NO_OUTPUT;

    private Map<String, ANSIColor> packageColors = new HashMap<String, ANSIColor>();

    private Map<String, ANSIColor> classColors = new HashMap<String, ANSIColor>();

    private Map<String, ANSIColor> methodColors = new HashMap<String, ANSIColor>();

    private Map<String, ANSIColor> fileColors = new HashMap<String, ANSIColor>();

    private StackTraceElement prevStackElement = null;
    
    private Thread prevThread = null;

    private String prevDisplayedClass = null;

    private String prevDisplayedMethod = null;

    private LogLevel level = Log.LEVEL9;

    private List<LogFilter> filters = new ArrayList<LogFilter>();

    private boolean useColor = true;

    /**
     * Adds a filter to be applied for output.
     *
     * @see org.incava.ijdk.log.LogFilter
     */
    public void addFilter(LogFilter filter) {
        filters.add(filter);
    }

    public void setDisabled(Class cls) {
        addFilter(new LogClassFilter(cls, null));
    }

    public void setClassColor(String className, ANSIColor color) {
        classColors.put(className, color);
    }

    public void setPackageColor(String pkg, ANSIColor color) {
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        methodColors.put(className + "#" + methodName, color);
    }

    public void clearClassColor(String className) {
        classColors.remove(className);
    }

    public void setFileColor(String fileName, ANSIColor color) {
        fileColors.put(fileName, color);
    }

    public void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        this.columns = columns;
        this.fileWidth = fileWidth;
        this.lineWidth = lineWidth;
        this.classWidth = classWidth;
        this.functionWidth = functionWidth;
    }

    /**
     * Sets the output type and level. Either verbose or quiet can be enabled.
     */
    public void setOutput(int type, LogLevel level) {
        this.outputType = type;
        this.level      = level;
    }

    public boolean verbose() {
        return outputType == VERBOSE;
    }

    public void setColumns(boolean cols) {
        columns = cols;
    }

    public void addClassSkipped(Class cls) {
        addClassSkipped(cls.getName());
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    /**
     * Resets parameters to their defaults.
     */
    public void clear() {
        this.packageColors = new HashMap<String, ANSIColor>();
        this.classColors = new HashMap<String, ANSIColor>();
        this.methodColors = new HashMap<String, ANSIColor>();
        this.fileColors = new HashMap<String, ANSIColor>();
        this.prevStackElement = null;
        this.prevThread = null;
        this.prevDisplayedClass = null;
        this.prevDisplayedMethod = null;
        this.level = Log.LEVEL9;
        this.filters = new ArrayList<LogFilter>();
    }

    public void reset() {
        this.prevThread       = Thread.currentThread();
        this.prevStackElement = null;
    }

    public boolean stack(LogLevel level, 
                         EnumSet<ANSIColor> msgColors,
                         String name,
                         Object obj,
                         ANSIColor fileColor,
                         ANSIColor classColor,
                         ANSIColor methodColor,
                         int numFrames) {

        System.err.println("obj: " + obj);

        if (isLoggable(level)) {
            String nm = name == null ? "" : name;
            System.err.println("nm: " + nm);
        
            if (obj == null) {
                String msg = nm + ": " + "null";
                return stack(level, msgColors, msg, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj instanceof Collection) {
                Collection<?> c = (Collection<?>)obj;
                return LogCollection.stack(level, msgColors, nm, c, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj instanceof Iterator) {
                Iterator<?> it = (Iterator<?>)obj;
                return LogIterator.stack(level, msgColors, nm, it, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj instanceof Enumeration) {
                Enumeration<?> en = (Enumeration<?>)obj;
                return LogEnumeration.stack(level, msgColors, nm, en, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj instanceof Object[]) {
                Object[] ary = (Object[])obj;
                return LogObjectArray.stack(level, msgColors, nm, ary, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj instanceof Map) {
                Map m = (Map)obj;
                return LogMap.stack(level, msgColors, nm, m, fileColor, classColor, methodColor, numFrames);
            }
            else if (obj.getClass().isArray()) {
                String[] strs = null;
                if (obj instanceof byte[]) {
                    byte[] ary = (byte[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                }
                else if (obj instanceof char[]) {
                    char[] ary = (char[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                }
                else if (obj instanceof double[]) {
                    double[] ary = (double[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                } 
                else if (obj instanceof float[]) {
                    float[] ary = (float[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                }
                else if (obj instanceof int[]) {
                    int[] ary = (int[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                }
                else if (obj instanceof long[]) {
                    long[] ary = (long[])obj;
                    strs = new String[ary.length];
                    for (int ai = 0; ai < ary.length; ++ai) {
                        strs[ai] = String.valueOf(ary[ai]);
                    }
                }

                return LogObjectArray.stack(level, msgColors, nm, strs, fileColor, classColor, methodColor, numFrames);
            }
            else {
                String msg = (name == null ? "" : (nm + ": ")) + objectToString(obj);

                System.err.println("msg: " + nm);

                return stack(level, msgColors, msg, fileColor, classColor, methodColor, numFrames);
            }
        }
        else {
            return true;
        }
    }

    public boolean isSkipped(StackTraceElement ste) {
        String className = ste.getClassName();
        if (classesSkipped.contains(className) || methodsSkipped.contains(ste.getMethodName())) {
            return true;
        }
        else {
            for (String pkgName : packagesSkipped) {
                if (className.startsWith(pkgName + ".")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLoggable(LogLevel level) {
        return outputType != NO_OUTPUT && this.level != null && this.level.compareTo(level) >= 0;
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

    public synchronized boolean stack(LogLevel lvl,
                                      EnumSet<ANSIColor> msgColor,
                                      String msg,
                                      ANSIColor fileColor,
                                      ANSIColor classColor,
                                      ANSIColor methodColor,
                                      int numFrames) {
        if (outputType != NO_OUTPUT && level != null && level.compareTo(lvl) >= 0) {

            if (outputType == QUIET) {
                numFrames = 1;
            }

            StackTraceElement[] stack = getStack(numFrames);

            // when we're switching threads, reset to a null state.
            if (!Thread.currentThread().equals(prevThread)) {
                reset();
            }

            int fi = findStackStart(stack);

            for (int framesShown = 0; fi < stack.length && framesShown < numFrames; ++fi, ++framesShown) {
                StackTraceElement stackElement = stack[fi];
                String            className    = stackElement.getClassName();
                String            methodName   = stackElement.getMethodName();
                boolean           isLoggable   = true;
                
                if (framesShown == 0) {
                    for (LogFilter filter : filters) {
                        int    lineNum  = stackElement.getLineNumber();
                        String fileName = stackElement.getFileName();
                            
                        if (filter.isMatch(fileName, lineNum, className, methodName)) {
                            LogLevel flevel = filter.getLevel();
                            isLoggable = flevel != null && level.compareTo(flevel) >= 0;
                        }
                    }
                }

                if (!isLoggable) {
                    return true;
                }

                StringBuffer buf = new StringBuffer();

                if (outputType == VERBOSE) {
                    if (showFiles) {
                        outputFileName(buf, fileColor, stackElement);
                    }
                    if (showClasses) {
                        outputClassAndMethod(buf, classColor, methodColor, stackElement);
                    }
                }
                outputMessage(buf, framesShown, msgColor, msg, stackElement);

                System.err.println("writing: " + buf.toString());
                out.println(buf.toString());

                prevStackElement = stackElement;
            }
        }
        return true;
    }

    void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    protected void outputFileName(StringBuffer buf, ANSIColor fileColor, StackTraceElement stackElement) {
        String fileName = stackElement.getFileName();
        
        buf.append("[");
        if (fileName == null) {
            fileName = "";
        }

        if (prevStackElement != null && 
            prevStackElement.getFileName() != null && 
            prevStackElement.getFileName().equals(fileName)) {
            
            int width = columns ? Math.min(fileWidth, fileName.length()) : fileName.length();
            fileName = StringExt.repeat(' ', width);
        }

        String lnStr = stackElement.getLineNumber() >= 0 ? String.valueOf(stackElement.getLineNumber()) : "";

        ANSIColor col = fileColor;
        if (col == null) {
            col = fileColors.get(fileName);
        }

        if (columns) {
            if (col == null) {
                appendPadded(buf, fileName, fileWidth);
                buf.append(' ');
                buf.append(StringExt.repeat(' ', lineWidth - lnStr.length()));
                buf.append(lnStr);
            }
            else {
                buf.append(col);
                buf.append(fileName);
                buf.append(Log.NONE);
                repeat(buf, fileWidth - fileName.length(), ' ');
                repeat(buf, 1 + lineWidth - lnStr.length(), ' ');
                buf.append(col).append(lnStr).append(Log.NONE);
            }
        }
        else if (col == null) {
            appendPadded(buf, fileName + ":" + lnStr, fileWidth);
        }
        else {
            buf.append(col);
            buf.append(fileName);
            buf.append(':');
            buf.append(lnStr);
            buf.append(Log.NONE);
            repeat(buf, fileWidth - fileName.length() - 1 - lnStr.length(), ' ');
        }

        buf.append("] ");
    }

    protected void outputClassAndMethod(StringBuffer buf,
                                        ANSIColor classColor,
                                        ANSIColor methodColor,
                                        StackTraceElement stackElement) {
        buf.append("{");

        String className = stackElement.getClassName();
        
        if (classColor == null) {
            classColor = classColors.get(className);
        }
        
        boolean sameClass = prevStackElement != null && prevStackElement.getClassName().equals(className);
        if (sameClass) {
            className = StringExt.repeat(' ', prevDisplayedClass.length());
            classColor = null;
        }
        else if (className != null && (className.startsWith("org.") || className.startsWith("com."))) {
            className = "..." + className.substring(className.indexOf('.', 5) + 1);
        }

        int totalWidth = classWidth + 1 + functionWidth;

        int classPadding = 0;
        if (className.length() > classWidth) {
            if (classWidth > 0) {
                className = className.substring(0, classWidth - 1) + '-';
            }
            else {
                className = "";
            }
        }
        else {
            classPadding = classWidth - className.length();
        }

        if (classColor != null) {
            buf.append(classColor);
        }
        buf.append(className);
        if (classColor != null) {
            buf.append(Log.NONE);
        }

        if (columns) {
            repeat(buf, classPadding, ' ');
        }

        prevDisplayedClass = className;

        buf.append('#');
        
        String methodName = stackElement.getMethodName();

        if (methodColor == null) {
            methodColor = methodColors.get(methodName);
        }
        
        if (sameClass && prevStackElement != null && prevStackElement.getMethodName().equals(methodName)) {
            methodName = StringExt.repeat(' ', prevDisplayedMethod.length());
            methodColor = null;
        }

        int methodPadding = 0;
        if (methodName.length() > functionWidth) {
            methodName = methodName.substring(0, functionWidth - 1) + '-';
        }
        else {
            methodPadding = functionWidth - methodName.length();
        }

        if (methodColor != null) {
            buf.append(methodColor);
        }
        buf.append(methodName);
        if (methodColor != null) {
            buf.append(Log.NONE);
        }

        if (!columns) {
            repeat(buf, classPadding, ' ');
        }
        repeat(buf, methodPadding, ' ');

        prevDisplayedMethod = methodName;

        buf.append("} ");
    }

    protected void outputMessage(StringBuffer buf,
                                 int framesShown,
                                 EnumSet<ANSIColor> msgColor,
                                 String msg,
                                 StackTraceElement stackElement) {
        // remove ending EOLN
        if (framesShown > 0) {
            msg = "\"\"";
        }
        else {
            while (msg.length() > 0 && "\r\n".indexOf(msg.charAt(msg.length() - 1)) != -1) {
                msg = msg.substring(0, msg.length() - 1);
            }
            if (useColor) {
                boolean hasColor = false;
                if (msgColor == null || msgColor.isEmpty()) {
                    ANSIColor col = null;
                    col = methodColors.get(stackElement.getClassName() + "#" + stackElement.getMethodName());
                    if (col == null) {
                        col = classColors.get(stackElement.getClassName());
                        if (col == null) {
                            col = fileColors.get(stackElement.getFileName());
                        }
                    }
                    if (col != null) {
                        msg = col.toString() + msg;
                        hasColor = true;
                    }
                }
                else {
                    for (ANSIColor col : msgColor) {
                        msg = col.toString() + msg;
                    }
                    hasColor = true;
                }

                if (hasColor) {
                    msg += Log.NONE;
                }
            }
        }

        buf.append(msg);
    }
    
    protected StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }

    protected StringBuffer repeat(StringBuffer buf, int len, char ch) {
        for (int i = 0; i < len; ++i) {
            buf.append(ch);
        }
        return buf;
    }

    protected void appendPadded(StringBuffer buf, String str, int maxSize) {
        if (str.length() > maxSize) {
            buf.append(str.substring(0, maxSize - 1)).append("-");
        }
        else {
            buf.append(str);
            repeat(buf, maxSize - str.length(), ' ');
        }
    }

    protected String objectToString(Object obj) {
        String str = null;
        if (obj == null) {
            str = "null";
        }
        else {            
            Class<?>[] undecoratedClasses = new Class<?>[] {
                String.class,
                Number.class,
                Character.class,
                Boolean.class
            };

            Class<?> objCls = obj.getClass();

            for (Class<?> undecCls : undecoratedClasses) {
                if (undecCls.isAssignableFrom(objCls)) {
                    str = obj.toString();
                    break;
                }
            }

            if (str == null) {
                str = obj.toString() + " (" + obj.getClass() + ") #" + Integer.toHexString(obj.hashCode());
            }
        }
        return str;
    }
}
