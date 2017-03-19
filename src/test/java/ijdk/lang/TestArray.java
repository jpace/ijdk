package ijdk.lang;

import junit.framework.TestCase;

public class TestArray extends TestCase {
    public TestArray(String name) {
        super(name);
    }

    // ctor

    public <T> Array<T> assertAccessors(Object value, int index, Array<T> ary) {
        assertEquals(value, ary.getArray()[index]);
        assertEquals(value, ary.ary()[index]);
        return ary;
    }

    public void testCtorNullArray() {
        Array<Object> ary = new Array<Object>((Object[])null);
        assertEquals(null, ary.getArray());
        assertEquals(null, ary.ary());
    }

    public void testCtorNullObject() {
        Array<Object> ary = new Array<Object>((Object)null);
        assertAccessors(null, 0, ary);
    }

    public void testCtorNotEmpty() {
        Array<String> ary = new Array<String>("a", "b", "c");
        assertAccessors("a", 0, ary);
        assertAccessors("b", 1, ary);
        assertAccessors("c", 2, ary);
    }

    // asList

    public <T extends Object> Array<T> assertAsList(java.util.ArrayList<T> expected, Array<T> ary) {
        assertEquals(expected, ary.asList());
        return ary;
    }

    public void testAsListNullArray() {
        assertAsList(null, new Array<Object>((Object[])null));
    }

    public void testAsListNullObject() {
        assertAsList(new java.util.ArrayList<Object>(java.util.Arrays.asList(new Object[] { null })), new Array<Object>((Object)null));
    }

    public void testAsListNotEmpty() {
        Array<String> ary = new Array<String>("a", "b", "c");
        assertAsList(new java.util.ArrayList<String>(java.util.Arrays.asList(new String[] { "a", "b", "c" })), ary);
    }

    // areAllNull

    public <T> Array<T> assertAreAllNull(boolean expected, Array<T> ary) {
        assertEquals(expected, ary.areAllNull());
        return ary;
    }

    public void testAreAllNullNullArray() {
        assertAreAllNull(false, new Array<Object>((Object[])null));
    }

    public void testAreAllNullNullObject() {
        assertAreAllNull(true, new Array<Object>((Object)null));
    }

    public void testAreAllNullNotEmpty() {
        Array<String > ary = new Array<String>("a", "b", "c");
        assertAreAllNull(false, ary);
    }

    // isAnyNull

    public <T> Array<T> assertIsAnyNull(boolean expected, Array<T> ary) {
        assertEquals(expected, ary.isAnyNull());
        return ary;
    }

    public void testIsAnyNullNullArray() {
        assertIsAnyNull(false, new Array<Object>((Object[])null));
    }

    public void testIsAnyNullOnlyNullObject() {
        assertIsAnyNull(true, new Array<Object>((Object)null));
    }

    public void testIsAnyNullOneNullObject() {
        assertIsAnyNull(true, new Array<String>("a", null));
    }

    public void testIsAnyNullNotEmpty() {
        Array<String> ary = new Array<String>("a", "b", "c");
        assertIsAnyNull(false, ary);
    }

    // length

    public <T> int assertLength(int expected, Array<T> ary) {
        int result = ary.length();
        assertEquals("ary: " + ary, expected, result);
        return result;
    }

    public void testLengthNullArray() {
        assertLength(0, new Array<Object>());
    }

    public void testLengthOne() {
        assertLength(1, new Array<String>("a"));
    }

    public void testLengthTwo() {
        assertLength(2, new Array<String>("a", "b"));
    }
    
    // get

    public Object assertGet(Object expected, int idx, Object ... elmts) {
        Array<Object> ary = new Array<Object>(elmts);
        Object result = ary.get(idx);
        assertEquals("ary: " + ary.toString(), expected, result);
        return result;
    }

    public void testGetEmptyZero() {
        assertGet(null, 0);
    }

    public void testGetEmptyOne() {
        assertGet(null, 1);
    }

    public void testGetEmptyNegative() {
        assertGet(null, -1);
    }

    public void testGetEmptyOneElementZero() {
        assertGet("a", 0, "a");
    }

    public void testGetEmptyOneElementPastRange() {
        assertGet(null, 1, "a");
    }

    public void testGetEmptyOneElementPreRange() {
        assertGet("a", -1, "a");
    }

    public void testGetEmptyTwoElementsZero() {
        assertGet("a", 0, "a", "b");
    }

    public void testGetEmptyTwoElementsOne() {
        assertGet("b", 1, "a", "b");
    }

    public void testGetEmptyTwoElementsTwo() {
        assertGet(null, 2, "a", "b");
    }

    public void testGetEmptyTwoElementsNegativeOne() {
        assertGet("b", -1, "a", "b");
    }

    public void testGetEmptyTwoElementsNegativeTwo() {
        assertGet("a", -2, "a", "b");
    }

    public void testGetEmptyTwoElementsNegativeThree() {
        assertGet(null, -3, "a", "b");
    }

    // isEmpty

    public void assertIsEmpty(boolean expected, Object[] args) {
        Array<Object> ary = new Array<Object>(args);
        assertEquals("ary: " + ary.toString(), expected, ary.isEmpty());
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
}
