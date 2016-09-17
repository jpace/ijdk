package ijdk.lang;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestArrayy extends TestCase {
    public TestArrayy(String name) {
        super(name);
    }

    public void testCtor() {
        Arrayy ary = new Arrayy("this", "is", "a", "test");
    }
}
