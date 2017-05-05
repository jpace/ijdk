package ijdk.lang;

import java.util.Arrays;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.objary;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestRange {
    @Test
    @Parameters
    public void firstLast(Integer expFirst, Integer expLast, Integer first, Integer last) {
        Range rg = new Range(first, last);
        String msg = message("rg", rg);
        assertEqual(expFirst, rg.getFirst(), msg);
        assertEqual(expLast, rg.getLast(), msg);

    }
    
    private List<Object[]> parametersForFirstLast() {
        return Arrays.asList(new Object[] { 3, 17, 3, 17 },
                             new Object[] { 17, 3, 17, 3 },
                             new Object[] { 3, 3, 3, 3 });
    }

    @Test
    @Parameters
    public void includes(boolean expected, Integer first, Integer last, Integer val) {
        Range rg = new Range(first, last);
        assertEqual(expected, rg.includes(val), message("rg", rg, "val", val));        
    }
    
    private List<Object[]> parametersForIncludes() {
        return Arrays.asList(
            // first < last
            objary(true, 3, 5, 3),
            objary(true, 3, 5, 4),
            objary(true, 3, 5, 5),
            objary(false, 3, 5, 2),
            objary(false, 3, 5, 6),
            
            // first == last
            objary(true, 3, 3, 3),
            objary(false, 3, 3, 2),
            objary(false, 3, 3, 4),
            
            // first > last
            objary(false, 5, 3, 2),
            objary(false, 5, 3, 3),
            objary(false, 5, 3, 4),
            objary(false, 5, 3, 5),
            objary(false, 5, 3, 6));        
    }

    @Test
    @Parameters
    public void equals_test(boolean expected, Range x, Object y) {
        assertEqual(expected, x.equals(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForEquals_test() {
        Range x = new Range(3, 7);
        return Arrays.asList(objary(false, x, null),
                             objary(false, x, new Object()),
                             objary(true, x, x),
                             objary(true, x, new Range(3, 7)),
                             objary(false, x, new Range(2, 7)),
                             objary(false, x, new Range(3, 6)));
    }

    @Test
    @Parameters
    public void toArray(Integer[] expected, Range rg) {
        assertEqual(expected, rg.toArray(), message("rg", rg));        
    }
    
    private List<Object[]> parametersForToArray() {
        return Arrays.asList(objary(new Integer[] { 3, 7 }, new Range(3, 7)),
                             objary(new Integer[] { 3, 3 }, new Range(3, 3)),
                             objary(new Integer[] { 7, 3 }, new Range(7, 3)));
    }

    @Test
    @Parameters
    public void toExpandedArray(Integer[] expected, Range rg) {
        assertEqual(expected, rg.toExpandedArray(), message("rg", rg));
    }
    
    private List<Object[]> parametersForToExpandedArray() {
        return Arrays.asList(objary(new Integer[] { 3, 4, 5, 6, 7 }, new Range(3, 7)),
                             objary(new Integer[] { 3 }, new Range(3, 3)),
                             objary(new Integer[] { }, new Range(7, 3)));
    }

    @Test
    @Parameters
    public void toExpandedList(List<Integer> expected, Range rg) {
        assertEqual(expected, rg.toExpandedList(), message("rg", rg));
    }
    
    private List<Object[]> parametersForToExpandedList() {
        return Arrays.asList(objary(Arrays.asList(3, 4, 5, 6, 7), new Range(3, 7)),
                            objary(Arrays.asList(3), new Range(3, 3)),
                            objary(Arrays.asList(), new Range(7, 3)));
    }

    @Test
    @Parameters
    public void iterator(Integer[] expected, Range rg) {
        int idx = 0;
        for (Integer i : rg) {
            assertEqual(expected[idx], i, message("idx", idx));
            ++idx;
        }
        assertEqual(expected.length, idx);
    }
    
    private List<Object[]> parametersForIterator() {
        return Arrays.asList(objary(new Integer[] { 3, 4, 5, 6, 7 }, new Range(3, 7)),
                             objary(new Integer[] { 3 }, new Range(3, 3)),
                             objary(new Integer[] { }, new Range(7, 3)));
    }

    @Test
    @Parameters
    public void compareTo(Integer expected, Range x, Range y) {
        assertEqual(expected, x.compareTo(y), message("x", x, "y", y));
    }
    
    private List<Object[]> parametersForCompareTo() {
        Range rg = new Range(3, 7);
        return Arrays.asList(objary(1, rg, null),
                             objary(0, rg, rg),
                             objary(0, rg, new Range(3, 7)),
                             objary(-1, new Range(2, 7), new Range(3, 7)),
                             objary(-1, new Range(3, 6), new Range(3, 7)));
    }

    @Test
    @Parameters
    public void toString_test(String expected, Range rg) {
        assertEqual(expected, rg.toString(), message("rg", rg));
    }
    
    private List<Object[]> parametersForToString_test() {
        return Arrays.asList(objary("[3 .. 7]", new Range(3, 7)),
                             objary("[3 .. 3]", new Range(3, 3)),
                             objary("[7 .. 3]", new Range(7, 3)));
    }    
}
