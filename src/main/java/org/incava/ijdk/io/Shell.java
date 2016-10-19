package org.incava.ijdk.io;

import java.io.*;
import java.util.List;
import org.incava.ijdk.lang.StringExt;

public class Shell {
    public static Process execute(List<String> args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.redirectErrorStream(true);

        Process shell = pb.start();
        shell.getOutputStream().close();
        return shell;
    }
}
