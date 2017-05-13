package org.incava.ijdk.lang;

/**
 * Otherwise known as a Pair.
 */
public class KeyValue<K, V> implements Comparable<KeyValue<K, V>> {
    /**
     * Shorter syntax than the default constructor, allowing the compiler to discern the object types.
     */
    public static <K, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>(key, value);
    }
    
    private final K key;
    private final V value;
    
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public boolean equals(Object obj) {
        if (obj instanceof KeyValue) {
            KeyValue<?, ?> other = (KeyValue<?, ?>)obj;
            return new Obj(key).equals(other.key()) && new Obj(value).equals(other.value());
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compareTo(KeyValue<K, V> other) {
        if (this.key instanceof Comparable) {
            Comparable ctk = (Comparable)this.key;
            Comparable cok = (Comparable)other.key;
            int cmp = Comp.compare(ctk, cok);
            if (cmp == 0) {
                if (this.value instanceof Comparable) {
                    Comparable ctv = (Comparable)this.value;
                    Comparable cov = (Comparable)other.value;
                    return Comp.compare(ctv, cov);
                }
                else {
                    return -1;
                }
            }
            else {
                return cmp;
            }
        }
        else {
            return -1;
        }
    }
}