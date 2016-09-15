package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import org.incava.ijdk.util.ListExt;
import org.incava.ijdk.util.Index;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Extensions to the String class.
 */
public class StringExt {
    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public static String[] split(String str, char delim, int max) {
        return split(str, String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public static String[] split(String str, String delim, int max) {
        if (str == null) {
            return null;
        }
        else if (max == 1) {
            return new String[] { str };
        }
        else {
            --max;              // adjust count between 0 and 1

            List<String> splitList = new ArrayList<String>();

            int  nFound = 0;
            int  strlen = str.length();
            int  end = 0;
            int  beg = 0;
            int  delimlen = delim.length();

            for (int idx = 0; idx < strlen; ++idx) {
                if (left(str.substring(idx), delimlen).equals(delim)) {
                    String substr = str.substring(beg, end);
                    splitList.add(substr);
                    beg = end + delimlen;
                    if (max > 0 && ++nFound >= max) {
                        break;
                    }
                }
                ++end;
            }

            if (strlen > beg) {
                String tmp = strlen == beg ? "" : str.substring(beg, strlen);
                splitList.add(tmp);
            }
            
            return splitList.toArray(new String[splitList.size()]);
        }
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public static String[] split(String str, char delim) {
        return split(str, String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public static String[] split(String str, String delim) {
        return split(str, delim, -1);
    }

    /**
     * Converts the (possibly quoted) string into a list, delimited by whitespace and commas.
     * Returns null if <code>str</code> is null.
     */
    public static List<String> toList(String str) {
        if (str == null) {
            return null;
        }
        
        // strip leading/trailing single/double quotes
        if (str.charAt(0) == str.charAt(str.length() - 1) &&
            (str.charAt(0) == '"' || str.charAt(0) == '\'')) {
            str = str.substring(1, str.length() - 1);
        }
        
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str, " \t\n\r\f,");
        while (st.hasMoreTokens()) {
            String tk = st.nextToken();
            list.add(tk);
        }
        return list;
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
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(ch);
        }
        return sb.toString();
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
        return str == null ? null : repeat(ch, length - str.length()) + str;
    }

    /**
     * Pads with spaces.
     */
    public static String pad(String str, int length) {
        return pad(str, ' ', length);
    }

    /**
     * Left-pads with spaces.
     */
    public static String padLeft(String str, int length) {
        return padLeft(str, ' ', length);
    }

    /**
     * Returns the string, repeated <code>num</code> times.
     */
    public static String repeat(String str, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; ++i) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Returns the character, repeated <code>num</code> times.
     */
    public static String repeat(char ch, int length) {
        return repeat(String.valueOf(ch), length);
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String left(String str, int num) {
        return num <= 0 ? "" : get(str, 0, num - 1);
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Does not throw the annoying IndexOutOfBoundsException. Returns null if the input
     * string is null. Returns an empty string if <code>num</code> is negative.
     */
    public static String right(String str, int num) {
        if (str == null) {
            return null;
        }
        else {
            return num <= 0 ? "" : get(str, -Math.min(num, str.length()), -1);
        }
    }

    /**
     * Returns the collection, joined by <code>str</code>. Returns null if <code>coll</code> is
     * null. If <code>str</code> is null, it is treated as the empty string.
     */
    public static String join(Collection<?> coll, String str) {
        if (coll == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Object obj : coll) {
            if (!isFirst) {
                sb.append(str == null ? "" : str);
            }
            else {
                isFirst = false;
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    /**
     * Returns the array, joined by <code>str</code>. Returns null if <code>ary</code> is null. If
     * <code>str</code> is null, it is treated as the empty string.
     */
    public static String join(Object[] ary, String str) {
        return ary == null ? null : join(Arrays.asList(ary), str);
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
        if (str == null) {
            return null;
        }
        else {
            Integer idx = getIndex(str, index);
            return isNull(idx) ? null : str.charAt(idx);
        }
    }

    /**
     * An alias for StringExt#charAt.
     *
     * @see StringExt#charAt(String, int).
     */
    public static Character get(String str, int index) {
        return charAt(str, index);
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
        if (str == null) {
            return null;
        }

        Integer frIdx = null;

        if (fromIndex == null) {
            frIdx = 0;
        }
        else {
            frIdx = getIndex(str, fromIndex);
            if (isNull(frIdx)) {
                return "";
            }
        }

        Integer toIdx = toIndex == null ? null : getIndex(str, toIndex);

        if (isNull(toIdx)) {
            toIdx = str.length() - 1;
        }

        if (frIdx > toIdx) {
            // We could return null, but Ruby returns "" if the indices are within the length of the
            // string, and null if not. We'll just go with empty.
            return "";
        }
        else {
            return str.substring(frIdx, 1 + toIdx);
        }
    }

    /**
     * Returns the substring from the <code>ch</code> character to the end of the string. If the
     * string does not contain the given character, or if <code>str</code> or <code>ch</code> is
     * null, then null is returned. If <code>ch</code> is the last character, then an empty string
     * is returned.
     */
    public static String substringAfter(String str, Character ch) {
        Integer idx = indexOf(str, ch);
        return idx == null ? null : substring(str, idx + 1, null);
    }

    /**
     * Returns the substring before the <code>ch</code> character. If the string does not contain
     * the given character, , or if <code>str</code> or <code>ch</code> is null, then null is
     * returned. If <code>ch</code> is the first character, then an empty string is returned.
     */
    public static String substringBefore(String str, Character ch) {
        Integer idx = indexOf(str, ch);
        return idx == null ? null : idx == 0 ? "" : substring(str, 0, idx - 1);
    }

    /**
     * Same as StringExt#substring, but with the indices inclusive, like the Ruby syntax <code>str[4
     * .. 8]</code>. This method the same as <code>StringExt.substring</code>.
     */
    public static String get(String str, Integer fromIndex, Integer toIndex) {
        return substring(str, fromIndex, toIndex);
    }
    
    /**
     * Converts the index, which can be positive or negative, to one within range for this string. A
     * negative index will result in the distance from the end of the string, with index -1 meaning
     * the last character in the string. Returns null if the resulting index is out of range. If
     * <code>str</code> is null, null is returned.
     */
    protected static Integer getIndex(String str, Integer index) {
        return isNull(str) ? null : Index.getIndex(str.length(), index);
    }

    /**
     * Returns whether the string <code>str</code> begins with the character
     * <code>ch</code>.
     */
    public static boolean startsWith(String str, char ch) {
        Character startChar = get(str, 0);
        return startChar != null && startChar == ch;
    }

    /**
     * Returns whether the string <code>str</code> begins with the string
     * <code>prefix</code>. For consistency with String.
     */
    public static boolean startsWith(String str, String pref) {
        return str != null && str.startsWith(pref);
    }

    /**
     * Removes end of line characters.
     */
    public static String chomp(String str) {
        int idx = str.length() - 1;
        while (idx >= 0 && "\r\n".indexOf(str.charAt(idx)) != -1) {
            --idx;
        }
        return str.substring(0, idx + 1);
    }

    /**
     * Returns whether the string contains the character. If <code>str</code> or
     * <code>ch</code> is null, false is returned.
     */
    public static boolean contains(String str, Character ch) {
        return indexOf(str, ch) != null;
    }
    
    /**
     * Returns the index of the first occurance of the character in the string, or null if either
     * <code>str</code> or <code>ch</code> is null, or if the character is not in the string.
     */
    public static Integer indexOf(String str, Character ch) {
        if (isNull(str) || isNull(ch)) {
            return null;
        }
        else {
            int idx = str.indexOf(ch);
            return idx >= 0 ? Integer.valueOf(idx) : null;
        }
    }

    /**
     * Returns whether the two strings are equal. If both are null, then true is returned.
     * Otherwise, if either is null, then false is returned.
     */
    public static Boolean eq(String a, String b) {
        return ObjectExt.areEqual(a, b);
    }

    /**
     * Returns whether the two strings are equal, without regard to case. If both are null, then
     * true is returned. Otherwise, if either is null, then false is returned.
     */
    public static Boolean eqi(String a, String b) {
        return a == null && b == null ? true : a == null ? false : b == null ? false : a.equalsIgnoreCase(b);
    }

    /**
     * If <code>str</code> is longer than <code>length</code>, then the returned string is cut at
     * the given length and appended with a dash, so <code>snip("foobar", 3) is "foo-. Returns null
     * if <code>str</code> is null. Returns an empty string if <code>length</code> is zero or less.
     */
    public static String snip(String str, int len) {
        if (str == null) {
            return null;
        }
        else if (len <= 0) {
            return "";
        }
        else if (str.length() > len)  {
            return get(str, 0, len - 1) + '-';
        }
        else {
            return str;
        }
    }

    /**
     * Returns whether the string is null or of zero length.
     */
    public static boolean isEmpty(String str) {
        // return str == null || str.isEmpty();
        return str == null || str.length() == 0;
    }
}
