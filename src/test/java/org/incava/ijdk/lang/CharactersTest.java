package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CharactersTest extends Parameterized {
    public static int compareIgnoreCaseDiff(Character x, Character y) {
        return Character.toUpperCase(x) - Character.toUpperCase(y);
    }

    public static int compareIgnoreCaseEqDiff(Character x, Character y) {
        return x.charValue() == y.charValue() ? 0 : Character.toUpperCase(x) - Character.toUpperCase(y);
    }
    
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isMatch(Boolean expected, Character x, Character y, Boolean ignoreCase) {
        Boolean result = Characters.isMatch(x, y, ignoreCase);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsMatch() {
        return paramsList(params(true,  'a', 'a', false),
                          params(false, 'b', 'a', false),
                          params(true,  'a', 'a', true),
                          params(true,  'a', 'A', true),
                          params(false, 'a', 'A', false));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compare(Integer expected, Character x, Character y, Boolean ignoreCase) {
        Integer result = Characters.compare(x, y, ignoreCase);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForCompare() {
        return paramsList(params(0,   'a', 'a', false),
                          params(0,   'a', 'a', true),
                          params(0,   'A', 'a', true),
                          params(-32, 'A', 'a', false));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void compareIgnoreCase(Integer expected, Character x, Character y) {
        Integer result = Characters.compareIgnoreCase(x, y);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForCompareIgnoreCase() {
        return paramsList(params(0, 'a', 'a'),
                          params(0, 'a', 'a'),
                          params(0, 'A', 'a'),
                          params(0, 'A', 'a'));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isWhitespace(boolean expected, Str str, int index) {
        boolean result = Characters.isWhitespace(str, index);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsWhitespace() {
        return paramsList(params(true,  Str.of(" "),   0),
                          params(true,  Str.of("\f"),  0),
                          params(false, Str.of("x"),   0),
                          params(true,  Str.of("x "), -1),
                          params(false, Str.of(" "),   1));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void isNewLine(boolean expected, Str str, int index) {
        boolean result = Characters.isNewLine(str, index);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForIsNewLine() {
        return paramsList(params(true,  Str.of("\r"),  0),
                          params(true,  Str.of("\n"),  0),
                          params(false, Str.of("\f"),  0),
                          params(true,  Str.of("a\r"), 1),
                          params(false, Str.of("a\r"), 0));
    }

    private long iterations = 500000000L;

    private long runCompareIgnoreCaseOrig(Character x, Character y) {
        long start = System.currentTimeMillis();
        
        for (int idx = 0; idx < iterations; ++idx) {
            Integer result = Characters.compareIgnoreCase(x, y);
        }
        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("case1: " + duration);
        return duration;
    }

    private long runCompareIgnoreCaseDiff(Character x, Character y) {
        long start = System.currentTimeMillis();
        
        for (int idx = 0; idx < iterations; ++idx) {
            Integer result = compareIgnoreCaseDiff(x, y);
        }
        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("case2: " + duration);
        return duration;
    }

    private long runCompareIgnoreCaseEqDiff(Character x, Character y) {
        long start = System.currentTimeMillis();
        
        for (int idx = 0; idx < iterations; ++idx) {
            Integer result = compareIgnoreCaseEqDiff(x, y);
        }
        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("case3: " + duration);
        return duration;
    }

    private void runCompareIgnoreCaseTest(Character x, Character y) {
        System.out.println("test(" + x + ", " + y + ")");
        
        runCompareIgnoreCaseOrig(x, y);
        runCompareIgnoreCaseDiff(x, y);
        runCompareIgnoreCaseEqDiff(x, y);
        System.out.println();
    }

    @Ignore @Test
    public void performanceCompare() {
        System.out.println();
        runCompareIgnoreCaseTest('x', 'x');
        runCompareIgnoreCaseTest('x', 'X');
        runCompareIgnoreCaseTest('x', 'y');
        runCompareIgnoreCaseTest('X', 'X');
        runCompareIgnoreCaseTest('X', 'Y');
    }
}
