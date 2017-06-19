package org.incava.ijdk.lang;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.incava.attest.Assertions.assertEqual;
import static org.incava.attest.Assertions.assertSame;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.Parameters.params;
import static org.incava.attest.Parameters.paramsList;

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
