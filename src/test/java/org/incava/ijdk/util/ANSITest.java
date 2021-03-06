package org.incava.ijdk.util;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

public class ANSITest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public String test(int code, String ansiName) {
        String expected = String.valueOf((char)27) + "[" + code + "m";
        assertEqual(expected, ansiName);
        return ansiName;
    }

    private java.util.List<Object[]> parametersForTest() {
        return paramsList(params(0, ANSI.NONE),
                          params(0, ANSI.RESET),
                          params(4, ANSI.UNDERSCORE),
                          params(4, ANSI.UNDERLINE),
                          params(5, ANSI.BLINK),
                          params(7, ANSI.REVERSE),
                          params(8, ANSI.CONCEALED),
                          params(30, ANSI.BLACK),
                          params(31, ANSI.RED),
                          params(32, ANSI.GREEN),
                          params(33, ANSI.YELLOW),
                          params(34, ANSI.BLUE),
                          params(35, ANSI.MAGENTA),
                          params(36, ANSI.CYAN),
                          params(37, ANSI.WHITE),
                          params(40, ANSI.ON_BLACK),
                          params(41, ANSI.ON_RED),
                          params(42, ANSI.ON_GREEN),
                          params(43, ANSI.ON_YELLOW),
                          params(44, ANSI.ON_BLUE),
                          params(45, ANSI.ON_MAGENTA),
                          params(46, ANSI.ON_CYAN),
                          params(47, ANSI.ON_WHITE));
    }

    @Test
    public void rgbToForeground_0_0_0() {
        String color = ANSI.foreground(0, 0, 0);
        String expected = String.valueOf((char)27) + "[38;5;" + 16 + "m";
        assertEqual(expected, color);
    }

    @Test
    public void rgbToBackground_0_0_0() {
        String color = ANSI.background(0, 0, 0);
        String expected = String.valueOf((char)27) + "[48;5;" + 16 + "m";
        assertEqual(expected, color);
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void rgbToCode(int expected, int r, int g, int b) {
        int code = ANSI.toCode(r, g, b);
        assertEqual(expected, code);
    }

    private java.util.List<Object[]> parametersForRgbToCode() {
        return paramsList(params(16, 0, 0, 0),
                          params(88, 127, 0, 0),
                          params(28, 0, 127, 0),
                          // multiple RGBs map to single ANSI codes:
                          params(18, 0, 0, 127),
                          params(18, 0, 0, 86),
                          params(18, 0, 0, 95));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void newRgbToCode(int expected, int r, int g, int b) {
        int code = ANSI.toCode(new RGB(r, g, b));
        assertEqual(expected, code);
    }

    private java.util.List<Object[]> parametersForNewRgbToCode() {
        // multiple RGBs map to single ANSI codes:
        return paramsList(params(18, 0, 0, 127),
                          params(18, 0, 0, 86),
                          params(18, 0, 0, 95));
    }
}
