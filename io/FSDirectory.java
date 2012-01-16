package org.incava.ijdk.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A directory within a file system. Wraps and extends File (aka Directory). Can
 * either suppress exceptions, or throw the unchecked IORuntimeException instead
 * of IOException.
 */
public class FSDirectory {
    private final File directory;
    
    public FSDirectory(String name) {
        this.directory = new File(name);
    }

    public FSDirectory(File dir) {
        this.directory = dir;
    }

    public File getDirectory() {
        return this.directory;
    }

    /**
     * Returns a list of files, with directories processed recursively,
     * collecting files with the suffix of <code>suffix</code> (or all files if
     * <code>suffix</code> is null.
     *
     * @see 
     */
    public List<String> find(final String suffix) {
        List<String> matches = new ArrayList<String>();
        
        if (this.directory.isDirectory()) {
            List<File> contents = list();

            for (File fd : contents) {
                if (fd.isDirectory()) {
                    FSDirectory fsd = new FSDirectory(fd);
                    // matches.addAll(
                }
                else if (fd.isFile()) {
                    if (fd.getName().endsWith(suffix)) {
                        matches.add(getCanonicalPath(fd));
                    }
                }
            }
        }

        return matches;
    }

    private static String getCanonicalPath(File fd) {
        try {
            return fd.getCanonicalPath();
        }
        catch (IOException ioe) {
            return null;
        }
    }

    /**
     * Returns a list of files and directories in the given directory.
     */
    public List<File> list() {
        return Arrays.asList(this.directory.listFiles());
    }
}
