package org.incava.ijdk.lang;

public class Characters {
    public static boolean isMatch(Character x, Character y, boolean ignoreCase) {
        return x.equals(y) || (ignoreCase && Character.toUpperCase(x) == Character.toUpperCase(y));
    }

    public static int compare(Character x, Character y, boolean ignoreCase) {
        return ignoreCase ? compareIgnoreCase(x, y) : x.compareTo(y);
    }

    public static int compareIgnoreCase(Character x, Character y) {
        int cmp = x.compareTo(y);
        return cmp == 0 ? cmp : Character.toUpperCase(x) - Character.toUpperCase(y);
    }

    public static boolean isWhitespace(Str str, int idx) {
        Character ch = str.get(idx);
        return ch != null && Character.isWhitespace(ch);
    }

    public static boolean isNewLine(Str str, int idx) {
        String nl = "\r\n";
        Character ch = str.get(idx);
        return ch != null && nl.indexOf(ch) >= 0;
    }
}
