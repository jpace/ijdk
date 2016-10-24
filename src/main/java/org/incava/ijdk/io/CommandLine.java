package org.incava.ijdk.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.incava.ijdk.lang.StringExt;

public class CommandLine {
    private final List<String> args;
    
    public CommandLine(List<String> args) {
        this.args = args;
    }

    public CommandResult execute(boolean writeLines) {
        List<String> lines = new ArrayList<String>();
        try {
            Process shell = Shell.execute(args);
            InputStream is = shell.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                else if (writeLines) {
                    System.out.println(line);
                }
                lines.add(line);
            }
        
            int shellExitStatus = shell.waitFor();
            is.close();
            return new CommandResult(shellExitStatus, lines);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
