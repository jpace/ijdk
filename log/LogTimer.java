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

    private Integer findBestMatch(StackTraceElement ste, String msg) {
        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        String fileName   = ste.getFileName();

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

        return bestMatch.getFirst();
    }

    public boolean end(String msg) {
        long endTime = System.currentTimeMillis();

        StackTraceElement ste = getFrame();

        // index of periods:
        Integer bestMatch = findBestMatch(ste, msg);

        if (bestMatch >= 0) {
            LogTimedPeriod qtp     = periods.remove(bestMatch.intValue());
            long           elapsed = endTime - qtp.getStartTime();
            String          str    = getMessage(ste, msg, elapsed);

            Log.log(str);
        }
        else {
            System.err.println("ERROR no matching start!");
        }

        return true;
    }

    private String getMessage(StackTraceElement ste, String msg, long elapsed) {
        String className  = ste.getClassName();
        String methodName = ste.getMethodName();
        int    lineNumber = ste.getLineNumber();
        String fileName   = ste.getFileName();

        StringBuilder  sb     = new StringBuilder();

        sb.append(format(elapsed));
        sb.append("; ");
                
        if (msg != null) {
            sb.append(msg);
            sb.append("; ");
        }            
            
        sb.append("from: [");
        sb.append(fileName);
        sb.append(":");
        sb.append(Integer.toString(lineNumber));
        sb.append("]");
        sb.append(" ");

        sb.append("{");
        sb.append(className);
        sb.append("#");
        sb.append(methodName);
        sb.append("}");

        return sb.toString();
    }

    protected StackTraceElement getFrame() {
        StackTraceElement[] stack = (new Exception("")).getStackTrace();
        int                 stIdx = Log.findStackStart(stack);
        StackTraceElement   ste   = stack[stIdx];

        return ste;
    }

    public String format(long duration) {
        StringBuilder sb = new StringBuilder();
        if (duration < 10000) {
            sb.append(Long.toString(duration));
            sb.append(" ms");
        }
        else if (duration < 100000) {
            double nSecs = duration / 1000.0;
            sb.append(Double.toString(nSecs));
            sb.append(" sec");
        }
        else if (duration < 1000000) {
            double nMin = Math.floor(duration / (60 * 1000.0));
            double nSec = (duration - 60.0 * nMin) / 1000.0;
            sb.append(Double.toString(nMin));
            sb.append(":");
            sb.append(Double.toString(nSec));
        }
        else {
            // convert to HH:MM:SS, etc.
            sb.append(Long.toString(duration));
        }
        return sb.toString();
    }
}
