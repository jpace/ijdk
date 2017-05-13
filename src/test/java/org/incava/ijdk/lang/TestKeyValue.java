package org.incava.ijdk.lang;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class TestKeyValue {
    @Test
    public void init() {
        KeyValue<String, Double> kv = new KeyValue<String, Double>("one", 1.23);
        assertEqual("one", kv.key());
        assertEqual(new Double(1.23), kv.value());
    }

    @Test
    public void of() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        assertEqual("one", kv.key());
        assertEqual(new Double(1.23), kv.value());
    }

    @Test
    public void getKey() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        assertEqual("one", kv.getKey());
    }

    @Test
    public void getValue() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        assertEqual(1.23, kv.getValue());
    }

    @Test
    public void equals() {
        KeyValue<String, Double> kv1 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv2 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv3 = KeyValue.of("one", 4.56);
        KeyValue<String, Double> kv4 = KeyValue.of("two", 1.23);

        assertEqual(true, kv1.equals(kv2));
        assertEqual(true, kv2.equals(kv1));

        assertEqual(false, kv1.equals(kv3));
        assertEqual(false, kv1.equals(kv4));
    }

    @Test
    public void hashCode_test() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        assertEqual(1171979044, kv.hashCode());
    }

    @Test
    @Parameters
    public <K, V> void toString_test(String expected, KeyValue<K, V> kv, String separator) {
        String result = separator == null ? kv.toString() : kv.toString(separator);
        assertEqual(expected, result, message("kv", kv, "separator", separator));
    }    

    private java.util.List<Object[]> parametersForToString_test() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        return paramsList(params("one => 1.23", kv, null),
                          params("one: 1.23", kv, ": "));
    }

    @Test
    @Parameters
    public <K, V> void compareTo(Integer expected, KeyValue<K, V> x, KeyValue<K, V> y) {
        int result = x.compareTo(y);
        assertEqual(result, expected, message("x", x, "y", y));        
    }
    
    private java.util.List<Object[]> parametersForCompareTo() {
        KeyValue<String, Double> aa = KeyValue.of("one", 1.2);
        KeyValue<String, Double> ab = KeyValue.of("one", 2.4);
        KeyValue<String, Double> ba = KeyValue.of("two", 1.2);
        
        return paramsList(params(0, aa, aa),
                          params(0, aa, KeyValue.of("one", 1.2)),
                          params(0, KeyValue.of("one", 1.2), aa),
                          params(-1, aa, ba),
                          params(1, ba, aa),
                          params(-1, aa, ba),
                          params(-1, aa, ab),
                          params(1, ab, aa),
                          // not comparable:
                          params(-1, KeyValue.of(new StringBuilder("one"), 1.2), KeyValue.of(new StringBuilder("one"), 1.2)),
                          params(-1, KeyValue.of(1.2, new StringBuilder("one")), KeyValue.of(1.2, new StringBuilder("one"))));
    }
}