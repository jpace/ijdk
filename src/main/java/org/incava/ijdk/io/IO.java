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
     * @return the lines, as a list of strings
     *
     * @see ReadOptionType
     */
    public static List<String> readLines(String fileName, EnumSet<ReadOptionType> options) {
        return FileExt.readLines(new File(fileName), options);
    }

    /**
     * Returns a list of Strings read the given file.
     *
     * @param fileName the file to read
     * @return the lines, as a list of strings
     *
     * @see ReadOptionType
     */
    public static List<String> readLines(String fileName) {
        return readLines(fileName, EnumSet.noneOf(ReadOptionType.class));
    }

    /**
     * Prints the lines (with println).
     *
     * @param fileName the file to read
     * @param lines the lines to print
     */
    public static void printLines(String fileName, List<String> lines) {
        FileExt.printLines(new File(fileName), lines);
    }
}
