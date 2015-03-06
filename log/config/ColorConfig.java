package org.incava.ijdk.log.config;

import java.util.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.log.output.ANSIColor;
import org.incava.ijdk.log.output.ANSIColorList;
import static org.incava.ijdk.util.IUtil.*;

public class ColorConfig {
    private final Map<String, ANSIColor> packageColors;
    private final Map<String, ANSIColor> classColors;
    private final Map<String, ANSIColor> methodColors;
    private final Map<String, ANSIColor> fileColors;
    private boolean useColor;

    public ColorConfig() {
        packageColors = new HashMap<String, ANSIColor>();
        classColors = new HashMap<String, ANSIColor>();
        methodColors = new HashMap<String, ANSIColor>();
        fileColors = new HashMap<String, ANSIColor>();
        useColor = true;
    }

    public ANSIColor getMethodColor(String className, String methodName) {
        return methodColors.get(className + "#" + methodName);
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        methodColors.put(className + "#" + methodName, color);
    }

    public ANSIColor getPackageColor(String packageName) {
        return packageColors.get(packageName);
    }

    public void setPackageColor(String packageName, ANSIColor color) {
        packageColors.put(packageName, color);
    }

    public ANSIColor getClassColor(String className) {
        return classColors.get(className);
    }

    public void setClassColor(String className, ANSIColor color) {
        classColors.put(className, color);
    }

    public ANSIColor getFileColor(String fileName) {
        return fileColors.get(fileName);
    }    

    public void setFileColor(String fileName, ANSIColor color) {
        fileColors.put(fileName, color);
    }

    public boolean useColor() {
        return useColor;
    }

    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }
}
