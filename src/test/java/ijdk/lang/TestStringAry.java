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

    public void testNull() {
        assertIsEmpty(true, null);
    }    

    public void testEmpty() {
        assertIsEmpty(true, new String[0]);
    }    

    public void testHasElement() {
        assertIsEmpty(false, new String[] { "x" });
    }

    // areEqual

    public void assertAreEqual(boolean expected, String[] x, String[] y) {
        StringAry xa = new StringAry(x);
        StringAry ya = new StringAry(y);
        
        assertEquals("x: " + xa.toString() + "; y: " + ya.toString(), expected, xa.equals(y));
    }

    public void testBothNull() {
        assertAreEqual(true, null, null);
    }    

    public void testNullEmpty() {
        assertAreEqual(true, null, new String[0]);
    }    

    public void testEmptyNull() {
        assertAreEqual(true, new String[0], null);
    }    

    public void testEmptyEmpty() {
        assertAreEqual(true, new String[0], new String[0]);
    } 

    public void testElementEmpty() {
        assertAreEqual(false, new String[] { "x" }, new String[0]);
    }

    public void testEmptyElement() {
        assertAreEqual(false, new String[0], new String[] { "x" });
    }

    public void testElementNull() {
        assertAreEqual(false, new String[] { "x" }, null);
    }

    public void testNullElement() {
        assertAreEqual(false, null, new String[] { "x" });
    }

    public void testSingleElementMatch() {
        assertAreEqual(true, new String[] { "x" }, new String[] { "x" });
    }

    public void testSingleElementNoMatch() {
        assertAreEqual(false, new String[] { "x" }, new String[] { "y" });
    }

    public void testTwoOneElements() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "x" });
    }

    public void testOneTwoElements() {
        assertAreEqual(false, new String[] { "x" }, new String[] { "x", "y" });
    }

    public void testTwoElementsMatch() {
        assertAreEqual(true, new String[] { "x", "y" }, new String[] { "x", "y" });
    }

    public void testTwoElementsNoMatch() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "x", "z" });
    }

    public void testTwoElementsMisordered() {
        assertAreEqual(false, new String[] { "x", "y" }, new String[] { "y", "x" });
    }    
}
