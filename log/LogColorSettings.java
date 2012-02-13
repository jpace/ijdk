package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Settings for colors in logging output.
 */
public class LogColorSettings {
    private Map<String, ANSIColor> packageColors;
    private Map<String, ANSIColor> classColors;
    private Map<String, ANSIColor> methodColors;
    private Map<String, ANSIColor> fileColors;
    private boolean useColor;

    public LogColorSettings() {
        useColor = true;
        packageColors = new HashMap<String, ANSIColor>();
        classColors = new HashMap<String, ANSIColor>();
        methodColors = new HashMap<String, ANSIColor>();
        fileColors = new HashMap<String, ANSIColor>();
    }

    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        methodColors.put(className + "#" + methodName, color);
    }

    public void setPackageColor(String packageName, ANSIColor color) {
        packageColors.put(packageName, color);
    }

    public void setClassColor(String className, ANSIColor color) {
        classColors.put(className, color);
    }

    public void setFileColor(String fileName, ANSIColor color) {
        fileColors.put(fileName, color);
    }

    public boolean useColor() {
        return useColor;
    }

    public ANSIColor getMethodColor(String className, String methodName) {
        return methodColors.get(className + "#" + methodName);
    }

    public ANSIColor getPackageColor(String packageName) {
        return packageColors.get(packageName);
    }

    public ANSIColor getClassColor(String className) {
        return classColors.get(className);
    }

    public ANSIColor getFileColor(String fileName) {
        return fileColors.get(fileName);
    }    

    public ANSIColor getColor(StackTraceElement ste) {
        return or(getMethodColor(ste.getClassName(), ste.getMethodName()),
                  getClassColor(ste.getClassName()),
                  getFileColor(ste.getFileName()));
    }
}
