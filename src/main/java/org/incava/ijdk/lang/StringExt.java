package org.incava.ijdk.lang;

import ijdk.lang.Stringg;
import java.util.Collection;
import java.util.List;

/**
 * Extensions to the String class.
 * Alternatively, the static methods here are defined as instance objects of the Stringg class. This
 * class exists for backward compatibility, and new string-related methods will be added to Stringg,
 * and likely not this class.
 *
 * @see ijdk.Stringg
 */
public class StringExt {
    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public static String[] split(String str, char delim, int max) {
        return new Stringg(str).split(String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public static String[] split(String str, String delim, int max) {
        return new Stringg(str).split(delim, max);
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public static String[] split(String str, char delim) {
        return new Stringg(str).split(String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public static String[] split(String str, String delim) {
        return new Stringg(str).split(delim, -1);
    }

    /**
     * Converts the (possibly quoted) string into a list, delimited by whitespace and commas.
     * Returns null if <code>str</code> is null.
     */
    public static List<String> toList(String str) {
        return new Stringg(str).toList();
    }

    /**
     * Returns a string starting with the <code>str</code> parameter, with <code>ch</code>'s
     * following the string to a length of <code>length</code>.
     *
     * Examples:
     *     pad("abcd", '*', 8) -> "abcd****"
     *     pad("abcd", '*', 3) -> "abcd"
     */
    public static String pad(String str, char ch, int length) {
        return new Stringg(str).pad(ch, length);
    }

    /**
     * Same as the <code>pad</code> method, but applies the padding to the left-hand (leading) side
     * of the string.
     *
     * Examples:
     * <pre>
     *     pad("420", '*', 8) -> "*****420"
     *     pad("1144", '*', 3) -> "1144"
     * </pre>
     */
    public static String padLeft(String str, char ch, int length) {
        return new Stringg(str).padLeft(ch, length);
    }

    /**
     * Pads with spaces.
     */
    public static String pad(String str, int length) {
        return new Stringg(str).pad(length);
    }

    /**
     * Left-pads with spaces.
     */
    public static String padLeft(String str, int length) {
        return new Stringg(str).padLeft(length);
    }

    /**
     * Returns the string, repeated <code>num</code> times.
     */
    public static String repeat(String str, int num) {
        return new Stringg(str).repeat(num);
    }

    /**
     * Returns the character, repeated <code>num</code> times.
     */
    public static String repeat(char ch, int length) {
        return new Stringg(ch).repeat(length);
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String left(String str, int num) {
        return new Stringg(str).left(num);
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String right(String str, int num) {
        return new Stringg(str).right(num);
    }

    /**
     * Returns the collection, joined by <code>delim</code>. Returns null if <code>coll</code> is
     * null. If <code>delim</code> is null, it is treated as the empty string.
     */
    public static String join(Collection<?> coll, String delim) {
        return Stringg.join(coll, delim).str();
    }

    /**
     * Returns the array, joined by <code>str</code>. Returns null if <code>ary</code> is null. If
     * <code>str</code> is null, it is treated as the empty string.
     */
    public static String join(Object[] ary, String str) {
        return Stringg.join(ary, str).str();
    }

    /**
     * Returns the character at the given index, or null if <code>index</code>
     * is out of range. If <code>index</code> is negative, the character is the
     * nth character from the end of the string, where -1 is the last character
     * in the string.
     *
     * @return The character at the given index, or null if out of range.
     * @param str The source string.
     * @param index The index into the source string. Negative value goes from end backward.
     */
    public static Character charAt(String str, int index) {
        return new Stringg(str).charAt(index);
    }

    /**
     * An alias for StringExt#charAt.
     *
     * @see StringExt#charAt(String, int).
     */
    public static Character get(String str, int index) {
        return new Stringg(str).get(index);
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
     * @see StringExt#get(String, int).
     */
    public static String substring(String str, Integer fromIndex, Integer toIndex) {
        return new Stringg(str).substring(fromIndex, toIndex);
    }

    /**
     * Returns the substring from the <code>ch</code> character to the end of the string. If the
     * string does not contain the given character, or if <code>str</code> or <code>ch</code> is
     * null, then null is returned. If <code>ch</code> is the last character, then an empty string
     * is returned.
     */
    public static String substringAfter(String str, Character ch) {
        return new Stringg(str).substringAfter(ch);
    }

    /**
     * Returns the substring before the <code>ch</code> character. If the string does not contain
     * the given character, , or if <code>str</code> or <code>ch</code> is null, then null is
     * returned. If <code>ch</code> is the first character, then an empty string is returned.
     */
    public static String substringBefore(String str, Character ch) {
        return new Stringg(str).substringBefore(ch);
    }

    /**
     * Same as StringExt#substring, but with the indices inclusive, like the Ruby syntax <code>str[4
     * .. 8]</code>. This method the same as <code>StringExt.substring</code>.
     */
    public static String get(String str, Integer fromIndex, Integer toIndex) {
        return new Stringg(str).get(fromIndex, toIndex);
    }    

    /**
     * Returns whether the string <code>str</code> begins with the character <code>ch</code>.
     * Returns false if <code>str</code> is null.
     */
    public static boolean startsWith(String str, char ch) {
        return new Stringg(str).startsWith(ch);
    }

    /**
     * Returns whether the string <code>str</code> begins with the string <code>prefix</code>. For
     * consistency with String. Returns false if <code>str</code> is null.
     */
    public static boolean startsWith(String str, String pref) {
        return new Stringg(str).startsWith(pref);
    }

    /**
     * Removes a single end of line character, either \\n or \\r. Even if there are multiple end of
     * line characters, only one is removed.
     *
     * @see #chompAll
     */
    public static String chomp(String str) {
        return new Stringg(str).chomp();
    }

    /**
     * Removes multiple end of line characters, either \\n or \\r.
     */
    public static String chompAll(String str) {
        return new Stringg(str).chompAll();
    }

    /**
     * Returns whether the string contains the character. If <code>str</code> or
     * <code>ch</code> is null, false is returned.
     */
    public static boolean contains(String str, Character ch) {
        return new Stringg(str).contains(ch);
    }
    
    /**
     * Returns the index of the first occurance of the character in the string, or null if either
     * <code>str</code> or <code>ch</code> is null, or if the character is not in the string.
     */
    public static Integer indexOf(String str, Character ch) {
        return new Stringg(str).indexOf(ch);
    }

    /**
     * Returns whether the two strings are equal. If both are null, then true is returned.
     * Otherwise, if either is null, then false is returned.
     */
    public static Boolean eq(String a, String b) {
        return new Stringg(a).eq(b);
    }

    /**
     * Returns whether the two strings are equal, without regard to case. If both are null, then
     * true is returned. Otherwise, if either is null, then false is returned.
     */
    public static Boolean eqi(String a, String b) {
        return new Stringg(a).eqi(b);
    }

    /**
     * If <code>str</code> is longer than <code>length</code>, then the returned string is cut at
     * the given length and appended with a dash, so <code>snip("foobar", 3) is "foo-. Returns null
     * if <code>str</code> is null. Returns an empty string if <code>length</code> is zero or less.
     */
    public static String snip(String str, int len) {
        return new Stringg(str).snip(len);
    }

    /**
     * Returns whether the string is null or of zero length.
     */
    public static boolean isEmpty(String str) {
        return new Stringg(str).isEmpty();
    }
}
