package org.incava.ijdk.str;

import java.util.Comparator;
import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.Str;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.incava.attest.Assertions.message;

public class StrComparatorsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void create(Class<Comparator<Str>> expected, Str.Option ... arguments) {
        StrComparators sc = new StrComparators();
        Comparator<Str> result = sc.create(arguments);
        assertThat(result, isA(expected));
    }
    
    private java.util.List<Object[]> parametersForCreate() {
        return paramsList(params(StrAlphanumericComparator.class, Str.Option.ALPHANUMERIC), 
                          params(StrIgnoreCaseComparator.class,   Str.Option.IGNORE_CASE),  
                          params(StrComparator.class,             new Str.Option[0]));
    }
}
