package org.incava.ijdk.lang;

import org.incava.ijdk.lang.ObjectTest;
import org.junit.Assert;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class TestNullableObject {
    @Test
    public void toStringString() {
        assertEqual("one", NullableObject.of("one").toString());
    }

    @Test
    public void toStringNull() {
        assertEqual("null", NullableObject.of(null).toString());
    }

    @Test
    public void toStringArray() {
        assertEqual("[a, b, c]", NullableObject.of(new Object[] { "a", "b", "c" }).toString());
    }    
}
