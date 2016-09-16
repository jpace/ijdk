package ijdk.lang;

import java.io.*;
import java.util.*;

public class Throwablee {
    private final Throwable thr;
    
    public Throwablee(Throwable thr) {
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
