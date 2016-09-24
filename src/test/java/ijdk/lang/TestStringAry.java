package ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestStringAry extends TestCase {
    public TestStringAry(String name) {
        super(name);
    }

    // isEmpty

    public void assertIsEmpty(boolean expected, String[] ary) {
        StringAry strAry = new StringAry(ary);
        assertEquals("ary: " + strAry.toString(), expected, strAry.isEmpty());
    }

    public void testIsEmptyNull() {
        assertIsEmpty(true, null);
    }    

    public void testIsEmptyEmpty() {
        assertIsEmpty(true, new String[0]);
    }    

    public void testIsEmptyHasElement() {
        assertIsEmpty(false, new String[] { "x" });
    }

    // areEqual

    public void assertStringAryEquals(boolean expected, String[] x, String[] y) {
        StringAry xa = new StringAry(x);
        StringAry ya = new StringAry(y);
        
        assertEquals("x: " + xa.toString() + "; y: " + ya.toString(), expected, xa.equals(y));
    }

    public void testEqualsBothNull() {
        assertStringAryEquals(true, null, null);
    }    

    public void testEqualsNullEmpty() {
        assertStringAryEquals(true, null, new String[0]);
    }    

    public void testEqualsEmptyNull() {
        assertStringAryEquals(true, new String[0], null);
    }    

    public void testEqualsEmptyEmpty() {
        assertStringAryEquals(true, new String[0], new String[0]);
    } 

    public void testEqualsElementEmpty() {
        assertStringAryEquals(false, new String[] { "x" }, new String[0]);
    }

    public void testEqualsEmptyElement() {
        assertStringAryEquals(false, new String[0], new String[] { "x" });
    }

    public void testEqualsElementNull() {
        assertStringAryEquals(false, new String[] { "x" }, null);
    }

    public void testEqualsNullElement() {
        assertStringAryEquals(false, null, new String[] { "x" });
    }

    public void testEqualsSingleElementMatch() {
        assertStringAryEquals(true, new String[] { "x" }, new String[] { "x" });
    }

    public void testEqualsSingleElementNoMatch() {
        assertStringAryEquals(false, new String[] { "x" }, new String[] { "y" });
    }

    public void testEqualsTwoOneElements() {
        assertStringAryEquals(false, new String[] { "x", "y" }, new String[] { "x" });
    }

    public void testEqualsOneTwoElements() {
        assertStringAryEquals(false, new String[] { "x" }, new String[] { "x", "y" });
    }

    public void testEqualsTwoElementsMatch() {
        assertStringAryEquals(true, new String[] { "x", "y" }, new String[] { "x", "y" });
    }

    public void testEqualsTwoElementsNoMatch() {
        assertStringAryEquals(false, new String[] { "x", "y" }, new String[] { "x", "z" });
    }

    public void testEqualsTwoElementsMisordered() {
        assertStringAryEquals(false, new String[] { "x", "y" }, new String[] { "y", "x" });
    }
}
