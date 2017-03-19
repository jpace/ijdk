package ijdk.lang;

import java.util.*;

/**
 * Otherwise known as a Pair (which is already defined in IJDK). An immutable object with public
 * fields for more concise access.
 */
public class KeyValue<K, V> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     */
    public static <K, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>(key, value);
    }
    
    public final K key;
    public final V value;
    
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj instanceof KeyValue) {
            KeyValue<?, ?> other = (KeyValue<?, ?>)obj;
            return new Obj(key).equals(other.key) && new Obj(value).equals(other.value);
        }
        else {
            return false;
        }
    }

    public String toString() {
        return toString(" => ");
    }

    public String toString(String sep) {
        return String.valueOf(key) + sep + String.valueOf(value);
    }

    public int hashCode() {
        return (key == null ? 1 : key.hashCode()) * 119 + (value == null ? 1 : value.hashCode());
    }
}
 