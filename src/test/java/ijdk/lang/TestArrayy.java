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

    // areAllNull

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
}
