package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.log.output.ANSIColor;
import org.incava.ijdk.log.output.ANSIColorList;
import static org.incava.ijdk.util.IUtil.*;

/**
 * System-wide settings for logging.
 */
public class Configuration {
    public static final Configuration WIDE = new Configuration(25, 5, 35, 25);
    public static final Configuration MEDIUM = new Configuration(15, 4, 25, 15);
    public static final Configuration NARROW = new Configuration(10, 4, 20, 10);
    public static final Configuration DEFAULT = MEDIUM;

    private int fileWidth = 15;
    private int lineWidth = 4;
    private int classWidth = 25;
    private int functionWidth = 25;
    
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

    public Configuration(int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        this();
        
        this.fileWidth = fileWidth;
        this.lineWidth = lineWidth;
        this.classWidth = classWidth;
        this.functionWidth = functionWidth;
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
