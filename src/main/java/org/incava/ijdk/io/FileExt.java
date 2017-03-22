package org.incava.ijdk.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.List;

public class FileExt {
    /**
     * Reads the file into a string array, returning an empty array if there is
     * an error.
     *
     * @see #readLines(File, EnumSet)
     */
    public static List<String> readLines(File file) {
        return readLines(file, null);
    }

    /**
     * Reads the file into a string array, without end-of-line characters
     * (sequences). Returns empty array on error, unless <code>options</code>
     * contains <code>ReadOptionType.WITH_EXCEPTION</code>.
     *
     * @see ReaderExt#readLines
     */
    public static List<String> readLines(File file, EnumSet<ReadOptionType> options) throws IORuntimeException {
        try {
            return ReaderExt.readLines(new FileReader(file), options);
        }
        catch (FileNotFoundException fnfe) {
            return IOExceptionHandler.handleReadException(fnfe, options);
        }
    }

    /**
     * Prints the file as lines, using writer.println.
     *
     * @see #printLines(File, List, EnumSet)
     */
    public static void printLines(File file, List<String> lines) {
        printLines(file, lines, null);
    }
    
    /**
     * Prints the file as lines, using writer.println.
     *
     * @exception IORuntimeException Thrown if <code>options</code> contains
     * <code>WriteOptionType.WITH_EXCEPTION</code>.
     */
    public static void printLines(File file, List<String> lines, EnumSet<WriteOptionType> options) throws IORuntimeException {
        try {
            PrintWriterExt.printLines(new PrintWriter(file), lines);
        }
        catch (FileNotFoundException fnfe) {
            IOExceptionHandler.handleWriteException(fnfe, options);
        }
    }

    /**
     * Resolves the file name, converting ~ to the home directory for the
     * current user.
     */
    public static String resolveFileName(String fname) {        
        return fname.replace("~", System.getProperty("user.home"));
    }

    /**
     * Returns the contents of the file, as a byte array.
     */
    public static byte[] readBytes(File file) throws IORuntimeException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int nBytes = 0;
            while ((nBytes = fis.read(buf)) != -1) {
                baos.write(buf, 0, nBytes);
            }
            fis.close();
            return baos.toByteArray();
        }
        catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
    }
}
