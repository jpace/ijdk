package ijdk.lang;

import java.util.*;
import junit.framework.TestCase;

public class TestArrayy extends TestCase {
    public TestArrayy(String name) {
        super(name);
    }

    // ctor

    public <T> Arrayy<T> assertAccessors(Object value, int index, Arrayy<T> ary) {
        assertEquals(value, ary.getArray()[index]);
        assertEquals(value, ary.ary()[index]);
        return ary;
    }

    public void testCtorNullArray() {
        Arrayy<Object> ary = new Arrayy<Object>((Object[])null);
        assertEquals(null, ary.getArray());
        assertEquals(null, ary.ary());
    }

    public void testCtorNullObject() {
        Arrayy<Object> ary = new Arrayy<Object>((Object)null);
        assertAccessors(null, 0, ary);
    }

    public void testCtorNotEmpty() {
        Arrayy<String> ary = new Arrayy<String>("a", "b", "c");
        assertAccessors("a", 0, ary);
        assertAccessors("b", 1, ary);
        assertAccessors("c", 2, ary);
    }

    // asList

    public <T extends Object> Arrayy<T> assertAsList(ArrayList<T> expected, Arrayy<T> ary) {
        assertEquals(expected, ary.asList());
        return ary;
    }

    public void testAsListNullArray() {
        assertAsList(null, new Arrayy<Object>((Object[])null));
    }

    public void testAsListNullObject() {
        assertAsList(new ArrayList<Object>(Arrays.asList(new Object[] { null })), new Arrayy<Object>((Object)null));
    }

    public void testAsListNotEmpty() {
        Arrayy<String> ary = new Arrayy<String>("a", "b", "c");
        assertAsList(new ArrayList<String>(Arrays.asList(new String[] { "a", "b", "c" })), ary);
    }

    // areAllNull

    public <T> Arrayy<T> assertAreAllNull(boolean expected, Arrayy<T> ary) {
        assertEquals(expected, ary.areAllNull());
        return ary;
    }

    public void testAreAllNullNullArray() {
        assertAreAllNull(false, new Arrayy<Object>((Object[])null));
    }

    public void testAreAllNullNullObject() {
        assertAreAllNull(true, new Arrayy<Object>((Object)null));
    }

    public void testAreAllNullNotEmpty() {
        Arrayy<String > ary = new Arrayy<String>("a", "b", "c");
        assertAreAllNull(false, ary);
    }

    // isAnyNull

    public <T> Arrayy<T> assertIsAnyNull(boolean expected, Arrayy<T> ary) {
        assertEquals(expected, ary.isAnyNull());
        return ary;
    }

    public void testIsAnyNullNullArray() {
        assertIsAnyNull(false, new Arrayy<Object>((Object[])null));
    }

    public void testIsAnyNullOnlyNullObject() {
        assertIsAnyNull(true, new Arrayy<Object>((Object)null));
    }

    public void testIsAnyNullOneNullObject() {
        assertIsAnyNull(true, new Arrayy<String>("a", null));
    }

    public void testIsAnyNullNotEmpty() {
        Arrayy<String> ary = new Arrayy<String>("a", "b", "c");
        assertIsAnyNull(false, ary);
    }

    // length

    public <T> int assertLength(int expected, Arrayy<T> ary) {
        int result = ary.length();
        assertEquals("ary: " + ary, expected, result);
        return result;
    }

    public void testLengthNullArray() {
        assertLength(0, new Arrayy<Object>());
    }

    public void testLengthOne() {
        assertLength(1, new Arrayy<String>("a"));
    }

    public void testLengthTwo() {
        assertLength(2, new Arrayy<String>("a", "b"));
    }
    
    // get

    public Object assertGet(Object expected, int idx, Object ... elmts) {
        Arrayy<Object> ary = new Arrayy<Object>(elmts);
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

}
