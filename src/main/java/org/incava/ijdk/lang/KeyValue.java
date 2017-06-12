package org.incava.ijdk.lang;

/**
 * Otherwise known as a Pair. This class, unlike <code>org.incava.ijdk.lang.Pair</code>, does not
 * require that the key and value types be Comparable, using their <code>compareTo</code> methods in
 * <code>KeyValue#compareTo</code> if they are subclasses of Comparable.
 */
public class KeyValue<K, V> implements Comparable<KeyValue<K, V>> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     *
     * @param key the key
     * @param value the value
     * @return a new KeyValue pair
     */
    public static <K, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>(key, value);
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
     * Returns if <code>obj</code> equals this one.
     *
     * @param obj the object to compare to this one
     * @return if <code>obj</code> equals this one
     */
    public boolean equals(Object obj) {
        if (obj instanceof KeyValue) {
            KeyValue<?, ?> other = (KeyValue<?, ?>)obj;
            return NullableObject.of(key).equals(other.key()) && NullableObject.of(value).equals(other.value());
        }
        else {
            return false;
        }
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
        return String.valueOf(key) + sep + String.valueOf(value);
    }

    /**
     * Returns the hash code for the key/value pair.
     * 
     * @return the hash code
     */
    public int hashCode() {
        return (key == null ? 1 : key.hashCode()) * 119 + (value == null ? 1 : value.hashCode());
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
}
