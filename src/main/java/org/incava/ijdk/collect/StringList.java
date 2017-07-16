package org.incava.ijdk.collect;

import java.util.Collection;
import org.incava.ijdk.io.IO;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.str.Criteria;

/**
 * An extension of org.incava.ijdk.collect.Array&lt;String&gt;, with a constructor for varargs, and
 * selectors that use closures.
 */
public class StringList extends Array<String> {
    /**
     * Creates a new StringList.
     *
     * @param args the strings to populate the new array
     * @return the newly-created array
     */
    public static StringList of(String ... args) {
        return new StringList(args);
    }
    
    /**
     * Creates an empty StringList.
     *
     * @return the newly-created array
     */
    @SuppressWarnings("unchecked")
    public static StringList empty() {
        return new StringList();
    }
    
    private static final long serialVersionUID = -5489075883851520676L;

    /**
     * Creates an empty StringList.
     */
    public StringList() {
    }

    /**
     * Creates a StringList from the given collection.
     *
     * @param coll the collection of strings to populate this array.
     */
    public StringList(Collection<String> coll) {
        super(coll);
    }

    /**
     * Creates a StringList from the given varargs.
     *
     * @param ary the strings to populate the new array
     */
    public StringList(String ... ary) {
        for (String str : ary) {
            add(str);
        }
    }

    /**
     * Returns whether any string in this list starts with the given one.
     *
     * @param substr the substring to match
     * @return whether any string in this list starts with the given one.
     */
    public boolean anyStartsWith(String substr) {
        return hasMatch(Criteria.startsWith(substr));
    }

    /**
     * Returns whether any string in this list contains the given one.
     *
     * @param substr the substring to match
     * @return whether any string in this list contains the given one.
     */
    public boolean anyContains(String substr) {
        return hasMatch(Criteria.contains(substr));
    }

    /**
     * Returns whether any string in this list ends with the given one.
     *
     * @param substr the substring to match
     * @return whether any string in this list ends with the given one.
     */
    public boolean anyEndsWith(String substr) {
        return hasMatch(Criteria.endsWith(substr));
    }

    /**
     * Returns whether any element matches the given one, without regard to case.
     *
     * @param str the substring to match
     * @return whether any element matches the given one, without regard to case.
     */
    public boolean anyEqualsIgnoreCase(String str) {
        return hasMatch(Criteria.equalsIgnoreCase(str));
    }

    /**
     * Returns all strings in this list starting with the given one.
     *
     * @param substr the substring to match
     * @return all strings in this list starting with the given one.
     */
    public StringList allStartingWith(String substr) {
        return findAll(Criteria.startsWith(substr));
    }

    /**
     * Returns all strings in this list containing the given one.
     *
     * @param substr the substring to match
     * @return all strings in this list containing the given one.
     */
    public StringList allContaining(String substr) {
        return findAll(Criteria.contains(substr));
    }

    /**
     * Returns all strings in this list ending with the given one.
     *
     * @param substr the substring to match
     * @return all strings in this list ending with the given one.
     */
    public StringList allEndingWith(String substr) {
        return findAll(Criteria.endsWith(substr));
    }

    /**
     * Returns the first string in the list for which the closure returns true. Returns null if the
     * criteria is null or the criteria is not matched.
     *
     * @param criteria the selection criteria
     * @return the first string in the list for which the closure returns true.
     * @see org.incava.ijdk.str.Criteria
     * @see org.incava.ijdk.lang.Closure
     */
    public String findFirst(Closure<Boolean, String> criteria) {
        if (criteria == null) {
            return null;
        }
        for (String str : this) {
            if (criteria.execute(str)) {
                return str;
            }
        }
        return null;
    }    

    /**
     * Returns whether the given criteria matches any element in the list.
     *
     * @param criteria the selection criteria
     * @return whether the given criteria matches any element in the list.
     * @see org.incava.ijdk.str.Criteria
     * @see org.incava.ijdk.lang.Closure
     */
    public Boolean hasMatch(Closure<Boolean, String> criteria) {
        return findFirst(criteria) != null;
    }    

    /**
     * Returns all strings in the list for which the closure returns true. Returns an empty list if
     * the criteria is null or the criteria is not matched.
     *
     * @param criteria the selection criteria
     * @return all strings in the list for which the closure returns true.
     * @see org.incava.ijdk.str.Criteria
     */
    public StringList findAll(Closure<Boolean, String> criteria) {
        StringList matching = new StringList();
        if (criteria == null) {
            return matching;
        }
        for (String str : this) {
            if (criteria.execute(str)) {
                matching.add(str);
            }
        }
        return matching;
    }

    /**
     * Returns the string list as lines, which have the current end-of-line character(s) for the
     * current OS, for any element that does not already have an EOLN character. Null elements will
     * have no EOLN added.
     *
     * @return the new list of strings
     */
    public StringList toLines() {
        String eoln = IO.EOLN;
        StringList newList = StringList.empty();
        for (String str : this) {
            if (str == null || str.endsWith(eoln)) {
                newList.append(str);
            }
            else {
                newList.append(str + eoln);
            }
        }
        return newList;
    }
}
