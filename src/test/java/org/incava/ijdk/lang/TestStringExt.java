package org.incava.ijdk.lang;

import org.incava.test.AbstractTestCaseExt;
import java.util.*;


public class TestStringExt extends AbstractTestCaseExt {

    public TestStringExt(String name) {
        super(name);
    }

    public void testSplit() {
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this;is;a;test", ';',         -1));
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this;is;a;test", ";",         -1));
        assertEquals(new String[] { "this", "is", "a",     "test"  }, StringExt.split("this ; is ; a ; test", " ; ", -1));
        assertEquals(new String[] { "this", "is", "a", "", "test"  }, StringExt.split("this;is;a;;test", ';',        -1));
    
        assertEquals(new String[] { "this;is;a;;test"              }, StringExt.split("this;is;a;;test", ';',         1));
        assertEquals(new String[] { "this", "is;a;;test"           }, StringExt.split("this;is;a;;test", ';',         2));
        assertEquals(new String[] { "this", "is", "a;;test"        }, StringExt.split("this;is;a;;test", ';',         3));
        assertEquals(new String[] { "this", "is", "a",     ";test" }, StringExt.split("this;is;a;;test", ';',         4));
    }

    public void testListify() {
        tr.Ace.setVerbose(true);
        List<String> list = StringExt.listify("fee, fi, foo, fum");
        tr.Ace.yellow("list", list);

        assertEquals(Arrays.asList(new String[] { "fee", "fi", "foo", "fum" }), StringExt.listify("fee, fi, foo, fum"));
        assertEquals(Arrays.asList(new String[] { "fee", "fi", "foo", "fum" }), StringExt.listify("\"fee, fi, foo, fum\""));
        assertEquals(Arrays.asList(new String[] { "fee", "fi", "foo", "fum" }), StringExt.listify("\'fee, fi, foo, fum\'"));
        assertEquals(Arrays.asList(new String[] { "fee", "fi", "foo", "fum" }), StringExt.listify("\'fee,\tfi,\nfoo,\rfum\'"));
    }

    public void testPad() {
        assertEquals("abcd****", StringExt.pad("abcd", '*', 8));
        assertEquals("abcd",     StringExt.pad("abcd", '*', 3));
        assertEquals("abcd",     StringExt.pad("abcd", '*', 4));
        assertEquals("abcd*",    StringExt.pad("abcd", '*', 5));

        assertEquals("abcd    ", StringExt.pad("abcd", 8));
        assertEquals("abcd",     StringExt.pad("abcd", 3));
        assertEquals("abcd",     StringExt.pad("abcd", 4));
        assertEquals("abcd ",    StringExt.pad("abcd", 5));
    }

    public void testPadLeft() {
        assertEquals("****abcd", StringExt.padLeft("abcd", '*', 8));
        assertEquals("abcd",     StringExt.padLeft("abcd", '*', 3));
        assertEquals("abcd",     StringExt.padLeft("abcd", '*', 4));
        assertEquals("*abcd",    StringExt.padLeft("abcd", '*', 5));

        assertEquals("    abcd", StringExt.padLeft("abcd", 8));
        assertEquals("abcd",     StringExt.padLeft("abcd", 3));
        assertEquals("abcd",     StringExt.padLeft("abcd", 4));
        assertEquals(" abcd",    StringExt.padLeft("abcd", 5));
    }

    public void testRepeat() {
        assertEquals("abcdabcdabcdabcdabcd",                                    StringExt.repeat("abcd",  5));
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", StringExt.repeat('a',    55));
        assertEquals("",                                                        StringExt.repeat('a',     0));
        assertEquals("",                                                        StringExt.repeat('a',    -1));
    }

    public void testLeft() {
        assertEquals("abcd", StringExt.left("abcdefgh",  4));
        assertEquals("abcd", StringExt.left("abcd",      4));
        assertEquals("abcd", StringExt.left("abcd",      5));
        assertEquals("abcd", StringExt.left("abcd",      6));
    }

    public void testRight() {
        assertEquals("abcd", StringExt.right("zyxwabcd",  4));
        assertEquals("abcd", StringExt.right("abcd",      4));
        assertEquals("abcd", StringExt.right("abcd",      5));
        assertEquals("abcd", StringExt.right("abcd",      6));
    }

    public void testJoin() {
        String[] contents    = new String[] { "a", "b", "c", "d" };
        String   commaJoined = "a,b,c,d";
        String   xxxJoined   = "axxxbxxxcxxxd";
        
        assertEquals("string array", commaJoined, StringExt.join(contents, ","));
        assertEquals("string array", xxxJoined,   StringExt.join(contents, "xxx"));

        List<String> contentList = Arrays.asList(contents);

        Collection<Collection<String>> collections = new ArrayList<Collection<String>>();
        collections.add(contentList);
        collections.add(new TreeSet<String>(contentList));
        collections.add(new Vector<String>(contentList));
        
        for (Collection<String> collection : collections) {
            assertEquals("collection.type: " + collection.getClass().getName(), commaJoined, StringExt.join(collection, ","));
            assertEquals("collection.type: " + collection.getClass().getName(), xxxJoined,   StringExt.join(collection, "xxx"));
        }
    }
}
