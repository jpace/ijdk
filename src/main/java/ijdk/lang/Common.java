package ijdk.lang;

/**
 * The most commonly used methods in ijdk, suitable for static import.
 */
public class Common extends ICore {
    /**
     * Returns a string in the form "key: value", where the string for value is via
     * <code>toString(value)</code>.
     */
    public static String keyValue(String key, Object value) {
        return KeyValue.of(key, value).toString(": ");
    }

    /**
     * Returns the object as a string. C style arrays (e.g., Double[]) are run through the toString
     * for java.util.List, giving them better output.
     */
    public static String toString(Object obj) {
        return new Obj(obj).toString();
    }   
}
