package org.incava.ijdk.lang;

import org.incava.ijdk.lang.Str;
import java.util.Collection;
import java.util.List;

/**
 * Extensions to the String class. Alternatively, the static methods here are defined as instance
 * objects of the <code>Str</code> class. See documentation of the equivalent methods in <code>Str</code>.
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

    public static String[] split(String str, char delim) {
        return new Str(str).split(String.valueOf(delim), -1);
    }

    public static String[] split(String str, String delim) {
        return new Str(str).split(delim, -1);
    }

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
        return new Str(str).unquote();
    }    

    public static String quote(String str) {
        return new Str(str).quote();
    }    
}
