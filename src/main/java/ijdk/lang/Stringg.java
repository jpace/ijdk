package ijdk.lang;

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
public class Stringg extends Objectt {
    /**
     * Returns a new Stringg for the given character.
     */
    public static Stringg strg(char ch) {
        return new Stringg(ch);
    }

    /**
     * Creates a string from the collection, joined by <code>delim</code>. If <code>coll</code> is
     * null, then the wrapped string is null. If <code>delim</code> is null, it is treated as the
     * empty string.
     */
    public static Stringg join(Collection<?> coll, String delim) {
        if (coll == null) {
            return new Stringg(null);
        }
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Object obj : coll) {
            if (!isFirst) {
                sb.append(delim == null ? "" : delim);
            }
            else {
                isFirst = false;
            }
            sb.append(obj.toString());
        }
        return new Stringg(sb.toString());
    }

    /**
     * Creates a string from the array, joined by <code>delim</code>. If <code>ary</code> is null,
     * then the wrapped string is null. If <code>delim</code> is null, it is treated as the empty
     * string.
     */
    public static Stringg join(Object[] ary, String delim) {
        return join(ary == null ? null : Arrays.asList(ary), delim);
    }
    
    private final String string;

    /**
     * Wraps the given string.
     */
    public Stringg(String string) {
        super(string);
        this.string = string;
    }

    /**
     * Creates a new string from the character, and wraps that string.
     */
    public Stringg(char ch) {
        this(String.valueOf(ch));
    }

    /**
     * Returns the wrapped string.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Returns the wrapped string.
     */
    public String str() {
        return this.string;
    }

    /**
     * Returns whether the given string is equal to this one.
     */
    public boolean equals(String other) {
        return this.string == null ? other == null : this.string.equals(other);
    }
    
    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public String[] split(char delim, int max) {
        return split(String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public String[] split(String delim, int max) {
        if (string == null) {
            return null;
        }
        else if (max == 1) {
            return new String[] { string };
        }
        else {
            --max;              // adjust count between 0 and 1

            List<String> splitList = new ArrayList<String>();

            int  nFound = 0;
            int  strlen = string.length();
            int  end = 0;
            int  beg = 0;
            int  delimlen = delim.length();

            for (int idx = 0; idx < strlen; ++idx) {
                Stringg strg = new Stringg(string.substring(idx));
                if (strg.left(delimlen).equals(delim)) {
                    String substr = string.substring(beg, end);
                    splitList.add(substr);
                    beg = end + delimlen;
                    if (max > 0 && ++nFound >= max) {
                        break;
                    }
                }
                ++end;
            }

            if (strlen > beg) {
                String tmp = strlen == beg ? "" : string.substring(beg, strlen);
                splitList.add(tmp);
            }
            
            return splitList.toArray(new String[splitList.size()]);
        }
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     */
    public String[] split(char delim) {
        return split(String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     */
    public String[] split(String delim) {
        return split(delim, -1);
    }

    /**
     * Converts the (possibly quoted) string into a list, delimited by whitespace and commas.
     * Returns null if this object is wrapping a null string.
     *
     * @deprecated I don't like the de-quoting behavior
     */
    public List<String> toList() {
        if (this.string == null) {
            return null;
        }

        String str = this.string;
        
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
    public String pad(char ch, int length) {
        if (this.string == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(this.string);
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
    public String padLeft(char ch, int length) {
        return this.string == null ? null : repeat(ch, length - this.string.length()) + this.string;
    }

    /**
     * Pads with spaces.
     */
    public String pad(int length) {
        return pad(' ', length);
    }

    /**
     * Left-pads with spaces.
     */
    public String padLeft(int length) {
        return padLeft(' ', length);
    }

    /**
     * Returns the string, repeated <code>num</code> times.
     */
    public String repeat(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; ++i) {
            sb.append(this.string);
        }
        return sb.toString();
    }

    /**
     * Returns the character, repeated <code>num</code> times.
     */
    public String repeat(char ch, int length) {
        return new Stringg(String.valueOf(ch)).repeat(length);
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw the annoying IndexOutOfBoundsException.
     * Returns null if the input string is null. Returns an empty string if <code>num</code> is
     * negative.
     */
    public String left(int num) {
        return num <= 0 ? "" : get(this.string, 0, num - 1);
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw the annoying IndexOutOfBoundsException.
     * Returns null if the input string is null. Returns an empty string if <code>num</code> is
     * negative.
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
     * .. 8]</code>. This method the same as <code>Stringg.substring</code>.
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
     * Returns whether the wrapped string is equal to the other. If both are null, then true is
     * returned. Otherwise, if either is null, then false is returned.
     */
    public Boolean eq(String other) {
        return Objectt.areEqual(this.string, other);
    }

    /**
     * Returns whether the wrapped string is equal to the other, without regard to case. If both are
     * null, then true is returned. Otherwise, if either is null, then false is returned.
     */
    public Boolean eqi(String other) {
        if (this.string == null || other == null) {
            return this.string == null && other == null;
        }
        else {
            return this.string.equalsIgnoreCase(other);
        }
    }

    /**
     * If <code>str</code> is longer than <code>length</code>, then the returned string is cut at
     * the given length and appended with a dash, so <code>snip("foobar", 3) is "foo-. Returns null
     * if <code>str</code> is null. Returns an empty string if <code>length</code> is zero or less.
     */
    public String snip(int len) {
        if (this.string == null) {
            return null;
        }
        else if (len <= 0) {
            return "";
        }
        else if (this.string.length() > len)  {
            return get(this.string, 0, len - 1) + '-';
        }
        else {
            return this.string;
        }
    }

    /**
     * Returns whether the wrapped string is null or of zero length.
     */
    public boolean isEmpty() {
        // str.isEmpty() is JDK 1.6+, and IJDK is backward compatible with 1.5.
        return this.string == null || this.string.length() == 0;
    }
}
