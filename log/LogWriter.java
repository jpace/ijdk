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
 * <code>Log</code> class offers a much cleaner and more thorough interface
 * than this class.</p>
 *
 * @see org.incava.ijdk.log.Log
 */
public class LogWriter {
    public LogMessageSettings settings = new LogMessageSettings();

    public boolean columns = true;

    // this writes to stdout even in Gradle and Ant, which redirect stdout.

    public PrintWriter out = new PrintWriter(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);

    private List<String> packagesSkipped = list("org.incava.ijdk.log", "org.incava.qualog");
    private List<String> classesSkipped = list("tr.Ace");
    private List<String> methodsSkipped = IUtil.<String>list();
    
    private LogOutputType outputType = LogOutputType.NONE;

    private LogColorSettings colorSettings = new LogColorSettings();

    private StackTraceElement prevStackElement = null;
    
    private Thread prevThread = null;

    private String prevDisplayedClass = null;

    private String prevDisplayedMethod = null;

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

    public void clearClassColor(String className) {
        colorSettings.setClassColor(className, null);
    }

    public void setFileColor(String fileName, ANSIColor color) {
        colorSettings.setFileColor(fileName, color);
    }

    public void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        this.columns = columns;

        settings = new LogMessageSettings();
        settings.fileWidth = fileWidth;
        settings.lineWidth = lineWidth;
        settings.classWidth = classWidth;
        settings.functionWidth = functionWidth;
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
        this.colorSettings = new LogColorSettings();
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
            LogElement le = LogElement.create(level, logColors, name, obj, numFrames);            
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
            LogElement le = LogElement.create(level, logColors, name, obj, numFrames);
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
        if (settings.showFiles) {
            outputFileName(sb, logColors, stackElement);
        }
        if (settings.showClasses) {
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
        colorSettings.setUseColor(useColor);
    }

    protected void outputFileName(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        String fileName = or(stackElement.getFileName(), "");
        
        sb.append("[");

        if (isPreviousFileName(stackElement)) {
            int width = this.columns ? Math.min(getFileWidth(), fileName.length()) : fileName.length();
            fileName = StringExt.repeat(' ', width);
        }

        String    lnStr = stackElement.getLineNumber() >= 0 ? String.valueOf(stackElement.getLineNumber()) : "";
        ANSIColor col   = or(logColors.fileColor, colorSettings.getFileColor(fileName));

        if (this.columns) {
            outputColumns(sb, col, fileName, lnStr);
        }
        else if (isNull(col)) {
            LogUtil.appendPadded(sb, fileName + ":" + lnStr, getFileWidth());
        }
        else {
            sb.append(col);
            sb.append(fileName);
            sb.append(':');
            sb.append(lnStr);
            sb.append(ANSIColor.NONE);
            LogUtil.addSpaces(sb, getFileWidth() - fileName.length() - 1 - lnStr.length());
        }

        sb.append("] ");
    }

    public int getLineWidth() {
        return settings.lineWidth;
    }

    public int getFileWidth() {
        return settings.fileWidth;
    }

    public int getFunctionWidth() {
        return settings.functionWidth;
    }

    public int getClassWidth() {
        return settings.classWidth;
    }

    public void setLineWidth(int lnWidth) {
        settings.lineWidth = lnWidth;
    }

    public void setFileWidth(int flWidth) {
        settings.fileWidth = flWidth;
    }

    public void setFunctionWidth(int funcWidth) {
        settings.functionWidth = funcWidth;
    }

    public void setClassWidth(int clsWidth) {
        settings.classWidth = clsWidth;
    }

    public void setShowClasses(boolean showCls) {
        settings.showClasses = showCls;
    }

    public void setShowFiles(boolean showFls) {
        settings.showFiles = showFls;
    }

    protected void outputColumns(StringBuilder sb, ANSIColor col, String fileName, String lnStr) {
        if (isNull(col)) {
            String fmt = "%-" + getFileWidth() + "s %" + getLineWidth() + "s";
            String val = String.format(fmt, fileName, lnStr);
            sb.append(val);
        }
        else {
            sb.append(col).append(fileName).append(ANSIColor.NONE);

            int nSpaces = getFileWidth() - fileName.length() + 1 + getLineWidth() - lnStr.length();
            LogUtil.addSpaces(sb, nSpaces);
            
            sb.append(col).append(lnStr).append(ANSIColor.NONE);
        }
    }

    protected int addClassForOutput(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        if (isPreviousClass(stackElement)) {
            LogUtil.addSpaces(sb, getClassWidth());
            return 0;
        }

        String className = stackElement.getClassName();
        ANSIColor classColor = or(logColors.classColor, colorSettings.getClassColor(className));        
        
        className = className.replaceFirst("(com|org)\\.\\w+\\.", "...");
        className = snip(className, getClassWidth());

        LogUtil.append(sb, className, classColor);

        int nSpaces = getClassWidth() - className.length();

        if (this.columns) {
            LogUtil.addSpaces(sb, nSpaces);
        }

        prevDisplayedClass = className;

        return nSpaces;
    }

    protected boolean isPreviousClass(StackTraceElement stackElement) {
        return prevStackElement != null && prevStackElement.getClassName().equals(stackElement.getClassName());
    }

    protected boolean isPreviousFileName(StackTraceElement stackElement) {
        return prevStackElement != null && ObjectExt.areEqual(prevStackElement.getFileName(), stackElement.getFileName());
    }

    protected boolean isPreviousMethod(StackTraceElement stackElement) {
        return isPreviousClass(stackElement) && ObjectExt.areEqual(prevStackElement.getMethodName(), stackElement.getMethodName());
    }

    protected String snip(String str, int len) {
        return len <= 0 ? "" : str.length() > len ? str.substring(0, len - 1) + '-' : str;
    }

    protected int addMethodForOutput(StringBuilder sb, LogColors logColors, StackTraceElement stackElement, int classPadding) {
        String methodName = stackElement.getMethodName();

        ANSIColor methodColor = or(logColors.methodColor, colorSettings.getMethodColor(stackElement.getClassName(), methodName));
        
        if (isPreviousMethod(stackElement)) {
            methodName = StringExt.repeat(' ', prevDisplayedMethod.length());
            methodColor = null;
        }

        methodName = snip(methodName, getFunctionWidth());

        LogUtil.append(sb, methodName, methodColor);

        int nSpaces = getFunctionWidth() - methodName.length();

        if (!this.columns) {
            LogUtil.addSpaces(sb, classPadding);
        }
        LogUtil.addSpaces(sb, nSpaces);

        prevDisplayedMethod = methodName;
        
        return nSpaces;
    }

    protected void outputClassAndMethod(StringBuilder sb, LogColors logColors, StackTraceElement stackElement) {
        sb.append("{");

        int classPadding = addClassForOutput(sb, logColors, stackElement);

        sb.append('#');

        addMethodForOutput(sb, logColors, stackElement, classPadding);

        sb.append("} ");
    }

    protected void outputMessage(StringBuilder sb,
                                 boolean isRepeatedFrame,
                                 EnumSet<ANSIColor> msgColors,
                                 String msg,
                                 StackTraceElement stackElement) {
        msg = isRepeatedFrame ? "\"\"" : LogMessage.getMessage(msg, stackElement, msgColors, colorSettings);
        sb.append(msg);
    }
    
    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
