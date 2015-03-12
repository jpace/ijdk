package org.incava.ijdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import junit.framework.TestCase;

public class TestCollectionExt extends TestCase {
    public static final Collection<String> SOURCE = Arrays.asList(new String[] { "one", "two", "three", "four" });
    public static final Collection<String> IDENTICAL = Arrays.asList(new String[] { "one", "two", "three", "four" });
    public static final Collection<String> SUBSET = Arrays.asList(new String[] { "one" });
    public static final Collection<String> SUPERSET = Arrays.asList(new String[] { "one", "two", "three", "four", "five" });
    public static final Collection<String> OVERLAP = Arrays.asList(new String[] { "two", "three", "four", "five" });
    public static final Collection<String> MISMATCH = Arrays.asList(new String[] { "five", "six" });
    public static final Collection<String> EMPTY = new ArrayList<String>();

    public TestCollectionExt(String name) {
        super(name);
    }

    public <T> void assertHasAny(boolean exp, Collection<T> src, Collection<T> tgt) {
        String msg = "CollectionExt.hasAny(src: '" + src + "', tgt: '" + tgt + "'";
        assertEquals(msg, exp, CollectionExt.hasAny(src, tgt));
    }

    public <T> void assertHasAll(boolean exp, Collection<T> src, Collection<T> tgt) {
        String msg = "CollectionExt.hasAll(src: '" + src + "', tgt: '" + tgt + "'";
        assertEquals(msg, exp, CollectionExt.hasAll(src, tgt));
    }

    public void testHasAny() {
        assertHasAny(true, SOURCE, SOURCE);
        assertHasAny(true, SOURCE, IDENTICAL);
        assertHasAny(true, SOURCE, SUBSET);
        assertHasAny(true, SOURCE, SUPERSET);
        assertHasAny(true, SOURCE, OVERLAP);

        assertHasAny(false, SOURCE, EMPTY);
        assertHasAny(false, SOURCE, null);
        assertHasAny(false, SOURCE, MISMATCH);

        assertHasAny(false, null,   EMPTY);
        assertHasAny(false, null,   null);
        assertHasAny(false, null,   MISMATCH);
    }

    public void testHasAll() {
        assertHasAll(true, SOURCE, SOURCE);
        assertHasAll(true, SOURCE, IDENTICAL);
        assertHasAll(true, SOURCE, SUBSET);
        assertHasAll(true, SOURCE, EMPTY);

        assertHasAll(true, EMPTY, EMPTY);

        assertHasAll(false, SOURCE, SUPERSET);
        assertHasAll(false, SOURCE, OVERLAP);
        assertHasAll(false, SOURCE, null);
        assertHasAll(false, SOURCE, MISMATCH);

        assertHasAll(false, null,   EMPTY);
        assertHasAll(false, null,   null);
        assertHasAll(false, null,   MISMATCH);
    }
}
