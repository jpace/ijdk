package ijdk.lang;

import java.io.File;
import java.util.*;
import org.incava.ijdk.lang.ObjectExt;

/**
 * Extends java.io.File with Ruby-ish functionality, based on pathname.rb.
 *
 * The main differences are:
 *  - pathnames can be specified by relative paths, which can be accessed (via relativePath())
 *  - fewer checked exceptions
 *  - clearer method names: relativePath(), fullPath(), baseName(), rootName(), extension()
 */
public class Pathname extends File {
    private static final long serialVersionUID = -4936465396173432030L;

    /**
     * Creates a Pathname from a file, using its relative -- not absolute or canonical -- path.
     */
    public Pathname(File file) {
        super(file.toString());
    }

    /**
     * Creates a Pathname from a pathname.
     */
    public Pathname(String pathName) {
        super(pathName);
    }

    /**
     * Returns the relative -- not absolute or canonical -- path.
     *
     * <pre>
     *     new Pathname("abc/def.txt").relativePath(); // == "abc/def.txt"
     * </pre>
     */
    public String relativePath() {
        return toString();
    }

    /**
     * Returns the base name (same as File#getName()).
     *
     * <pre>
     *     new Pathname("abc/def.txt").baseName();     // == "def.txt"
     * </pre>
     */
    public String baseName() {
        return getName();
    }

    /**
     * Returns the extension, the segment trailing the final dot, or null if there is no dot in the
     * basename.
     *
     * <pre>
     *     new Pathname("abc/def.txt").extension();    // == "txt"
     *     new Pathname("abc/def.tar.gz").extension(); // == "gz"
     *     new Pathname("abc/def.").extension();       // == ""
     *     new Pathname("abc/def").extension();        // == null
     * </pre>
     */
    public String extension() {
        String name = baseName();
        int lastDot = name.lastIndexOf('.');
        return lastDot < 0 ? null : new Str(name).get(lastDot + 1, -1);
    }

    public File file() {
        return this;
    }
}
