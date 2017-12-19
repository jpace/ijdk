package org.incava.ijdk.tuple;

import org.incava.ijdk.lang.KeyValue;

/**
 * A pair of Strings.
 */
public class StringPair extends Pair<String, String> {
    public static StringPair of(String first, String second) {
        return new StringPair(first, second);
    }
    
    public StringPair(String first, String second) {
        super(first, second);
    }
}
