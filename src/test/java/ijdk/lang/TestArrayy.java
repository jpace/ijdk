package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestArrayy extends TestCase {
    public TestArrayy(String name) {
        super(name);
    }

    // ctor

    public Arrayy assertAccessors(Object value, int index, Arrayy ary) {
        assertEquals(value, ary.getArray()[index]);
        assertEquals(value, ary.ary()[index]);
        return ary;
    }

    public void testCtorNullArray() {
        Arrayy ary = new Arrayy((Object[])null);
        assertEquals(null, ary.getArray());
        assertEquals(null, ary.ary());
    }

    public void testCtorNullObject() {
        Arrayy ary = new Arrayy((Object)null);
        assertAccessors(null, 0, ary);
    }

    public void testCtorNotEmpty() {
        Arrayy ary = new Arrayy("a", "b", "c");
        assertAccessors("a", 0, ary);
        assertAccessors("b", 1, ary);
        assertAccessors("c", 2, ary);
    }

    // asList

    public Arrayy assertAsList(ArrayList<Object> expected, Arrayy ary) {
        assertEquals(expected, ary.asList());
        return ary;
    }

    public void testAsListNullArray() {
        assertAsList(null, new Arrayy((Object[])null));
    }

    public void testAsListNullObject() {
        assertAsList(new ArrayList<Object>(Arrays.asList(new Object[] { null })), new Arrayy((Object)null));
    }

    public void testAsListNotEmpty() {
        Arrayy ary = new Arrayy("a", "b", "c");
        assertAsList(new ArrayList<Object>(Arrays.asList(new Object[] { "a", "b", "c" })), ary);
    }

    // areAllNull

    public Arrayy assertAreAllNull(boolean expected, Arrayy ary) {
        assertEquals(expected, ary.areAllNull());
        return ary;
    }

    public void testAreAllNullNullArray() {
        assertAreAllNull(false, new Arrayy((Object[])null));
    }

    public void testAreAllNullNullObject() {
        assertAreAllNull(true, new Arrayy((Object)null));
    }

    public void testAreAllNullNotEmpty() {
        Arrayy ary = new Arrayy("a", "b", "c");
        assertAreAllNull(false, ary);
    }

    // areAllNull

    public Arrayy assertIsAnyNull(boolean expected, Arrayy ary) {
        assertEquals(expected, ary.isAnyNull());
        return ary;
    }

    public void testIsAnyNullNullArray() {
        assertIsAnyNull(false, new Arrayy((Object[])null));
    }

    public void testIsAnyNullOnlyNullObject() {
        assertIsAnyNull(true, new Arrayy((Object)null));
    }

    public void testIsAnyNullOneNullObject() {
        assertIsAnyNull(true, new Arrayy("a", null));
    }

    public void testIsAnyNullNotEmpty() {
        Arrayy ary = new Arrayy("a", "b", "c");
        assertIsAnyNull(false, ary);
    }
}
