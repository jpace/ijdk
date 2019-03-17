package org.incava.ijdk.io;

import java.io.IOException;

/**
 * Wraps IO exceptions with RuntimeExceptions, so they don't have to be declares
 * in throws clauses.
 */
public class IORuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public IORuntimeException(IOException ioe) {
        super(ioe);
    }

    public IORuntimeException(String message, IOException ioe) {
        super(message, ioe);
    }
}
