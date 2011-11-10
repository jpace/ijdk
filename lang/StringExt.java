package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import org.incava.ijdk.util.ListExt;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Extensions to the String class.
 */
public class StringExt {
    /**
     * Returns an array of strings split at the character delimiter.
     */
    public static String[] split(String str, char delim, int max) {
        return split(str, String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter.
     */
    public static String[] split(String str, String delim, int max) {
        if (max == 1) {
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
     * Returns an array of strings split at the character delimiter.
     */
    public static String[] split(String str, char delim) {
        return split(str, String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter.
     */
    public static String[] split(String str, String delim) {
        return split(str, delim, -1);
    }

    /**
     * Converts the (possibly quoted) string into a list, delimited by
     * whitespace and commas..
     */
    public static List<String> toList(String str) {
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
     * Returns a string starting with the <code>str</code> parameter, with
     * <code>ch</code>'s following the string to a length of
     * <code>length</code>.
     *
     * Examples:
     *     pad("abcd", '*', 8) -> "abcd****"
     *     pad("abcd", '*', 3) -> "abcd"
     */
    public static String pad(String str, char ch, int length) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Same as the <code>pad</code> method, but applies the padding to the
     * left - hand (leading) side of the string.
     *
     * Examples:
     * <pre>
     *     pad("420", '*', 8) -> "*****420"
     *     pad("1144", '*', 3) -> "1144"
     * </pre>
     */
    public static String padLeft(String str, char ch, int length) {
        return repeat(ch, length - str.length()) + str;
    }

    public static String pad(String str, int length) {
        return pad(str, ' ', length);
    }

    public static String padLeft(String str, int length) {
        return padLeft(str, ' ', length);
    }

    public static String repeat(String str, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String repeat(char ch, int length) {
        return repeat(String.valueOf(ch), length);
    }

    /**
     * Returns the leftmost n characters of the string, not exceeding the length
     * of the string. Does not throw the annoying IndexOutOfBoundsException.
     */
    public static String left(String str, int n) {
        return get(str, 0, n - 1);
    }

    /**
     * Returns the rightmost n characters of the string, not exceeding the
     * length of the string. Does not throw the annoying
     * IndexOutOfBoundsException.
     */
    public static String right(String str, int n) {
        return get(str, -Math.min(n, str.length()), -1);
    }

    public static String join(Collection c, String str) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Object obj : c) {
            if (!isFirst) {
                sb.append(str);
            }
            else {
                isFirst = false;
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static String join(Object[] ary, String str) {
        return join(Arrays.asList(ary), str);
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
     * @see String#charAt(String, int).
     */
    public static Character get(String str, int index) {
        return charAt(str, index);
    }

    /**
     * Returns a substring from the string, with negatives indices applying to
     * the distance from the end of the string. If fromIndex is null, the
     * substring begins with the first character. If toIndex is null, the
     * substring ends with the last character. If the indices are out of bounds,
     * they will be restricted to the size of the string. The range is
     * inclusive: "foobar"[2 .. 4] == "oba", which is unlike the JDK
     * String#substring behavior.
     *
     * @see String#get(String, int).
     */
    public static String substring(String str, Integer fromIndex, Integer toIndex) {
        if (str == null) {
            return null;
        }

        Integer frIdx = getIndex(str, fromIndex);

        if (isNull(frIdx)) {
            return "";
        }

        Integer toIdx = getIndex(str, toIndex);

        if (isNull(toIdx)) {
            toIdx = str.length() - 1;
        }

        if (frIdx > toIdx) {
            // We could return null, but Ruby returns "" if the indices are
            // within the length of the string, and null if not. We'll just go
            // with empty.
            return "";
        }
        else {
            return str.substring(frIdx, 1 + toIdx);
        }
    }

    /**
     * Same as StringExt#substring, but more like the syntax str[4 .. 8].
     */
    public static String get(String str, Integer fromIndex, Integer toIndex) {
        return substring(str, fromIndex, toIndex);
    }
    
    /**
     * Converts the index, which can be positive or negative, to one within
     * range for this string. A negative index will result in the distance from
     * the end of the string, with index -1 meaning the last character in the
     * string. Returns null if the resulting index is out of range.
     */
    protected static Integer getIndex(String str, Integer index) {
        return isNull(str) ? null : ListExt.getIndex(str.length(), index);
    }
}
