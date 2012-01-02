package org.incava.ijdk.io;

import java.io.PrintWriter;
import java.util.List;

/**
 * Wrapper for PrintWriter.
 */
public class PrintWriterExt {
    /**
     * Prints the lines (with println), and flushes and closes the writer.
     */
    public static void printLines(PrintWriter pw, List<String> lines) {
        for (String line : lines) {
            pw.println(line);
        }
        pw.flush();
        pw.close();
    }
}
