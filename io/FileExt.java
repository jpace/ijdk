package org.incava.ijdk.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.EnumSet;
import static org.incava.ijdk.util.IUtil.*;

public class FileExt {
    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static List<String> readLines(File file, EnumSet<ReadOptionType> options) throws IORuntimeException {
        try {
            return ReaderExt.readLines(new FileReader(file), options);
        }
        catch (FileNotFoundException fnfe) {
            return IOExceptionHandler.handle(fnfe, options);
        }
    }

    public static List<String> readLines(File file) {
        return readLines(file, null);
    }

    /**
     * Resolves the file name, converting ~ to the home directory for the
     * current user.
     */
    public static String resolveFileName(String fname) {        
        return fname.replace("~", System.getProperty("user.home"));
    }
}
