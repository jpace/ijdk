package org.incava.ijdk.io;

import java.io.File;
import java.util.EnumSet;
import java.util.List;

public class FileExt {
    /**
     * Reads the file into a string array, returning an empty array if there is
     * an error.
     *
     * @param file the file to read from
     * @return the lines of the file
     * @see #readLines(File, EnumSet)
     */
    public static List<String> readLines(File file) {
        return Files.readLines(file);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error, unless <code>options</code>
     * contains <code>ReadOptionType.WITH_EXCEPTION</code>.
     *
     * @param file the file to read from
     * @param options options for reading
     * @return the lines of the file
     * @see ReaderExt#readLines
     */
    public static List<String> readLines(File file, EnumSet<ReadOptionType> options) throws IORuntimeException {
        return Files.readLines(file, options);
    }

    /**
     * Prints the file as lines, using writer.println.
     *
     * @param file the file to write to
     * @param lines the lines to write
     * @see #printLines(File, List, EnumSet)
     */
    public static void printLines(File file, List<String> lines) {
        Files.printLines(file, lines);
    }
    
    /**
     * Prints the file as lines, using writer.println.
     *
     * @param file the file to write to
     * @param lines the lines to write
     * @param options the options
     * @exception IORuntimeException Thrown if <code>options</code> contains
     * <code>WriteOptionType.WITH_EXCEPTION</code>.
     */
    public static void printLines(File file, List<String> lines, EnumSet<WriteOptionType> options) throws IORuntimeException {
        Files.printLines(file, lines, options);
    }

    /**
     * Resolves the file name, converting ~ to the home directory for the current user.
     *
     * @param fname the name of the file
     * @return the resolved file name
     */
    public static String resolveFileName(String fname) {
        return Files.resolveFileName(fname);
    }

    /**
     * Returns the contents of the file, as a byte array.
     *
     * @param file the file to read from
     * @return the bytes of the file
     */
    public static byte[] readBytes(File file) throws IORuntimeException {
        return Files.readBytes(file);
    }
}
