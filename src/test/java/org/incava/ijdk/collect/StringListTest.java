package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.hamcrest.Matchers;
import org.incava.attest.Parameterized;
import org.incava.ijdk.lang.Closure;
import org.incava.ijdk.str.StringAlphanumericComparator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
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
        List<String> list = Arrays.asList(new String[] { "one", "two", "three" });
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
    
    private List<Object[]> parametersForAnyStartsWith() {
        return paramsList(params(false, "o", new String[0]),
                          params(true,  "o", "one"),
                          params(false, "n", "one"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void anyContains(boolean expected, String substr, String ... args) {
        StringList sl = new StringList(args);
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
        StringList sl = new StringList(args);
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
        StringList sl = new StringList(args);
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
    public void findAll(StringList expected, Closure<Boolean, String> criteria, String ... args) {
        StringList sl = new StringList(args);
        StringList result = sl.findAll(criteria);
        assertThat(result, withContext(equalTo(expected), message("sl", sl, "criteria", criteria)));
    }
    
    private List<Object[]> parametersForFindAll() {
        List<Object[]> params = paramsList();

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
    
    private List<Object[]> parametersForAnyEqualsIgnoreCase() {
        return paramsList(params(false, "o",   new String[0]),
                          params(true,  "one", "one"),
                          params(true,  "one", "one", "two"),
                          params(true,  "one", "One"));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void toLines(StringList expected, StringList sl) {
        StringList result = sl.toLines();
        assertThat(result, withContext(equalTo(expected), message("sl", sl)));
    }
    
    private List<Object[]> parametersForToLines() {
        return paramsList(params(StringList.empty(),          StringList.empty()),
                          params(StringList.of((String)null), StringList.of((String)null)),
                          params(StringList.of("a\n"),        StringList.of("a")),
                          params(StringList.of("a\n", "b\n"), StringList.of("a", "b")),
                          params(StringList.of("a\n"),        StringList.of("a\n")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sorted(StringList expected, StringList ary) {
        StringList result = ary.sorted();
        assertThat(result, equalTo(expected));
        assertThat(result, not(equalTo(ary)));
    }
    
    private List<Object[]> parametersForSorted() {
        return paramsList(params(StringList.of("a", "b", "c"), StringList.of("b", "a", "c")),
                          params(StringList.of("a", "b", "c"), StringList.of("b", "c", "a")));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void sortedComparator(StringList expected, StringList ary, Comparator<String> comp) {
        StringList result = ary.sorted(comp);
        assertThat(result, equalTo(expected));
        assertThat(result, not(equalTo(ary)));
    }
    
    private List<Object[]> parametersForSortedComparator() {
        return paramsList(params(StringList.of("a9", "a10", "b"), StringList.of("b", "a10", "a9"), new StringAlphanumericComparator()), 
                          params(StringList.of("b3", "b4",  "c"), StringList.of("c", "b4",  "b3"), new StringAlphanumericComparator()));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void collect(StringList expected, StringList ary, String repl) {
        StringList result = ary.collect(repl);
        StringList original = new StringList(ary);
        assertThat(result, equalTo(expected));
        assertThat(ary, equalTo(original));
    }
    
    private List<Object[]> parametersForCollect() {
        return paramsList(params(StringList.of("x-a", "x-b", "x-c"), StringList.of("a", "b", "c"), "x-%s"),
                          params(StringList.of("a", "b", "c"), StringList.of("a", "b", "c"), "%s"));
    }    

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void unique(StringList expected, StringList ary) {
        StringList result = ary.unique();
        StringList original = new StringList(ary);
        assertThat(result, equalTo(expected));
        assertThat(ary, equalTo(original));
    }
    
    private List<Object[]> parametersForUnique() {
        return paramsList(params(StringList.of("b", "a", "c"), StringList.of("b", "a", "c")),
                          params(StringList.of("b", "c", "a"), StringList.of("b", "c", "a")));
    }    
}
