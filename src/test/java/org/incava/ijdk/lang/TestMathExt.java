package org.incava.ijdk.lang;

import org.incava.test.AbstractTestCaseExt;

public class TestMathExt extends AbstractTestCaseExt {
    public TestMathExt(String name) {
        super(name);
    }

    public void testMinEmptyArray() {
        int[] nums = new int[] { };
        int m = MathExt.min(nums);
        assertEquals(Integer.MIN_VALUE, m);
    }

    public void testMinOneElement() {
        int[] nums = new int[] { };
        int m = MathExt.min(nums);
        assertEquals(Integer.MIN_VALUE, m);
    }

    public void testMinMultipleElements() {
        int[] nums = new int[] { 3, 5, 1, 9 };
        int m = MathExt.min(nums);
        assertEquals(1, m);
    }
}
