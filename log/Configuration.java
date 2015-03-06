package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.log.config.ColorConfig;
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

    private final ColorConfig colorConfig;

    public Configuration() {
        colorConfig = new ColorConfig();
    }

    public Configuration(int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        this();
        
        this.fileWidth = fileWidth;
        this.lineWidth = lineWidth;
        this.classWidth = classWidth;
        this.functionWidth = functionWidth;
    }

    public ColorConfig getColorConfig() {
        return colorConfig;
    }

    public ANSIColor getColor(StackTraceElement ste) {
        return or(colorConfig.getMethodColor(ste.getClassName(), ste.getMethodName()),
                  colorConfig.getClassColor(ste.getClassName()),
                  colorConfig.getFileColor(ste.getFileName()));
    }

    public int getFileWidth() {
        return fileWidth;
    }

    public void setFileWidth(int fileWidth) {
        this.fileWidth = fileWidth;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getFunctionWidth() {
        return functionWidth;
    }

    public void setFunctionWidth(int functionWidth) {
        this.functionWidth = functionWidth;
    }

    public int getClassWidth() {
        return classWidth;
    }

    public void setClassWidth(int classWidth) {
        this.classWidth = classWidth;
    }

    public boolean showFiles() {
        return showFiles;
    }

    public void setShowFiles(boolean showFiles) {
        this.showFiles = showFiles;
    }

    public boolean showClasses() {
        return showClasses;
    }

    public void setShowClasses(boolean showClasses) {
        this.showClasses = showClasses;
    }

    public boolean useColumns() {
        return useColumns;
    }

    public void setUseColumns(boolean useColumns) {
        this.useColumns = useColumns;
    }
}
