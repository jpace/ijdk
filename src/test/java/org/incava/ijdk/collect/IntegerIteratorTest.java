package org.incava.ijdk.collect;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegerIteratorTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void hasNext(boolean expected, IntegerIterator rit) {
        assertThat(rit.hasNext(), equalTo(expected));
    }
    
    private List<Object[]> parametersForHasNext() {
        return paramsList(params(true,  new IntegerIterator(0, 1)),
                          params(true,  new IntegerIterator(1, 2)),
                          params(true,  new IntegerIterator(1, 1)),
                          params(false, new IntegerIterator(2, 1)),
                          params(true,  new IntegerIterator(0, 1, 1)),
                          params(true,  new IntegerIterator(0, 1, 2)),
                          params(true,  new IntegerIterator(1, 1, 1)),
                          params(false, new IntegerIterator(2, 1, 1)));
    }

    @Test @Parameters @TestCaseName("{method} {index} {params}")
    public void next(Integer expected, IntegerIterator rit) {
        if (expected == null) {
            try {
                rit.next();
                throw new RuntimeException("expected exception");
            }
            catch (NoSuchElementException nsee) {
                // good
            }
        }
        else {
            assertThat(rit.next(), equalTo(expected));
        }
    }
    
    private List<Object[]> parametersForNext() {
        IntegerIterator x = new IntegerIterator(1, 2, 1);
        x.next();
        IntegerIterator y = new IntegerIterator(1, 2, 2);
        y.next();
        return paramsList(params(0,    new IntegerIterator(0, 1)),
                          params(1,    new IntegerIterator(1, 2)),
                          params(1,    new IntegerIterator(1, 1)),
                          params(1,    new IntegerIterator(1, 1, 1)),
                          params(1,    new IntegerIterator(1, 1, 2)),
                          params(2,    x),
                          params(null, y),
                          params(null, new IntegerIterator(2, 1)));
    }

    @Test
    public void remove() {
        IntegerIterator rit = new IntegerIterator(0, 1);
        try {
            rit.remove();
            throw new RuntimeException("expected exception");
        }
        catch (UnsupportedOperationException uoe) {
            // good
        }
    }
}
