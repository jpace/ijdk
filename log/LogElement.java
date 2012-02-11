package org.incava.ijdk.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import org.incava.ijdk.lang.*;
import static org.incava.ijdk.util.IUtil.*;

/**
 * An item that can be logged.
 *
 * @see org.incava.ijdk.log.Log
 */
public class LogElement {
    public static LogElement create(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        return new LogElement(level, logColors, name, obj, numFrames);
    }

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
        String msg = (name == null ? "" : (name + ": ")) + LogObject.toString(object);
        return msg;
    }

    public boolean stack() {
        return Logger.stack(this);
    }

}
