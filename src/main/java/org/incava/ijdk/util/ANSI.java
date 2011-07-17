package org.incava.ijdk.util;


public class ANSI {
    private static String ESC = String.valueOf(new byte[] { 27 });

    public static String makeColor(int n) {
        return ESC + "[" + n + "m";
    }

    public final static String NONE = makeColor(0);
    public final static String RESET = makeColor(0);
    public final static String BOLD = makeColor(1);
    public final static String UNDERSCORE = makeColor(4);
    public final static String UNDERLINE = makeColor(4);
    public final static String BLINK = makeColor(5);
    public final static String REVERSE = makeColor(7);
    public final static String CONCEALED = makeColor(8);
    public final static String BLACK = makeColor(30);
    public final static String RED = makeColor(31);
    public final static String GREEN = makeColor(32);
    public final static String YELLOW = makeColor(33);
    public final static String BLUE = makeColor(34);
    public final static String MAGENTA = makeColor(35);
    public final static String CYAN = makeColor(36);
    public final static String WHITE = makeColor(37);
    public final static String ON_BLACK = makeColor(40);
    public final static String ON_RED = makeColor(41);
    public final static String ON_GREEN = makeColor(42);
    public final static String ON_YELLOW = makeColor(43);
    public final static String ON_BLUE = makeColor(44);
    public final static String ON_MAGENTA = makeColor(45);
    public final static String ON_CYAN = makeColor(46);
    public final static String ON_WHITE = makeColor(47);
    
    public static void main(String[] args) {
        String ESC = new String(new byte[] { 27 });
        System.out.println(ESC + "[34mblue" + ESC + "[0m");
        System.out.println(BOLD + "bold" + RESET);
    }

}
