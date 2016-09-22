package org.incava.ijdk.io;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.EnumSet;

public class InputStreamExt {
    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     *
     * @param stream the stream to read lines from
     * @return the lines, as a list of strings
     */
    public static List<String> readLines(InputStream stream) throws IORuntimeException {
        return ReaderExt.readLines(new InputStreamReader(stream), null);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     *
     * @param stream the stream to read lines from
     * @param options whether to read whitespace lines, to include eolns, and/or to throw exceptions
     * @return the lines, as a list of strings
     */
    public static List<String> readLines(InputStream stream, EnumSet<ReadOptionType> options) throws IORuntimeException {
        return ReaderExt.readLines(new InputStreamReader(stream), options);
    }
}
