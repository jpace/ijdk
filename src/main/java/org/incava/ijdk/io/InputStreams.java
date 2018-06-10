package org.incava.ijdk.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.List;

public class InputStreams {
    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     *
     * @param stream the stream to read lines from
     * @return the lines, as a list of strings
     */
    public static List<String> readLines(InputStream stream) throws IORuntimeException {
        return Readers.readLines(new InputStreamReader(stream), null);
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
        return Readers.readLines(new InputStreamReader(stream), options);
    }

    /**
     * Reads lines from an input stream into a list of strings.
     */
    public static void readLines(List<String> list, InputStream stream) {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
}
