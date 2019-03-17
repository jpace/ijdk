package org.incava.ijdk.version;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class StrUtilTest extends Parameterized {
    private List<Object[]> parametersForGetNumberStrings() {
        String[] ary = new String[] { "6",  "7", "8", "9" };
        return paramsList(params(6,  ary, 0, 13),
                          params(7,  ary, 1, 13),
                          params(13, ary, 4, 13));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getNumberStrings(Integer expected, String[] strs, Integer idx, Integer defValue) {
        Integer result = StrUtil.getNumber(strs, idx, defValue);
        assertThat(result, withContext(message("strs", strs, "idx", idx, "defValue", defValue), equalTo(expected)));
    }

    private List<Object[]> parametersForGetNumberIntegers() {
        Integer[] ary = new Integer[] { 6,  7, 8, 9 };
        return paramsList(params(6,  ary, 0, 13),
                          params(7,  ary, 1, 13),
                          params(13, ary, 4, 13));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void getNumberIntegers(Integer expected, Integer[] ary, Integer idx, Integer defValue) {
        Integer result = StrUtil.getNumber(ary, idx, defValue);
        assertThat(result, withContext(message("ary", ary, "idx", idx, "defValue", defValue), equalTo(expected)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void append(StringBuilder expected, StringBuilder sb, Integer value, boolean prependDot) {
        StringBuilder result = StrUtil.append(sb, value, prependDot);
        // StringBuilder doesn't have equals(StringBuilder)
        assertThat(result.toString(), equalTo(expected.toString()));
    }
    
    private List<Object[]> parametersForAppend() {
        return paramsList(params(new StringBuilder("1.2"), new StringBuilder("1"), 2, true),
                          params(new StringBuilder("12"),  new StringBuilder("1"), 2, false),
                          params(new StringBuilder("1"),   new StringBuilder(),    1, false));
    }
}
