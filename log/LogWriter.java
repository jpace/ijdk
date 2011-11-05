package org.incava.ijdk.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;


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
        this.columns = cols;
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

        if (!isLoggable(level)) {
            return true;
        }

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
            return stackArray(level, msgColors, nm, obj, fileColor, classColor, methodColor, numFrames);
        }
        else {
            String msg = (name == null ? "" : (nm + ": ")) + LogObject.toString(obj);
            return stack(level, msgColors, msg, fileColor, classColor, methodColor, numFrames);
        }
    }

    protected boolean stackArray(LogLevel level, 
                                 EnumSet<ANSIColor> msgColors,
                                 String name,
                                 Object obj,
                                 ANSIColor fileColor,
                                 ANSIColor classColor,
                                 ANSIColor methodColor,
                                 int numFrames) {
        String[] strs = null;
        if (obj instanceof boolean[]) {
            boolean[] ary = (boolean[])obj;
            strs = BooleanArray.toStringArray(ary);
        }
        else if (obj instanceof byte[]) {
            byte[] ary = (byte[])obj;
            strs = ByteArray.toStringArray(ary);
        }
        else if (obj instanceof char[]) {
            char[] ary = (char[])obj;
            strs = CharArray.toStringArray(ary);
        }
        else if (obj instanceof double[]) {
            double[] ary = (double[])obj;
            strs = DoubleArray.toStringArray(ary);
        } 
        else if (obj instanceof float[]) {
            float[] ary = (float[])obj;
            strs = FloatArray.toStringArray(ary);
        }
        else if (obj instanceof int[]) {
            int[] ary = (int[])obj;
            strs = IntArray.toStringArray(ary);
        }
        else if (obj instanceof long[]) {
            long[] ary = (long[])obj;
            strs = LongArray.toStringArray(ary);
        }
        else if (obj instanceof short[]) {
            short[] ary = (short[])obj;
            strs = ShortArray.toStringArray(ary);
        }

        return LogObjectArray.stack(level, msgColors, name, strs, fileColor, classColor, methodColor, numFrames);
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
        if (outputType == NO_OUTPUT || isNull(level) || level.compareTo(lvl) < 0) {
            return true;
        }

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

            if (framesShown == 0 && !isLoggable(stackElement)) {
                return true;
            }

            outputFrame(stackElement, framesShown, msgColor, msg, fileColor, classColor, methodColor);
        }
        return true;
    }

    public void outputVerbose(StringBuilder sb,
                              StackTraceElement stackElement, 
                              ANSIColor fileColor,
                              ANSIColor classColor,
                              ANSIColor methodColor) {
        if (this.showFiles) {
            outputFileName(sb, fileColor, stackElement);
        }
        if (this.showClasses) {
            outputClassAndMethod(sb, classColor, methodColor, stackElement);
        }
    }

    public void outputFrame(StackTraceElement stackElement, 
                            int framesShown, 
                            EnumSet<ANSIColor> msgColor,
                            String msg,
                            ANSIColor fileColor,
                            ANSIColor classColor,
                            ANSIColor methodColor) {
        StringBuilder sb = new StringBuilder();
        
        if (outputType == VERBOSE) {
            outputVerbose(sb, stackElement, fileColor, classColor, methodColor);
        }
        outputMessage(sb, framesShown, msgColor, msg, stackElement);

        System.err.println("writing: " + sb.toString());
        out.println(sb.toString());

        prevStackElement = stackElement;
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
    
    void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    protected void outputFileName(StringBuilder sb, ANSIColor fileColor, StackTraceElement stackElement) {
        String fileName = or(stackElement.getFileName(), "");
        
        sb.append("[");

        if (prevStackElement != null && 
            prevStackElement.getFileName() != null && 
            prevStackElement.getFileName().equals(fileName)) {
            
            int width = this.columns ? Math.min(this.fileWidth, fileName.length()) : fileName.length();
            fileName = StringExt.repeat(' ', width);
        }

        String    lnStr = stackElement.getLineNumber() >= 0 ? String.valueOf(stackElement.getLineNumber()) : "";
        ANSIColor col   = or(fileColor, fileColors.get(fileName));

        if (this.columns) {
            outputColumns(sb, col, fileName, lnStr);
        }
        else if (isNull(col)) {
            appendPadded(sb, fileName + ":" + lnStr, this.fileWidth);
        }
        else {
            sb.append(col);
            sb.append(fileName);
            sb.append(':');
            sb.append(lnStr);
            sb.append(ANSIColor.NONE);
            repeat(sb, this.fileWidth - fileName.length() - 1 - lnStr.length(), ' ');
        }

        sb.append("] ");
    }

    protected void outputColumns(StringBuilder sb, ANSIColor col, String fileName, String lnStr) {
        if (isNull(col)) {
            appendPadded(sb, fileName, this.fileWidth);
            sb.append(' ');
            sb.append(StringExt.repeat(' ', this.lineWidth - lnStr.length()));
            sb.append(lnStr);
        }
        else {
            sb.append(col);
            sb.append(fileName);
            sb.append(ANSIColor.NONE);
            repeat(sb, this.fileWidth - fileName.length(), ' ');
            repeat(sb, 1 + this.lineWidth - lnStr.length(), ' ');
            sb.append(col).append(lnStr).append(ANSIColor.NONE);
        }
    }

    protected void outputClassAndMethod(StringBuilder sb,
                                        ANSIColor classColor,
                                        ANSIColor methodColor,
                                        StackTraceElement stackElement) {
        sb.append("{");

        String className = stackElement.getClassName();

        classColor = or(classColor, classColors.get(className));
        
        boolean sameClass = prevStackElement != null && prevStackElement.getClassName().equals(className);
        if (sameClass) {
            className = StringExt.repeat(' ', prevDisplayedClass.length());
            classColor = null;
        }
        else if (className != null && (className.startsWith("org.") || className.startsWith("com."))) {
            className = "..." + className.substring(className.indexOf('.', 5) + 1);
        }

        int totalWidth = this.classWidth + 1 + this.functionWidth;

        int classPadding = 0;
        if (className.length() > this.classWidth) {
            className = this.classWidth > 0 ? className.substring(0, this.classWidth - 1) + '-' : "";
        }
        else {
            classPadding = this.classWidth - className.length();
        }

        if (classColor != null) {
            sb.append(classColor);
        }
        sb.append(className);
        if (classColor != null) {
            sb.append(ANSIColor.NONE);
        }

        if (this.columns) {
            repeat(sb, classPadding, ' ');
        }

        prevDisplayedClass = className;

        sb.append('#');
        
        String methodName = stackElement.getMethodName();

        methodColor = or(methodColor, methodColors.get(methodName));
        
        if (sameClass && prevStackElement != null && prevStackElement.getMethodName().equals(methodName)) {
            methodName = StringExt.repeat(' ', prevDisplayedMethod.length());
            methodColor = null;
        }

        int methodPadding = 0;
        if (methodName.length() > this.functionWidth) {
            methodName = methodName.substring(0, this.functionWidth - 1) + '-';
        }
        else {
            methodPadding = this.functionWidth - methodName.length();
        }

        if (methodColor != null) {
            sb.append(methodColor);
        }
        sb.append(methodName);
        if (methodColor != null) {
            sb.append(ANSIColor.NONE);
        }

        if (!this.columns) {
            repeat(sb, classPadding, ' ');
        }
        repeat(sb, methodPadding, ' ');

        prevDisplayedMethod = methodName;

        sb.append("} ");
    }

    protected String unwindThroughEoln(String str) {
        while (str.length() > 0 && "\r\n".indexOf(StringExt.get(str, -1)) != -1) {
            str = StringExt.get(str, 0, -1);
        }
        return str;
    }

    protected void outputMessage(StringBuilder sb,
                                 int framesShown,
                                 EnumSet<ANSIColor> msgColors,
                                 String msg,
                                 StackTraceElement stackElement) {
        msg = framesShown > 0 ? "\"\"" : getMessage(msg, stackElement, msgColors);        
        sb.append(msg);
    }

    protected String getMessage(String msg, StackTraceElement ste, EnumSet<ANSIColor> msgColor) {
        // remove ending EOLN
        msg = unwindThroughEoln(msg);
        
        if (this.useColor) {
            msg = colorizeMessage(msg, ste, msgColor);
        }

        return msg;
    }

    protected String colorizeMessage(String msg, StackTraceElement stackElement, EnumSet<ANSIColor> msgColors) {
        boolean hasColor = false;
        if (msgColors == null || msgColors.isEmpty()) {
            ANSIColor col = null;
            col = or(methodColors.get(stackElement.getClassName() + "#" + stackElement.getMethodName()),
                     classColors.get(stackElement.getClassName()),
                     fileColors.get(stackElement.getFileName()));
            
            if (isTrue(col)) {
                msg = col.toString() + msg;
                hasColor = true;
            }
        }
        else {
            for (ANSIColor col : msgColors) {
                msg = col.toString() + msg;
            }
            hasColor = true;
        }

        if (hasColor) {
            msg += ANSIColor.NONE;
        }

        return msg;
    }
    
    protected StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }

    protected StringBuilder repeat(StringBuilder sb, int len, char ch) {
        for (int i = 0; i < len; ++i) {
            sb.append(ch);
        }
        return sb;
    }

    protected void appendPadded(StringBuilder sb, String str, int maxSize) {
        if (str.length() > maxSize) {
            sb.append(str.substring(0, maxSize - 1)).append("-");
        }
        else {
            sb.append(str);
            repeat(sb, maxSize - str.length(), ' ');
        }
    }

}
