package org.incava.ijdk.util;

public class ANSI {
    public final static String NONE       = effect(0);
    public final static String RESET      = effect(0);
    public final static String BOLD       = effect(1);
    public final static String UNDERSCORE = effect(4);
    public final static String UNDERLINE  = effect(4);
    public final static String BLINK      = effect(5);
    public final static String REVERSE    = effect(7);
    public final static String CONCEALED  = effect(8);
    
    public final static String BLACK      = foreground(0);
    public final static String RED        = foreground(1);
    public final static String GREEN      = foreground(2);
    public final static String YELLOW     = foreground(3);
    public final static String BLUE       = foreground(4);
    public final static String MAGENTA    = foreground(5);
    public final static String CYAN       = foreground(6);
    public final static String WHITE      = foreground(7);
    
    public final static String ON_BLACK   = background(0);
    public final static String ON_RED     = background(1);
    public final static String ON_GREEN   = background(2);
    public final static String ON_YELLOW  = background(3);
    public final static String ON_BLUE    = background(4);
    public final static String ON_MAGENTA = background(5);
    public final static String ON_CYAN    = background(6);
    public final static String ON_WHITE   = background(7);
    
    // this behavior changed in Java 1.4.2-01, so this is a char, not a byte. 27 == \e (escape)
    private static final char ESCAPE = (char)27;

    /**
     * Returns a string for one of the basic colors, as a foreground (text) color:
     *
     *     black: 0
     *     red: 1
     *     green: 2
     *     yellow: 3
     *     blue: 4
     *     magenta: 5
     *     cyan: 6
     *     white: 7
     *
     *
     * @param sgrIndex the color index, as above
     * @return the formatting string
     */
    public static String foreground(int sgrIndex) {
        return asString(30 + sgrIndex);
    }

    /**
     * Returns a string for one of the basic colors, as a background color:
     *
     *     black: 0
     *     red: 1
     *     green: 2
     *     yellow: 3
     *     blue: 4
     *     magenta: 5
     *     cyan: 6
     *     white: 7
     *
     * @param sgrIndex the color index, as above
     * @return the formatting string
     */
    public static String background(int sgrIndex) {
        return asString(40 + sgrIndex);
    }
    
    /**
     * Returns a string for a foreground, defined by an RGB value, where red, green, and blue are
     * 0-255.
     *
     * @param red the red value, 0 through 255
     * @param green the green value, 0 through 255
     * @param blue the blue value, 0 through 255
     * @return the formatting string
     */    
    public static String foreground(int red, int green, int blue) {
        int code = toCode(red, green, blue);
        return asString("38;5;" + code);
    }

    /**
     * Returns a string for a background, defined by an RGB value, where red, green, and blue are
     * 0-255.
     *
     * @param red the red value, 0 through 255
     * @param green the green value, 0 through 255
     * @param blue the blue value, 0 through 255
     * @return the formatting string
     */
    public static String background(int red, int green, int blue) {
        int code = toCode(red, green, blue);
        return asString("48;5;" + code);
    }

    /**
     * Returns a string for an effect:
     * 
     *     none: 0
     *     reset: 0
     *     bold: 1
     *     underscore: 4
     *     underline: 4
     *     blink: 5
     *     reverse: 7
     *     concealed: 8
     *
     * @param n the effect, as above
     * @return the formatting string
     */
    public static String effect(int n) {
        return asString(n);
    }

    private static String asString(int code) {
        return asString(String.valueOf(code));
    }

    private static String asString(String code) {
        return "" + ESCAPE + "[" + code + "m";
    }    

    // red, green, blue values are 0-255
    protected static int toCode(int red, int green, int blue) {
        return 16 + 36 * toAnsi(red) + 6 * toAnsi(green) + toAnsi(blue);
    }    

    protected static int toCode(RGB rgb) {
        return toCode(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
    }
    
    private static int toAnsi(int value) {
        return (int)(6.0 * (value / 256.0));
    }
}
