package org.incava.ijdk.lang;

import java.io.File;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.assertSame;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class PathnameTest extends Parameterized {
    // shortcuts used within this test

    public Pathname pn(String fileName) {
        return new Pathname(fileName);
    }

    public Pathname pn(File file) {
        return new Pathname(file);
    }

    @Test
    public void testPnName() {
        // make sure that .equals works:
        String fileName = "f.x";
        Pathname pn = pn(fileName);
        assertEqual(true, new Pathname(fileName).equals(pn), message("fileName", fileName, "pn", pn));
    }

    @Test
    public void testPnFile() {
        // make sure that .equals works:        
        File file = new File("f.x");
        Pathname pn = pn(file);
        assertEqual(true, new Pathname(file).equals(pn), message("file", file, "pn", pn));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void equalsTest(boolean expected, Pathname x, Pathname y) {
        boolean result = x.equals(y);
        assertEqual(expected, result, message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForEqualsTest() {
        return paramsList(params(true, pn("f.x"), pn("f.x")),
                          params(true, pn(new File("f.x")), pn(new File("f.x"))),
                          params(false, pn("f.x"), pn("g.x")),
                          params(false, pn(new File("f.x")), pn(new File("g.x"))));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ctorString(Pathname expected, String fileName) {
        Pathname result = new Pathname(fileName);
        assertEqual(expected, result, message("fileName", fileName));
    }
    
    private List<Object[]> parametersForCtorString() {
        return paramsList(params(new Pathname("f.x"), "f.x"),
                          params(new Pathname("d/f.x"), "d/f.x"),
                          // empty args is current directory:
                          params(new Pathname(), System.getProperty("user.dir")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void ctorFile(Pathname expected, File file) {
        Pathname result = new Pathname(file);
        assertEqual(expected, result, message("file", file));
    }
    
    private List<Object[]> parametersForCtorFile() {
        return paramsList(params(new Pathname("f.x"), new File("f.x")),
                          params(new Pathname("d/f.x"), new File("d/f.x")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void baseName(String expected, Pathname pn) {
        String result = pn.baseName();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForBaseName() {
        return paramsList(params("f.x", pn("f.x")),
                          params("f.x", pn("d/f.x")),
                          params(".",   pn(".")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void rootName(String expected, Pathname pn) {
        String result = pn.rootName();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForRootName() {
        return paramsList(params("f",   pn("f.x")),
                          params("f",   pn("d/f.x")),
                          params("f",   pn("d/f")),
                          params("f.x", pn("d/f.x.y")),
                          params("f",   pn("d/f.")),
                          params(".",   pn(".")));
    }
    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void relativePath(String expected, Pathname pn) {
        String result = pn.relativePath();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForRelativePath() {
        return paramsList(params("f.x",   pn("f.x")),
                          params("d/f.x", pn("d/f.x")));
    }

    @Test
    public void file() {
        Pathname pn = pn("f.x");
        assertSame(pn, pn.file());
    }

    // extension

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void extension(String expected, Pathname pn) {
        String result = pn.extension();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForExtension() {
        return paramsList(params("x",  pn("f.x")),
                          params("y",  pn("f.x.y")),
                          params("",   pn("f.")),
                          params(null, pn("f")));
    }

    // parent

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void parent(Pathname expected, Pathname pn) {
        Pathname result = pn.parent();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForParent() {
        return paramsList(params(pn("."), pn("a")),
                          params(pn(".."), pn(".")),
                          params(pn("../.."), pn("..")),
                          params(pn("d"), pn("d/f")),
                          params(pn("d"), pn("d/./f")),
                          params(pn("d"), pn("d/././f")),
                          params(pn("d/.."), pn("d/../f")),
                          params(pn(".."), pn("")));
    }

    // expand path

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void expandPath(String expected, Pathname pn) {
        String result = pn.expandPath();
        assertEqual(expected, result, message("pn", pn));
    }
    
    private List<Object[]> parametersForExpandPath() {
        String userDir = System.getProperty("user.dir");
        String separator = "/";
        
        return paramsList(params(userDir + "/" + "a", pn("a")),
                          params(userDir + "/" + "a/b", pn("a/b")),
                          // this is what Ruby pathname does:
                          params(userDir + "/" + "a/./b", pn("a/./b")),
                          params(userDir + "/" + "a/../b", pn("a/../b")),
                          params("/a", pn("/a")),
                          params("/a/../b/c", pn("/a/../b/c")));
    }
}
