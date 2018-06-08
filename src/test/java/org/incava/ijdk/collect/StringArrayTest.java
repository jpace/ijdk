package org.incava.ijdk.collect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.hamcrest.Matchers;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.str.StringAlphanumericComparator;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class StringArrayTest extends Parameterized {
    @Test
    public void empty() {
        StringArray sl = StringArray.empty();
        assertThat(sl, Matchers.empty());
    }
    
    @Test
    public void ctorEmpty() {
        StringArray sl = new StringArray();
        assertThat(sl, Matchers.empty());
    }

    @Test
    public void ctorCollection() {
        List<String> list = Arrays.asList(new String[] { "one", "two", "three" });
        StringArray sl = new StringArray(list);
        assertThat(sl, hasSize(3));
    }

    @Test
    public void ctorVarArgsOne() {
        StringArray sl = new StringArray("one");
        assertThat(sl, hasSize(1));
        assertThat(sl, contains("one"));
    }

    @Test
    public void ctorVarArgsTwo() {
        StringArray sl = new StringArray("one", "two");
        assertThat(sl, hasSize(2));
        assertThat(sl.get(0), equalTo("one"));
        assertThat(sl.get(1), equalTo("two"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyStartsWith(boolean expected, String substr, String ... args) {
        StringArray sl = new StringArray(args);
        boolean result = sl.anyStartsWith(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));        
    }
    
    private List<Object[]> parametersForAnyStartsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true,  "o", "one"),
                          params(false, "n", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyContains(boolean expected, String substr, String ... args) {
        StringArray sl = new StringArray(args);
        boolean result = sl.anyContains(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private List<Object[]> parametersForAnyContains() {
        return paramsList(params(false, "o", new String[0]),
                          params(true,  "o", "one"),
                          params(true,  "n", "one"),
                          params(false, "z", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyEndsWith(boolean expected, String substr, String ... args) {
        StringArray sl = new StringArray(args);
        boolean result = sl.anyEndsWith(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private List<Object[]> parametersForAnyEndsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true,  "e", "one"),
                          params(false, "n", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void findFirst(String expected, Closure<Boolean, String> criteria, String ... args) {
        StringArray sl = new StringArray(args);
        String result = sl.findFirst(criteria);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "criteria", criteria)));
    }
    
    private List<Object[]> parametersForFindFirst() {
        List<Object[]> params = paramsList();

        params.add(params(null, null, new String[0]));

        Closure<Boolean, String> critOne = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "one".equals(str);
                }
            };
        params.add(params("one", critOne, "one", "two"));

        Closure<Boolean, String> critTwo = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "two".equals(str);
                }
            };
        params.add(params("two", critTwo, "one", "two"));
        
        Closure<Boolean, String> critThree = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "three".equals(str);
                }
            };
        params.add(params(null, critThree, "one", "two"));
        
        return params;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void findAll(StringArray expected, Closure<Boolean, String> criteria, String ... args) {
        StringArray sl = new StringArray(args);
        StringArray result = sl.findAll(criteria);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "criteria", criteria)));
    }
    
    private List<Object[]> parametersForFindAll() {
        List<Object[]> params = paramsList();

        params.add(params(new StringArray(), null, new String[0]));

        Closure<Boolean, String> critOne = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "one".equals(str);
                }
            };
        params.add(params(new StringArray("one"), critOne, "one", "two"));

        Closure<Boolean, String> critTwo = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "two".equals(str);
                }
            };
        params.add(params(new StringArray("two"), critTwo, "one", "two"));

        Closure<Boolean, String> critContO = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return str.contains("o");
                }
            };
        params.add(params(new StringArray("one", "two"), critContO, "one", "two", "three"));

        Closure<Boolean, String> critThree = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "three".equals(str);
                }
            };
        params.add(params(new StringArray(), critThree, "one", "two"));
        
        return params;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyEqualsIgnoreCase(boolean expected, String substr, String ... args) {
        StringArray sl = new StringArray(args);
        boolean result = sl.anyEqualsIgnoreCase(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private List<Object[]> parametersForAnyEqualsIgnoreCase() {
        return paramsList(params(false, "o",   new String[0]),
                          params(true,  "one", "one"),
                          params(true,  "one", "one", "two"),
                          params(true,  "one", "One"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toLines(StringArray expected, StringArray sl) {
        StringArray result = sl.toLines();
        assertThat(result, withContext(equalTo(expected), message("sl", sl)));
    }
    
    private List<Object[]> parametersForToLines() {
        return paramsList(params(StringArray.empty(),          StringArray.empty()),
                          params(StringArray.of((String)null), StringArray.of((String)null)),
                          params(StringArray.of("a\n"),        StringArray.of("a")),
                          params(StringArray.of("a\n", "b\n"), StringArray.of("a", "b")),
                          params(StringArray.of("a\n"),        StringArray.of("a\n")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sorted(StringArray expected, StringArray ary) {
        StringArray result = ary.sorted();
        assertThat(result, equalTo(expected));
        assertThat(result, not(equalTo(ary)));
    }
    
    private List<Object[]> parametersForSorted() {
        return paramsList(params(StringArray.of("a", "b", "c"), StringArray.of("b", "a", "c")),
                          params(StringArray.of("a", "b", "c"), StringArray.of("b", "c", "a")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sortedComparator(StringArray expected, StringArray ary, Comparator<String> comp) {
        StringArray result = ary.sorted(comp);
        assertThat(result, equalTo(expected));
        assertThat(result, not(equalTo(ary)));
    }
    
    private List<Object[]> parametersForSortedComparator() {
        return paramsList(params(StringArray.of("a9", "a10", "b"), StringArray.of("b", "a10", "a9"), new StringAlphanumericComparator()), 
                          params(StringArray.of("b3", "b4",  "c"), StringArray.of("c", "b4",  "b3"), new StringAlphanumericComparator()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void collect(StringArray expected, StringArray ary, String repl) {
        StringArray result = ary.collect(repl);
        StringArray original = new StringArray(ary);
        assertThat(result, equalTo(expected));
        assertThat(ary, equalTo(original));
    }
    
    private List<Object[]> parametersForCollect() {
        return paramsList(params(StringArray.of("x-a", "x-b", "x-c"), StringArray.of("a", "b", "c"), "x-%s"),
                          params(StringArray.of("a", "b", "c"), StringArray.of("a", "b", "c"), "%s"));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(StringArray expected, StringArray ary) {
        StringArray result = ary.unique();
        StringArray original = new StringArray(ary);
        assertThat(result, equalTo(expected));
        assertThat(ary, equalTo(original));
    }
    
    private List<Object[]> parametersForUnique() {
        return paramsList(params(StringArray.of("b", "a", "c"), StringArray.of("b", "a", "c")),
                          params(StringArray.of("b", "c", "a"), StringArray.of("b", "c", "a")));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void fromFile(StringArray expected, File file) {
        if (expected == null) {
            try {
                StringArray.from(file);
                Assert.fail("exception expected");
            }
            catch (Exception ex) {
                // good
            }
        }
        else {
            StringArray result = StringArray.from(file);
            assertThat(result, equalTo(expected));
        }
    }
    
    private List<Object[]> parametersForFromFile() {
        Path bac = Paths.get("/tmp", "bac.txt");
        Path xyz = Paths.get("/tmp", "xyz.txt");

        try {
            Files.write(bac, StringArray.of("b", "a", "c"));
            Files.write(xyz, StringArray.of("x", "y", "z"));
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        
        return paramsList(params(StringArray.of("b", "a", "c"), new File("/tmp/bac.txt")),
                          params(StringArray.of("x", "y", "z"), new File("/tmp/xyz.txt")),
                          params(null, new File("/tmp/doesnotexists.nil")));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void fromInputStream(StringArray expected, InputStream inputStream) {
        if (expected == null) {
            try {
                StringArray.from(inputStream);
                Assert.fail("exception expected");
            }
            catch (Exception ex) {
                // good
            }
        }
        else {
            StringArray result = StringArray.from(inputStream);
            assertThat(result, equalTo(expected));
        }
    }
    
    private List<Object[]> parametersForFromInputStream() {
        File def = new File("/tmp/def.txt");
        File pqr = new File("/tmp/pqr.txt");

        try {
            Files.write(def.toPath(), StringArray.of("d", "e", "f"));
            Files.write(pqr.toPath(), StringArray.of("p", "q", "r"));

            return paramsList(params(StringArray.of("d", "e", "f"), new FileInputStream(def)),
                              params(StringArray.of("p", "q", "r"), new FileInputStream(pqr)));            
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }        
    }    
}
