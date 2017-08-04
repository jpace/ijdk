package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class ItTest extends Parameterized {
    @Test
    public void list() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });
        int index = 0;
        for (It<String> it : Iterate.each(list)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(list.get(index)));
            ++index;
        }
        assertThat(index, equalTo(list.size()));
    }
    
    @Test
    public void array() {
        String[] ary = new String[] { "a", "b", "c" };
        int index = 0;
        for (It<String> it : Iterate.each(ary)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(ary[index]));
            ++index;
        }
        assertThat(index, equalTo(ary.length));
    }
}
