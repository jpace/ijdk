package org.incava.ijdk.log;

import java.lang.reflect.*;
import java.util.*;
import static org.incava.ijdk.util.IUtil.*;


/**
 * Wraps logging for an object.
 */
public class LogObject {

    public enum InspectOptionType { INCLUDE_SUPERCLASSES, INCLUDE_STATIC };

    /**
     * Primitive or quasi-primitive classes, use for toString().
     */
    public static final List<Class<?>> UNDECORATED_CLASSES = Arrays.asList(new Class<?>[] {
            String.class,
            Number.class,
            Character.class,
            Boolean.class
        });
    
    public static Map<String, Object> inspect(Object obj) {
        return inspect(EnumSet.noneOf(InspectOptionType.class), obj);
    }

    public static Map<String, Object> inspect(EnumSet<InspectOptionType> inspOpts, Object obj) {
        return isNotNull(obj) ? inspectForClass(inspOpts, obj.getClass(), obj) : null;
    }

    public static Map<String, Object> inspectFully(Object obj) {
        return inspectFully(EnumSet.of(InspectOptionType.INCLUDE_SUPERCLASSES), obj);
    }

    public static Map<String, Object> inspectFully(EnumSet<InspectOptionType> inspOpts, Object obj) {
        if (isNull(obj)) {
            return null;
        }

        Map<String, Object> attributes = new TreeMap<String, Object>();
        Class cls = obj.getClass();

        while (cls != null) {
            attributes.putAll(inspectForClass(inspOpts, cls, obj));
            cls = inspOpts.contains(InspectOptionType.INCLUDE_SUPERCLASSES) ? cls.getSuperclass() : null;
        }
        return attributes;
    }

    public static Map<String, Object> inspectForClass(EnumSet<InspectOptionType> inspOpts, Class cls, Object obj) {
        if (isNull(obj)) {
            return null;
        }

        Map<String, Object> attributes = new TreeMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fld : fields) {
            tr.Ace.log("fld", fld);

            getFieldValue(inspOpts, obj, fld, attributes);
        }

        return attributes;
    }

    public static void getFieldValue(EnumSet<InspectOptionType> inspOpts, Object obj, Field fld, Map<String, Object> attributes) {
        int mods = fld.getModifiers();

        if (Modifier.isStatic(mods) && !inspOpts.contains(InspectOptionType.INCLUDE_STATIC)) {
            return;
        }

        boolean wasAccessible = fld.isAccessible();

        if (!wasAccessible) {
            fld.setAccessible(true);
        }

        try {
            Object val = fld.get(obj);
            tr.Ace.log("val", val);
            attributes.put(fld.getName(), val);
        }
        catch (IllegalAccessException iae) {
        }

        if (!wasAccessible) {
            fld.setAccessible(false);
        }
    }

    public static String toString(Object obj) {
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

    /**
     * Returns whether the class of the object is assignable from any of the
     * undecorated classes.
     */
    protected static boolean isUndecorated(Object obj) {
        Class<?> objCls = obj.getClass();

        boolean undec = false;

        for (int ci = 0; !undec && ci < UNDECORATED_CLASSES.size(); ++ci) {
            Class<?> undecCls = UNDECORATED_CLASSES.get(ci);
            undec = undecCls.isAssignableFrom(objCls);
        }
        return undec;
    }
}
