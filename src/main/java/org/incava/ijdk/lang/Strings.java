package org.incava.ijdk.lang;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extensions to the String class. Many of the static methods here are defined as instance methods
 * of the <code>Str</code> class.
 *
 * @see org.incava.ijdk.lang.Str
 */
public class Strings {
    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     *
     * @param str the string to split
     * @param delim the character to split at
     * @param max the maximum number to split
     * @return the array of split strings
     */
    public static String[] split(String str, char delim, int max) {
        return split(str, String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     *
     * @param str the string to split
     * @param delim the string to split at
     * @param max the maximum number to split
     * @return the array of split strings
     */
    public static String[] split(String str, String delim, int max) {
        List<String> list = new Str(str).split(delim, max);
        return list == null ? null : list.toArray(new String[0]);
    }

    public static String[] split(String str, char delim) {
        return split(str, delim, -1);
    }

    public static String[] split(String str, String delim) {
        return split(str, delim, -1);
    }

    public static List<String> toList(String str) {
        Str strg = new Str(str);
        if (strg.isNull()) {
            return null;
        }
        else {
            strg = strg.unquote();
            return strg.toList();
        }
    }

    private static Str toStr(String str) {
        return Str.of(str);
    }

    private static String fromStr(Str str) {
        return str == null ? null : str.str();
    }

    public static String pad(String str, char ch, int length) {
        return fromStr(toStr(str).pad(ch, length));
    }

    public static String padLeft(String str, char ch, int length) {
        return fromStr(toStr(str).padLeft(ch, length));
    }

    public static String pad(String str, int length) {
        return fromStr(toStr(str).pad(length));
    }

    public static String padLeft(String str, int length) {
        return fromStr(toStr(str).padLeft(length));
    }

    public static String repeat(String str, int num) {
        return repeat(new StringBuilder(), str, num);
    }

    public static String repeat(Character ch, int num) {
        return ch == null ? null : repeat(String.valueOf(ch), num);
    }

    public static String repeat(char ch, int num) {
        return repeat(String.valueOf(ch), num);
    }

    public static String repeat(StringBuilder sb, String str, int num) {
        if (str == null) {
            return null;
        }
        else {
            for (int i = 0; i < num; ++i) {
                sb.append(str);
            }
            return sb.toString();
        }
    }    

    public static String left(String str, int num) {
        Str lstr = new Str(str).left(num);
        return lstr == null ? null : lstr.str();
    }

    public static String right(String str, int num) {
        Str rstr = new Str(str).right(num);
        return rstr == null ? null : rstr.str();
    }

    public static String join(Collection<?> coll, String delim) {
        return Str.join(coll, delim).str();
    }

    public static String join(Object[] ary, String str) {
        return Str.join(ary, str).str();
    }

    public static Character charAt(String str, int index) {
        return new Str(str).charAt(index);
    }

    public static Character get(String str, int index) {
        return new Str(str).get(index);
    }

    public static String substring(String str, Integer fromIndex, Integer toIndex) {
        return new Str(str).substring(fromIndex, toIndex);
    }

    public static String substringAfter(String str, Character ch) {
        return new Str(str).substringAfter(ch);
    }

    public static String substringBefore(String str, Character ch) {
        return new Str(str).substringBefore(ch);
    }

    public static String get(String str, Integer fromIndex, Integer toIndex) {
        return new Str(str).get(fromIndex, toIndex);
    }

    public static boolean startsWith(String str, char ch) {
        return new Str(str).startsWith(ch);
    }

    public static boolean startsWith(String str, String pref) {
        return new Str(str).startsWith(pref);
    }

    public static String chomp(String str) {
        Str chomped = new Str(str).chomp();
        return chomped == null ? null : chomped.str();
    }

    public static String chompAll(String str) {
        Str chomped = new Str(str).chompAll();
        return chomped == null ? null : chomped.str();
    }

    public static boolean contains(String str, Character ch) {
        return new Str(str).contains(ch);
    }
    
    public static Integer indexOf(String str, Character ch) {
        return new Str(str).indexOf(ch);
    }

    public static Boolean eq(String a, String b) {
        return new Str(a).eq(b);
    }

    public static Boolean eqi(String a, String b) {
        return new Str(a).eqi(b);
    }

    public static String snip(String str, int len) {
        Str snipped = new Str(str).snip(len);
        return snipped == null ? null : snipped.str();
    }

    public static boolean isEmpty(String str) {
        return new Str(str).isEmpty();
    }

    public static int length(String str) {
        return new Str(str).length();
    }

    public static String unquote(String str) {
        return new Str(str).unquote().str();
    }

    public static String quote(String str) {
        Str qstr = new Str(str).quote();
        return qstr == null ? null : qstr.str();
    }

    public static int compareChars(String s, String t, int idx) {
        Character sCh = s.charAt(idx);
        Character tCh = t.charAt(idx);
        return sCh.compareTo(tCh);
    }

    /**
     * Returns the value starting at <code>idx</code> in <code>str</code> as an integer, if the
     * characters are digits.
     *
     * @param str the string to check
     * @param idx the starting index
     * @return the number, as an integer
     */
    public static Integer matchInteger(String str, int idx) {
        String intPart = matchDigits(str, idx);
        return intPart == null ? null : Integer.valueOf(intPart);
    }

    /**
     * Returns the value starting at <code>idx</code> in <code>str</code> as a double, if the
     * characters are digits separated by one dot.
     *
     * @param str the string to check
     * @param idx the starting index
     * @return the number, as a double
     */
    public static Double matchDouble(String str, int idx) {
        String intPart = matchDigits(str, idx);
        if (intPart != null) {
            idx += intPart.length();
            if (matchChar(str, idx, '.') != null) {
                String fractPart = matchDigits(str, idx + 1);
                if (fractPart != null) {
                    return Double.valueOf(intPart + '.' + fractPart);
                }
            }
        }
        return null;
    }

    /**
     * Returns the character at <code>idx</code> as a character, if the character exists and is a
     * digit. Returns null otherwise.
     *
     * @param str the string to match
     * @param idx the location within the string
     * @return the matched digit
     */
    public static Character matchDigit(String str, int idx) {
        if (idx >= str.length()) {
            return null;
        }
        else {
            char ch = str.charAt(idx);
            return Character.isDigit(ch) ? ch : null;
        }
    }

    /**
     * Returns the character at <code>idx</code> as a character, if the character exists and is
     * equal to <code>match</code>. Returns null otherwise.
     *
     * @param str the string to match
     * @param idx the location within the string
     * @param match the character to match
     * @return the matched character
     */
    public static Character matchChar(String str, int idx, char match) {
        if (idx >= str.length()) {
            return null;
        }
        else {
            char ch = str.charAt(idx);
            return ch == match ? ch : null;
        }
    }

    /**
     * Returns the characters starting at <code>idx</code>, if the characters at that point are
     * digits. Returns null otherwise.
     *
     * @param str the string to match
     * @param idx the location within the string
     * @return the matched digits
     */
    public static String matchDigits(String str, int idx) {
        Character ch = matchDigit(str, idx);
        if (ch == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ch);

        while ((ch = matchDigit(str, ++idx)) != null) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
