package org.incava.ijdk.util;

import java.util.Collection;
import java.util.Map;

public class MultiMapEntry<K, V> implements Map.Entry<K, Collection<V>>, Comparable<Map.Entry<K, Collection<V>>> {
    private final K key;
    private Collection<V> value;

    public MultiMapEntry(K key, Collection<V> value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public Collection<V> getValue() {
        return value;
    }

    public Collection<V> setValue(Collection<V> newValue) {
        Collection<V> oldValue = value;
        value = newValue;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj instanceof MultiMapEntry) {
            MultiMapEntry<?, ?> other = (MultiMapEntry<?, ?>)obj;
            return areEqual(key, other.getKey()) && areEqual(value, other.getValue());
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    public int compareTo(Map.Entry<K, Collection<V>> other) {
        int cmp = compareObjects(key, other.getKey());
        if (cmp == 0) {
            cmp = compareObjects(value, other.getValue());
        }
        return cmp;
    }

    public boolean areEqual(Object first, Object second) {
        return first == null ? second == null : first.equals(second);
    }

    @SuppressWarnings("unchecked")
    public int compareObjects(Object first, Object second) {
        if (first == second) { // NOPMD
            return 0;
        }
        else if (first == null) {
            return 1;
        }
        else if (second == null) {
            return -1;
        }
        else if (first instanceof Comparable && second instanceof Comparable) {
            return ((Comparable)first).compareTo((Comparable)second);
        }
        else {
            return 1;
        }
    }
}
