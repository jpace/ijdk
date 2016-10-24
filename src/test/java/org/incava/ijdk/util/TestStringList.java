package org.incava.ijdk.util;

import java.util.*;
import junit.framework.TestCase;
import org.incava.ijdk.lang.Closure;

public class TestStringList extends TestCase {
    public TestStringList(String name) {
        super(name);
    }

    // ctor

    public void testCtorEmpty() {
        StringList sl = new StringList();
        assertEquals(0, sl.size());
    }

    public void testCtorSize() {
        StringList sl = new StringList(10);
        assertEquals(0, sl.size());
    }

    public void testCtorCollection() {
        List<String> list = Arrays.asList(new String[] { "one", "two", "three" });
        StringList sl = new StringList(list);
        assertEquals(3, sl.size());
    }

    public void testCtorVarArgsOne() {
        StringList sl = new StringList("one");
        assertEquals(1, sl.size());
        assertEquals("one", sl.get(0));
    }

    public void testCtorVarArgsTwo() {
        StringList sl = new StringList("one", "two");
        assertEquals(2, sl.size());
        assertEquals("one", sl.get(0));
        assertEquals("two", sl.get(1));
    }

    // startsWith

    public void assertAnyStartsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEquals("sl: " + sl + "; substr: " + substr, expected, sl.anyStartsWith(substr));
    }

    public void testAnyStartsWithEmpty() {
        assertAnyStartsWith(false, "o");
    }

    public void testAnyStartsWithMatch() {
        assertAnyStartsWith(true, "o", "one");
    }

    public void testAnyStartsWithNoMatch() {
        assertAnyStartsWith(false, "n", "one");
    }

    // contains

    public void assertAnyContains(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEquals("sl: " + sl + "; substr: " + substr, expected, sl.anyContains(substr));
    }

    public void testAnyContainsEmpty() {
        assertAnyContains(false, "o");
    }

    public void testAnyContainsFirstChar() {
        assertAnyContains(true, "o", "one");
    }

    public void testAnyContainsSecondChar() {
        assertAnyContains(true, "n", "one");
    }

    public void testAnyContainsNoMatch() {
        assertAnyContains(false, "z", "one");
    }

    // ends with

    public void assertAnyEndsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEquals("sl: " + sl + "; substr: " + substr, expected, sl.anyEndsWith(substr));
    }

    public void testAnyEndsWithEmpty() {
        assertAnyEndsWith(false, "o");
    }

    public void testAnyEndsWithMatch() {
        assertAnyEndsWith(true, "e", "one");
    }

    public void testAnyEndsWithNoMatch() {
        assertAnyEndsWith(false, "n", "one");
    }

    // findFirst

    public String assertFindFirst(String expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        String result = sl.findFirst(criteria);
        assertEquals("sl: " + sl + "; criteria: " + criteria, expected, result);
        return result;
    }

    public void testFindFirstNull() {
        assertFindFirst(null, null);
    }    

    public void testFindFirstFirstMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "one".equals(str);
                }
            };
        assertFindFirst("one", criteria, "one", "two");
    }    

    public void testFindFirstSecondMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "two".equals(str);
                }
            };
        assertFindFirst("two", criteria, "one", "two");
    }

    public void testFindFirstNoMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "three".equals(str);
                }
            };
        assertFindFirst(null, criteria, "one", "two");
    }

    // findAll

    public StringList assertFindAll(StringList expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        StringList result = sl.findAll(criteria);
        assertEquals("sl: " + sl + "; criteria: " + criteria, expected, result);
        return result;
    }

    public void testFindAllNull() {
        assertFindAll(new StringList(), null);
    }    

    public void testFindAllFirstMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "one".equals(str);
                }
            };
        assertFindAll(new StringList("one"), criteria, "one", "two");
    }    

    public void testFindAllSecondMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "two".equals(str);
                }
            };
        assertFindAll(new StringList("two"), criteria, "one", "two");
    }

    public void testFindAllTwoElementsMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return str.contains("o");
                }
            };
        assertFindAll(new StringList("one", "two"), criteria, "one", "two", "three");
    }

    public void testFindAllAllMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return str.contains("o");
                }
            };
        assertFindAll(new StringList("one", "two"), criteria, "one", "two");
    }

    public void testFindAllNoMatching() {
        Closure<Boolean, String> criteria = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "three".equals(str);
                }
            };
        assertFindAll(new StringList(), criteria, "one", "two");
    }

    // pop

    public String assertPop(String expected, StringList sl) {
        String result = sl.pop();
        assertEquals(expected, result);
        return result;
    }

    public void testPopEmpty() {
        assertPop(null, new StringList());
    }

    public void testPopNullElement() {
        assertPop(null, new StringList((String)null));
    }

    public void testPopOneElement() {
        assertPop("a", new StringList("a"));
    }

    public void testPopTwoElements() {
        assertPop("b", new StringList("a", "b"));
    }

    // shift

    public String assertShift(String expected, StringList sl) {
        String result = sl.shift();
        assertEquals(expected, result);
        return result;
    }

    public void testShiftEmpty() {
        assertShift(null, new StringList());
    }

    public void testShiftNullElement() {
        assertShift(null, new StringList((String)null));
    }

    public void testShiftOneElement() {
        assertShift("a", new StringList("a"));
    }

    public void testShiftTwoElements() {
        assertShift("a", new StringList("a", "b"));
    }
}
