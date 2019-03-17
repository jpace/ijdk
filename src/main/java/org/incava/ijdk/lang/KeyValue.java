package org.incava.ijdk.lang;

import java.util.Arrays;
import org.incava.ijdk.collect.Array;

/**
 * Otherwise known as a Pair. This class, unlike <code>org.incava.ijdk.lang.Pair</code>, does not
 * require that the key and value types be Comparable, using their <code>compareTo</code> methods in
 * <code>KeyValue#compareTo</code> if they are subclasses of Comparable.
 */
public class KeyValue<K, V> implements Comparable<KeyValue<K, V>>, HasInstanceValues {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @param value the value
     * @return a new KeyValue pair
     */
    public static <K, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>(key, value);
    }

    /**
     * Splits the string, using the delimiter, which is a regular expression.
     *
     * @param str the string to split
     * @param regexp the regular expression
     * @return a new KeyValue pair
     */
    public static KeyValue<String, String> split(String str, String regexp) {
        String[] elements = str == null ? null : str.split(regexp, 2);
        return elements != null && elements.length == 2 ? new KeyValue<>(elements[0], elements[1]) : null;
    }
    
    private final K key;
    private final V value;
    
    /**
     * Constructs a KeyValue. Either <code>key</code> or <code>value</code> can be null.
     *
     * @param key the key
     * @param value the value
     */
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key.
     *
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * Returns the key.
     *
     * @return the key
     */
    public K key() {
        return key;
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public V value() {
        return value;
    }

    /**
     * Returns whether the other object is equal to the wrapped object, including whether they are
     * both null. If <code>other</code> is a <code>Obj</code>, then its wrapped object is
     * compared.
     *
     * @param obj the object to compare to the wrapped object
     * @return whether the given object equals this one
     */
    public boolean equals(Object obj) {
        if (obj instanceof KeyValue) {
            KeyValue<?, ?> other = (KeyValue)obj;
            return Objects.equals(this, other);
        }
        else {
            return false;
        }
    }

    /**
     * Returns the hash code of the wrapped object. Returns 0 if the referenced object is null.
     *
     * @return the hash code
     */
    public int hashCode() {
        return Objects.hashCode(this);
    }

    /**
     * Returns this key/value pair as a string, in the form "#{key} =&gt; #{value}".
     *
     * @return this object as a string
     */
    public String toString() {
        return toString(" => ");
    }

    /**
     * Returns this key/value pair as a string, using the given separator, in the form
     * "#{key}#{separator}#{value}".
     *
     * @param sep the separator to use
     * @return this object as a string
     */
    public String toString(String sep) {
        return Objects.toString(this, sep);
    }

    /**
     * Compares the given object to this one, using their <code>compareTo</code> methods if the key
     * and/or value implement <code>Comparable</code>.
     *
     * @param other the KeyValue to compare to this one
     * @return the comparison value, which is -1, 0, or 1
     */
    public int compareTo(KeyValue<K, V> other) {
        int cmp = compare(this.key, other.key);
        if (cmp == 0) {
            return compare(this.value, other.value);
        }
        else {
            return cmp;
        }
    }

    /**
     * Compares the given objects, using their <code>compareTo</code> method if they implement
     * <code>Comparable</code>. Returns -1 if the objects do not implement <code>Comparable</code>.
     *
     * @return the comparison value, which is -1, 0, or 1
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> int compare(T x, T y) {
        if (x instanceof Comparable) {
            Comparable cx = (Comparable)x;
            Comparable cy = (Comparable)y;
            return Comp.compare(cx, cy);
        }
        else {
            return -1;
        }
    }

    /**
     * Returns the values that comprise this object in terms of equality and hash codes. This is the
     * key and the value.
     */
    public Array<Object> getInstanceValues() {
        return Array.of(key, value);
    }
}
