package org.incava.ijdk.lang;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.test.Parameterized;
import org.junit.Test;

import static org.incava.test.Assertions.assertEqual;
import static org.incava.test.Assertions.assertSame;
import static org.incava.test.Assertions.message;
import static org.incava.test.Parameters.params;
import static org.incava.test.Parameters.paramsList;

public class BoolTest extends Parameterized {
    @Test
    public void init() {
        Bool bl = new Bool() {
                public boolean isTrue() {
                    return true;
                }

                public boolean isFalse() {
                    return false;
                }

                public boolean isEmpty() {
                    return false;
                }
            };
        
    }
}
