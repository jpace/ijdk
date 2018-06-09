package org.incava.ijdk.collect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.incava.ijdk.io.IO;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.str.Criteria;

/**
 * An extension of org.incava.ijdk.collect.Array&lt;String&gt;, with a constructor for varargs, and
 * selectors that use closures.
 */
public class StringArray extends BaseArray<String, StringArray> {
    /**
     * Creates a new StringArray.
     *
     * @param args the strings to populate the new array
     * @return the newly-created array
     */
    public static StringArray of(String ... args) {
        return new StringArray(args);
    }

    /**
     * Reads lines from a text file.
     *
     * @param file the file to read from
     * @return an array of lines from the file
     */
    public static StringArray from(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            return new StringArray(lines);
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Reads lines from an input stream, which is presumed to be text.
     *
     * @param stream the stream to read from
     * @return an array of lines from the stream
     */
    public static StringArray from(InputStream stream) {
        InputStreamReader isr = null;
        try {
            StringArray lines = empty();
            isr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException ioe) {
            }
        }
    }
    
    /**
     * Creates an empty StringArray.
     *
     * @return the newly-created array
     */
    @SuppressWarnings("unchecked")
    public static StringArray empty() {
        return new StringArray();
    }
    
    private static final long serialVersionUID = -5489075883851520676L;

    /**
     * Creates an empty StringArray.
     */
    public StringArray() {
    }

    public StringArray newInstance() {
        return new StringArray();
    }

    /**
     * Creates a StringArray from the given collection.
     *
     * @param coll the collection of strings to populate this array.
     */
    public StringArray(Collection<String> coll) {
        super(coll);
    }

    /**
     * Creates a StringArray from the given varargs.
     *
     * @param ary the strings to populate the new array
     */
    public StringArray(String ... ary) {
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
    public StringArray allStartingWith(String substr) {
        return findAll(Criteria.startsWith(substr));
    }

    /**
     * Returns all strings in this list containing the given one.
     *
     * @param substr the substring to match
     * @return all strings in this list containing the given one.
     */
    public StringArray allContaining(String substr) {
        return findAll(Criteria.contains(substr));
    }

    /**
     * Returns all strings in this list ending with the given one.
     *
     * @param substr the substring to match
     * @return all strings in this list ending with the given one.
     */
    public StringArray allEndingWith(String substr) {
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
            Boolean exec = criteria.execute(str);
            if (exec != null && exec) {
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
    public StringArray findAll(Closure<Boolean, String> criteria) {
        StringArray matching = new StringArray();
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
    public StringArray toLines() {
        String eoln = IO.EOLN;
        StringArray newList = StringArray.empty();
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

    /**
     * Returns a list of strings, formatted via <code>repl</code>.
     *
     * @param repl the formatting to apply to each element in this list
     * @return a new StringArray, containing the formatted elements
     * @see java.lang.String#format
     * @see java.util.Formatter
     */
    public StringArray collect(String repl) {
        StringArray sl = StringArray.empty();
        for (String it : this) {
            sl.add(String.format(repl, it));
        }
        return sl;
    }
}
