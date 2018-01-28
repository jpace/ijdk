package org.incava.ijdk.io;

import java.io.IOException;
import java.util.List;

public class Shell {
    public static Process execute(List<String> args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.redirectErrorStream(true);

        Process shell = pb.start();
        shell.getOutputStream().close();
        return shell;
    }
}
