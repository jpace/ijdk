package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.message;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class KeyValueTest extends Parameterized {
    private KeyValue<String, Double> kvOne123 = KeyValue.of("one", 1.23);

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void getKey(String expKey, Double expValue, KeyValue<String, Double> kv) {
        String result = kv.getKey();
        assertThat(result, equalTo(expKey));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void key(String expKey, Double expValue, KeyValue<String, Double> kv) {
        Object result = kv.key();
        assertThat(result, equalTo(expKey));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void getValue(String expKey, Double expValue, KeyValue<String, Double> kv) {
        Object result = kv.getValue();
        assertThat(result, equalTo(expValue));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void value(String expKey, Double expValue, KeyValue<String, Double> kv) {
        Object result = kv.value();
        assertThat(result, equalTo(expValue));
    }

    private List<Object[]> parametersForAccessors() {
        return paramsList(params("one", 1.23, kvOne123),
                          params(null,  1.23, new KeyValue<String, Double>(null, 1.23)),
                          params("one", null, new KeyValue<String, Double>("one", null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void equalsTest(boolean expected, KeyValue<K, V> kv, Object other) {
        boolean result = kv.equals(other);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForEqualsTest() {
        return paramsList(params(true,  kvOne123, kvOne123),
                          params(false, kvOne123, null),
                          params(false, kvOne123, "abc"),
                          params(true,  kvOne123, KeyValue.of("one", 1.23)),
                          params(false, kvOne123, KeyValue.of("one", 3.45)),
                          params(false, kvOne123, KeyValue.of("two", 1.23)));
    }

    @Test
    public void hashCodeTest() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        int result = kv.hashCode();
        assertThat(result, equalTo(1162283989));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void toStringTest(String expected, KeyValue<K, V> kv, String separator) {
        String result = separator == null ? kv.toString() : kv.toString(separator);
        assertThat(result, equalTo(expected));
    }

    private java.util.List<Object[]> parametersForToStringTest() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        return paramsList(params("one => 1.23", kv, null),
                          params("one: 1.23", kv, ": "));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void compareTo(Integer expected, KeyValue<K, V> x, KeyValue<K, V> y) {
        int result = x.compareTo(y);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForCompareTo() {
        KeyValue<String, Integer> aa  = KeyValue.of("x", 1);
        KeyValue<String, Integer> aa2 = KeyValue.of("x", 1);
        KeyValue<String, Integer> ab  = KeyValue.of("x", 2);
        KeyValue<String, Integer> ba  = KeyValue.of("y", 1);
        
        return paramsList(params(0,  aa,  aa),
                          params(0,  aa,  aa2),
                          params(0,  aa2, aa),
                          params(-1, aa,  ba),
                          params(1,  ba,  aa));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <K, V> void split(KeyValue<String, String> expected, String str, String delimiter) {
        KeyValue<String, String> result = KeyValue.split(str, delimiter);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForSplit() {
        return paramsList(params(new KeyValue<>("abc", "123"), "abc:123", ":"),
                          params(new KeyValue<String, String>("abc", "345"), "abc:345", ":"),
                          params(new KeyValue<String, String>("def", "123"), "def:123", ":"),
                          params(new KeyValue<String, String>("abc", "123"), "abc=123", "="),
                          params(new KeyValue<String, String>("abc", "123"), "abc: 123", ": "),
                          params(new KeyValue<String, String>("abc", "123"), "abc  : 123", "\\s*:\\s*"),
                          params(new KeyValue<String, String>("abc", "123:345"), "abc:123:345", ":"),
                          params(null, "abc:123", "^"),
                          params(null, null, ""),
                          params(null, null, null),
                          params(null, null, null));
    }
}
