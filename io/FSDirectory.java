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
public class FSDirectory extends File {
    public static final long serialVersionUID = 1L;

    public FSDirectory(String name) {
        super(name);
    }

    /**
     * Returns a list of files, with directories processed recursively,
     * collecting files with the suffix of <code>suffix</code> (or all files if
     * <code>suffix</code> is null.
     */
    public List<String> find(final String suffix) {
        List<String> matches = new ArrayList<String>();
        
        List<File> contents = listAll();
        
        for (File fd : contents) {
            if (fd.isDirectory()) {
                FSDirectory fsd = new FSDirectory(fd.getAbsolutePath());
                matches.addAll(fsd.find(suffix));
            }
            else if (fd.isFile() && fd.getName().endsWith(suffix)) {
                matches.add(getCanonicalPath(fd));
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
    public List<File> listAll() {
        return Arrays.asList(listFiles());
    }
}
