package ijdk.lang;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.*;
import static ijdk.lang.Common.*;

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
        return list(ary("one => 1.23", kv, null),
                    ary("one: 1.23", kv, ": "));
    }    
}
