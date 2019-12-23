package org.incava.ijdk.collect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IteratorStateTest extends Parameterized {
    @Test
    public void singleElement() {
        List<String> list = new ArrayList<>();
        list.add("a");

        IteratorState<String> iter = new IteratorState<>(list);
        assertThat(iter.isFirst(), equalTo(false));
        assertThat(iter.isLast(), equalTo(false));
        iter.next();
        assertThat(iter.isFirst(), equalTo(true));
        assertThat(iter.isLast(), equalTo(true));
    }

    @Test
    public void multipleElements() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        IteratorState<String> iter = new IteratorState<>(list);
        assertThat(iter.isFirst(), equalTo(false));
        assertThat(iter.isLast(), equalTo(false));
        iter.next();
        assertThat(iter.isFirst(), equalTo(true));
        assertThat(iter.isLast(), equalTo(false));
    }

    @Test
    public void noElements() {
        List<String> list = new ArrayList<>();

        IteratorState<String> iter = new IteratorState<>(list);
        assertThat(iter.isFirst(), equalTo(false));
        assertThat(iter.isLast(), equalTo(true));
    }
}
