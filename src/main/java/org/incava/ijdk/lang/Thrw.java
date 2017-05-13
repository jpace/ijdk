package org.incava.ijdk.lang;

import java.io.StringWriter;
import java.io.PrintWriter;

public class Thrw {
    private final Throwable thr;
    
    public Thrw(Throwable thr) {
        this.thr = thr;
    }

    /**
     * Returns the stack trace as a string. Returns null if the wrapped throwable is null.
     */
    public String getStackTraceString() {
        if (thr == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        thr.printStackTrace(pw);
        return sw.toString();
    }
}
