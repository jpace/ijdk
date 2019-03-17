package org.incava.ijdk.lang;

import java.io.File;
import java.util.List;

public class PathnameGlob {
    public static final String SEPARATOR = File.separator;

    /**
     * Returns the glob as a pattern (regular expression).
     *
     * @param glob the glob
     * @return the pattern
     */
    public static String toPattern(String glob) {
        List<String> components = new Str(glob).split(SEPARATOR, null);
        List<String> patList = ICore.<String>list();
        for (String comp : components) {
            patList.add(elementToPattern(comp));
        }
        return Str.join(patList, SEPARATOR).str();
    }

    /**
     * Converts an element within a glob, to a pattern, escaping characters such as '*' and '.'.
     *
     * Roughly based on Dir#glob: http://ruby-doc.org/core-1.9.3/Dir.html
     *
     * @param element the element, such as "**"
     * @return the pattern
     */
    protected static String elementToPattern(String element) {
        if (element.equals("**")) {
            return ".*";
        }
        else {
            String elmt = element;
            elmt = elmt.replaceAll("\\*", "[^\\" + SEPARATOR + "]*");
            elmt = elmt.replaceAll("\\.", "\\\\.");
            return elmt;
        }
    }
}
