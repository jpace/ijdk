package org.incava.ijdk.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.hamcrest.Matchers;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class ItTest extends Parameterized {
    @Test
    public void list() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });
        int index = 0;
        for (It<String> it : It.of(list)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(list.get(index)));
            ++index;
        }
    }
    
    @Test
    public void array() {
        String[] list = new String[] { "a", "b", "c" };
        int index = 0;
        for (It<String> it : It.of(list)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(list[index]));
            ++index;
        }
    }
}
