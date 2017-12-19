package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IterateTest extends Parameterized {
    @Test
    public void count() {
        int count = 0;
        for (Integer i : Iterate.count(3)) {
            ++count;
        }
        assertThat(count, equalTo(3));
    }

    @Test
    public void overArray() {
        String[] ary = new String[] { "a", "b", "c" };
        int index = 0;
        for (String s : Iterate.over(ary)) {
            assertThat(s, equalTo(ary[index]));
            ++index;
        }
        assertThat(index, equalTo(ary.length));
    }

    @Test
    public void overArrayNull() {
        String[] ary = null;
        int index = 0;
        for (String s : Iterate.over(ary)) {
            ++index;
        }
        assertThat(index, equalTo(0));
    }

    @Test
    public void overIterable() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });
        int index = 0;
        for (String s : Iterate.over(list)) {
            assertThat(s, equalTo(list.get(index)));
            ++index;
        }
        assertThat(index, equalTo(list.size()));
    }

    @Test
    public void overIterableNull() {
        List<String> list = null;
        int index = 0;
        for (String s : Iterate.over(list)) {
            ++index;
        }
        assertThat(index, equalTo(0));
    }

    @Test
    public void eachList() {
        List<String> list = Arrays.asList(new String[] { "a", "b", "c" });
        int index = 0;
        for (It<String> it : Iterate.each(list)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(list.get(index)));
            ++index;
        }
    }    

    @Test
    public void eachListNull() {
        List<String> list = null;
        int index = 0;
        for (It<String> it : Iterate.each(list)) {
            ++index;
        }
        assertThat(index, equalTo(0));
    }
    
    @Test
    public void eachArray() {
        String[] ary = new String[] { "a", "b", "c" };
        int index = 0;
        for (It<String> it : Iterate.each(ary)) {
            assertThat(it.index(), equalTo(index));
            assertThat(it.value(), equalTo(ary[index]));
            ++index;
        }
    }    
    
    @Test
    public void eachArrayNull() {
        String[] ary = null;
        int index = 0;
        for (It<String> it : Iterate.each(ary)) {
            ++index;
        }
        assertThat(index, equalTo(0));
    }    
}
