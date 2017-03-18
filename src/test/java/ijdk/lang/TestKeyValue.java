package ijdk.lang;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class TestKeyValue {
    @Test
    public void init() {
        KeyValue<String, Double> kv = new KeyValue<String, Double>("one", 1.23);
        Assert.assertEquals("one", kv.key);
        Assert.assertEquals(new Double(1.23), kv.value);
    }

    @Test
    public void of() {
        KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
        Assert.assertEquals("one", kv.key);
        Assert.assertEquals(new Double(1.23), kv.value);
    }

    @Test
    public void equals() {
        KeyValue<String, Double> kv1 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv2 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv3 = KeyValue.of("one", 4.56);
        KeyValue<String, Double> kv4 = KeyValue.of("two", 1.23);

        Assert.assertEquals(true, kv1.equals(kv2));
        Assert.assertEquals(true, kv2.equals(kv1));

        Assert.assertEquals(false, kv1.equals(kv3));
        Assert.assertEquals(false, kv1.equals(kv4));
    }

    @Test
    public void hashCode_test() {
        KeyValue<String, Double> kv1 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv2 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv3 = KeyValue.of("one", 4.56);
        KeyValue<String, Double> kv4 = KeyValue.of("two", 1.23);

        Assert.assertEquals(1171979044, kv1.hashCode());
    }

    @Test
    public void toString_test() {
        KeyValue<String, Double> kv1 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv2 = KeyValue.of("one", 1.23);
        KeyValue<String, Double> kv3 = KeyValue.of("one", 4.56);
        KeyValue<String, Double> kv4 = KeyValue.of("two", 1.23);

        Assert.assertEquals("one => 1.23", KeyValue.of("one", 1.23).toString());
        Assert.assertEquals("one: 1.23", KeyValue.of("one", 1.23).toString(": "));
        Assert.assertEquals("one:1.23", KeyValue.of("one", 1.23).toString(":"));
    }    
}
