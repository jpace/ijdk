package org.incava.ijdk.util;

import ijdk.collect.List;
import java.util.Collection;
import junit.framework.TestCase;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.*;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestANSI {
    @Test
    @Parameters
    public String test(int code, String ansiName) {
        String expected = String.valueOf((char)27) + "[" + code + "m";
        assertEqual(expected, ansiName);
        return ansiName;
    }

    private List<Object[]> parametersForTest() {
        return List.<Object[]>of(
            objary(0, ANSI.NONE),
            objary(0, ANSI.RESET),
            objary(4, ANSI.UNDERSCORE),
            objary(4, ANSI.UNDERLINE),
            objary(5, ANSI.BLINK),
            objary(7, ANSI.REVERSE),
            objary(8, ANSI.CONCEALED),
            objary(30, ANSI.BLACK),
            objary(31, ANSI.RED),
            objary(32, ANSI.GREEN),
            objary(33, ANSI.YELLOW),
            objary(34, ANSI.BLUE),
            objary(35, ANSI.MAGENTA),
            objary(36, ANSI.CYAN),
            objary(37, ANSI.WHITE),
            objary(40, ANSI.ON_BLACK),
            objary(41, ANSI.ON_RED),
            objary(42, ANSI.ON_GREEN),
            objary(43, ANSI.ON_YELLOW),
            objary(44, ANSI.ON_BLUE),
            objary(45, ANSI.ON_MAGENTA),
            objary(46, ANSI.ON_CYAN),
            objary(47, ANSI.ON_WHITE));
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

    @Test
    @Parameters
    public void rgbToCode(int expected, int r, int g, int b) {
        int code = ANSI.toCode(r, g, b);
        assertEqual(expected, code);
    }

    private List<Object[]> parametersForRgbToCode() {
        return List.<Object[]>of(
            objary(16, 0, 0, 0),
            objary(88, 127, 0, 0),
            objary(28, 0, 127, 0),
            // multiple RGBs map to single ANSI codes:
            objary(18, 0, 0, 127),
            objary(18, 0, 0, 86),
            objary(18, 0, 0, 95));
    }

    @Test
    @Parameters
    public void newRgbToCode(int expected, int r, int g, int b) {
        int code = ANSI.toCode(new RGB(r, g, b));
        assertEqual(expected, code);
    }

    private List<Object[]> parametersForNewRgbToCode() {
        return List.<Object[]>of(
            // multiple RGBs map to single ANSI codes:
            objary(18, 0, 0, 127),
            objary(18, 0, 0, 86),
            objary(18, 0, 0, 95));
    }
}
