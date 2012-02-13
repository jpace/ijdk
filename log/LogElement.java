package org.incava.ijdk.log;

import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * An item that can be logged.
 *
 * @see org.incava.ijdk.log.Log
 */
public class LogElement {
    private final LogLevel level;
    private final LogColors logColors;
    private final String name;
    private final Object object;
    private final int numFrames;
        
    public LogElement(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        this.level = level;
        this.logColors = logColors;
        this.name = name;
        this.object = obj;
        this.numFrames = numFrames;
    }

    public LogLevel getLevel() {
        return level;
    }
    
    public LogColors getColors() {
        return logColors;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    public int getNumFrames() {
        return numFrames;
    }
    
    public String getMessage() {
        String nm = getName();
        return (nm == null ? "" : (nm + ": ")) + LogObject.toString(object);
    }

    public boolean stack(LogWriter lw) {
        return lw.stack(this);
    }

    public boolean stackEmptyCollection() {
        return Log.stack(level, logColors, name, "()", numFrames);
    }
}
