package org.incava.ijdk.lang;

import org.incava.ijdk.lang.Str;
import java.util.Collection;
import java.util.List;

/**
 * Extensions to the String class. Alternatively, the static methods here are defined as instance
 * objects of the Str class.
 *
 * @see org.incava.ijdk.lang.Str
 */
public class StringExt {
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
        return new Str(str).split(String.valueOf(delim), max);
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
        return new Str(str).split(delim, max);
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     *
     * @param str the string to split
     * @param delim the character to split at
     * @return the array of split strings
     */
    public static String[] split(String str, char delim) {
        return new Str(str).split(String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     *
     * @param str the string to split
     * @param delim the string to split at
     * @return the array of split strings
     */
    public static String[] split(String str, String delim) {
        return new Str(str).split(delim, -1);
    }

    /**
     * Converts the (possibly quoted) string into a list, delimited by whitespace and commas.
     * Returns null if <code>str</code> is null.
     *
     * The unquoting behavior exists for pre-3.0 versions of IJDK.
     *
     * @param str the string to 
     * @return the list of strings
     * @see org.incava.ijdk.lang.Str#unquote
     * @see org.incava.ijdk.lang.Str#toList
     */
    public static List<String> toList(String str) {
        Str strg = new Str(str);
        if (strg.isNull()) {
            return null;
        }
        else {
            strg = new Str(strg.unquote());
            return strg.toList();
        }
    }

    public static String pad(String str, char ch, int length) {
        return new Str(str).pad(ch, length);
    }

    public static String padLeft(String str, char ch, int length) {
        return new Str(str).padLeft(ch, length);
    }

    public static String pad(String str, int length) {
        return new Str(str).pad(length);
    }

    public static String padLeft(String str, int length) {
        return new Str(str).padLeft(length);
    }

    public static String repeat(String str, int num) {
        if (str == null) {
            return null;
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num; ++i) {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public static String repeat(Character ch, int num) {
        return repeat(ch == null ? null : String.valueOf(ch), num);
    }

    public static String repeat(char ch, int num) {
        return repeat(String.valueOf(ch), num);
    }    

    public static String left(String str, int num) {
        return new Str(str).left(num);
    }

    public static String right(String str, int num) {
        return new Str(str).right(num);
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

    /**
     * An alias for StringExt#charAt.
     *
     * @see #charAt(String, int)
     */
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
        return new Str(str).chomp();
    }

    public static String chompAll(String str) {
        return new Str(str).chompAll();
    }

    /**
     * Returns whether the string contains the character. If <code>str</code> or <code>ch</code> is
     * null, false is returned.
     */
    public static boolean contains(String str, Character ch) {
        return new Str(str).contains(ch);
    }
    
    /**
     * Returns the index of the first occurance of the character in the string, or null if either
     * <code>str</code> or <code>ch</code> is null, or if the character is not in the string.
     */
    public static Integer indexOf(String str, Character ch) {
        return new Str(str).indexOf(ch);
    }

    /**
     * Returns whether the two strings are equal. If both are null, then true is returned.
     * Otherwise, if either is null, then false is returned.
     */
    public static Boolean eq(String a, String b) {
        return new Str(a).eq(b);
    }

    /**
     * Returns whether the two strings are equal, without regard to case. If both are null, then
     * true is returned. Otherwise, if either is null, then false is returned.
     */
    public static Boolean eqi(String a, String b) {
        return new Str(a).eqi(b);
    }

    /**
     * If <code>str</code> is longer than <code>length</code>, then the returned string is cut at
     * the given length and appended with a dash, so <code>snip("foobar", 3)</code> is "foo-.
     * Returns null if <code>str</code> is null. Returns an empty string if <code>length</code> is
     * zero or less.
     */
    public static String snip(String str, int len) {
        return new Str(str).snip(len).str();
    }

    /**
     * Returns whether the string is null or of zero length.
     */
    public static boolean isEmpty(String str) {
        return new Str(str).isEmpty();
    }

    /**
     * Returns the length of the string, returning 0 if the string is null.
     */
    public static int length(String str) {
        return new Str(str).length();
    }

    /**
     * Unquotes the string, removing matching leading and trailing single or double quotes, if both
     * of either type wraps the string. Returns the string if neither case is true. Returns null if
     * the string is null.
     */
    public static String unquote(String str) {
        return new Str(str).unquote();
    }    

    /**
     * Quotes the string, using double quotes. Returns null if the string is null.
     */
    public static String quote(String str) {
        return new Str(str).quote();
    }    
}
