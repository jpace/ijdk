package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Settings for logging messages.
 */
public class LogLineSettings {
    private int fileWidth = 25;
    private int lineWidth = 5;
    private int functionWidth = 25;
    private int classWidth = 35;

    private boolean showFiles = true;
    private boolean showClasses = true;

    private boolean useColumns = true;

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
