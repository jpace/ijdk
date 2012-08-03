package org.incava.ijdk.log.types;

import java.util.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.output.LogColors;
import org.incava.ijdk.log.output.Writer;
import static org.incava.ijdk.util.IUtil.*;

/**
 * An item that can be logged.
 *
 * @see org.incava.ijdk.log.Log
 */
public class LogElement {
    /**
     * Primitive or quasi-primitive classes, use for toString().
     */
    public static final List<Class<?>> UNDECORATED_CLASSES = Arrays.asList(new Class<?>[] {
            String.class,
            Number.class,
            Character.class,
            Boolean.class
        });
    
    /**
     * Returns whether the class of the object is assignable from any of the
     * undecorated classes.
     */
    protected static boolean isUndecorated(Object obj) {
        Class<?> objCls = obj.getClass();
        for (int ci = 0; ci < UNDECORATED_CLASSES.size(); ++ci) {
            Class<?> undecCls = UNDECORATED_CLASSES.get(ci);
            if (undecCls.isAssignableFrom(objCls)) {
                return true;
            }
        }
        return false;
    }

    private final Level level;
    private final LogColors logColors;
    private final String name;
    private final Object object;
    private final int numFrames;
        
    public LogElement(Level level, LogColors logColors, String name, Object obj, int numFrames) {
        this.level = level;
        this.logColors = logColors;
        this.name = name;
        this.object = obj;
        this.numFrames = numFrames;
    }

    public Level getLevel() {
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
        return (nm == null ? "" : (nm + ": ")) + toString(object);
    }

    public boolean stack(Writer lw) {
        return lw.stack(this);
    }

    public boolean stackEmptyCollection(Writer lw) {
        return lw.stack(level, logColors, name, "()", numFrames);
    }

    public String toString(Object obj) {
        if (isNull(obj)) {
            return "null";
        }

        if (isUndecorated(obj)) {
            return obj.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(obj.toString());
        sb.append(" (");
        sb.append(obj.getClass().getName());
        sb.append(')');
        sb.append(" #");
        sb.append(Integer.toHexString(obj.hashCode()));
        
        return sb.toString();
    }
}
