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
public class Str extends Obj {
    /**
     * Creates a string from the collection, joined by <code>delim</code>. If <code>coll</code> is
     * null, then the wrapped string is null. If <code>delim</code> is null, it is treated as the
     * empty string.
     */
    public static Str join(Collection<?> coll, String delim) {
        if (coll == null) {
            return new Str(null);
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
        return new Str(sb.toString());
    }

    /**
     * Creates a string from the array, joined by <code>delim</code>. If <code>ary</code> is null,
     * then the wrapped string is null. If <code>delim</code> is null, it is treated as the empty
     * string.
     */
    public static Str join(Object[] ary, String delim) {
        return join(ary == null ? null : Arrays.asList(ary), delim);
    }
    
    private final String string;

    /**
     * Wraps the given string.
     */
    public Str(String string) {
        super(string);
        this.string = string;
    }

    /**
     * Creates a new string from the character, and wraps that string.
     */
    public Str(char ch) {
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
     * Returns whether the given string is equal to this one.
     */
    public boolean equals(Object obj) {
        return obj instanceof Str ? equals(((Str)obj).str()) : false;
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
                Str strg = new Str(string.substring(idx));
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
     * Converts the string into a list, delimited by whitespace and commas.
     */
    public List<String> toList() {
        if (isNull()) {
            return null;
        }
        
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(this.string, " \t\n\r\f,");
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
     *     pad("abcd", '*', 8) -&gt; "abcd****"
     *     pad("abcd", '*', 3) -&gt; "abcd"
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
     *     pad("420", '*', 8)  -&gt; "*****420"
     *     pad("1144", '*', 3) -&gt; "1144"
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
        return new Str(String.valueOf(ch)).repeat(length);
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw the annoying IndexOutOfBoundsException.
     * Returns null if the input string is null. Returns an empty string if <code>num</code> is
     * negative.
     */
    public String left(int num) {
        return num <= 0 ? "" : get(0, num - 1);
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw the annoying IndexOutOfBoundsException.
     * Returns null if the wrapped string is null. Returns an empty string if <code>num</code> is
     * negative.
     */
    public String right(int num) {
        if (isNull()) {
            return null;
        }
        else {
            return num <= 0 ? "" : get(-Math.min(num, this.string.length()), -1);
        }
    }

    /**
     * Returns the character at the given index, or null if <code>index</code> is out of range. If
     * <code>index</code> is negative, the character is the nth character from the end of the
     * string, where -1 is the last character in the string. If the wrapped string is null, then
     * null is returned.
     *
     * @return The character at the given index, or null if out of range.
     * @param index The index into the source string. Negative value goes from end backward.
     */
    public Character charAt(int index) {
        if (isNull()) {
            return null;
        }
        else {
            Integer idx = getIndex(index);
            return isNull(idx) ? null : this.string.charAt(idx);
        }
    }

    /**
     * An alias for Str#charAt.
     *
     * @see Str#charAt(int)
     */
    public Character get(int index) {
        return charAt(index);
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
     * @see Str#get(Integer, Integer)
     */
    public String substring(Integer fromIndex, Integer toIndex) {
        if (isNull()) {
            return null;
        }

        Integer frIdx = null;

        if (fromIndex == null) {
            frIdx = 0;
        }
        else {
            frIdx = getIndex(fromIndex);
            if (isNull(frIdx)) {
                return "";
            }
        }


        Integer toIdx = toIndex == null ? null : getIndex(toIndex);

        if (isNull(toIdx)) {
            toIdx = this.string.length() - 1;
        }

        if (frIdx > toIdx) {
            // We could return null, but Ruby returns "" if the indices are within the length of the
            // string, and null if not. We'll just go with empty.
            return "";
        }
        else {
            return this.string.substring(frIdx, 1 + toIdx);
        }
    }

    /**
     * Returns the substring from the <code>ch</code> character to the end of the string. If the
     * string does not contain the given character, or if <code>str</code> or <code>ch</code> is
     * null, then null is returned. If <code>ch</code> is the last character, then an empty string
     * is returned.
     */
    public String substringAfter(Character ch) {
        Integer idx = indexOf(ch);
        return idx == null ? null : substring(idx + 1, null);
    }

    /**
     * Returns the substring before the <code>ch</code> character. If the string does not contain
     * the given character, , or if <code>str</code> or <code>ch</code> is null, then null is
     * returned. If <code>ch</code> is the first character, then an empty string is returned.
     */
    public String substringBefore(Character ch) {
        Integer idx = indexOf(ch);
        return idx == null ? null : idx == 0 ? "" : substring(0, idx - 1);
    }

    /**
     * Same as StringExt#substring, but with the indices inclusive, like the Ruby syntax <code>str[4
     * .. 8]</code>. This method the same as <code>Str.substring</code>.
     */
    public String get(Integer fromIndex, Integer toIndex) {
        return substring(fromIndex, toIndex);
    }
    
    /**
     * Converts the index, which can be positive or negative, to one within range for this string. A
     * negative index will result in the distance from the end of the string, with index -1 meaning
     * the last character in the string. Returns null if the resulting index is out of range. If
     * the wrapped string is null, then null is returned.
     */
    protected Integer getIndex(Integer index) {
        return isNull() || this.string.length() == 0 ? null : Index.getIndex(this.string.length(), index);
    }

    /**
     * Returns whether the string <code>str</code> begins with the character <code>ch</code>.
     * Returns false if the wrapped string is null.
     */
    public boolean startsWith(char ch) {
        Character startChar = get(0);
        return startChar != null && startChar == ch;
    }

    /**
     * Returns whether the string <code>str</code> begins with the string <code>prefix</code>. For
     * consistency with String. Returns false if the wrapped string is null.
     */
    public boolean startsWith(String pref) {
        return this.string != null && this.string.startsWith(pref);
    }

    /**
     * Removes a single end of line character, either \\n or \\r. Even if there are multiple end of
     * line characters, only one is removed. Returns null if the wrapped string is null.
     *
     * @see #chompAll
     */
    public String chomp() {
        if (this.string == null) {
            return null;
        }
        else {
            Character lastChar = get(-1);
            if (lastChar == null) {
                return "";
            }
            else if ("\r\n".indexOf(lastChar) >= 0) {
                return substring(0, -2);
            }
            else {
                return this.string;
            }
        }
    }

    /**
     * Removes multiple end of line characters, either \\n or \\r. Returns null if the wrapped
     * string is null.
     *
     * @see #chomp
     */
    public String chompAll() {
        if (this.string == null) {
            return null;
        }
        else {
            int idx = this.string.length() - 1;
            while (idx >= 0 && "\r\n".indexOf(this.string.charAt(idx)) != -1) {
                --idx;
            }
            return this.string.substring(0, idx + 1);
        }
    }


    /**
     * Returns whether the string contains the character. If <code>str</code> or
     * <code>ch</code> is null, false is returned.
     */
    public boolean contains(Character ch) {
        return indexOf(ch) != null;
    }
    
    /**
     * Returns the index of the first occurance of the character in the string, or null if either
     * <code>str</code> or <code>ch</code> is null, or if the character is not in the string.
     */
    public Integer indexOf(Character ch) {
        if (isNull() || isNull(ch)) {
            return null;
        }
        else {
            int idx = this.string.indexOf(ch);
            return idx >= 0 ? Integer.valueOf(idx) : null;
        }
    }

    /**
     * Returns whether the wrapped string is equal to the other. If both are null, then true is
     * returned. Otherwise, if either is null, then false is returned.
     */
    public Boolean eq(String other) {
        return equals(other);
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
     * the given length and appended with a dash, so <code>snip("foobar", 3)</code> is "foo-.
     * Returns null if <code>str</code> is null. Returns an empty string if <code>length</code> is
     * zero or less.
     */
    public String snip(int len) {
        if (this.string == null) {
            return null;
        }
        else if (len <= 0) {
            return "";
        }
        else if (this.string.length() > len)  {
            return get(0, len - 1) + '-';
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

    /**
     * Returns the length of the string, returning 0 if the wrapped string is null.
     */
    public int length() {
        return isNull() ? 0 : this.string.length();
    }

    /**
     * Unquotes the string, removing matching leading and trailing single or double quotes, if both
     * of either type wraps the string. Returns the wrapped string if neither case is true. Returns
     * null if the wrapped string is null.
     */
    public String unquote() {
        if (length() >= 2 && get(0) == get(-1) && (get(0) == '"' || get(0) == '\'')) {
            return get(1, -2);
        }
        else {
            return this.string;
        }
    }    

    /**
     * Quotes the string, using double quotes. Returns null if the wrapped string is null.
     */
    public String quote() {
        return isNull() ? null : "\"" + this.string + "\"";
    }    
}
