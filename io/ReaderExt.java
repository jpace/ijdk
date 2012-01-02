package org.incava.ijdk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.incava.ijdk.util.CollectionExt;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Wraps java.io.Reader.
 */
public class ReaderExt {    
    /**
     * Reads lines from the given reader, applying options.
     *
     * @see ReadOptionType
     */
    public static List<String> readLines(Reader rdr, EnumSet<ReadOptionType> options) {
        try {
            List<String>   lines = new ArrayList<String>();
            BufferedReader br    = new BufferedReader(rdr);            
            boolean        checkEmpty = CollectionExt.contains(options, ReadOptionType.NONEMPTY);

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
            return IOExceptionHandler.handle(ioe, options);
        }
    }

    /**
     * Returns the file as a string, optionally with end-of-line characters
     * (sequences).
     */
    public static String readAsString(Reader fr, EnumSet<ReadOptionType> options) {
        StringBuilder sb = new StringBuilder();
        List<String> lines = readLines(fr, EnumSet.noneOf(ReadOptionType.class));
        boolean addEoln = CollectionExt.contains(options, ReadOptionType.ADD_EOLNS);
        for (String line : lines) {
            sb.append(line);
            if (addEoln) {
                sb.append(IO.EOLN);
            }
        }

        return sb.toString();
    }
}
