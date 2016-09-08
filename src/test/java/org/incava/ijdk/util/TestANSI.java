package org.incava.ijdk.util;

import junit.framework.TestCase;

public class TestANSI extends TestCase {
    public TestANSI(String name) {
        super(name);
    }

    public String assertANSI(int code, String ansiName) {
        String expected = String.valueOf((char)27) + "[" + code + "m";
        assertEquals(expected, ansiName);
        return ansiName;
    }

    public void testNone() {
        assertANSI(0, ANSI.NONE);
    }

    public void testReset() {
        assertANSI(0, ANSI.RESET);
    }

    public void testUnderscore() {
        assertANSI(4, ANSI.UNDERSCORE);
    }
    
    public void testUnderline() {
        assertANSI(4, ANSI.UNDERLINE);
    }
    
    public void testBlink() {
        assertANSI(5, ANSI.BLINK);
    }
    
    public void testReverse() {
        assertANSI(7, ANSI.REVERSE);
    }
    
    public void testConcealed() {
        assertANSI(8, ANSI.CONCEALED);
    }    
    
    public void testBlack() {
        assertANSI(30, ANSI.BLACK);
    }
    
    public void testRed() {
        assertANSI(31, ANSI.RED);
    }
    
    public void testGreen() {
        assertANSI(32, ANSI.GREEN);
    }
    
    public void testYellow() {
        assertANSI(33, ANSI.YELLOW);
    }
    
    public void testBlue() {
        assertANSI(34, ANSI.BLUE);
    }
    
    public void testMagenta() {
        assertANSI(35, ANSI.MAGENTA);
    }
    
    public void testCyan() {
        assertANSI(36, ANSI.CYAN);
    }
    
    public void testWhite() {
        assertANSI(37, ANSI.WHITE);
    }
    
    public void testOnBlack() {
        assertANSI(40, ANSI.ON_BLACK);
    }
    
    public void testOnRed() {
        assertANSI(41, ANSI.ON_RED);
    }
    
    public void testOnGreen() {
        assertANSI(42, ANSI.ON_GREEN);
    }
    
    public void testOnYellow() {
        assertANSI(43, ANSI.ON_YELLOW);
    }
    
    public void testOnBlue() {
        assertANSI(44, ANSI.ON_BLUE);
    }
    
    public void testOnMagenta() {
        assertANSI(45, ANSI.ON_MAGENTA);
    }
    
    public void testOnCyan() {
        assertANSI(46, ANSI.ON_CYAN);
    }
    
    public void testOnWhite() {
        assertANSI(47, ANSI.ON_WHITE);
    }

    public void testRgbToCode_0_0_0() {
        int code = ANSI.toCode(0, 0, 0);
        assertEquals(16, code);
    }

    public void testRgbToForeground_0_0_0() {
        String color = ANSI.foreground(0, 0, 0);
        String expected = String.valueOf((char)27) + "[38;5;" + 16 + "m";
        assertEquals(expected, color);
    }

    public void testRgbToBackground_0_0_0() {
        String color = ANSI.background(0, 0, 0);
        String expected = String.valueOf((char)27) + "[48;5;" + 16 + "m";
        assertEquals(expected, color);
    }

    public void testRgbToCode_127_0_0() {
        int code = ANSI.toCode(127, 0, 0);
        assertEquals(88, code);
    }

    public void testRgbToCode_0_127_0() {
        int code = ANSI.toCode(0, 127, 0);
        assertEquals(28, code);
    }

    // multiple RGBs map to single ANSI codes

    public void testRgbToCode_0_0_127() {
        int code = ANSI.toCode(0, 0, 127);
        assertEquals(18, code);
    }

    public void testRgbToCode_0_0_86() {
        int code = ANSI.toCode(0, 0, 86);
        assertEquals(18, code);
    }

    public void testRgbToCode_0_0_95() {
        int code = ANSI.toCode(0, 0, 95);
        assertEquals(18, code);
    }

    public void testRgbClassToCode_0_0_127() {
        int code = ANSI.toCode(new RGB(0, 0, 127));
        assertEquals(18, code);
    }

    public void testRgbClassToCode_0_0_86() {
        int code = ANSI.toCode(new RGB(0, 0, 86));
        assertEquals(18, code);
    }

    public void testRgbClassToCode_0_0_95() {
        int code = ANSI.toCode(new RGB(0, 0, 95));
        assertEquals(18, code);
    }
}
