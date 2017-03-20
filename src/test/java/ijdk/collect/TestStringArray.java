package ijdk.collect;

import junit.framework.TestCase;

public class TestStringArray extends TestCase {
    public TestStringArray(String name) {
        super(name);
    }

    // isEmpty

    public void assertIsEmpty(boolean expected, String[] ary) {
        StringArray strArray = new StringArray(ary);
        assertEquals("ary: " + strArray.toString(), expected, strArray.isEmpty());
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

    public void assertStringArrayEquals(boolean expected, String[] x, String[] y) {
        StringArray xa = new StringArray(x);
        StringArray ya = new StringArray(y);
        
        assertEquals("x: " + xa.toString() + "; y: " + ya.toString(), expected, xa.equals(y));
    }

    public void testEqualsBothNull() {
        assertStringArrayEquals(true, null, null);
    }    

    public void testEqualsNullEmpty() {
        assertStringArrayEquals(true, null, new String[0]);
    }    

    public void testEqualsEmptyNull() {
        assertStringArrayEquals(true, new String[0], null);
    }    

    public void testEqualsEmptyEmpty() {
        assertStringArrayEquals(true, new String[0], new String[0]);
    } 

    public void testEqualsElementEmpty() {
        assertStringArrayEquals(false, new String[] { "x" }, new String[0]);
    }

    public void testEqualsEmptyElement() {
        assertStringArrayEquals(false, new String[0], new String[] { "x" });
    }

    public void testEqualsElementNull() {
        assertStringArrayEquals(false, new String[] { "x" }, null);
    }

    public void testEqualsNullElement() {
        assertStringArrayEquals(false, null, new String[] { "x" });
    }

    public void testEqualsSingleElementMatch() {
        assertStringArrayEquals(true, new String[] { "x" }, new String[] { "x" });
    }

    public void testEqualsSingleElementNoMatch() {
        assertStringArrayEquals(false, new String[] { "x" }, new String[] { "y" });
    }

    public void testEqualsTwoOneElements() {
        assertStringArrayEquals(false, new String[] { "x", "y" }, new String[] { "x" });
    }

    public void testEqualsOneTwoElements() {
        assertStringArrayEquals(false, new String[] { "x" }, new String[] { "x", "y" });
    }

    public void testEqualsTwoElementsMatch() {
        assertStringArrayEquals(true, new String[] { "x", "y" }, new String[] { "x", "y" });
    }

    public void testEqualsTwoElementsNoMatch() {
        assertStringArrayEquals(false, new String[] { "x", "y" }, new String[] { "x", "z" });
    }

    public void testEqualsTwoElementsMisordered() {
        assertStringArrayEquals(false, new String[] { "x", "y" }, new String[] { "y", "x" });
    }
}
