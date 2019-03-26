package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.incava.attest.Assertions.assertEqual;

public class ArrayTest extends Parameterized {
    private Array<Integer> emptyIntegerList() {
        return Array.<Integer>of();
    }

    @Test
    public void empty() {
        Array<Object> ary = Array.empty();
        assertThat(ary, hasSize(0));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void of(Array<Object> expected, Array<Object> ary) {
        assertThat(ary, equalTo(expected));
    }
    
    private List<Object[]> parametersForOf() {
        Array<String> aryAbc = new Array<String>();
        aryAbc.add("a");
        aryAbc.add("b");
        aryAbc.add("c");

        Array<String> aryDe = new Array<String>();
        aryDe.add("d");
        aryDe.add("e");
        
        return paramsList(params(aryAbc,              Array.of("a", "b", "c")),
                          params(aryDe,               Array.of("d", "e")),
                          params(new Array<String>(), Array.<String>of()),
                          params(new Array<String>(), Array.<String>of((String[])null)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void newInstance(Class<?> cls, Array<Object> ary) {
        assertThat(ary.newInstance(), is(instanceOf(cls)));
    }
    
    private List<Object[]> parametersForNewInstance() {
        List<String> abcList = Arrays.asList(new String[] { "a", "b", "c" });
        return paramsList(params(Array.class, new Array<String>()));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void init(List<Object> expected, Array<Object> result) {
        assertThat(expected, equalTo(result));
    }
    
    private List<Object[]> parametersForInit() {
        List<String> abcList = Arrays.asList(new String[] { "a", "b", "c" });
        
        return paramsList(params(new Array<Object>(), new Array<Object>()),
                          params(abcList,             new Array<Object>(abcList)),
                          params(abcList,             new Array<Object>("a", "b", "c")),
                          params(new Array<Object>(), new Array<Object>((Collection<Object>)null)),
                          params(new Array<Object>(), Array.<Object>empty()));
    }

    @Test
    public void demo() {
        Integer x = null;
        Array<Integer> nums = Array.of(1, 3, 5, 7);
        x = nums.get(0);
        assertEqual(1, x);
        
        x = nums.get(-1);
        assertEqual(7, x);
        
        nums.append(9).append(11).append(13);
        assertEqual(Array.of(1, 3, 5, 7, 9, 11, 13), nums);

        x = nums.get(-3);
        assertEqual(9, x);
        
        x = nums.first();
        assertEqual(1, x);

        x = nums.last();
        assertEqual(13, x);
        
        x = nums.takeFirst();
        assertEqual(1, x);
        assertEqual(Array.of(3, 5, 7, 9, 11, 13), nums);
        
        x = nums.takeFirst();
        assertEqual(3, x);
        assertEqual(Array.of(5, 7, 9, 11, 13), nums);

        x = nums.takeLast();
        assertEqual(13, x);
        assertEqual(Array.of(5, 7, 9, 11), nums);

        StringArray strAry = nums.toStringArray();
        assertEqual(StringArray.of("5", "7", "9", "11"), strAry);

        nums.append(2).append(2).append(2);
        assertEqual(Array.of(5, 7, 9, 11, 2, 2, 2), nums);

        Array<Integer> uniq = nums.unique();
        assertEqual(Array.of(5, 7, 9, 11, 2), uniq);

        assertEqual(true, nums.containsAny(2, 3));
        assertEqual(false, nums.containsAny(3, 4));

        nums.removeAll(2);
        assertEqual(Array.of(5, 7, 9, 11), nums);

        nums.set(0, 4);
        assertEqual(Array.of(4, 7, 9, 11), nums);

        nums.set(-1, 10);
        assertEqual(Array.of(4, 7, 9, 10), nums);

        nums.set(-2, 8);
        assertEqual(Array.of(4, 7, 8, 10), nums);

        nums.set(1, 6);
        assertEqual(Array.of(4, 6, 8, 10), nums);

        x = nums.getRandomElement();
        assertEqual(true, Array.of(4, 6, 8, 10).contains(x));

        String str = nums.join(" + ");
        assertEqual("4 + 6 + 8 + 10", str);
        
        Array<Integer> odds = Array.of(1, 3, 5);
        Array<Integer> evens = Array.of(2, 4, 6);
        Array<Integer> numbers = odds.plus(evens);
        assertEqual(Array.of(1, 3, 5, 2, 4, 6), numbers);
        
        Array<Integer> squares = numbers.minus(Array.of(2, 3, 5, 6));
        assertEqual(Array.of(1, 4), squares);

        Array<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
        assertEqual(Array.of(3, 1, 4, 1, 5), elements);
    }
}
