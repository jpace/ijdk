package org.incava.ijdk.io;

import java.io.File;
import java.util.List;
import java.util.EnumSet;
import static org.incava.ijdk.util.IUtil.*;

public class IO {
    /**
     * The end-of-line character/sequence for this OS.
     */
    public final static String EOLN = System.getProperty("line.separator");

    /**
     * Returns a list of Strings for the given file.
     *
     * @param fileName the file to read.
     * @param options denotes special options when reading.
     *
     * @see ReadOptionType
     */
    public static List<String> readLines(String fileName, EnumSet<ReadOptionType> options) {
        return FileExt.readLines(new File(fileName), options);
    }

    /**
     * Returns a list of Strings read the given file.
     *
     * @param fileName the file to read.
     * @param options denotes special options when reading.
     *
     * @see ReadOptionType
     */
    public static List<String> readLines(String fileName) {
        return readLines(fileName, EnumSet.noneOf(ReadOptionType.class));
    }
}
