package org.incava.ijdk.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.EnumSet;
import static org.incava.ijdk.util.IUtil.*;

public class FileExt {
    public enum ReadOptionType { NONEMPTY, WITH_EXCEPTION };
    
    /**
     * The end-of-line character/sequence for this OS.
     */
    public final static String EOLN = System.getProperty("line.separator");

    /**
     * Reads the file into a single string, which is null on error. The returned
     * string will contain end-of-line characters. The <code>arg</code> argument
     * is just so we can overload based on return type.
     */
    public static String readFile(String fileName, String arg) {
        return readFile(new File(fileName), arg);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). The array is null on error. The <code>arg</code> argument is
     * just so we can overload based on return type.
     */
    public static String[] readFile(String fileName, String[] arg) {
        return readFile(new File(fileName), arg);
    }

    /**
     * Reads the file into a single string, which is null on error. The
     * <code>arg</code> argument is just so we can overload based on return
     * type.
     */
    public static String readFile(File file, String arg) {
        String[] contents = readFile(file, new String[] {});
        if (contents == null) {
            return null;
        }
        else {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; contents != null && i < contents.length; ++i) {
                sb.append(contents[i]).append(EOLN);
            }
            
            return sb.toString();
        }
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). The <code>arg</code> argument is just so we can overload
     * based on return type.
     */
    public static String[] readFile(File file, String[] arg) {
        return readLines(file);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static String[] readLines(File file, EnumSet<ReadOptionType> options) {
        try {
            return readLines(new FileReader(file), options);
        }
        catch (FileNotFoundException fnfe) {
            return new String[0];
        }
    }

    public static String[] readLines(File file) {
        return readLines(file, null);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static String[] readLines(InputStream stream) {
        return readLines(new InputStreamReader(stream), null);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static String[] readLines(InputStream stream, EnumSet<ReadOptionType> options) {
        return readLines(new InputStreamReader(stream), options);
    }

    /**
     * Reads into a string array, without end-of-line characters (sequences).
     * Returns an empty array on error.
     */
    public static String[] readLines(Reader reader) throws RuntimeException {
        return readLines(reader, null);
    }

    /**
     * Reads into a string array, without end-of-line characters (sequences).
     * Returns an empty array on error.
     */
    public static String[] readLines(Reader reader, EnumSet<ReadOptionType> options) throws RuntimeException {
        try {
            BufferedReader br    = new BufferedReader(reader);
            List<String>   lines = new ArrayList<String>();
            boolean        checkEmpty = options != null && options.contains(ReadOptionType.NONEMPTY);

            String in;
            while ((in = br.readLine()) != null) {
                if (!checkEmpty || !isEmpty(in)) {
                    lines.add(in);
                }
            }

            return lines.toArray(new String[lines.size()]);
        }
        catch (Exception e) {
            if (hasReadOption(options, ReadOptionType.WITH_EXCEPTION)) {
                // $$$ todo: add UncheckedException
                throw new RuntimeException(e);
            }
            return new String[0];
        }
    }

    public static String[] readLines(String fName) {
        return readLines(fName, null);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static String[] readLines(String fName, EnumSet<ReadOptionType> options) {
        return readLines(new File(fName), options);
    }

    /**
     * Reads the file into a string array, optionally with end-of-line
     * characters (sequences).
     */
    public static String read(Reader fr, boolean eoln) {
        StringBuilder sb = new StringBuilder();
        
        try {
            BufferedReader br = new BufferedReader(fr);

            String in;
            while ((in = br.readLine()) != null) {
                sb.append(in);
                if (eoln) {
                    sb.append(EOLN);
                }
            }

            return sb.toString();
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Resolves the file name, converting ~ to /home/user.
     */
    public static String resolveFileName(String fname) {
        int tildePos = fname.indexOf('~');
        if (tildePos >= 0) {
            return fname.substring(0, tildePos) + System.getProperty("user.home") + fname.substring(tildePos + 1);
        }
        return fname;
    }

    public static boolean hasReadOption(EnumSet<ReadOptionType> options, ReadOptionType opt) {
        return options != null && options.contains(opt);
    }
}
