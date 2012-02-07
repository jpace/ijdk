package org.incava.ijdk.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.incava.ijdk.lang.Pair;

public class LogTimer {
    private final List<LogTimedPeriod> periods;

    public LogTimer() {
        this.periods = new ArrayList<LogTimedPeriod>();
    }

    public boolean start() {
        return start(null);
    }

    public boolean end() {
        return end(null);        
    }

    public boolean start(String msg) {
        StackTraceElement ste = getFrame();

        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        int    lineNumber = ste.getLineNumber();
        String fileName   = ste.getFileName();

        LogTimedPeriod qtp = new LogTimedPeriod(fileName, className, methodName, lineNumber, msg);
        periods.add(qtp);

        return true;
    }

    public boolean end(String msg) {
        long endTime = System.currentTimeMillis();

        StackTraceElement ste = getFrame();

        String className     = ste.getClassName();
        String methodName    = ste.getMethodName();
        int    lineNumber    = ste.getLineNumber();
        String fileName      = ste.getFileName();

        // first is index, second is score.
        Pair<Integer, Integer> bestMatch = new Pair<Integer, Integer>(-1, -1);
        int    bestMatchIdx  = -1;
        int    bestMatchness = -1;
        
        Iterator<LogTimedPeriod> pit = periods.iterator();
        for (int idx = 0; pit.hasNext(); ++idx) {
            LogTimedPeriod qtp       = pit.next();
            int           matchness = 0;
            
            matchness += qtp.getMessage() != null && msg.equals(qtp.getMessage()) ? 1 : 0;
            matchness += className.equals(qtp.getClassName())   ? 1 : 0;
            matchness += fileName.equals(qtp.getFileName())     ? 1 : 0;
            matchness += methodName.equals(qtp.getMethodName()) ? 1 : 0;

            if (matchness >= bestMatch.getSecond()) {
                bestMatch = new Pair<Integer, Integer>(idx, matchness);
            }
        }

        if (bestMatch.getFirst() >= 0) {
            LogTimedPeriod qtp    = periods.remove(bestMatch.getFirst().intValue());
            long          elapsed = endTime - qtp.getStartTime();
            StringBuffer  buf     = new StringBuffer();

            buf.append(format(elapsed));
            buf.append("; ");
                
            if (msg != null) {
                buf.append(msg);
                buf.append("; ");
            }            
            
            buf.append("from: [");
            buf.append(fileName);
            buf.append(":");
            buf.append(Integer.toString(lineNumber));
            buf.append("]");
            buf.append(" ");

            buf.append("{");
            buf.append(className);
            buf.append("#");
            buf.append(methodName);
            buf.append("}");

            Log.log(buf.toString());
        }
        else {
            System.err.println("ERROR no matching start!");
        }

        return true;
    }

    protected StackTraceElement getFrame() {
        StackTraceElement[] stack = (new Exception("")).getStackTrace();
        int                 stIdx = Log.findStackStart(stack);
        StackTraceElement   ste   = stack[stIdx];

        return ste;
    }

    public String format(long duration) {
        StringBuffer buf = new StringBuffer();
        if (duration < 10000) {
            buf.append(Long.toString(duration));
            buf.append(" ms");
        }
        else if (duration < 100000) {
            double nSecs = duration / 1000.0;
            buf.append(Double.toString(nSecs));
            buf.append(" sec");
        }
        else if (duration < 1000000) {
            double nMin = Math.floor(duration / (60 * 1000.0));
            double nSec = (duration - 60.0 * nMin) / 1000.0;
            buf.append(Double.toString(nMin));
            buf.append(":");
            buf.append(Double.toString(nSec));
        }
        else {
            // convert to HH:MM:SS, etc.
            buf.append(Long.toString(duration));
        }
        return buf.toString();
    }
}
