package org.incava.ijdk.lang;

/**
 * The most commonly used methods in ijdk, suitable for static import and replete with name
 * collisions.
 */
public class Common extends ICore {
    /**
     * Returns a string in the form "key: value", where the string for value is via
     * <code>toString(value)</code>.
     *
     * @param key the key
     * @param value the value
     * @return the key joined by ": " with the value
     * @see KeyValue
     */
    public static String keyValue(String key, Object value) {
        return KeyValue.of(key, value).toString(": ");
    }

    /**
     * Returns the object as a string. C style arrays (e.g., Double[]) are run through the toString
     * for java.util.List, giving them better output.
     *
     * @param obj the object whence to get a string
     * @return the string
     * @see Obj#toString
     */
    public static String toString(Object obj) {
        return Obj.of(obj).toString();
    }

    /**
     * Converts varargs to Object[]. <code>ary("one", 1)</code> is shorter than <code>new Object[] {
     * "one", 1 }</code>
     *
     * @param args the array, as varargs
     * @return the array as an array
     */
    public static Object[] objary(Object ... args) {
        return args;
    }
}
