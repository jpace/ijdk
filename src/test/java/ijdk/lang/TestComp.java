package ijdk.lang;

import ijdk.collect.List;
import java.util.Arrays;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ijdk.lang.Common.objary;
import static org.incava.test.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class TestComp {
    @Test
    @Parameters
    public <T extends Comparable<T>> void compare(int expected, T x, T y) {
        int result = Comp.compare(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public List<Object[]> parametersForCompare() {
        String obj = new String();
        
        return Common.<Object[]>list(objary(0,  null, null),
                                     objary(-1, null, new String()),
                                     objary(1,  new String(), null),
                                     objary(0,  obj, obj),
                                     objary(0,  new String(), new String()),
                                     objary(0,  new String("abc"), new String("abc")),
                                     objary(-3, new String("abc"), new String("def")),
                                     objary(3,  new String("def"), new String("abc")));
    }

    @Test
    @Parameters
    public <T extends Comparable<T>> void lessThan(boolean expected, T x, T y) {
        boolean result = Comp.lessThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public List<Object[]> parametersForLessThan() {
        String obj = new String();
        
        return Common.<Object[]>list(objary(false, null, null),
                                     objary(true,  null, new String()),
                                     objary(false, new String(), null),
                                     objary(false, obj, obj),
                                     objary(false, new String(), new String()),
                                     objary(false, new String("abc"), new String("abc")),
                                     objary(true,  new String("abc"), new String("def")),
                                     objary(false, new String("def"), new String("abc")));
    }    

    @Test
    @Parameters
    public <T extends Comparable<T>> void greaterThan(boolean expected, T x, T y) {
        boolean result = Comp.greaterThan(x, y);
        assertEqual(expected, result, message("x", x, "y", y));
    }

    public List<Object[]> parametersForGreaterThan() {
        String obj = new String();
        
        return Common.<Object[]>list(objary(false, null, null),
                                     objary(false, null, new String()),
                                     objary(true,  new String(), null),
                                     objary(false, obj, obj),
                                     objary(false, new String(), new String()),
                                     objary(false, new String("abc"), new String("abc")),
                                     objary(false, new String("abc"), new String("def")),
                                     objary(true,  new String("def"), new String("abc")));
    }
}
