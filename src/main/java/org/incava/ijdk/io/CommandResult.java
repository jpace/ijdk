package org.incava.ijdk.io;

import java.util.List;

public class CommandResult {
    private final int exitStatus;
    private final List<String> lines;
    
    public CommandResult(int exitStatus, List<String> lines) {
        this.exitStatus = exitStatus;
        this.lines = lines;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public List<String> getLines() {
        return lines;
    }
}
