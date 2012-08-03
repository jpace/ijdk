package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * System-wide settings for logging.
 */
public class Configuration {
    private int fileWidth = 25;
    private int lineWidth = 5;
    private int functionWidth = 25;
    private int classWidth = 35;
    
    private boolean showFiles = true;
    private boolean showClasses = true;
    private boolean useColumns = true;

    private Map<String, ANSIColor> packageColors;
    private Map<String, ANSIColor> classColors;
    private Map<String, ANSIColor> methodColors;
    private Map<String, ANSIColor> fileColors;

    private boolean useColor;

    public Configuration() {
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

    public int getFileWidth() {
        return fileWidth;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public int getFunctionWidth() {
        return functionWidth;
    }

    public int getClassWidth() {
        return classWidth;
    }

    public void setFileWidth(int fileWidth) {
        this.fileWidth = fileWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setFunctionWidth(int functionWidth) {
        this.functionWidth = functionWidth;
    }

    public void setClassWidth(int classWidth) {
        this.classWidth = classWidth;
    }

    public boolean showFiles() {
        return showFiles;
    }

    public boolean showClasses() {
        return showClasses;
    }

    public boolean useColumns() {
        return useColumns;
    }

    public void setShowFiles(boolean showFiles) {
        this.showFiles = showFiles;
    }

    public void setShowClasses(boolean showClasses) {
        this.showClasses = showClasses;
    }

    public void setUseColumns(boolean useColumns) {
        this.useColumns = useColumns;
    }
}
