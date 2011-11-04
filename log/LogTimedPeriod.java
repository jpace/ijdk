package org.incava.ijdk.log;


public class LogTimedPeriod {
    private final String fileName;

    private final String className;

    private final String methodName;
    
    private final int lineNumber;

    private final String message;

    private final long start;

    public LogTimedPeriod(String fileName, String className, String methodName, int lineNumber, String message) {
        this.fileName   = fileName;
        this.className  = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.message    = message;
        this.start      = System.currentTimeMillis();
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getClassName() {
        return this.className;
    }
    
    public String getMethodName() {
        return this.methodName;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getMessage() {
        return this.message;
    }

    public long getStartTime() {
        return this.start;
    }
}
