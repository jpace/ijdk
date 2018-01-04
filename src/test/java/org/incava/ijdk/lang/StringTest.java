package org.incava.ijdk.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public abstract class StringTest extends Parameterized {
    public List<Object[]> parametersForSplitCharDelim() {
        return paramsList(params(null, null, ';', -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ';', -1),
                          params(new String[] { "this", "is", "a", "", "test" }, "this;is;a;;test", ';', -1),
                          params(new String[] { "this", "is;a;;test" }, "this;is;a;;test", ';', 2),
                          params(new String[] { "this", "is", "a;;test" }, "this;is;a;;test", ';', 3),
                          params(new String[] { "this", "is", "a", ";test" }, "this;is;a;;test", ';', 4));
    }
    
    public List<Object[]> parametersForSplitStringDelim() {
        return paramsList(params(null, null, ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this;is;a;test", ";", -1),
                          params(new String[] { "this", "is", "a", "test" }, "this ; is ; a ; test", " ; ", -1));
    }
    
    public List<Object[]> parametersForToList() {
        return paramsList(params(null, null),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee, fi, foo, fum"),
                          params(new String[] { "fee", "fi", "foo", "fum" }, "fee,\tfi,\nfoo,\rfum"));
    }
    
    public List<Object[]> parametersForPadWithChar() {
        return paramsList(params(null,       null,   '*', 8), 
                          params("abcd****", "abcd", '*', 8), 
                          params("abcd",     "abcd", '*', 3), 
                          params("abcd",     "abcd", '*', 4), 
                          params("abcd*",    "abcd", '*', 5));
    }    
    
    public List<Object[]> parametersForPadWithoutChar() {
        return paramsList(params("abcd    ", "abcd", 8), 
                          params("abcd",     "abcd", 3), 
                          params("abcd",     "abcd", 4), 
                          params("abcd ",    "abcd", 5));
    }
    
    public List<Object[]> parametersForPadLeftWithChar() {
        return paramsList(params(null,       null,   '*', 8), 
                          params("****abcd", "abcd", '*', 8), 
                          params("abcd",     "abcd", '*', 3), 
                          params("abcd",     "abcd", '*', 4), 
                          params("*abcd",    "abcd", '*', 5));
    }
    
    public List<Object[]> parametersForPadLeftWithoutChar() {
        return paramsList(params(null,       null,   8), 
                          params("    abcd", "abcd", 8), 
                          params("abcd",     "abcd", 3), 
                          params("abcd",     "abcd", 4), 
                          params(" abcd",    "abcd", 5));
    }
    
    public List<Object[]> parametersForRepeatString() {
        return paramsList(params(null,       null,   0),  
                          params("",         "abcd", -1), 
                          params("",         "abcd", 0),  
                          params("abcd",     "abcd", 1),  
                          params("abcdabcd", "abcd", 2));
    }
    
    public List<Object[]> parametersForRepeatChar() {
        return paramsList(params("",   'a', -1), 
                          params("",   'a', 0),  
                          params("a",  'a', 1),  
                          params("aa", 'a', 2));
    }
    
    public List<Object[]> parametersForLeft() {
        return paramsList(params(null,   null,       1), 
                          params("abcd", "abcdefgh", 4), 
                          params("abcd", "abcd",     4), 
                          params("abcd", "abcd",     5), 
                          params("",     "abcd",     0), 
                          params("",     "abcd",     -1));
    }
    
    public List<Object[]> parametersForRight() {
        return paramsList(params(null,   null,       1), 
                          params("efgh", "abcdefgh", 4), 
                          params("abcd", "abcd",     4), 
                          params("abcd", "abcd",     5), 
                          params("",     "abcd",     0), 
                          params("",     "abcd",     -1));
    }
    
    public List<Object[]> parametersForCharAt() {
        return paramsList(params(null, null,  0),  
                          params('a',  "abc", 0),  
                          params('b',  "abc", 1),  
                          params('c',  "abc", 2),  
                          params(null, "abc", 3),  
                          params('c',  "abc", -1), 
                          params('b',  "abc", -2), 
                          params('a',  "abc", -3), 
                          params(null, "abc", -4));
    }
    
    public List<Object[]> parametersForGet() {
        return paramsList(params(null, null,  0),  
                          params('a',  "abc", 0),  
                          params('b',  "abc", 1),  
                          params('c',  "abc", 2),  
                          params(null, "abc", 3),  
                          params('c',  "abc", -1), 
                          params('b',  "abc", -2), 
                          params('a',  "abc", -3), 
                          params(null, "abc", -4));
    }
    
    public List<Object[]> parametersForSubstring() {
        return paramsList(params(null,   null,     1,    0),    
                          params("abcd", "abcd",   0,    3),    
                          params("abc",  "abcd",   0,    2),    
                          params("a",    "abcd",   0,    0),    
                          // expect "",  not null, per Ruby behavior
                          params("",     "abcd",   1,    0),    
                          params("",     "abcd",   4,    5),    
                          params("abcd", "abcd",   -4,   -1),   
                          params("abc",  "abcd",   -4,   -2),   
                          params("a",    "abcd",   -4,   -4),   
                          // expect "",  not null, per Ruby behavior
                          params("",     "abcd",   -1,   -2),   
                          // null == first in string
                          params("abc",  "abcd",   null, -2),   
                          params("cd",   "abcd",   -2,   null), 
                          params("abcd", "abcd",   null, null));
    }
    
    public List<Object[]> parametersForStartsWith() {
        return paramsList(params(false, null,   'j'), 
                          params(true,  "java", 'j'), 
                          params(false, "java", 'a'), 
                          params(false, "java", 'J'));
    }
    
    public List<Object[]> parametersForIndexOf() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params(0,    "abc", 'a'),  
                          params(2,    "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }
    
    public List<Object[]> parametersForContains() {
        return paramsList(params(false, "abc", null), 
                          params(false, null,  'a'),  
                          params(false, null,  null), 
                          params(true,  "abc", 'a'),  
                          params(true,  "abc", 'c'),  
                          params(false, "abc", 'd'),  
                          params(false, "abc", 'A'));
    }
    
    public List<Object[]> parametersForSubstringAfter() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params("bc", "abc", 'a'),  
                          params("",   "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }
    
    public List<Object[]> parametersForSubstringBefore() {
        return paramsList(params(null, "abc", null), 
                          params(null, null,  'a'),  
                          params(null, null,  null), 
                          params("",   "abc", 'a'),  
                          params("ab", "abc", 'c'),  
                          params(null, "abc", 'd'),  
                          params(null, "abc", 'A'));
    }
    
    public List<Object[]> parametersForEq() {
        return paramsList(params(false, null, ""),   
                          params(false, "",   null), 
                          params(true,  null, null), 
                          params(false, null, "a"),  
                          params(false, "a",  null), 
                          params(true,  "a",  "a"),  
                          params(false, "a",  "b"),  
                          params(false, "a",  "A"),  
                          params(false, "a",  "ab"));
    }
    
    public List<Object[]> parametersForEqi() {
        return paramsList(params(false, null, ""),   
                          params(false, "",   null), 
                          params(true,  null, null), 
                          params(false, null, "a"),  
                          params(false, "a",  null), 
                          params(true,  "a",  "a"),  
                          params(false, "a",  "b"),  
                          params(true,  "A",  "a"),  
                          params(false, "a",  "ab"));
    }
    
    public List<Object[]> parametersForSnip() {
        return paramsList(params(null,  null,   3), 
                          params("",    "",     3), 
                          params("-",   "abc",  1), 
                          params("a-",  "abc",  2), 
                          params("abc", "abc",  3), 
                          params("ab-", "abcd", 3));
    }
    
    public List<Object[]> parametersForIsEmpty() {
        return paramsList(params(true,  null), 
                          params(true,  ""),   
                          params(false, "a"));
    }
    
    public List<Object[]> parametersForLength() {
        return paramsList(params(0, null), 
                          params(0, ""),   
                          params(1, "a"));
    }
    
    public List<Object[]> parametersForChomp() {
        return paramsList(params(null,  null),    
                          params("",    ""),      
                          params("a",   "a"),     
                          params("a",   "a\n"),   
                          params("a\n", "a\n\n"), 
                          params("a",   "a\r"),   
                          params("a\r", "a\r\r"), 
                          params("a\r", "a\r\n"));
    }
    
    public List<Object[]> parametersForChompAll() {
        return paramsList(params(null, null),    
                          params("",   ""),      
                          params("a",  "a"),     
                          params("a",  "a\n"),   
                          params("a",  "a\n\n"), 
                          params("a",  "a\r"),   
                          params("a",  "a\r\r"), 
                          params("a",  "a\r\n"));
    }
    
    public List<Object[]> parametersForUnquote() {
        return paramsList(params(null,      null),
                          params("",        ""),
                          params("abc",     "abc"),
                          params("\'abc\"", "\'abc\""),
                          params("\"abc\'", "\"abc\'"),
                          params("abc",     "\'abc\'"),
                          params("abc",     "\"abc\""),
                          params("\"",      "\""),
                          params("",        "\"\""));
    }
    
    public List<Object[]> parametersForQuote() {
        return paramsList(params(null,      null), 
                          params("\"\"",    ""),   
                          params("\"abc\"", "abc"));
    }
}
