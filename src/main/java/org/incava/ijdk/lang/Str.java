package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.incava.ijdk.util.Indexable;

/**
 * Extensions to the String class. A Java String (java.lang.String) can be dereferenced from the Str
 * object via the <code>str</code> and <code>obj</code> methods. Str instances are immutable.
 */
public class Str extends Obj<String> implements Comparing<Str> {
    public static final Str EMPTY = new Str("");
    
    /**
     * Creates an empty string. There is only one shared, immutable empty string object, to conserve
     * memory.
     *
     * @return the empty string
     */
    public static Str empty() {
        return EMPTY;
    }
    
    /**
     * Creates a string. In a future implementation, this may pool frequently-used objects for reduced memory.
     *
     * @param str the string; may be null
     * @return the new Str
     */
    public static Str of(String str) {
        return new Str(str);
    }
    
    /**
     * Creates a string from the collection, joined by <code>delim</code>. If <code>coll</code> is
     * null, then the wrapped string is null. If <code>delim</code> is null, it is treated as the
     * empty string.
     * 
     * @param coll the collection to be joined
     * @param delim the delimiter within the joined string
     * @return the joined string
     */
    public static Str join(Collection<?> coll, String delim) {
        if (coll == null) {
            return new Str(null);
        }
        StringBuilder sb = null;
        for (Object obj : coll) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            else {
                sb.append(delim == null ? "" : delim);
            }            
            sb.append(String.valueOf(obj));
        }
        return new Str(sb == null ? "" : sb.toString());
    }

    /**
     * Creates a string from the array, joined by <code>delim</code>. If <code>ary</code> is null,
     * then the wrapped string is null. If <code>delim</code> is null, it is treated as the empty
     * string.
     *
     * @param ary the array to join
     * @param delim the delimiter within the joined string
     * @return the joined string
     */
    public static Str join(Object[] ary, String delim) {
        return join(ary == null ? null : Arrays.asList(ary), delim);
    }

    /**
     * Wraps the given string.
     */
    public Str(String string) {
        super(string);
    }

    /**
     * Creates a new string from the character, and wraps that string.
     *
     * @param ch the character to wrap
     */
    public Str(char ch) {
        this(String.valueOf(ch));
    }

    /**
     * Repeats the given string <code>num</code> times.
     *
     * @param str the string to repeat
     * @param num the number of times to repeat
     */
    public Str(String str, int num) {
        super(StringExt.repeat(str, num));
    }

    /**
     * Repeats the given character <code>num</code> times.
     *
     * @param ch the character to repeat
     * @param num the number of times to repeat
     */
    public Str(char ch, int num) {
        super(StringExt.repeat(ch, num));
    }
    
    /**
     * Returns the wrapped string, as a <code>java.lang.String</code>. Will be null if the wrapped
     * string is null.
     *
     * @return the wrapped string
     */
    public String getString() {
        return str();
    }

    /**
     * Returns the wrapped string, as a <code>java.lang.String</code>. Will be null if the wrapped
     * string is null.
     *
     * @return the wrapped string
     */
    public String str() {
        return obj();
    }

    /**
     * Returns whether the given string is equal to this one.
     *
     * @param other the string to compare to this one
     * @return the comparison value
     */
    public boolean equals(String other) {
        return str() == null ? other == null : str().equals(other);
    }

    /**
     * Returns whether the given string is equal to this one.
     *
     * @param obj the object to compare to this one
     * @return the comparison value
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return str() == null;
        }
        else if (obj instanceof String) {
            return equals((String)obj);
        }
        else if (obj instanceof Str) {
            return equals(((Str)obj).str());
        }
        else {
            return false;
        }
    }
    
    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     *
     * @param delim the delimiter to split at
     * @param max the maximum number of elements
     * @return the array of split strings
     */
    public String[] split(char delim, int max) {
        return split(String.valueOf(delim), max);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null. Unlike <code>java.lang.String#split</code>, the delimiter is only a string, not a
     * regular expression.
     *
     * @param delim the delimiter to split at
     * @param max the maximum number of elements
     * @return the array of split strings
     */
    public String[] split(String delim, int max) {
        List<String> list = splitToList(delim, max);
        return list == null ? null : list.toArray(new String[list.size()]);
    }

    public List<String> splitToList(char delim, Integer max) {
        return splitToList(String.valueOf(delim), max);
    }

    public List<String> splitToList(String delim, Integer max) {
        List<String> list = new ArrayList<>();

        if (isNull()) {
            return null;
        }
        else if (max != null && Integer.valueOf(max) == 1) {
            list.add(str());
        }
        else if (!isEmpty()) {
            int strlen = str().length();
            int dellen = delim.length();
            int idx = 0;
            int from = 0;
            while (idx < strlen) {
                if (delim.equals(get(idx, idx + dellen - 1))) {
                    if (max != null && list.size() + 1 == max) {
                        list.add(get(from, -1));
                        return list;
                    }
                    else {
                        String substr = idx == 0 ? "" : get(from, idx - 1);
                        list.add(substr);
                        idx += dellen;
                        from = idx;
                    }
                }
                else {
                    ++idx;
                }
            }
            list.add(get(from, idx == 0 ? 0 : idx - 1));
        }

        return list;
    }

    /**
     * Returns an array of strings split at the character delimiter. Returns null if
     * <code>str</code> is null.
     *
     * @param delim the delimiter to split at
     * @return the array of split strings
     */
    public String[] split(char delim) {
        return split(String.valueOf(delim), -1);
    }

    /**
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null.
     *
     * @param delim the delimiter to split at
     * @return the array of split strings
     */
    public String[] split(String delim) {
        return split(delim, -1);
    }

    /**
     * Converts the string into a list, delimited by whitespace and commas.
     *
     * @return the list of strings, without whitespace or commas
     */
    public List<String> toList() {
        if (isNull()) {
            return null;
        }
        
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str(), " \t\n\r\f,");
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
     * <pre>
     * Examples:
     *     pad("abcd", '*', 8) -&gt; "abcd****"
     *     pad("abcd", '*', 3) -&gt; "abcd"
     * </pre>
     * 
     * @param ch the character to use for padding
     * @param length the length of the padded string
     * @return the padded string
     */
    public Str pad(char ch, int length) {
        if (str() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str());
        while (sb.length() < length) {
            sb.append(ch);
        }
        return Str.of(sb.toString());
    }

    /**
     * Same as the <code>pad</code> method, but applies the padding to the left-hand (leading) side
     * of the string.
     *
     * Examples:
     * <pre>
     *     pad("1234", '*', 8)  -&gt; "****1234"
     *     pad("1234", '*', 3) -&gt; "1234"
     * </pre>
     * 
     * @param ch the character to use for padding
     * @param length the length of the padded string
     * @return the padded string
     */
    public Str padLeft(char ch, int length) {
        if (isNull()) {
            return null;
        }
        else {
            int len = length - str().length();
            Str cstr = new Str(ch, len);
            return Str.of(cstr.str() + str());
        }
    }

    /**
     * Pads with spaces.
     * 
     * @param length the length of the padded string
     * @return the padded string
     */
    public Str pad(int length) {
        return pad(' ', length);
    }

    /**
     * Left-pads with spaces.
     * 
     * @param length the length of the padded string
     * @return the padded string
     */
    public Str padLeft(int length) {
        return padLeft(' ', length);
    }

    /**
     * Returns the string, repeated <code>num</code> times. Returns null if the referenced string is
     * null.
     * 
     * @param num the number of times to repeat this string
     * @return the repeated string
     */
    public Str repeat(int num) {
        if (isNull()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; ++i) {
            sb.append(str());
        }
        return Str.of(sb.toString());
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw IndexOutOfBoundsException. Returns null
     * if the input string is null. Returns an empty string if <code>num</code> is negative or zero.
     *
     * @param num the number of characters to extract
     * @return the extracted string
     */
    public Str left(int num) {
        if (num <= 0) {
            return Str.empty();
        }
        else {
            String s = get(0, num - 1);
            return s == null ? null : Str.of(s);
        }
    }

    /**
     * Returns the rightmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw IndexOutOfBoundsException. Returns null
     * if the wrapped string is null. Returns an empty string if <code>num</code> is negative or
     * zero.
     *
     * @param num the number of characters to extract
     * @return the extracted string
     */
    public Str right(int num) {
        if (isNull()) {
            return null;
        }
        else if (num <= 0) {
            return Str.empty();
        }
        else {
            int from = Math.min(num, str().length());
            String s = get(-from, -1);
            return s == null ? null : Str.of(s);
        }
    }

    /**
     * Returns the character at the given index, or null if <code>index</code> is out of range. If
     * <code>index</code> is negative, the character is the nth character from the end of the
     * string, where -1 is the last character in the string. If the wrapped string is null, then
     * null is returned.
     *
     * @return The character at the given index, or null if out of range
     * @param index The index into the source string. Negative value goes from end backward
     */
    public Character charAt(int index) {
        if (isNull()) {
            return null;
        }
        else {
            Integer idx = getIndex(index);
            return idx == null ? null : str().charAt(idx);
        }
    }

    /**
     * An alias for Str#charAt, handling negative indices.
     *
     * @param index the index at which to get the character
     * @return the character at the index
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
     * @param fromIndex the starting index, inclusive
     * @param toIndex the ending index, inclusive
     * @return the substring
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
            if (frIdx == null) {
                return "";
            }
        }

        Integer toIdx = toIndex == null ? null : getIndex(toIndex);
        if (toIdx == null) {
            toIdx = str().length() - 1;
        }

        if (frIdx > toIdx) {
            // We could return null, but Ruby returns "" if the indices are within the length of the
            // string, and null if not. We'll just go with empty.
            return "";
        }
        else {
            return str().substring(frIdx, 1 + toIdx);
        }
    }

    /**
     * Returns the substring from the index after the <code>ch</code> character to the end of the
     * string. If the string does not contain the given character, or if <code>str</code> or
     * <code>ch</code> is null, then null is returned. If <code>ch</code> is the last character,
     * then an empty string is returned.
     *
     * @param ch the character to find
     * @return the extracted substring
     */
    public String substringAfter(Character ch) {
        Integer idx = indexOf(ch);
        return idx == null ? null : substring(idx + 1, null);
    }

    /**
     * Returns the substring before the <code>ch</code> character. If the string does not contain
     * the given character, , or if <code>str</code> or <code>ch</code> is null, then null is
     * returned. If <code>ch</code> is the first character, then an empty string is returned.
     *
     * @param ch the character to find
     * @return the extracted substring
     */
    public String substringBefore(Character ch) {
        Integer idx = indexOf(ch);
        if (idx == null) {
            return null;
        }
        else if (idx == 0) {
            return "";
        }
        else {
            return substring(0, idx - 1);
        }
    }

    /**
     * Same as <code>String#substring</code>, but with the indices <em>inclusive</em>, like the Ruby syntax
     * <code>str[4 .. 8]</code>. This method the same as <code>Str.substring</code>.
     *
     * @param fromIndex the starting index, inclusive
     * @param toIndex the ending index, inclusive
     * @return the substring
     */
    public String get(Integer fromIndex, Integer toIndex) {
        return substring(fromIndex, toIndex);
    }
    
    /**
     * Converts the index, which can be positive or negative, to one within range for this string. A
     * negative index will result in the distance from the end of the string, with index -1 meaning
     * the last character in the string. Returns null if the resulting index is out of range. If
     * the wrapped string is null, then null is returned.
     *
     * @param index the index
     * @return the relative index
     */
    protected Integer getIndex(Integer index) {
        return isNull() || str().length() == 0 ? null : new Indexable(str().length()).get(index);
    }

    /**
     * Returns whether the string <code>str</code> begins with the character <code>ch</code>.
     * Returns false if the wrapped string is null.
     *
     * @param ch the character to find
     * @return whether the string starts with <code>ch</code>
     */
    public boolean startsWith(char ch) {
        Character startChar = get(0);
        return startChar != null && startChar == ch;
    }

    /**
     * Returns whether the wrapped string begins with the string <code>str</code>. For
     * consistency with String. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str) {
        return str() != null && str().startsWith(str);
    }

    /**
     * Returns whether the wrapped string ends with the character <code>ch</code>.
     * Returns false if the wrapped string is null.
     *
     * @param ch the character to find
     * @return whether the string ends with <code>ch</code>
     */
    public boolean endsWith(char ch) {
        Character startChar = get(-1);
        return startChar != null && startChar == ch;
    }

    /**
     * Returns whether the wrapped string ends with the string <code>str</code>. For
     * consistency with String. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @return whether the string ends with <code>str</code>
     */
    public boolean endsWith(String str) {
        return str() != null && str().endsWith(str);
    }

    /**
     * Removes a single end-of-line character, either \\n or \\r. Even if there are multiple
     * end-of-line characters, only one is removed. Returns null if the wrapped string is null.
     *
     * @return the string without one end-of-line character
     * @see #chompAll
     */
    public Str chomp() {
        if (str() == null) {
            return null;
        }
        else {
            Character lastChar = get(-1);
            if (lastChar == null) {
                return Str.empty();
            }
            else if ("\r\n".indexOf(lastChar) >= 0) {
                return Str.of(get(0, -2));
            }
            else {
                return this;
            }
        }
    }

    /**
     * Removes multiple end-of-line characters, either \\n or \\r. Returns null if the wrapped
     * string is null.
     *
     * @return the string without all end-of-line characters
     * @see #chomp
     */
    public Str chompAll() {
        String string = str();
        if (string == null) {
            return null;
        }
        else {
            int idx = string.length() - 1;
            while (idx >= 0 && "\r\n".indexOf(string.charAt(idx)) != -1) {
                --idx;
            }
            return Str.of(get(0, idx));
        }
    }

    /**
     * Returns whether the string contains the character. If <code>str</code> or
     * <code>ch</code> is null, false is returned.
     *
     * @param ch the character to find
     * @return whether the string contains <code>ch</code>
     */
    public boolean contains(Character ch) {
        return indexOf(ch) != null;
    }
    
    /**
     * Returns the index of the first occurance of the character in the string, or null if either
     * <code>str</code> or <code>ch</code> is null, or if the character is not in the string.
     *
     * @param ch the character to find
     * @return the index of <code>ch</code>
     */
    public Integer indexOf(Character ch) {
        if (isNull() || ch == null) {
            return null;
        }
        else {
            int idx = str().indexOf(ch);
            return idx >= 0 ? Integer.valueOf(idx) : null;
        }
    }

    /**
     * Returns whether the wrapped string is equal to the other. If both are null, then true is
     * returned. Otherwise, if either is null, then false is returned.
     *
     * @param other the other string
     * @return the comparison value
     */
    public Boolean eq(String other) {
        return equals(other);
    }

    /**
     * Returns whether the wrapped string is equal to the other, <em>without regard to case</em>. If
     * both are null, then true is returned. Otherwise, if either is null, then false is returned.
     *
     * @param other the other string
     * @return the comparison value
     */
    public Boolean eqi(String other) {
        if (str() == null || other == null) {
            return str() == null && other == null;
        }
        else {
            return str().equalsIgnoreCase(other);
        }
    }

    /**
     * If <code>str</code> is longer than <code>length</code>, then the returned string is cut at
     * the given length - 1, and appended with a dash, so <code>snip("foobar", 4)</code> is "foo-".
     * Returns null if <code>str</code> is null. Returns an empty string if <code>length</code> is
     * zero or less.
     *
     * @param len the length of the snipped string
     * @return the snipped string
     */
    public Str snip(int len) {
        if (str() == null) {
            return null;
        }
        else if (len <= 0) {
            return Str.empty();
        }
        else if (str().length() > len)  {
            String substr = len - 2 < 0 ? "" : get(0, len - 2);
            return Str.of(substr + '-');
        }
        else {
            return this;
        }
    }

    /**
     * Returns whether the wrapped string is null or of zero length.
     *
     * @return whether the string is empty
     */
    public boolean isEmpty() {
        // str.isEmpty() is JDK 1.6+, and IJDK is backward compatible with 1.5.
        return isNull() || str().length() == 0;
    }

    /**
     * Returns the length of the string, returning 0 if the wrapped string is null.
     *
     * @return the length of the string
     */
    public int length() {
        return isNull() ? 0 : str().length();
    }

    /**
     * Unquotes the string, removing matching leading and trailing single or double quotes, if both
     * of either type wraps the string. Returns the wrapped string if neither case is true. Returns
     * null if the wrapped string is null.
     *
     * @return the string, unquoted
     * @see #quote
     */
    public Str unquote() {
        if (isNull() || length() < 2) {
            return this;
        }
        else {
            Character first = get(0);
            Character last = get(-1);
            if (first.equals(last) && (first.equals('"') || first.equals('\''))) {
                return Str.of(get(1, -2));
            }
            else {
                return this;
            }
        }
    }    

    /**
     * Quotes the string, using double quotes. Returns null if the wrapped string is null.
     *
     * @return the string, quoted
     */
    public Str quote() {
        return isNull() ? null : Str.of("\"" + str() + "\"");
    }

    /**
     * Returns a negative number, zero, or a positive number, for when <code>other</code> is less
     * than, equal to, or greater than this one.
     *
     * @param other the other string
     * @return the comparison value
     */
    public int compareTo(Str other) {
        if (isNull()) {
            return other == null || other.isNull() ? 0 : -1;
        }
        else if (other.isNull()) {
            return 1;
        }
        else {
            return str().compareTo(other.str());
        }
    }

    /**
     * Returns the hash code of the wrapped string, or zero if null.
     *
     * @return the hash code
     */
    public int hashCode() {
        return isNull() ? 0 : str().hashCode();
    }

    /**
     * Returns whether the wrapped string is less than that of the other.
     *
     * @param other the other string
     * @return the comparison value
     */
    public boolean lt(Str other) {
        return new DefaultComparing<Str>(this).lt(other);
    }

    /**
     * Returns whether the wrapped string is less than or equal to that of the other.
     *
     * @param other the other string
     * @return the comparison value
     */
    public boolean lte(Str other) {
        return new DefaultComparing<Str>(this).lte(other);
    }

    /**
     * Returns whether the wrapped string is greater than that of the other.
     *
     * @param other the other string
     * @return the comparison value
     */
    public boolean gt(Str other) {
        return new DefaultComparing<Str>(this).gt(other);
    }

    /**
     * Returns whether the wrapped string is greater than or equal to that of the other.
     *
     * @param other the other string
     * @return the comparison value
     */
    public boolean gte(Str other) {
        return new DefaultComparing<Str>(this).gte(other);
    }

    /**
     * Returns the first character in the string, or null if the string is null or empty.
     *
     * @return the first character
     */
    public Character first() {
        return get(0);
    }

    /**
     * Returns the last character in the string, or null if the string is null or empty.
     *
     * @return the last character
     */
    public Character last() {
        return get(-1);
    }

    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>. Unlike
     * String#replaceAll, this does not apply <code>from</code> as a regular expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @return the string, with substitutions
     */
    public String replaceAll(String from, String to) {
        return replaceAll(from, to, false);
    }
    
    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>, without regard to
     * case. Unlike String#replaceAll, this does not apply <code>from</code> as a regular
     * expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @return the string, with substitutions
     */
    public String replaceAllIgnoreCase(String from, String to) {
        return replaceAll(from, to, true);
    }

    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>, optionally without
     * regard to case. Unlike String#replaceAll, this does not apply <code>from</code> as a regular
     * expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @param ignoreCase whether to ignore case
     * @return the string, with substitutions
     */
    public String replaceAll(String from, String to, boolean ignoreCase) {
        if (isNull()) {
            return null;
        }
        Str newStr = new Str(obj());
        int pos = 0;
        while ((pos = newStr.indexOf(from, pos, ignoreCase)) >= 0) {
            newStr = new Str(newStr.obj().substring(0, pos) + to + newStr.obj().substring(pos + from.length()));
            pos += to.length();
        }
        return newStr.obj();
    }

    /**
     * Returns the position of <code>str</code> in this string, starting at <code>pos</code>,
     * optionally ignoring case.
     *
     * @param substr the string to search for
     * @param pos the starting position of the search
     * @param ignoreCase whether to ignore case
     * @return the index at which substr is found
     */
    public int indexOf(String substr, int pos, boolean ignoreCase) {
        String str = obj();
        if (str == null) {
            return -1;
        }
        else if (ignoreCase) {
            str = str.toUpperCase();
            substr = substr.toUpperCase();
        }
        return str.indexOf(substr, pos);
    }    
}
