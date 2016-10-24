package org.incava.ijdk.util;

import java.util.*;
import org.incava.ijdk.lang.Closure;

/**
 * An extension of List&lt;String&gt;, with a constructor for varargs, and selectors that use closures.
 */
public class StringList extends ArrayList<String> {
    private static final long serialVersionUID = -5489075883851520676L;
    
    /**
     * Creates a StringList with the given initial capacity.
     */
    public StringList(int capacity) {
        super(capacity);
    }

    /**
     * Creates an empty StringList.
     */
    public StringList() {
    }

    /**
     * Creates a StringList from the given collection.
     */
    public StringList(Collection<String> coll) {
        super(coll);
    }

    /**
     * Creates a StringList from the given array.
     */
    public StringList(String ... ary) {
        for (String str : ary) {
            add(str);
        }
    }

    /**
     * Returns whether any string in this list starts with the given one.
     */
    public boolean anyStartsWith(String substr) {
        return findFirst(StringCriteria.startsWith(substr)) != null;
    }

    /**
     * Returns whether any string in this list contains the given one.
     */
    public boolean anyContains(String substr) {
        return findFirst(StringCriteria.contains(substr)) != null;
    }

    /**
     * Returns whether any string in this list ends with the given one.
     */
    public boolean anyEndsWith(String substr) {
        return findFirst(StringCriteria.endsWith(substr)) != null;
    }

    /**
     * Returns all strings in this list starting with the given one.
     */
    public StringList allStartingWith(String substr) {
        return findAll(StringCriteria.startsWith(substr));
    }

    /**
     * Returns all strings in this list containing the given one.
     */
    public StringList allContaining(String substr) {
        return findAll(StringCriteria.contains(substr));
    }

    /**
     * Returns all strings in this list ending with the given one.
     */
    public StringList allEndingWith(String substr) {
        return findAll(StringCriteria.endsWith(substr));
    }

    /**
     * Returns the first string in the list for which the closure returns true. Returns null if the
     * criteria is null or the criteria is not matched.
     *
     * @see StringCriteria
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
     * Returns all strings in the list for which the closure returns true. Returns an empty list if
     * the criteria is null or the criteria is not matched.
     *
     * @see StringCriteria
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
     * Pops the <em>last</em> element off the list and returns it, or null if the list is empty.
     * This is consistent with Ruby and Perl, but other languages 
     */
    public String pop() {
        return isEmpty() ? null : remove(size() - 1);
    }

    /**
     * Removes (shifts) the first element off the list and returns it, or null if the list is empty.
     */
    public String shift() {
        return isEmpty() ? null : remove(0);
    }
}
