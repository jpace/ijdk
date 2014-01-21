package org.incava.ijdk.log.types;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.incava.ijdk.log.Level;
import org.incava.ijdk.log.output.ItemColors;

public class LogElementFactory {
    private static final Map<Class<?>, Class<? extends LogElement>> clsToElmtClasses = new HashMap<Class<?>, Class<? extends LogElement>>();
    
    public static void add(Class<?> cls, Class<? extends LogElement> elmtCls) {
        clsToElmtClasses.put(cls, elmtCls);
    }

    public static Class<? extends LogElement> findElmtClass(Object obj) {
        Class<?> objCls = obj.getClass();
        for (Map.Entry<Class<?>, Class<? extends LogElement>> entry : clsToElmtClasses.entrySet()) {
            if (entry.getKey().isAssignableFrom(objCls)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static LogElement createLogElement(Class<? extends LogElement> elmtCls, Level level, ItemColors colors, String name, Object obj, int numFrames) {
        try {
            Constructor<?> ctor = elmtCls.getConstructor(Level.class, ItemColors.class, String.class, Object.class, int.class);
            return (LogElement)ctor.newInstance(level, colors, name, obj, numFrames);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static LogElement create(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        if (obj == null) {
            return new LogElement(level, colors, name, obj, numFrames);
        }
        else {
            Class<? extends LogElement> elmtCls = findElmtClass(obj);
            if (elmtCls != null) {
                return createLogElement(elmtCls, level, colors, name, obj, numFrames);
            }
        }

        if (obj.getClass().isArray()) {
            return LogObjectArray.create(level, colors, name, obj, numFrames);
        }
        else if (obj instanceof Collection) {
            Collection<?> coll = (Collection<?>)obj;
            return new LogCollection(level, colors, name, coll, numFrames);
        }
        else if (obj instanceof Iterator) {
            Iterator<?> it = (Iterator<?>)obj;
            return LogIterator.create(level, colors, name, it, numFrames);
        }
        else if (obj instanceof Enumeration) {
            Enumeration<?> en = (Enumeration<?>)obj;
            return new LogEnumeration(level, colors, name, en, numFrames);
        }
        else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>)obj;
            return new LogMap(level, colors, name, map, numFrames);
        }
        else {
            return new LogElement(level, colors, name, obj, numFrames);
        }
    }
}
