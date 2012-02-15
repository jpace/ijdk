package org.incava.ijdk.log;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class LogElementFactory {
    public static LogElement create(LogLevel level, LogColors logColors, String name, Object obj, int numFrames) {
        if (obj == null) {
            return new LogElement(level, logColors, name, obj, numFrames);
        }
        else if (obj.getClass().isArray()) {
            return LogObjectArray.create(level, logColors, name, obj, numFrames);
        }
        else if (obj instanceof Collection) {
            Collection coll = (Collection)obj;
            return new LogCollection(level, logColors, name, coll, numFrames);
        }
        else if (obj instanceof Iterator) {
            Iterator<?> it = (Iterator<?>)obj;
            return LogIterator.create(level, logColors, name, it, numFrames);
        }
        else if (obj instanceof Enumeration) {
            Enumeration<?> en = (Enumeration<?>)obj;
            return new LogEnumeration(level, logColors, name, en, numFrames);
        }
        else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>)obj;
            return new LogMap(level, logColors, name, map, numFrames);
        }
        else {
            return new LogElement(level, logColors, name, obj, numFrames);
        }
    }
}
