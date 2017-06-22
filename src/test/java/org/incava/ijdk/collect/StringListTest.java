package org.incava.ijdk.collect;

import java.util.Arrays;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.hamcrest.Matchers;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Closure;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class StringListTest extends Parameterized {
    @Test
    public void empty() {
        StringList sl = StringList.empty();
        assertThat(sl, Matchers.empty());
    }
    
    @Test
    public void ctorEmpty() {
        StringList sl = new StringList();
        assertThat(sl, Matchers.empty());
    }

    @Test
    public void ctorCollection() {
        java.util.List<String> list = Arrays.asList(new String[] { "one", "two", "three" });
        StringList sl = new StringList(list);
        assertThat(sl, hasSize(3));
    }

    @Test
    public void ctorVarArgsOne() {
        StringList sl = new StringList("one");
        assertThat(sl, hasSize(1));
        assertThat(sl, contains("one"));
    }

    @Test
    public void ctorVarArgsTwo() {
        StringList sl = new StringList("one", "two");
        assertThat(sl, hasSize(2));
        assertThat(sl.get(0), equalTo("one"));
        assertThat(sl.get(1), equalTo("two"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyStartsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        boolean result = sl.anyStartsWith(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
        
    }
    
    private java.util.List<Object[]> parametersForAnyStartsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "o", "one"),
                          params(false, "n", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyContains(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        boolean result = sl.anyContains(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private java.util.List<Object[]> parametersForAnyContains() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "o", "one"),
                          params(true, "n", "one"),
                          params(false, "z", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyEndsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        boolean result = sl.anyEndsWith(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private java.util.List<Object[]> parametersForAnyEndsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "e", "one"),
                          params(false, "n", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void findFirst(String expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        String result = sl.findFirst(criteria);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "criteria", criteria)));
    }
    
    private java.util.List<Object[]> parametersForFindFirst() {
        java.util.List<Object[]> params = paramsList();

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
    public void findAll(StringList expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        StringList result = sl.findAll(criteria);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "criteria", criteria)));
    }
    
    private java.util.List<Object[]> parametersForFindAll() {
        java.util.List<Object[]> params = paramsList();

        params.add(params(new StringList(), null, new String[0]));

        Closure<Boolean, String> critOne = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "one".equals(str);
                }
            };
        params.add(params(new StringList("one"), critOne, "one", "two"));

        Closure<Boolean, String> critTwo = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "two".equals(str);
                }
            };
        params.add(params(new StringList("two"), critTwo, "one", "two"));

        Closure<Boolean, String> critContO = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return str.contains("o");
                }
            };
        params.add(params(new StringList("one", "two"), critContO, "one", "two", "three"));

        Closure<Boolean, String> critThree = new Closure<Boolean, String>() {
                public Boolean execute(String str) {
                    return "three".equals(str);
                }
            };
        params.add(params(new StringList(), critThree, "one", "two"));
        
        return params;
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyEqualsIgnoreCase(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        boolean result = sl.anyEqualsIgnoreCase(substr);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "substr", substr)));
    }
    
    private java.util.List<Object[]> parametersForAnyEqualsIgnoreCase() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "one", "one"),
                          params(true, "one", "one", "two"),
                          params(true, "one", "One"));
    }
}