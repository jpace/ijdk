package org.incava.ijdk.io;

import java.io.PrintWriter;
import java.util.List;

/**
 * Wrapper for PrintWriter.
 */
public class PrintWriters {
    /**
     * Prints the lines (with println), and flushes and closes the writer.
     *
     * @param pw the writer into which to print the lines
     * @param lines the lines to print
     */
    public static void printLines(PrintWriter pw, List<String> lines) {
        for (String line : lines) {
            pw.println(line);
        }
        pw.flush();
        pw.close();
    }
}
