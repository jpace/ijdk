package org.incava.ijdk.lang;

import java.io.File;
import org.incava.ijdk.io.FileExt;
import org.incava.ijdk.io.IO;

/**
 * Extends java.io.File with Ruby-ish functionality, based on pathname.rb.
 *
 * The main differences are:
 *  - pathnames can be specified by relative paths, which can be accessed (via relativePath())
 *  - fewer checked exceptions
 *  - clearer method names: relativePath(), fullPath(), baseName(), rootName(), extension()
 */
public class Pathname extends File {
    public static java.util.List<Pathname> glob(String glob) {
        String pattern = PathnameGlob.toPattern(glob);
        java.util.List<Pathname> all = findAll();
        return all;
    }

    public static java.util.List<Pathname> findAll() {
        Pathname root = new Pathname();
        java.util.List<Pathname> all = new java.util.ArrayList<Pathname>();
        for (Pathname child : root.children()) {
            all.add(child);
        }
        return all;
    }
    
    private static final long serialVersionUID = -4936465396173432030L;

    /**
     * Creates a Pathname from a file, using its relative -- not absolute or canonical -- path.
     *
     * @param file the file for this pathname
     */
    public Pathname(File file) {
        super(file.toString());
    }

    /**
     * Creates a Pathname from a name.
     *
     * @param pathName the name of this object
     */
    public Pathname(String pathName) {
        super(pathName);
    }

    /**
     * Creates a Pathname from the current directory (user.dir).
     */
    public Pathname() {
        this(new File(System.getProperty("user.dir")));
    }

    /**
     * Returns the relative -- not absolute or canonical -- path.
     *
     * <pre>
     *     new Pathname("abc/def.txt").relativePath(); // == "abc/def.txt"
     * </pre>
     *
     * @return the relative path of this pathname
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
     *
     * @return the base name of this pathname
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
     *
     * @return the extension of this pathname
     */
    public String extension() {
        String name = baseName();
        int lastDot = name.lastIndexOf('.');
        return lastDot < 0 ? null : new Str(name).get(lastDot + 1, -1);
    }

    /**
     * Returns the root name, which is the base name minus the (last) extension. If there is no
     * extension, then the root name is the same as the base name.
     *
     * <pre>
     *     new Pathname("abc/def.txt").rootName();     // == "def"
     *     new Pathname("abc/def.tar.gz").rootName();  // == "def.tar"
     * </pre>
     *
     * @return the root name of this pathname
     */
    public String rootName() {
        String baseName = baseName();
        String ext = extension();
        return ext == null ? baseName : new Str(baseName).get(0, -ext.length() - 2);
    }

    public File file() {
        return this;
    }

    /**
     * Returns the parent directory as a pathname, as a relative path. The parent directory of a
     * file is the directory of that file, so if the relative path refers to a file in the current
     * directory (<code>new Pathname("a")</code>), then its "parent" directory is the current
     * directory (<code>new Pathname(".")</code> ). All parents continue to be relative, i.e., of
     * the form "../../..", even if that would resolve to the root (or above) of the current file
     * system.
     *
     * <pre>
     *     new Pathname("a/b").parent();                 // == "a"
     *     new Pathname("b").parent();                   // == "."
     *     new Pathname("b").parent().parent();          // == ".."
     *     new Pathname("b").parent().parent().parent(); // == "../.."
     * </pre>
     *
     * @return the path of this pathname
     */
    public Pathname parent() {
        String separator = "/";
        String[] components = new Str(relativePath()).split(separator);
        
        String parentPath = null;
        if (components.length == 0) {
            parentPath = "..";
        }
        else if (components.length == 1) {
            if (components[0].equals(".")) {
                parentPath = "..";
            }
            else if (!components[0].equals("..") && components[0].equals(baseName())) {
                parentPath = ".";
            }
            else {
                parentPath = components[0] + separator + "..";
            }
        }
        else {
            // reduce single dots
            java.util.List<String> comps = new java.util.ArrayList<String>();
            for (String c : components) {
                if (!c.equals(".")) {
                    comps.add(c);
                }
            }
            comps.remove(comps.size() - 1);
            parentPath = Str.join(comps, separator).str();
        }
        return new Pathname(parentPath);
    }

    /**
     * Returns the path, fully expanded, i.e., resolved within the file system. If the path is
     * relative (e.g., "lib/foo.rb"), then it will be resolved against the current directory. If the
     * path is absolute ("/usr/local/bin/irb"), then it is returned.
     *
     * This currently does not resolve intermediate elements, such as "." and "..", and it may in
     * the future.
     *
     * @return the expanded path
     */
    public String expandPath() {
        return getAbsolutePath();
    }

    /**
     * Reads the file, returning a list of lines, which do not include end of line characters.
     *
     * @return the lines of the file
     */
    public java.util.List<String> readLines() {
        return IO.readLines(relativePath());
    }

    /**
     * Reads the file as an array of bytes.
     *
     * @return the bytes of the file
     */
    public byte[] readBytes() {
        return FileExt.readBytes(this);
    }

    /**
     * Writes the lines to the file.
     *
     * @param lines the lines to print
     */
    public void printLines(java.util.List<String> lines) {
        IO.printLines(relativePath(), lines);
    }

    /**
     * Returns the immediate subelements of this pathname.
     *
     * @return the children of this pathname
     */
    public java.util.List<Pathname> children() {
        java.util.List<Pathname> children = new java.util.ArrayList<Pathname>();
        for (File child : listFiles()) {
            children.add(new Pathname(child));
        }
        return children;
    }
}
