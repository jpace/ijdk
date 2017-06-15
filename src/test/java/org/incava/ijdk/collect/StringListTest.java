package org.incava.ijdk.collect;

import java.util.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.incava.ijdk.lang.Closure;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

@RunWith(JUnitParamsRunner.class)
public class StringListTest {
    @Test
    public void ctorEmpty() {
        StringList sl = new StringList();
        assertEqual(0, sl.size());
    }

    @Test
    public void ctorCollection() {
        java.util.List<String> list = Arrays.asList(new String[] { "one", "two", "three" });
        StringList sl = new StringList(list);
        assertEqual(3, sl.size());
    }

    @Test
    public void ctorVarArgsOne() {
        StringList sl = new StringList("one");
        assertEqual(1, sl.size());
        assertEqual("one", sl.get(0));
    }

    @Test
    public void ctorVarArgsTwo() {
        StringList sl = new StringList("one", "two");
        assertEqual(2, sl.size());
        assertEqual("one", sl.get(0));
        assertEqual("two", sl.get(1));
    }

    @Test
    @Parameters
    public void anyStartsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEqual(expected, sl.anyStartsWith(substr), message("sl", sl, "substr", substr));
        
    }
    
    private java.util.List<Object[]> parametersForAnyStartsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "o", "one"),
                          params(false, "n", "one"));
    }

    @Test
    @Parameters
    public void anyContains(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEqual(expected, sl.anyContains(substr), message("sl", sl, "substr", substr));
    }
    
    private java.util.List<Object[]> parametersForAnyContains() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "o", "one"),
                          params(true, "n", "one"),
                          params(false, "z", "one"));
    }

    @Test
    @Parameters
    public void anyEndsWith(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEqual(expected, sl.anyEndsWith(substr), message("sl", sl, "substr", substr));
    }
    
    private java.util.List<Object[]> parametersForAnyEndsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "e", "one"),
                          params(false, "n", "one"));
    }

    @Test
    @Parameters
    public void findFirst(String expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        String result = sl.findFirst(criteria);
        assertEqual(expected, result, message("sl", sl, "criteria", criteria));
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

    @Test
    @Parameters
    public void findAll(StringList expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        StringList result = sl.findAll(criteria);
        assertEqual(expected, result, message("sl", sl, "criteria", criteria));
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

    @Test
    @Parameters
    public void anyEqualsIgnoreCase(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
        assertEqual(expected, sl.anyEqualsIgnoreCase(substr), message("sl", sl, "substr", substr));
        
    }
    
    private java.util.List<Object[]> parametersForAnyEqualsIgnoreCase() {
        return paramsList(params(false, "o", new String[0]),
                          params(true, "one", "one"),
                          params(true, "one", "one", "two"),
                          params(true, "one", "One"));
    }
}
