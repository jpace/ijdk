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
     */
    public static String[] split(String str, char delim, int max) {
        return new Str(str).split(String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public static String[] split(String str, String delim, int max) {
        return new Str(str).split(delim, max);
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public static String[] split(String str, char delim) {
        return new Str(str).split(String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
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

    /**
     * Returns a string starting with the <code>str</code> parameter, with <code>ch</code>'s
     * following the string to a length of <code>length</code>.
     *
     * Examples:
     * <pre>
     *     pad("abcd", '*', 8) -&gt; "abcd****"
     *     pad("abcd", '*', 3) -&gt; "abcd"
     * </pre>
     */
    public static String pad(String str, char ch, int length) {
        return new Str(str).pad(ch, length);
    }

    /**
     * Same as the <code>pad</code> method, but applies the padding to the left-hand (leading) side
     * of the string.
     *
     * Examples:
     * <pre>
     *     pad("420", '*', 8)  -&gt; "*****420"
     *     pad("1144", '*', 3) -&gt; "1144"
     * </pre>
     */
    public static String padLeft(String str, char ch, int length) {
        return new Str(str).padLeft(ch, length);
    }

    /**
     * Pads with trailing spaces.
     */
    public static String pad(String str, int length) {
        return new Str(str).pad(length);
    }

    /**
     * Left-pads with spaces.
     */
    public static String padLeft(String str, int length) {
        return new Str(str).padLeft(length);
    }

    /**
     * Returns the string, repeated <code>num</code> times.
     */
    public static String repeat(String str, int num) {
        return new Str(str).repeat(num);
    }

    /**
     * Returns the character, repeated <code>num</code> times.
     */
    public static String repeat(char ch, int length) {
        return new Str(ch).repeat(length);
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String left(String str, int num) {
        return new Str(str).left(num);
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String right(String str, int num) {
        return new Str(str).right(num);
    }

    /**
     * Returns the collection, joined by <code>delim</code>. Returns null if <code>coll</code> is
     * null. If <code>delim</code> is null, it is treated as the empty string.
     */
    public static String join(Collection<?> coll, String delim) {
        return Str.join(coll, delim).str();
    }

    /**
     * Returns the array, joined by <code>str</code>. Returns null if <code>ary</code> is null. If
     * <code>str</code> is null, it is treated as the empty string.
     */
    public static String join(Object[] ary, String str) {
        return Str.join(ary, str).str();
    }

    /**
     * Returns the character at the given index, or null if <code>index</code> is out of range. If
     * <code>index</code> is negative, the character is the nth character from the end of the
     * string, where -1 is the last character in the string.
     *
     * @return The character at the given index, or null if out of range.
     * @param str The source string.
     * @param index The index into the source string. Negative value goes from end backward.
     */
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

    /**
     * Returns a substring from the string, with negatives indices applying to the distance from the
     * end of the string. If <code>fromIndex</code> is null, the substring begins with the first
     * character. If <code>toIndex</code> is null, the substring ends with the last character. If
     * the indices are out of bounds, they will be restricted to the size of the string. The range
     * is inclusive: <code>"foobar"[2 .. 4] == "oba"</code>, which is unlike the JDK
     * String#substring behavior, where the equivalent would be <code>str.substring(2, 5)</code>.
     *
     * If <code>str</code> is null, then null is returned.
     *
     * @see #get(String, Integer, Integer)
     */
    public static String substring(String str, Integer fromIndex, Integer toIndex) {
        return new Str(str).substring(fromIndex, toIndex);
    }

    /**
     * Returns the substring from the <code>ch</code> character to the end of the string. If the
     * string does not contain the given character, or if <code>str</code> or <code>ch</code> is
     * null, then null is returned. If <code>ch</code> is the last character, then an empty string
     * is returned.
     */
    public static String substringAfter(String str, Character ch) {
        return new Str(str).substringAfter(ch);
    }

    /**
     * Returns the substring before the <code>ch</code> character. If the string does not contain
     * the given character, , or if <code>str</code> or <code>ch</code> is null, then null is
     * returned. If <code>ch</code> is the first character, then an empty string is returned.
     */
    public static String substringBefore(String str, Character ch) {
        return new Str(str).substringBefore(ch);
    }

    /**
     * Same as StringExt#substring, but with the indices inclusive, like the Ruby syntax <code>str[4
     * .. 8]</code>. This method the same as <code>StringExt.substring</code>.
     */
    public static String get(String str, Integer fromIndex, Integer toIndex) {
        return new Str(str).get(fromIndex, toIndex);
    }    

    /**
     * Returns whether the string <code>str</code> begins with the character <code>ch</code>.
     * Returns false if <code>str</code> is null.
     */
    public static boolean startsWith(String str, char ch) {
        return new Str(str).startsWith(ch);
    }

    /**
     * Returns whether the string <code>str</code> begins with the string <code>prefix</code>. For
     * consistency with String. Returns false if <code>str</code> is null.
     */
    public static boolean startsWith(String str, String pref) {
        return new Str(str).startsWith(pref);
    }

    /**
     * Removes a single end of line character, either \\n or \\r. Even if there are multiple end of
     * line characters, only one is removed.
     *
     * @see #chompAll
     */
    public static String chomp(String str) {
        return new Str(str).chomp();
    }

    /**
     * Removes multiple end of line characters, either \\n or \\r.
     */
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
        return new Str(str).snip(len);
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
