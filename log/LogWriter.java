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
 * @see org.incava.ijdk.log.Log
 */
public class LogWriter {
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
    
    private LogOutputType outputType = LogOutputType.NONE;

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
    public void setOutput(LogOutputType type, LogLevel level) {
        this.outputType = type;
        this.level      = level;
    }

    public boolean verbose() {
        return outputType.equals(LogOutputType.VERBOSE);
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

    public boolean stack(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        if (!isLoggable(level)) {
            return true;
        }

        String nm = name == null ? "" : name;
        
        if (obj == null) {
            LogElement le = new LogElement(level, logColors, name, obj, numFrames);            
            return stack(le);
        }
        else if (obj instanceof Collection) {
            Collection<?> coll = (Collection<?>)obj;
            return LogCollection.stack(level, logColors, nm, coll, numFrames);
        }
        else if (obj instanceof Iterator) {
            Iterator<?> it = (Iterator<?>)obj;
            return LogIterator.stack(level, logColors, nm, it, numFrames);
        }
        else if (obj instanceof Enumeration) {
            Enumeration<?> en = (Enumeration<?>)obj;
            return LogEnumeration.stack(level, logColors, nm, en, numFrames);
        }
        else if (obj instanceof Object[]) {
            Object[] ary = (Object[])obj;
            return LogObjectArray.stack(level, logColors, nm, ary, numFrames);
        }
        else if (obj instanceof Map) {
            Map m = (Map)obj;
            return LogMap.stack(level, logColors, nm, m, numFrames);
        }
        else if (obj.getClass().isArray()) {
            return stackArray(level, logColors, nm, obj, numFrames);
        }
        else {
            LogElement le = new LogElement(level, logColors, name, obj, numFrames);
            return stack(le);
        }
    }

    protected boolean stackArray(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
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

        return LogObjectArray.stack(level, logColors, name, strs, numFrames);
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
        return !outputType.equals(LogOutputType.NONE) && this.level != null && this.level.compareTo(level) >= 0;
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

    public synchronized boolean stack(LogElement le) {
        LogLevel level = le.getLevel();
        if (!isLoggable(level)) {
            return true;
        }

        // when we're switching threads, reset to a null state.
        if (!Thread.currentThread().equals(prevThread)) {
            reset();
        }

        int numFrames = outputType.equals(LogOutputType.QUIET) ? 1 : le.getNumFrames();
        StackTraceElement[] stack = getStack(numFrames);

        LogColors colors = le.getColors();
        String msg = le.getMessage();

        int fi = findStackStart(stack);
        for (int framesShown = 0; fi < stack.length && framesShown < numFrames; ++fi, ++framesShown) {
            StackTraceElement stackElement = stack[fi];

            if (framesShown == 0 && !isLoggable(stackElement)) {
                return true;
            }

            outputFrame(stackElement, framesShown > 0, colors, msg);
        }
        return true;
    }

    public void outputVerbose(StringBuilder sb, StackTraceElement stackElement, LogColors logColors) {
        if (this.showFiles) {
            outputFileName(sb, logColors, stackElement);
        }
        if (this.showClasses) {
            outputClassAndMethod(sb, logColors, stackElement);
        }
    }

    public void outputFrame(StackTraceElement stackElement, boolean isRepeatedFrame, LogColors logColors, String msg) {
        StringBuilder sb = new StringBuilder();
        
        if (outputType.equals(LogOutputType.VERBOSE)) {
            outputVerbose(sb, stackElement, logColors);
        }
        outputMessage(sb, isRepeatedFrame, logColors.msgColors, msg, stackElement);

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
    
    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    protected void outputFileName(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        String fileName = or(stackElement.getFileName(), "");
        
        sb.append("[");

        if (prevStackElement != null && 
            prevStackElement.getFileName() != null && 
            prevStackElement.getFileName().equals(fileName)) {
            
            int width = this.columns ? Math.min(this.fileWidth, fileName.length()) : fileName.length();
            fileName = StringExt.repeat(' ', width);
        }

        String    lnStr = stackElement.getLineNumber() >= 0 ? String.valueOf(stackElement.getLineNumber()) : "";
        ANSIColor col   = or(logColors.fileColor, fileColors.get(fileName));

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

    protected int addClassForOutput(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        if (isPreviousClass(stackElement)) {
            repeat(sb, this.classWidth, ' ');
            return 0;
        }

        String className = stackElement.getClassName();
        ANSIColor classColor = or(logColors.classColor, classColors.get(className));        
        
        className = className.replaceFirst("(com|org)\\.\\w+\\.", "...");

        if (className.length() > this.classWidth) {
            className = this.classWidth > 0 ? className.substring(0, this.classWidth - 1) + '-' : "";
        }

        append(sb, className, classColor == null ? EnumSet.noneOf(ANSIColor.class) : EnumSet.of(classColor));

        int nSpaces = this.classWidth - className.length();

        if (this.columns) {
            repeat(sb, nSpaces, ' ');
        }

        prevDisplayedClass = className;

        return nSpaces;
    }

    protected boolean isPreviousClass(StackTraceElement stackElement) {
        return prevStackElement != null && prevStackElement.getClassName().equals(stackElement.getClassName());
    }

    protected void outputClassAndMethod(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        sb.append("{");

        int classPadding = addClassForOutput(sb, logColors, stackElement);

        sb.append('#');
        
        String methodName = stackElement.getMethodName();

        ANSIColor methodColor = or(logColors.methodColor, methodColors.get(methodName));
        
        if (isPreviousClass(stackElement) && prevStackElement.getMethodName().equals(methodName)) {
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

        append(sb, methodName, methodColor == null ? EnumSet.noneOf(ANSIColor.class) : EnumSet.of(methodColor));

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
                                 boolean isRepeatedFrame,
                                 EnumSet<ANSIColor> msgColors,
                                 String msg,
                                 StackTraceElement stackElement) {
        msg = isRepeatedFrame ? "\"\"" : getMessage(msg, stackElement, msgColors);        
        sb.append(msg);
    }

    protected String getMessage(String msg, StackTraceElement ste, EnumSet<ANSIColor> msgColors) {
        // remove ending EOLN
        msg = unwindThroughEoln(msg);
        
        if (this.useColor) {
            msg = colorizeMessage(msg, ste, msgColors);
        }

        return msg;
    }

    protected String colorizeMessage(String msg, StackTraceElement stackElement, EnumSet<ANSIColor> msgColors) {
        EnumSet<ANSIColor> colors = msgColors;
        
        if (isEmpty(colors)) {
            ANSIColor col = or(methodColors.get(stackElement.getClassName() + "#" + stackElement.getMethodName()),
                               classColors.get(stackElement.getClassName()),
                               fileColors.get(stackElement.getFileName()));
            if (isTrue(col)) {
                colors = EnumSet.of(col);
            }
        }

        if (isTrue(colors)) {
            StringBuilder sb = new StringBuilder();
            append(sb, msg, colors);
            return sb.toString();
        }
        
        return msg;
    }
    
    protected StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }

    protected StringBuilder repeat(StringBuilder sb, int len, char ch) {
        sb.append(StringExt.repeat(ch, len));
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

    protected void append(StringBuilder sb, String str, EnumSet<ANSIColor> colors) {
        if (isTrue(colors)) {
            for (ANSIColor col : colors) {
                sb.append(col.toString());
            }
            sb.append(str);
            sb.append(ANSIColor.NONE);
        }
        else {
            sb.append(str);
        }
    }
}
