package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class KeyValueTest extends Parameterized {
    private KeyValue<String, Double> kvOne123 = KeyValue.of("one", 1.23);

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void getKey(String expKey, Double expValue, KeyValue<String, Double> kv) {
        assertEqual(expKey, kv.getKey(), message("kv", kv));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void key(String expKey, Double expValue, KeyValue<String, Double> kv) {
        assertEqual(expKey, kv.key(), message("kv", kv));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void getValue(String expKey, Double expValue, KeyValue<String, Double> kv) {
        assertEqual(expValue, kv.getValue(), message("kv", kv));
    }

    @Test @Parameters(method="parametersForAccessors") @TestCaseName("{method} {index} {params}")
    public void value(String expKey, Double expValue, KeyValue<String, Double> kv) {
        assertEqual(expValue, kv.value(), message("kv", kv));
    }

    private List<Object[]> parametersForAccessors() {
        return paramsList(params("one", 1.23, kvOne123),
                          params(null,  1.23, new KeyValue<String, Double>(null, 1.23)),
                          params("one", null, new KeyValue<String, Double>("one", null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void equalsTest(boolean expected, KeyValue<K, V> kv, Object other) {
        boolean result = kv.equals(other);
        assertEqual(expected, result, message("kv", kv, "other", other));
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
        assertEqual(1171979044, kv.hashCode());
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void toStringTest(String expected, KeyValue<K, V> kv, String separator) {
        String result = separator == null ? kv.toString() : kv.toString(separator);
        assertEqual(expected, result, message("kv", kv, "separator", separator));
    }    

    private java.util.List<Object[]> parametersForToStringTest() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        return paramsList(params("one => 1.23", kv, null),
                          params("one: 1.23", kv, ": "));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public <K, V> void compareTo(Integer expected, KeyValue<K, V> x, KeyValue<K, V> y) {
        int result = x.compareTo(y);
        assertEqual(result, expected, message("x", x, "y", y));        
    }
    
    private java.util.List<Object[]> parametersForCompareTo() {
        KeyValue<String, Double> aa = KeyValue.of("one", 1.2);
        KeyValue<String, Double> ab = KeyValue.of("one", 2.4);
        KeyValue<String, Double> ba = KeyValue.of("two", 1.2);

        // StringBuilder is not comparable:
        StringBuilder notComparable = new StringBuilder("one");
        
        return paramsList(params(0,  aa, aa),
                          params(0,  aa, KeyValue.of("one", 1.2)),
                          params(0,  KeyValue.of("one", 1.2), aa),
                          params(-1, aa, ba),
                          params(1,  ba, aa),
                          params(-1, aa, ba),
                          params(-1, aa, ab),
                          params(1,  ab, aa),
                          // @todo although these are equal, compareTo returns -1, as if they are not.
                          params(-1, KeyValue.of(notComparable, 1.2), KeyValue.of(notComparable, 1.2)),
                          params(-1, KeyValue.of(1.2, notComparable), KeyValue.of(1.2, notComparable)));
    }
}
