package org.incava.ijdk.io;

import java.io.PrintWriter;
import java.util.List;

/**
 * Wrapper for PrintWriter.
 *
 * @deprecated use PrintWriters instead.
 */
@Deprecated
public class PrintWriterExt {
    public static void printLines(PrintWriter pw, List<String> lines) {
        PrintWriters.printLines(pw, lines);
    }
}
