package org.incava.ijdk.lang;

public class TestCaseObj extends ObjectTest {
    public TestCaseObj(String name) {
        super(name);
    }

    public <T> NullableObject<T> assertAccessors(T expected, NullableObject<T> objt) {
        assertEquals(expected, objt.get());
        assertEquals(expected, objt.obj());
        return objt;
    }

    private <T> NullableObject<T> newObj(T obj) {
        return new NullableObject<T>(obj);
    }

    public void testCtorNull() {
        assertAccessors(null, newObj(null));
    }

    public void testCtorNotNull() {
        Object obj = new Object();
        assertAccessors(obj, newObj(obj));
    }

    public boolean objectsEqual(Object x, Object y) {
        return newObj(x).equals(y);
    }

    public boolean isTrue(Object obj) {
        return newObj(obj).isTrue();
    }

    public boolean isFalse(Object obj) {
        return newObj(obj).isFalse();
    }

    public boolean isEmpty(Object obj) {
        return newObj(obj).isEmpty();
    }    

    public boolean isNull(Object obj) {
        return newObj(obj).isNull();
    }    
    
    public boolean isNotNull(Object obj) {
        return newObj(obj).isNotNull();
    }
}
