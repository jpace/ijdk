package org.incava.ijdk.lang;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class PathnameGlobTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void method(String expected, String glob) {
        String result = PathnameGlob.toPattern(glob);
        assertEqual(expected, result, message("glob", glob));
    }
    
    private List<Object[]> parametersForMethod() {
        return paramsList(params("", ""),
                          params("ab", "ab"),
                          params("a/b", "a/b"),
                          params("a[^/]*", "a*"),
                          params("a/.*", "a/**"),
                          params("a/.*/b", "a/**/b"),
                          // a**b == a*b, per Ruby:
                          params("a[^/]*[^/]*/b", "a**/b"),
                          params("a\\.b", "a.b"),
                          params("a\\.b\\.c", "a.b.c"));
    }
 }
