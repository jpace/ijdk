package org.incava.test;

import java.util.*;

import static org.incava.test.Assertions.*;

public class StringAssertions {
    public void assertStartsWith(String str, String substr) {
        assertEqual(true, str.startsWith(substr), message("str", str, "substr", substr));
    }
}
