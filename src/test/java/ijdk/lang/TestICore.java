package ijdk.lang;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestICore {
    @Test
    public void mapEmpty() {
        TreeMap<String, String> expected = new TreeMap<String, String>();
        Assert.assertEquals(expected, ICore.<String, String>map());        
    }

    @Test
    public void mapOne() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        Assert.assertEquals(expected, ICore.map("one", 1));
    }

    @Test
    public void mapTwo() {
        TreeMap<String, Integer> expected = new TreeMap<String, Integer>();
        expected.put("one", 1);
        expected.put("two", 2);
        Assert.assertEquals(expected, ICore.map("one", 1, "two", 2));
    }

    @Test
    public void ary() {
        Assert.assertEquals(Arrays.asList(new String[] { "one", "two" }), Arrays.asList(ICore.ary("one", "two")));
    }
}
