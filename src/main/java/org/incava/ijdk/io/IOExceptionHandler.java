package org.incava.ijdk.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.incava.ijdk.util.Collections;

import static org.incava.ijdk.util.IUtil.*;

/**
 * Handles IO exceptions, by return null or empty arrays, or by throwing an
 * exception.
 */
public class IOExceptionHandler {
    public static List<String> handleReadException(IOException ioe, EnumSet<ReadOptionType> options) throws IORuntimeException {
        if (Collections.contains(options, ReadOptionType.WITH_EXCEPTION)) {
            throw new IORuntimeException(ioe);
        }
        return new ArrayList<String>();

    }
    public static void handleWriteException(IOException ioe, EnumSet<WriteOptionType> options) throws IORuntimeException {
        if (Collections.contains(options, WriteOptionType.WITH_EXCEPTION)) {
            throw new IORuntimeException(ioe);
        }
    }
}
