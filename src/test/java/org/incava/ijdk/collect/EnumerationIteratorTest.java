package org.incava.ijdk.collect;

import java.util.Enumeration;
import java.util.Vector;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EnumerationIteratorTest extends Parameterized {
    @Test
    public void init() {
        // very old school ...
        Vector<String> vec = new Vector<String>();
        vec.addElement("a");
        vec.addElement("b");
        vec.addElement("c");
        Enumeration<String> en = vec.elements();
        EnumerationIterator<String> iter = new EnumerationIterator<String>(en);
        
        for (String str : new String[] { "a", "b", "c" }) {
            assertThat(iter.hasNext(), equalTo(true));
            assertThat(iter.next(), equalTo(str));
        }
        assertThat(iter.hasNext(), equalTo(false));
    }
}
