package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.regexp.MatchData;
import org.incava.ijdk.regexp.Regexp;
import org.incava.ijdk.str.StrComparators;
import org.incava.ijdk.util.Collections;
import org.incava.ijdk.util.Indexable;

/**
 * Extensions to the String class. A Java String (java.lang.String) can be dereferenced from the Str
 * object via the <code>str</code> and <code>obj</code> methods. Str instances are immutable.
 */
public class Str extends Obj<String> implements Comparing<Str> {
    public enum Option {
        ALPHANUMERIC,
        IGNORE_CASE,
        IGNORE_WHITESPACE
    }
    
    public static final Str EMPTY = new Str("");
    
    public static final Str NULL = new Str(null);
    
    /**
     * Creates an empty Str. There is only one shared, immutable empty string object, to conserve
     * memory.
     *
     * @return the empty string
     */
    public static Str empty() {
        return new StrFactory().empty();
    }
    
    /**
     * Creates an Str. In a future implementation, this may pool frequently-used objects for reduced memory.
     *
     * @param str the string; may be null
     * @return the new Str
     */
    public static Str of(String str) {
        return new StrFactory().of(str);
    }
    
    /**
     * Creates an Str from an object. The value of <code>obj.toString()</code> is used at the point
     * of object creation, so subsequent changes to <code>obj</code>, and therefore a different
     * return value of <code>obj.toString()</code>, are not updated in the returned
     * <code>Str</code>.
     *
     * @param obj the object
     * @return the new Str
     */
    @SuppressWarnings("unchecked")
    public static Str of(Object obj) {
        return new StrFactory().of(obj);
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
        return new StrFactory().join(coll, delim);
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
        return new StrFactory().join(ary, delim);
    }

    /**
     * Wraps the given string.
     *
     * @param string the string to wrap
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
        this(Strings.repeat(str, num));
    }

    /**
     * Repeats the given character <code>num</code> times.
     *
     * @param ch the character to repeat
     * @param num the number of times to repeat
     */
    public Str(char ch, int num) {
        this(Strings.repeat(ch, num));
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
     * Returns an array of strings split at the string delimiter. Returns null if <code>str</code>
     * is null. Unlike <code>java.lang.String#split</code>, the delimiter is a literal string, not a
     * regular expression.
     *
     * @param delim the delimiter to split at
     * @param max the maximum number of elements
     * @return the array of split strings
     */
    public List<String> split(String delim, Integer max) {
        if (isNull()) {
            return null;
        }
        
        Pattern pat = Pattern.compile(delim, Pattern.LITERAL);
        if (max == null) {
            max = 0;
        }
        String[] ary = pat.split(str(), max);
        return new ArrayList<>(Arrays.asList(ary));
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
        return isNull() ? null : Str.of(str() + Strings.repeat(ch, length - length()));
    }

    /**
     * Same as the <code>pad</code> method, but applies the padding to the left-hand (leading) side
     * of the string.
     *
     * Examples:
     * <pre>
     *     pad("1234", '*', 8) -&gt; "****1234"
     *     pad("1234", '*', 3) -&gt; "1234"
     * </pre>
     *
     * @param ch the character to use for padding
     * @param length the length of the padded string
     * @return the padded string
     */
    public Str padLeft(char ch, int length) {
        return isNull() ? null : Str.of(Strings.repeat(ch, length - length()) + str());
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
        return isNull() ? null : Str.of(Strings.repeat(str(), num));
    }

    /**
     * Returns the leftmost <code>num</code> characters of the string, not exceeding the length of
     * the string. Unlike String#substring, does not throw IndexOutOfBoundsException. Returns null
     * if the referenced string is null. Returns an empty string if <code>num</code> is negative or
     * zero.
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
            int from = Math.min(num, length());
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
     * If the wrapped string is is null, then null is returned.
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
            toIdx = length() - 1;
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
     * Returns the substring, as an <code>Str</code>, before the <code>ch</code> character. If the
     * string does not contain the given character, , or if <code>str</code> or <code>ch</code> is
     * null, then null is returned. If <code>ch</code> is the first character, then an empty string
     * is returned.
     *
     * @param ch the character to find
     * @param str an Str instance; for method overloading
     * @return the extracted substring
     */
    public Str substringBefore(Character ch, Str str) {
        Integer idx = indexOf(ch);
        if (idx == null) {
            return null;
        }
        else if (idx == 0) {
            return Str.EMPTY;
        }
        else {
            return Str.of(substring(0, idx - 1));
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
     * Returns the substring, as an <code>Str</code>, from the index after the <code>ch</code>
     * character to the end of the string. If the string does not contain the given character, or if
     * <code>str</code> or <code>ch</code> is null, then null is returned. If <code>ch</code> is the
     * last character, then an empty string is returned.
     *
     * @param ch the character to find
     * @param str an Str instance; for method overloading
     * @return the extracted substring
     */
    public Str substringAfter(Character ch, Str str) {
        Integer idx = indexOf(ch);
        return idx == null ? null : Str.of(substring(idx + 1, null));
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
        int len = isNull() ? 0 : length();
        return new Indexable(len).get(index);
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
     * Returns whether the wrapped string begins with the string <code>str</code>. Returns false if
     * the wrapped string is null.
     *
     * @param str the string to find
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str) {
        return startsWith(str, 0);
    }
    
    /**
     * Returns whether the wrapped string begins with the string <code>str</code>, starting at the
     * offset, and applying the given options. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @param offset the index from which to start the match
     * @param options the options to apply (valid: IGNORE_CASE)
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str, int offset, Str.Option ... options) {
        return startsWith(Str.of(str), offset, options);
    }
    
    /**
     * Returns whether the wrapped string begins with the string <code>str</code>, applying the
     * given options. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @param offset the index from which to start the match
     * @param options the options to apply (valid: IGNORE_CASE)
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str, int offset, EnumSet<Str.Option> options) {
        return startsWith(Str.of(str), offset, toArray(options));
    }

    /**
     * Returns whether the wrapped string begins with the string <code>str</code>, applying the
     * given options. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @param options the options to apply (valid: IGNORE_CASE)
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str, EnumSet<Str.Option> options) {
        return startsWith(str, 0, toArray(options));
    }    

    /**
     * Returns whether the wrapped string begins with the string <code>str</code>, applying the
     * given options. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @param options the options to apply (valid: IGNORE_CASE)
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(String str, Str.Option ... options) {
        return startsWith(str, 0, options);
    }    

    /**
     * Returns whether the wrapped string begins with the string <code>str</code>, starting at the
     * offset and applying the given options. Returns false if the wrapped string is null.
     *
     * @param str the string to find
     * @param offset the index from which to start the match
     * @param options the options to apply (valid: IGNORE_CASE)
     * @return whether the string starts with <code>str</code>
     */
    public boolean startsWith(Str str, int offset, Str.Option ... options) {
        if (isNull() || str == null || str.isNull() || offset + str.length() > length()) {
            return false;
        }
        
        boolean ignoreCase = options != null && Array.of(options).contains(Str.Option.IGNORE_CASE);
        for (int idx = 0; idx < str.length(); ++idx) {
            Character x = get(offset + idx);
            Character y = str.get(idx);
            if (!Characters.isMatch(x, y, ignoreCase)) {
                return false;
            }
        }
        return true;
    }    

    /**
     * Returns whether this string ends with the character <code>ch</code>. Returns false if the
     * wrapped string is null.
     *
     * @param ch the character to find
     * @return whether the string ends with <code>ch</code>
     */
    public boolean endsWith(char ch) {
        Character lastChar = get(-1);
        return lastChar != null && lastChar == ch;
    }

    /**
     * Returns whether this string ends with <code>str</code>. Returns false if the wrapped string
     * is null.
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
        if (isNull()) {
            return null;
        }
        else if (isEmpty()) {
            return Str.empty();
        }
        else if (Characters.isNewLine(this, -1)) {
            return Str.of(get(0, -2));
        }
        else {
            return this;
        }
    }

    /**
     * Removes all multiple end-of-line characters from the end of the string, either \\n or \\r.
     * Returns null if the wrapped string is null.
     *
     * @return the string without all end-of-line characters
     * @see #chomp
     */
    public Str chompAll() {
        if (isNull()) {
            return null;
        }
        else {
            int idx = length() - 1;
            while (idx >= 0 && Characters.isNewLine(this, idx)) {
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
        String s = str();
        return s == null || other == null ? (s == null && other == null) : s.equalsIgnoreCase(other);
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
        if (isNull()) {
            return null;
        }
        else if (len <= 0) {
            return Str.empty();
        }
        else if (length() > len)  {
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
        return isNull() || str().length() == 0;
    }

    /**
     * Returns whether the wrapped string is null or of zero length, optionally ignoring whitespace.
     *
     * @param option option to use in comparing; valid: IGNORE_WHITESPACE
     * @return whether the string is empty
     */
    public boolean isEmpty(Str.Option option) {
        return isEmpty() || (Str.Option.IGNORE_WHITESPACE.equals(option) && trim().isEmpty());
    }

    /**
     * Returns the length of the string, returning 0 if the wrapped string is null.
     *
     * @return the length of the string
     */
    public Integer length() {
        return isNull() ? 0 : str().length();
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
        
        Character first = first();
        Character last = last();
        if (first.equals(last) && (first.equals('"') || first.equals('\''))) {
            return Str.of(get(1, -2));
        }
        else {
            return this;
        }
    }

    /**
     * Returns a negative number, zero, or a positive number, for when <code>this</code> is less
     * than, equal to, or greater than <code>other</code> .
     *
     * @param other the other string
     * @return the comparison value
     */
    public int compareTo(Str other) {
        return compareTo(other, new Str.Option[0]);
    }
    
    /**
     * Returns a negative number, zero, or a positive number, for when <code>other</code> is less
     * than, equal to, or greater than this one. Takes a list of options, IGNORE_CASE and
     * ALPHANUMERIC the valid ones, which currently cannot be combined.
     *
     * @param other the other string
     * @param options options to use in comparing, IGNORE_CASE and ALPHANUMERIC
     * @return the comparison value
     */
    public int compareTo(Str other, Str.Option ... options) {
        if (isNull() || other == null || other.isNull()) {
            return Boolean.compare(isNull(), other == null || other.isNull());
        }
        else {
            Comparator<Str> comp = new StrComparators().create(options);
            return comp.compare(this, other);
        }
    }
    
    /**
     * Returns a negative number, zero, or a positive number, for when <code>other</code> is less
     * than, equal to, or greater than this one.
     *
     * @param other the other string
     * @param options options to use in comparing, IGNORE_CASE and ALPHANUMERIC
     * @return the comparison value
     */
    public int compareTo(Str other, EnumSet<Str.Option> options) {
        return compareTo(other, toArray(options));
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
     * Returns the first <code>num</code> characters in the string, or null if the string is null or
     * empty. Returns null if <code>num</code> is equal to or less than zero.
     *
     * @param num the number of characters
     * @return the first <code>num</code> characters
     */
    public Str first(int num) {
        if (num <= 0) {
            return isNull() ? null : EMPTY;
        }
        else {
            String fs = get(0, num - 1);
            return fs == null ? null : Str.of(fs);
        }
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
     * Returns the last <code>num</code> characters in the string, or null if the string is null or
     * empty. Returns null if <code>num</code> is equal to or less than zero.
     *
     * @param num the number of characters
     * @return the last <code>num</code> characters
     */
    public Str last(int num) {
        if (num <= 0) {
            return isNull() ? null : EMPTY;
        }
        else {
            int from = Math.min(num, length());
            String fs = get(-from, -1);
            return fs == null ? null : Str.of(fs);
        }
    }

    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>. Unlike
     * String#replaceAll, this applies <code>from</code> as a literal, not as a regular expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @return the string, with substitutions
     */
    public Str replaceAll(String from, String to) {
        return replaceAll(from, to, false);
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
    public Str replaceAll(String from, String to, boolean ignoreCase) {
        return replaceAll(from, to, ignoreCase ? EnumSet.of(Str.Option.IGNORE_CASE) : null);
    }

    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>, optionally without
     * regard to case. Unlike String#replaceAll, this does not apply <code>from</code> as a regular
     * expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @param options options use in replacing (valid: IGNORE_CASE)
     * @return the string, with substitutions
     */
    public Str replaceAll(String from, String to, EnumSet<Str.Option> options) {
        return replaceAll(from, to, toArray(options));
    }

    /**
     * Replaces literal occurrances of <code>from</code> with <code>to</code>, optionally without
     * regard to case. Unlike String#replaceAll, this does not apply <code>from</code> as a regular
     * expression.
     *
     * @param from the left-hand side of the replacement
     * @param to the right-hand side of the replacement
     * @param options options use in replacing (valid: IGNORE_CASE)
     * @return the string, with substitutions
     */
    public Str replaceAll(String from, String to, Str.Option ... options) {
        if (isNull()) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        String s = str();
        int len = length();

        int idx = 0;
        while (idx < len) {
            if (startsWith(from, idx, options)) {
                sb.append(to);
                idx += from.length();
            }
            else {
                sb.append(charAt(idx));
                ++idx;
            }
        }
        return Str.of(sb.toString());
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

    /**
     * Returns a list of matchers where the pattern was matched in the string. This is the
     * opposite of String#split, which returns the elements where the pattern does <em>not</em>
     * match. Each element in the returned list is a list, in which the first element is the entire
     * match, and the other elements are the capturing groups.
     *
     * <pre>
     * Str str = Str.of("abaca");
     * List&lt;List&lt;String&gt;&gt; result = str.scan(Pattern.compile("a(b).(.)"));
     * // result == list of "abac", "b", "c"
     * </pre>
     *
     * @param pattern the pattern to scan for
     * @return a list of list of matching elements, one inner list per section matched
     */
    public List<List<String>> scan(Pattern pattern) {
        List<MatchData> matches = matches(pattern);

        List<List<String>> matchList = new ArrayList<List<String>>();
        for (MatchData match : matches) {
            matchList.add(match);
        }

        return matchList;
    }

    /**
     * Returns a list of matchdata instances where the pattern was matched in the string. This is
     * the opposite of String#split, which returns the elements where the pattern does <em>not</em>
     * match.
     *
     * <pre>
     * Str str = Str.of("abaca");
     * List&lt;MatchData&gt; result = str.matches(Pattern.compile("a(b).(.)"));
     * // result == list of "abac", "b", "c"
     * </pre>
     *
     * @param pattern the pattern to match against
     * @return a list of match data instances, one per section matched
     */
    public List<MatchData> matches(Pattern pattern) {
        int idx = 0;
        String s = str();
        int len = length();
        Matcher m = pattern.matcher(s);

        List<MatchData> matchList = new ArrayList<MatchData>();
            
        while (idx < len && m.find(idx)) {
            MatchData md = MatchData.of(m);
            matchList.add(md);
            idx = m.end();
        }
        return matchList;
    }

    /**
     * Returns a copy of this string, without leading and trailing whitespace.
     *
     * @return the trimmed string
     */
    public Str trim() {
        return trimLeft().trimRight();
    }

    /**
     * Returns a copy of this string, without leading whitespace.
     *
     * @return the trimmed string
     */
    public Str trimLeft() {
        int len = length();
        int start = 0;
        while (start < len && isWhitespace(start)) {
            ++start;
        }
        return Str.of(get(start, len - 1));
    }

    /**
     * Returns a copy of this string, without trailing whitespace.
     *
     * @return the trimmed string
     */
    public Str trimRight() {
        int end = length() - 1;
        while (end >= 0 && isWhitespace(end)) {
            --end;
        }
        return Str.of(end < 0 ? "" : get(0, end));
    }

    /**
     * Returns a copy of the string with spaces escaped.
     *
     * @return the escaped string
     */
    public Str escape() {
        return Str.of(str().replaceAll(" ", "\\\\ "));
    }

    /**
     * Returns a copy of this string concatenated with the other.
     *
     * @param other the string to concatenate to this one
     * @return the concatenated string
     */
    public Str plus(Str other) {
        return append(other);
    }

    /**
     * Returns a copy of this string concatenated with the other.
     *
     * @param other the str to concatenate to this one
     * @return the concatenated string
     */
    public Str append(Str other) {
        return append(other.str());
    }

    /**
     * Returns a copy of this string concatenated with the other.
     *
     * @param other the string to concatenate to this one
     * @return the concatenated string
     */
    public Str append(String other) {
        return Str.of(str() + other);
    }

    /**
     * Returns a copy of this string concatenated with the character.
     *
     * @param ch the character to concatenate to this one
     * @return the concatenated string
     */
    public Str append(Character ch) {
        return append(String.valueOf(ch));
    }

    /**
     * Returns the index at which the pattern matches. Returns null if the pattern does not match.
     *
     * @param pattern the pattern to match
     * @return the index of the match, or null if none
     */
    public Integer indexOf(Pattern pattern) {
        return indexOf(pattern, 0);
    }

    /**
     * Returns the index at which the pattern matches, starting at the offset. Returns null if the
     * pattern does not match.
     *
     * @param pattern the pattern to match
     * @param offset the offset into the string to start
     * @return the index of the match, or null if none
     */
    public Integer indexOf(Pattern pattern, Integer offset) {
        Matcher m = pattern.matcher(str());
        return m.find(offset) ? m.start() : null;
    }

    /**
     * Returns the match data at which the regular expression matches, starting at the beginning of
     * the string. Returns null if the regexp does not match.
     *
     * @param re the regular expression to match
     * @return the match data, or null if none
     */
    public MatchData match(Regexp re) {
        return re.match(str());
    }

    /**
     * Returns the match data at which the regular expression matches, starting at the offset.
     * Returns null if the regexp does not match.
     *
     * @param re the regular expression to match
     * @param offset the offset into the string to start
     * @return the match data, or null if none
     */
    public MatchData match(Regexp re, Integer offset) {
        return re.match(str(), offset);
    }

    private Str.Option[] toArray(EnumSet<Str.Option> options) {
        return options == null ? new Str.Option[0] : options.toArray(new Str.Option[options.size()]);
    }

    private boolean isWhitespace(int index) {
        return Characters.isWhitespace(this, index);
    }
}
