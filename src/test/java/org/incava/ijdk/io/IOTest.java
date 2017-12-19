package org.incava.ijdk.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;

public class IOTest extends Parameterized {
    private final String tmpdir = System.getProperty("java.io.tmpdir");

    public File writeFile(String fname, String ... lines) {
        File file = new File(fname);        
        try {
            PrintWriter pw = new PrintWriter(fname);
            for (String line : lines) {
                pw.println(line);
            }
            pw.flush();
            pw.close();
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return file;
    }

    @Test
    public void testReadLines() {
        String[] lines = new String[] { "first line", "line #2", "and the third line" };

        String tmpFname = tmpdir + "/" + "jkjkljkljkljkl.txt";
        File tmpFile = writeFile(tmpFname, lines);

        List<String> readLines = IO.readLines(tmpFname);
        assertEqual(Arrays.asList(lines), readLines);

        tmpFile.delete();
    }

    @Test
    public void testReadLinesNonEmpty() {
        String[] lines = new String[] { 
            "",
            "first line",
            "",
            "line #2",
            "",
            "    ",
            "and the third line"
        };

        String tmpFname = tmpdir + "/" + "zdvmdfqeraz.txt";
        File tmpFile = writeFile(tmpFname, lines);

        List<String> expLines = new ArrayList<String>();
        expLines.add(lines[1]);
        expLines.add(lines[3]);
        expLines.add(lines[5]);
        expLines.add(lines[6]);

        List<String> readLines = IO.readLines(tmpFname, EnumSet.of(ReadOptionType.NONEMPTY));
        assertEqual(expLines, readLines);

        tmpFile.delete();        
    }

    @Test
    public void testNoExceptionForMissingFile() {
        try {
            IO.readLines("/tmp/zawsdfsadfzv.jkljkl");
        }
        catch (Exception e) {
            Assert.fail("should be no exception");
        }
    }

    @Test
    public void testExceptionForMissingFile() {
        try {
            IO.readLines("/tmp/zawsdfsadfzv.jkljkl", EnumSet.of(ReadOptionType.WITH_EXCEPTION));
            Assert.fail("should be an exception");
        }
        catch (Exception e) {
        }
    }
}
