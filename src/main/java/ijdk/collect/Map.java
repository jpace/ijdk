package ijdk.collect;

import org.incava.ijdk.tuple.Pair;

public class Map<K, V> extends java.util.TreeMap<K, V> implements Iterable<java.util.Map.Entry<K, V>> {
    /**
     * Creates an empty tree map.
     */
    public static <KeyType, ValueType> Map<KeyType, ValueType> of() {
        return new Map<KeyType, ValueType>();
    }

    /**
     * Creates a map with one key/value pair.
     */
    public static <KeyType, ValueType> Map<KeyType, ValueType> of(KeyType k1, ValueType v1) {
        Map<KeyType, ValueType> map = of();
        map.put(k1, v1);
        return map;
    }

    /**
     * Creates a map with two key/value pairs.
     */
    public static <KeyType, ValueType> Map<KeyType, ValueType> of(KeyType k1, ValueType v1, KeyType k2, ValueType v2) {
        Map<KeyType, ValueType> map = of(k1, v1);
        map.put(k2, v2);
        return map;
    }

    /**
     * Creates a map with three key/value pairs.
     */
    public static <KeyType, ValueType> Map<KeyType, ValueType> of(KeyType k1, ValueType v1, KeyType k2, ValueType v2, KeyType k3, ValueType v3) {
        Map<KeyType, ValueType> map = of(k1, v1, k2, v2);
        map.put(k3, v3);
        return map;
    }

    /**
     * Creates a tree map from a list of pairs.
     */
    public static <KeyType, ValueType> Map<KeyType, ValueType> of(List<Pair<KeyType, ValueType>> list) {
        return new Map<KeyType, ValueType>(list);
    }    

    public static final long serialVersionUID = 1L;

    /**
     * Creates a IJDK map from a list of pairs.
     */
    public Map(List<Pair<K, V>> elements) {
        for (Pair<K, V> element : elements) {
            put(element.first(), element.second());
        }
    }

    /**
     * Creates a IJDK map from a JDK one.
     */
    public Map(java.util.Map<K, V> other) {
        putAll(other);
    }

    /**
     * Creates a IJDK map.
     */
    public Map() {
    }

    /**
     * Adds or replaces the value for the given key, either of which can be null. Returns the map,
     * so this can be chained, in the form <code>m.set("x", 1).set("y", 2).set("z", 3)</code>.
     */
    public Map<K, V> set(K key, V value) {
        put(key, value);
        return this;
    }

    /**
     * Returns the keys, the same as <code>keySet</code>.
     */
    public java.util.Set<K> keys() {
        return keySet();
    }

    /**
     * Returns the entries, the same as <code>entrySet</code>.
     */
    public java.util.Set<java.util.Map.Entry<K, V>> entries() {
        return entrySet();
    }

    /**
     * Returns an iterator over the entries.
     */
    public java.util.Iterator<java.util.Map.Entry<K, V>> iterator() {
        return entrySet().iterator();
    }
}
