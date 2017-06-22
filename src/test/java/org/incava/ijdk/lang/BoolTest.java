package org.incava.ijdk.lang;

import org.junit.Test;

public class BoolTest {
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
