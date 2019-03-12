package org.incava.ijdk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.incava.ijdk.util.Collections;

import static org.incava.ijdk.lang.ICore.isEmpty;

/**
 * Wraps java.io.Reader.
 */
public class ReaderExt {    
    /**
     * Reads lines from the given reader, applying options.
     *
     * @param rdr the reader from which to read lines
     * @param options how to handle whitespace only lines, eolns, and exceptions
     * @return the lines, as a list
     * @see ReadOptionType
     */
    public static List<String> readLines(Reader rdr, EnumSet<ReadOptionType> options) {
        try {
            List<String>   lines = new ArrayList<String>();
            BufferedReader br    = new BufferedReader(rdr);            
            boolean        checkEmpty = Collections.contains(options, ReadOptionType.NONEMPTY);

            String line = br.readLine();
            while (line != null) {
                if (!checkEmpty || !isEmpty(line)) {
                    lines.add(line);
                }
                line = br.readLine();
            }
            
            return lines;
        }
        catch (IOException ioe) {
            return IOExceptionHandler.handleReadException(ioe, options);
        }
    }

    /**
     * Returns the file as a singl string, optionally with end-of-line characters (sequences).
     *
     * @param rdr the reader from which to read lines
     * @param options how to handle whitespace only lines, eolns, and exceptions
     * @return the lines, as a single string
     * @see ReadOptionType
     */
    public static String readAsString(Reader rdr, EnumSet<ReadOptionType> options) {
        StringBuilder sb = new StringBuilder();
        List<String> lines = readLines(rdr, EnumSet.noneOf(ReadOptionType.class));
        boolean addEoln = Collections.contains(options, ReadOptionType.ADD_EOLNS);
        for (String line : lines) {
            sb.append(line);
            if (addEoln) {
                sb.append(IO.EOLN);
            }
        }

        return sb.toString();
    }
}
