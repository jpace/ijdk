package ijdk.lang;

import java.util.Arrays;
import java.util.ArrayList;
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
        // results in warnings; use Common.objary instead:
        Assert.assertEquals(Arrays.asList(new Object[] { Boolean.TRUE, "two" }), Arrays.asList(ICore.<Object>ary(Boolean.TRUE, "two")));
    }

    @Test
    public void strlist() {
        List<String> expected = new ArrayList<String>();
        Assert.assertEquals(expected, ICore.strlist());
    }

    @Test
    public void intlist() {
        List<Integer> expected = new ArrayList<Integer>();
        Assert.assertEquals(expected, ICore.intlist());
    }    
}
