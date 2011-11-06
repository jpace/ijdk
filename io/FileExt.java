package org.incava.ijdk.io;

import java.io.*;
import java.util.*;


public class FileExt {
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
    public static String[] readLines(File file) {
        try {
            BufferedReader br    = new BufferedReader(new FileReader(file));
            List<String>   lines = new ArrayList<String>();

            String in;
            while ((in = br.readLine()) != null) {
                lines.add(in);
            }

            return lines.toArray(new String[lines.size()]);
        }
        catch (Exception e) {
            return new String[0];
        }
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error.
     */
    public static String[] readLines(String fName) {
        return readLines(new File(fName));
    }

    /**
     * Reads the file into a string array, optionally with end-of-line
     * characters (sequences).
     */
    public static String read(FileReader fr, boolean eoln) {
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
}
