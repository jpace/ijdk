package org.incava.ijdk.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maps from a single key to a collection of values, the equivalent of <code>Map&lt;String,
 * List&lt;Integer&gt;&gt;</code>. By default the collection type is <code>ArrayList</code>, but it
 * can be any collection, by overriding <code>getCollection</code>.
 */
public class MultiMap<K, V> extends AbstractMap<K, Collection<V>> {
    private Set<Map.Entry<K, Collection<V>>> entrySet;

    public MultiMap() {
        entrySet = null;
    }

    // get entrySet on access; allow to override

    public Set<Map.Entry<K, Collection<V>>> entrySet() {
        if (entrySet == null) {
            entrySet = new HashSet<Map.Entry<K, Collection<V>>>();
        }
        return entrySet;
    }

    public Collection<V> getCollection() {
        return new ArrayList<V>();
    }

    public Collection<V> put(K key, Collection<V> value) {
        Set<Map.Entry<K, Collection<V>>> entries = entrySet();
        for (Map.Entry<K, Collection<V>> entry : entries) {
            if (key == entry.getKey() || (key != null && key.equals(entry.getKey()))) {
                return entry.setValue(value);
            }
        }
        entries.add(new MultiMapEntry<K, V>(key, value));
        return null;
    }

    public Collection<V> add(K key, V value) {
        Collection<V> coll = get(key);
        if (coll == null) {
            coll = getCollection();
            put(key, coll);
        }
        coll.add(value);
        return coll;
    }

    @SafeVarargs
    public final Collection<V> putEach(K key, V ... values) {
        Collection<V> coll = null;
        for (V val : values) {
            coll = add(key, val);
        }
        return coll;
    }
}
