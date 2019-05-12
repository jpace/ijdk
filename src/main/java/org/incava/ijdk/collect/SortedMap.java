package org.incava.ijdk.collect;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.incava.ijdk.tuple.Pair;

/**
 * A mapping from keys, sorted, to values, via java.util.TreeMap.
 *
 * @param <K> the type of keys of the map
 * @param <K> the type of values of the map
 */
public class SortedMap<K, V> extends TreeMap<K, V> implements Iterable<Map.Entry<K, V>> {
    /**
     * Creates an empty tree map.
     *
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return an empty map
     * @see #empty
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> of() {
        return empty();
    }

    /**
     * Creates an empty tree map. The map is empty, but can be expanded.
     *
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return an empty map
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> empty() {
        return new SortedMap<KeyType, ValueType>();
    }

    /**
     * Creates a map with one key/value pair.
     *
     * @param k1 the first parameter
     * @param v1 the first value
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return the new Map
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> of(KeyType k1, ValueType v1) {
        SortedMap<KeyType, ValueType> map = empty();
        map.put(k1, v1);
        return map;
    }

    /**
     * Creates a map with two key/value pairs.
     *
     * @param k1 the first parameter
     * @param v1 the first value
     * @param k2 the second parameter
     * @param v2 the second value
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return the new Map
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> of(KeyType k1, ValueType v1, KeyType k2, ValueType v2) {
        SortedMap<KeyType, ValueType> map = of(k1, v1);
        map.put(k2, v2);
        return map;
    }

    /**
     * Creates a map with three key/value pairs.
     *
     * @param k1 the first parameter
     * @param v1 the first value
     * @param k2 the second parameter
     * @param v2 the second value
     * @param k3 the third parameter
     * @param v3 the third value
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return the new Map
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> of(KeyType k1, ValueType v1, KeyType k2, ValueType v2, KeyType k3, ValueType v3) {
        SortedMap<KeyType, ValueType> map = of(k1, v1, k2, v2);
        map.put(k3, v3);
        return map;
    }

    /**
     * Creates a map from a list of pairs.
     *
     * @param list the list from which to populate the new map
     * @param <KeyType> the type of keys of the map
     * @param <ValueType> the type of values of the map
     * @return the new Map
     */
    public static <KeyType, ValueType> SortedMap<KeyType, ValueType> of(List<Pair<KeyType, ValueType>> list) {
        return new SortedMap<KeyType, ValueType>(list);
    }

    public static final long serialVersionUID = 1L;

    /**
     * Creates a map from a list of pairs.
     *
     * @param elements the keys and values for the map.
     */
    public SortedMap(List<Pair<K, V>> elements) {
        for (Pair<K, V> element : elements) {
            put(element.first(), element.second());
        }
    }

    /**
     * Creates a IJDK map from a JDK one. The new map is non-backing to the old one.
     *
     * @param other the JDK map from which to populate this one
     */
    public SortedMap(Map<K, V> other) {
        putAll(other);
    }

    /**
     * Creates an empty tree map.
     */
    public SortedMap() {
    }

    /**
     * Adds or replaces the value for the given key, either of which can be null. Returns the map,
     * so this can be chained, in the form <code>m.set("x", 1).set("y", 2).set("z", 3)</code>.
     *
     * @param key the key
     * @param value the value
     * @return this map, updated
     */
    public SortedMap<K, V> set(K key, V value) {
        put(key, value);
        return this;
    }

    /**
     * Returns the keys, the same as <code>keySet</code>.
     *
     * @return the keys as a set
     */
    public Set<K> keys() {
        return keySet();
    }

    /**
     * Returns the entries, the same as <code>entrySet</code>.
     *
     * @return the entries as a set
     */
    public Set<Map.Entry<K, V>> entries() {
        return entrySet();
    }

    /**
     * Returns an iterator over the entries, so this map can be used as an iterable in a for-each
     * block:
     *
     * <pre>
     * SortedMap&lt;String, Integer&gt; map = SortedMap.&lt;String, Integer&gt;of("one", 1, "two", 2);
     * for (Map.Entry&lt;String, Integer&gt; it : map) {
     *     // use it.getKey(), it.getValue()
     * }
     * </pre>
     *
     * @return the entries as a set
     */
    public Iterator<Map.Entry<K, V>> iterator() {
        return entrySet().iterator();
    }

    /**
     * Returns the value for the given entry. If there is no such entry, then the default value is returned.
     *
     * @param key the key
     * @param defValue the value to use if there is no entry for <code>key</code>
     * @return the value or <code>defValue</code>
     */
    public V fetch(K key, V defValue) {
        return containsKey(key) ? get(key) : defValue;
    }

    /**
     * Returns the value for the given entry. If there is no such entry, then an
     * IllegalArgumentException is thrown.
     *
     * @param key the key
     * @return the value or <code>defValue</code>
     * @throws IllegalArgumentException if there is no such key
     */
    public V fetch(K key) {
        if (containsKey(key)) {
            return get(key);
        }
        else {
            throw new IllegalArgumentException("key not found: '" + key + "'");
        }
    }
}
